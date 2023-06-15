
use tonic::{Request, Response, Status};
use std::sync::Mutex;
use crate::smarthome::{
    smart_bulb_server::SmartBulb,
    Empty,
    SmartBulbState,
    SmartBulbStateMessage
};
use log::{info, warn};


#[derive(Debug)]
pub(crate) struct SmartBulbService {
    state: Mutex<SmartBulbState>
}
impl SmartBulbService {
    pub(crate) fn new(state: SmartBulbState) -> Self {
        Self {
            state: 
                Mutex::new(
                        state
                )
        }
    }
}

impl Default for SmartBulbService {
    fn default() -> Self {
        Self::new(SmartBulbState::Off)
    }
}


#[tonic::async_trait]
impl SmartBulb for SmartBulbService {
    async fn turn_on(&self, _request: Request<Empty>) -> Result<Response<Empty>, Status> {
        let mut val = self.state.lock().unwrap();
        match *val {
            SmartBulbState::On => {
                warn!(target: "SMARTBULB", "smartbulb already on");
                Err(Status::failed_precondition("already on"))
            }
            SmartBulbState::Off => {
                info!(target: "SMARTBULB", "turning on the smartbulb");
                *val = SmartBulbState::On;
                Ok(Response::new(Empty {}))
            }
        }
    }
    async fn turn_off(&self, _request: Request<Empty>) -> Result<Response<Empty>, Status> {
        let mut val = self.state.lock().unwrap();
        match *val {
            SmartBulbState::Off => {
                warn!(target: "SMARTBULB", "smartbulb already off");
                Err(Status::failed_precondition("already off"))
            }
            SmartBulbState::On => {
                info!(target: "SMARTBULB", "turning off the smartbulb");
                *val = SmartBulbState::Off;
                Ok(Response::new(Empty {}))
            }
        }
    }
    async fn get_state(&self, _request: Request<Empty>) -> Result<Response<SmartBulbStateMessage>, Status> {
        let val = *self.state.lock().unwrap();
        info!(target: "SMARTBULB", "sending smartbulb state to client"); 
        Ok(Response::new(SmartBulbStateMessage { state: val.into() }))
    }
}