package trafficlight.states;

public interface State {

    /**
     * Abstract method to set next state + previous state as current state
     */
    void nextState();

    /**
     * abstract method to implement which returns the colour of a state
     * @return - returns TrafficLightColor color of the state
     */
    TrafficLightColor getState();


}