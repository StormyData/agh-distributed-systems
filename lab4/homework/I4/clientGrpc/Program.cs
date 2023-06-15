// See https://aka.ms/new-console-template for more information

using ExampleProto;
using Grpc.Net.Client;

using var channel = GrpcChannel.ForAddress("http://127.0.0.2:20000");
var client = new Example.ExampleClient(channel);

var replay1 = client.MethodWithOptionalArgs(new MessageWithOptionalField{Arg1 = 0, Arg2 = 5});
Console.WriteLine("MethodWithOptionalArgs(new MessageWithOptionalField{Arg1 = 0, Arg2 = 5}) returned");
Console.WriteLine(replay1);
var replay2 = client.MethodWithOptionalArgs(new MessageWithOptionalField{Arg2 = 5});
Console.WriteLine("MethodWithOptionalArgs(new MessageWithOptionalField{Arg2 = 5}) returned");
Console.WriteLine(replay2);


var replay3 = client.MethodWithoutOptionalArgs(new MessageWithoutOptionalField{Arg1 = 0, Arg2 = 5});
Console.WriteLine("MethodWithoutOptionalArgs(new MessageWithoutOptionalField{Arg1 = 0, Arg2 = 5}) returned");
Console.WriteLine(replay3);
var replay4 = client.MethodWithoutOptionalArgs(new MessageWithoutOptionalField{Arg2 = 5});
Console.WriteLine("MethodWithoutOptionalArgs(new MessageWithoutOptionalField{Arg2 = 5}) returned");
Console.WriteLine(replay4);