using Example;
using Ice;

namespace serverIce;

public class ExampleI : ExampleInterfaceDisp_
{
    public override Optional<int> methodWithOptionalReturnAndSomeArgs(Optional<int> arg1, int arg2, Current? current = null)
    {
        if (arg1.HasValue)
            return arg1.Value + arg2;
        return new Optional<int>();
    }

    public override void methodThrowingExceptionWithOptionalValue(bool hasWhy, Current? current = null)
    {
        var optional = hasWhy ? "why field is present" : new Optional<string>();
        throw new ExceptionWithOptionalValue(optional);
    }

    public override int methodAcceptingClassWithOptionalValues(ClassWithOptionalField arg, Current? current = null)
    {
        if (arg.optionalField.HasValue)
        {
            return arg.required + arg.optionalField.Value;
        }

        return arg.required;
    }
}