
use std::{collections::HashMap, time::Duration};
use log::{info, warn, error};


use tonic::{Request, Response, Status};
use tokio::sync::mpsc;
use tokio_stream::{wrappers::ReceiverStream};

use crate::smarthome::{
    generic_sensor_controler_server::GenericSensorControler, 
    GenericSensorControlerSensorValue, 
    generic_sensor_controler_describe_sensors::SensorDescription,
    Empty,
    GenericSensorControlerDescribeSensors,
    GenericSensorControlerGetArg,
    GenericSensorControlerSubArg,
    GenericSensorControlerDescArg
};
use crate::sensor::Sensor;


#[derive(Debug, Default)]
pub struct GenericSensorControlerService {
    sensors: HashMap<i32, Sensor>
}
impl GenericSensorControlerService {
    pub fn new(sensors: HashMap<i32, Sensor>) -> Self {
        GenericSensorControlerService {
            sensors 
        }
    }
}

#[tonic::async_trait]
impl GenericSensorControler for GenericSensorControlerService {
    type SubReadingsStream = ReceiverStream<Result<GenericSensorControlerSensorValue,Status>>;
    
    async fn describe_sensor(&self, request: Request<GenericSensorControlerDescArg>) -> Result<Response<SensorDescription>, Status> {
        let args = request.into_inner();
        match self.sensors.get(&args.sensor_id) {
            None => {
                warn!(target: "DESCRIBE_SENSOR", "client requested non existent sensor id {}", args.sensor_id);
                Err(Status::invalid_argument("invalid sensor_id"))
            }

            Some(sensor) => {
                info!(target: "DESCRIBE_SENSOR", "sending sensor id={} desription to client", args.sensor_id);
                Ok(Response::new(SensorDescription {
                    id: args.sensor_id,
                    name: sensor.name.clone(),
                    location: sensor.location.clone(),
                    r#type: sensor.sensor_type.into(),
                    unit: sensor.unit.clone() 
                }))
            }
        }
    }

    async fn describe_sensors(&self, _request: Request<Empty>) -> Result<Response<GenericSensorControlerDescribeSensors>, Status> {
        info!(target: "DESCRIBE_SENSORS", "sending sensor desriptions to client");
        Ok(Response::new(GenericSensorControlerDescribeSensors {
            sensors: self.sensors.iter().map(|(id, sensor)| {
                SensorDescription {
                    id: *id,
                    name: sensor.name.clone(),
                    location: sensor.location.clone(),
                    r#type: sensor.sensor_type.into(),
                    unit: sensor.unit.clone()
            }}).collect() 
        }))
    }
    async fn get_readings(&self, request: Request<GenericSensorControlerGetArg>) -> Result<Response<GenericSensorControlerSensorValue>, Status> {
        let args = request.into_inner();
        match self.sensors.get(&args.sensor_id) {
            None => {
                warn!(target: "GET_READINGS", "client requested non existent sensor id {}", args.sensor_id);
                Err(Status::invalid_argument("invalid sensor_id"))
            }
            Some(sensor) => {
                let mut stream = sensor.watch.clone();
                let value = *stream.borrow_and_update();

                info!(target: "GET_READINGS", "sending value {} of sensor {} to client", args.sensor_id, value);
                
                Ok(Response::new(GenericSensorControlerSensorValue {
                    current_value: value
                }))
            }
        }
        
    }
    async fn sub_readings(&self, request: Request<GenericSensorControlerSubArg>) -> Result<Response<Self::SubReadingsStream>, Status> {
        
        let args = request.into_inner();
        if args.delay_milis <= 0 {
            warn!(target: "SUB_READINGS", "client requested negative min delay {}", args.delay_milis);
            return Err(Status::invalid_argument("delay_milis must be positive"));
        }
        let delay: u64 = args.delay_milis.try_into().unwrap();
        let sensor = self.sensors.get(&args.sensor_id);
        if sensor.is_none() {
            warn!(target: "SUB_READINGS", "client requested non existent sensor id {}", args.sensor_id);
            return Err(Status::invalid_argument("invalid sensor_id"))
        }
        let mut stream = sensor.unwrap().watch.clone();

        let (tx, rx) = mpsc::channel(128);
        tokio::spawn(async move {
            let mut interval = tokio::time::interval(Duration::from_millis(delay));
            interval.set_missed_tick_behavior(tokio::time::MissedTickBehavior::Skip);
            loop {
                if let Err(_e) = stream.changed().await {
                    error!(target: "SUB_READINGS", "sensor no longer available");
                    if tx.send(Err(Status::cancelled("the sensor is no longer available"))).await.is_err() {
                        warn!(target: "SUB_READINGS", "unable to send sensor unavailable to the client");
                    }
                    break;
                }
                let value: f64 = *stream.borrow();
                match tx.send(Ok(GenericSensorControlerSensorValue { current_value: value })).await {
                    Ok(_) => {
                        info!(target: "SUB_READINGS", "sent value {} to client", value);
                    }
                    Err(_item) => {
                        info!(target: "SUB_READINGS","client disconnected");
                        break;
                    }
                }
                interval.tick().await;
            }
            
        });
        Ok(Response::new(ReceiverStream::new(rx)))
    }
}