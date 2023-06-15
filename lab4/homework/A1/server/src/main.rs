
use std::{collections::HashMap, time::Duration, net::SocketAddr};
use structopt::StructOpt;
use log::info;
use tonic::transport::Server;
use tokio::signal;

use smarthome::generic_sensor_controler_server::GenericSensorControlerServer;
use smarthome::smart_bulb_server::SmartBulbServer;
use service::GenericSensorControlerService;
use sensor::Sensor;
use bulb::SmartBulbService;
extern crate pretty_env_logger;

mod smarthome;
mod service;
mod sensor;
mod bulb;




#[derive(Debug,StructOpt)]
#[structopt(name="server",about="Simple grpc server")]
struct Cli {
    #[structopt(long, short, default_value="127.0.0.1:8768", env="SERVER__ADDRESS")]
    address: SocketAddr
}



#[tokio::main(flavor = "multi_thread")]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    pretty_env_logger::init_timed();
    let args = Cli::from_args();
    let address = args.address;
    info!("running on {}", address);

    let mut sensors: HashMap<i32, Sensor> = Default::default();
    sensors.extend((0..10)
        .map(|index| {(index, Sensor::spawn_random_sensor_with_tasks(index))})
    );

    for (id, sensor) in sensors.iter() {
        info!(target: "INIT", "generated sensor id={},type={},name={},location={},unit={}", id, sensor.sensor_type.as_str_name(), sensor.name, sensor.location, sensor.unit);
    }
    info!(target:"INIT", "starting server");
    
    let reflection_service = tonic_reflection::server::Builder::configure()
    .register_encoded_file_descriptor_set(smarthome::FILE_DESCRIPTOR_SET)
    .build()
    .unwrap();

    let service = GenericSensorControlerService::new(sensors);
    let bulb = SmartBulbService::default();
    Server::builder()
        .tcp_keepalive(Some(Duration::from_secs(300)))
        .http2_keepalive_interval(Some(Duration::from_secs(600)))
        .add_service(reflection_service)
        .add_service(GenericSensorControlerServer::new(service))
        .add_service(SmartBulbServer::new(bulb))
        .serve_with_shutdown(address, async {signal::ctrl_c().await.expect("failed to await ctrl+c"); }).await?;
    info!(target:"SHUTDOWN", "shutting down");
    Ok(())
}
//grpcurl -format json -plaintext 127.0.0.1:8768 smarthome.GenericSensorControler.DescribeSensors
//grpcurl -format json -plaintext -d '{"sensorId": 3, "delayMilis": 1000}'  127.0.0.1:8768 smarthome.GenericSensorControler.SubReadings
