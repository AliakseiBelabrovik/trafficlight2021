package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

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
