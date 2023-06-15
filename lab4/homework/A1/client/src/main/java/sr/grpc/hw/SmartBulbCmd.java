package sr.grpc.hw;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import sr.grpc.gen.SmartBulbState;

import java.util.Arrays;

public class SmartBulbCmd implements IServiceCmd {
    @Override
    public void set(String server, String[] args, GrpcClient client) {
        var state = Util.getParameter(Arrays.stream(args), "state=")
                .map(SmartBulbState::valueOf)
                .orElse(null);
        if (state == null) {
            System.out.println("state is missing or is not ON or OFF");
            return;
        }
        try {
            client.setSmartbulbState(server, state);
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.FAILED_PRECONDITION) {
                System.out.printf("failed setting the state: %s\n", e.getStatus().getDescription());
            } else {
                throw e;
            }
        }
        System.out.println("state set successfully");

    }

    @Override
    public void get(String server, String[] args, GrpcClient client) {
        var state = client.getSmartbulbState(server);
        System.out.printf("the smartbulb is %s\n", state);
    }

    @Override
    public void help() {
        System.out.println("""
                this service exposes an interface of a smart lightbulb
                set state=(ON|OFF) <- sets the state of the bulb
                get <- gets the current state of the bulb""");
    }
}
