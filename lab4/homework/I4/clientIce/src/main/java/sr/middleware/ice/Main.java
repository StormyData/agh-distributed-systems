package sr.middleware.ice;

import Example.ClassWithOptionalField;
import Example.ExampleInterfacePrx;
import Example.ExceptionWithOptionalValue;
import com.zeroc.Ice.*;

import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        try(Communicator communicator = Util.initialize(args))
        {
            ObjectPrx prx = communicator.stringToProxy("ExampleI:tcp -h 127.0.0.2 -p 10000");
            ExampleInterfacePrx obj = ExampleInterfacePrx.checkedCast(prx);
            if(obj == null)
            {
                System.out.println("invalid proxy");
                return;
            }
            var returned1 = obj.methodWithOptionalReturnAndSomeArgs(OptionalInt.empty(), 5);
            System.out.println("methodWithOptionalReturnAndSomeArgs(OptionalInt.empty(), 5) returned");
            System.out.println(returned1);
            var returned2 = obj.methodWithOptionalReturnAndSomeArgs(OptionalInt.of(3), 7);
            System.out.println("methodWithOptionalReturnAndSomeArgs(OptionalInt.of(3), 7) returned");
            System.out.println(returned2);
            try {
                obj.methodThrowingExceptionWithOptionalValue(false);
            } catch (ExceptionWithOptionalValue e) {
                System.out.println("methodThrowingExceptionWithOptionalValue(false) threw");
                System.out.println(e.optionalWhy());
            }
            try {
                obj.methodThrowingExceptionWithOptionalValue(true);
            } catch (ExceptionWithOptionalValue e) {
                System.out.println("methodThrowingExceptionWithOptionalValue(true) threw");
                System.out.println(e.optionalWhy());
            }
            var returned3 = obj.methodAcceptingClassWithOptionalValues(new ClassWithOptionalField(0));
            System.out.println("methodAcceptingClassWithOptionalValues(new ClassWithOptionalField(0)) returned");
            System.out.println(returned3);
            var returned4 = obj.methodAcceptingClassWithOptionalValues(new ClassWithOptionalField(6, 1));
            System.out.println("methodAcceptingClassWithOptionalValues(new ClassWithOptionalField(6, 1)) returned");
            System.out.println(returned4);

        }
    }
}