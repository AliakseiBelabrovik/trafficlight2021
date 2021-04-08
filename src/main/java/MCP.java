import trafficlight.ctrl.TrafficLightCtrl;

/**
 * URL: https://github.com/AliakseiBelabrovik/trafficlight2021.git
 */
public class MCP {
    public static void main(String[] args) {

        //run the app using the singleton pattern
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance(); //implementation of singleton
        ctrl.run();
    }
}