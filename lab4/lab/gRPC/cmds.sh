#!/bin/bash
protoc -I. --java_out=gen person.proto 
protoc -I. --java_out=gen --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java-1.54.0-linux-x86_64.exe --grpc-java_out=gen calculator.proto 
protoc -I. --java_out=gen --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java-1.54.0-linux-x86_64.exe --grpc-java_out=gen streaming.proto
