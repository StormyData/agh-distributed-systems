use std::time::Duration;
use rand::seq::SliceRandom;
use rand_distr::{Normal, Distribution};
use log::debug;

use tokio::sync::watch;
use crate::smarthome::GenericSensorControlerSensorType;



#[derive(Debug)]
pub struct Sensor {
    pub(crate) name: String,
    pub(crate) location: String,
    pub(crate) sensor_type: GenericSensorControlerSensorType, 
    pub(crate) watch: watch::Receiver<f64>,
    pub(crate) unit: String
}

impl Sensor {
    pub fn spawn_random_sensor_with_tasks(id: i32) -> Self {
        let names = vec!["test name 1", "test name 2", "test name 3"];
        let locations = vec!["kitchen", "bathroom", "garage"];
        let sensors = vec![
            ("%", GenericSensorControlerSensorType::Humidity, 0.0, 100.0, 50.0, 10.0),
            ("°C", GenericSensorControlerSensorType::Temperature, -20.0, 100.0, 20.0, 10.0),
            ("°F", GenericSensorControlerSensorType::Temperature, -4.0, 212.0, 60.0, 30.0),
            ("Lux",GenericSensorControlerSensorType::Light, 0.0, 10000.0, 5000.0, 1000.0)
        ];
        let mut rng = rand::thread_rng();
    
        let (unit, sensor_type, min, max, mean, std) = *sensors.choose(&mut rng).unwrap();
        let name = *names.choose(&mut rng).unwrap();
        let location =*locations.choose(&mut rng).unwrap();
        let (tx, rx) = watch::channel(f64::NAN);
        let gen = Normal::new(mean, std).unwrap();
        
        tokio::spawn(async move {
            let mut interval = tokio::time::interval(Duration::from_millis(500));
            loop {
                let value: f64;
                {
                    let mut rng = rand::thread_rng();
                    value = gen.sample(&mut rng);
                }
                let value = value.clamp(min, max);
                debug!(target:"DATAGEN" ,"generated value={} for sensor id={}", value, id);
                match tx.send(value) {
                    Ok(_) => {}
                    Err(_item) => {
                        break;
                    }
                }
    
                interval.tick().await;
            }
        });
        Sensor { 
            name: name.into(), 
            location: location.into(), 
            sensor_type, 
            watch: rx, 
            unit: unit.into() 
        }
    }
}