package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

/**
 * The original state is OFF, because the traffic light is off
 * Only after the button nextState or AutoMode was clicked, the state changes to the first light: RED.
 */
public class Off implements State {
    TrafficLightColor trafficLightColor;
    TrafficLightCtrl trafficLightCtrl;

    public Off(TrafficLightCtrl trafficLightCtrl) {
        this.trafficLightColor = TrafficLightColor.OFF;
        this.trafficLightCtrl = trafficLightCtrl;
    }

    @Override
    public void nextState() {
        trafficLightCtrl.setCurrentState(trafficLightCtrl.getRedState());
        trafficLightCtrl.setPreviousState(this);
    }

    @Override
    public TrafficLightColor getState() {
        return trafficLightColor;
    }
}
