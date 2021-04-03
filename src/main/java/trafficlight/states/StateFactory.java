package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

/**
 * Factory pattern to generate an object of concrete color
 */
public class StateFactory {

    /**
     * Use getState method to get object of type shape
     * @param trafficLightColor
     * @return - returns an object of required color
     */
    public State getState(TrafficLightColor trafficLightColor, TrafficLightCtrl trafficLightCtrl) {
        if (trafficLightColor == null) {
            return null;
        }
        if (trafficLightColor == TrafficLightColor.RED) {
            return new Red(trafficLightCtrl);
        }
        if(trafficLightColor == TrafficLightColor.YELLOW) {
            return new Yellow(trafficLightCtrl);
        }
        if (trafficLightColor == TrafficLightColor.GREEN) {
            return new Green(trafficLightCtrl);
        }
        return null;
    }


}
