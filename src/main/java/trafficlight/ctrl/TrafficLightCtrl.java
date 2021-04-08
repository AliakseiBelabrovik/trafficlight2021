package trafficlight.ctrl;

import trafficlight.gui.TrafficLightGui;
import trafficlight.states.Off;
import trafficlight.states.State;
import trafficlight.states.StateFactory;
import trafficlight.states.TrafficLightColor;

public class TrafficLightCtrl {

    /**
     * URL: https://github.com/AliakseiBelabrovik/trafficlight2021.git
     * static variable to implement Singleton - delete if not allowed to declare new instance variables
     */
    private static TrafficLightCtrl instance = null;


    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private TrafficLightGui gui;

    /**
     * The constructor is private because we use Singleton pattern
     */
    private TrafficLightCtrl() {
        super();
        initStates();
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
    }

    /**
     * This method represents the implementation of the Singleton pattern, which is called in the main class,
     * when we try to create a controller object. This pattern does not allow to create many objects of this class.
     * @return - returns an instance of TrafficLightCtrl
     */
    public static TrafficLightCtrl getInstance() {
        if (instance == null)
            instance = new TrafficLightCtrl();
        return instance;
    }

    /**
     * This void method uses the Factory Pattern (see StateFactory class) to create three states.
     * Firstly, it creates an object of StateFactory class.
     * Then, it takes the respective color and this (TrafficLightCtrl object) to create three states via
     * the getState method of StateFactory class
     */
    private void initStates() {
        //TODO create the states and set current and previous state

        ////initialize the factory pattern
        StateFactory stateFactory = new StateFactory();

        //use factory pattern and polymorphism to create the states
        redState = stateFactory.getState(TrafficLightColor.RED, this);
        yellowState = stateFactory.getState(TrafficLightColor.YELLOW, this);
        greenState = stateFactory.getState(TrafficLightColor.GREEN, this);

        //set Off state as an original state and previous state
        setCurrentState(stateFactory.getState(TrafficLightColor.OFF, this));
        setPreviousState(stateFactory.getState(TrafficLightColor.OFF, this));

    }

    public State getGreenState() {
        return greenState;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public void run() {
        gui.run();
    }

    /**
     * using the current state, we change the state with a nextState() method.
     * Then it calls the setLight method of the GUI to set/change the light.
     */
    public void nextState() {
        //TODO handle GUi request and update GUI
        currentState.nextState(); //change the state to the next one
        gui.setLight(currentState.getState()); // get the color of the new state and set the light
    }
}