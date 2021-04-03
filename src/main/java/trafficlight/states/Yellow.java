package trafficlight.states;


import trafficlight.ctrl.TrafficLightCtrl;

public class Yellow implements State {
    private State nextState;
    private State currentState;
    TrafficLightColor trafficLightColor;
    TrafficLightCtrl trafficLightCtrl;

    public Yellow(TrafficLightCtrl trafficLightCtrl) {
        this.trafficLightColor = TrafficLightColor.YELLOW;
        this.trafficLightCtrl = trafficLightCtrl;
    }



    @Override
    public void nextState() {
        if (trafficLightCtrl.getPreviousState().getState() == TrafficLightColor.GREEN) {
            trafficLightCtrl.setCurrentState(trafficLightCtrl.getRedState());
            trafficLightCtrl.setPreviousState(this);
        }


        if (trafficLightCtrl.getPreviousState().getState() == TrafficLightColor.RED) {
            trafficLightCtrl.setCurrentState(trafficLightCtrl.getGreenState());
            trafficLightCtrl.setPreviousState(this);
        }

    }

    @Override
    public TrafficLightColor getState() {
        return trafficLightColor;
    }
}
