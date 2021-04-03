package trafficlight.ctrl;

import trafficlight.gui.TrafficLightGui;
import trafficlight.states.Off;
import trafficlight.states.State;
import trafficlight.states.StateFactory;
import trafficlight.states.TrafficLightColor;

public class TrafficLightCtrl {

    //static variable to implement Singleton - delete if not allowed to declare new instance variables
    private static TrafficLightCtrl instance = null;


    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private TrafficLightGui gui;

    //make this private and add function getInstance to use the singleton
    //make public again if not allowed to change
    private TrafficLightCtrl() {
        super();
        initStates();
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
    }

    //getInstance method to implement the Singleton
    public static TrafficLightCtrl getInstance() {
        if (instance == null)
            instance = new TrafficLightCtrl();
        return instance;
    }

    private void initStates() {
        //TODO create the states and set current and previous state
        ////initialize the factory pattern
        StateFactory stateFactory = new StateFactory();
        //use factory pattern and polymorphism to create the states
        redState = stateFactory.getState(TrafficLightColor.RED, this); //vielleicht auch this übergeben
        yellowState = stateFactory.getState(TrafficLightColor.YELLOW, this);
        greenState = stateFactory.getState(TrafficLightColor.GREEN, this);

        //original state is off
        setCurrentState(new Off(this));
        setPreviousState(new Off(this));

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

    public void nextState() {
        //TODO handle GUi request and update GUI
        currentState.nextState(); //change the state to the next one
        gui.setLight(currentState.getState()); // get the color of the new state
    }
}