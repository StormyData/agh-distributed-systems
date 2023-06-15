package sr.grpc.hw;

import sr.grpc.gen.GenericSensorControlerDescribeSensors;
import sr.grpc.gen.GenericSensorControlerSensorType;

public record Sensor(int id, String name, String location, GenericSensorControlerSensorType type, String unit,
                     String server) {
    public static Sensor fromDescription(GenericSensorControlerDescribeSensors.SensorDescription description, String server) {
        return new Sensor(description.getId(),
                description.getName(),
                description.getLocation(),
                description.getType(),
                description.getUnit(),
                server);
    }

    @Override
    public String toString() {
        return String.format("%s %s sensor in %s at %s with id %d", type, unit, location, server, id);
    }
}
