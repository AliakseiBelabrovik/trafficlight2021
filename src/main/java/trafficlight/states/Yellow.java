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


    /**
     * In order to be able to switch to the correct next state, this method in class Yellow
     * checks what was the previous state (red or green). If the previous state was green,
     * it switches to red. If the previous class was red, it switches to green.
     */
    @Override
    public void nextState() {
        if (trafficLightCtrl.getPreviousState().getState() == TrafficLightColor.GREEN) {
            trafficLightCtrl.setCurrentState(trafficLightCtrl.getRedState());
            trafficLightCtrl.setPreviousState(this);
        } else {
            //(trafficLightCtrl.getPreviousState().getState() == TrafficLightColor.RED) {
            trafficLightCtrl.setCurrentState(trafficLightCtrl.getGreenState());
            trafficLightCtrl.setPreviousState(this);
        }

    }

    @Override
    public TrafficLightColor getState() {
        return trafficLightColor;
    }
}
