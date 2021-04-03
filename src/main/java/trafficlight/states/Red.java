package trafficlight.states;

import trafficlight.ctrl.TrafficLightCtrl;

public class Red implements State {
    private State nextState;
    private State currentState;
    TrafficLightColor trafficLightColor;
    TrafficLightCtrl trafficLightCtrl;


    public Red(TrafficLightCtrl trafficLightCtrl) {
        trafficLightColor = TrafficLightColor.RED; //ist es so korrekt? lese über enums
        this.trafficLightCtrl = trafficLightCtrl;
    }


    @Override
    public TrafficLightColor getState() {
        return trafficLightColor;
    }


    @Override
    public void nextState() {
        trafficLightCtrl.setCurrentState(trafficLightCtrl.getYellowState());
        trafficLightCtrl.setPreviousState(this);

    }


}
