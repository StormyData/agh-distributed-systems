

using serverIce;

try
{
    using(Ice.Communicator communicator = Ice.Util.initialize(ref args))
    {
        var adapter =
            communicator.createObjectAdapterWithEndpoints("ExampleIAdapter", "tcp -h 127.0.0.2 -p 10000");
        adapter.add(new ExampleI(), Ice.Util.stringToIdentity("ExampleI"));
        adapter.activate();
        communicator.waitForShutdown();
    }
}
catch(Exception e)
{
    Console.Error.WriteLine(e);
    return 1;
}
return 0;