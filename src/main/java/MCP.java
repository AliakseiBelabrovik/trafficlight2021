import trafficlight.ctrl.TrafficLightCtrl;


public class MCP {
    public static void main(String[] args) {

        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance(); //implementation of singleton
        //TrafficLightCtrl ctrl = new TrafficLightCtrl();
        ctrl.run();
    }
}