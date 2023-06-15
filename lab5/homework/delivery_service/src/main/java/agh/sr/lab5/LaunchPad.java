package agh.sr.lab5;

public class LaunchPad {
    public synchronized String deliverAThing(String type, String data, String agency, String id) throws InterruptedException {
//        int delay = switch (type) {
//            case "people" -> 5000;
//            case "cargo"  -> 10000;
//            case "satelite" -> 2000;
//            default -> 1000;
//        };
        System.out.printf("launching %s %s for %s with request id %s\n", type, data, agency, id);
//        Thread.sleep(delay);
        System.out.printf("delivered %s %s for %s with request id %s\n", type, data, agency, id);
        return "delivered %s %s (id: %s)".formatted(type, data, id);
    }
}
