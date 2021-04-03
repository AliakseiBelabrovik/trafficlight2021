package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

public class Green implements State {
    private State nextState;
    private State currentState;
    TrafficLightColor trafficLightColor;
    TrafficLightCtrl trafficLightCtrl;

    public Green(TrafficLightCtrl trafficLightCtrl) {
        this.trafficLightColor = TrafficLightColor.GREEN;
        this.trafficLightCtrl = trafficLightCtrl;
    }


    @Override
    public void nextState() {
        trafficLightCtrl.setCurrentState(trafficLightCtrl.getYellowState());
        trafficLightCtrl.setPreviousState(this);
    }

    @Override
    public TrafficLightColor getState() {
        return trafficLightColor;
    }
}
