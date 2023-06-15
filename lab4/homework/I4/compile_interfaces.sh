mkdir -p serverIce/generated
slice2cs --output-dir serverIce/generated example.ice
mkdir -p clientIce/generated
slice2java --output-dir clientIce/generated example.ice
mkdir -p serverGrpc/gen
protoc -I. --java_out=serverGrpc/gen --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java-1.54.0-linux-x86_64.exe --grpc-java_out=serverGrpc/gen example.proto 
