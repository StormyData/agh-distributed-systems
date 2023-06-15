use std::{path::PathBuf, env};

fn main() -> Result<(), Box<dyn std::error::Error>> {
    let out_dir = PathBuf::from(env::var("OUT_DIR").unwrap());
    //println!("cargo:warning=output files in {}", out_dir.to_str().unwrap());   
    tonic_build::configure()
    .build_client(false)
    .file_descriptor_set_path(out_dir.join("smarthome.bin"))
    .compile(&["proto/smarthome.proto"], &["proto"])
    .unwrap();
    Ok(())
 }