package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

/**
 * URL: https://github.com/AliakseiBelabrovik/trafficlight2021.git
 * Factory pattern to generate an object of a concrete color (yellow, red or green).
 * This pattern is used by the controller TrafficLightCtrl in the method initStates() to
 * create three states
 */
public class StateFactory {
    /**
     * Use getState method to create an object of type State
     * @param trafficLightColor - this is an enum in the Class TrafficLightColor to differentiate
     *                          the objects (i.e. states)
     * @param trafficLightCtrl - this is the controller that we pass to the created object (i.e. state)
     *                         to be able to change its states
     * @return - This method returns an object of type State using Factory pattern
     */
    public State getState(TrafficLightColor trafficLightColor, TrafficLightCtrl trafficLightCtrl) {

        if (trafficLightColor == TrafficLightColor.OFF) {
            return new Off(trafficLightCtrl);
        } else if (trafficLightColor == TrafficLightColor.RED) {
            return new Red(trafficLightCtrl);
        } else if(trafficLightColor == TrafficLightColor.YELLOW) {
            return new Yellow(trafficLightCtrl);
        } else {
            return new Green(trafficLightCtrl);
        }

    }


}
