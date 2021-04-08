import org.junit.jupiter.api.*;
import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.states.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * URL: https://github.com/AliakseiBelabrovik/trafficlight2021.git
 * This class serves to test the states of the controller
 *
 */

public class TrafficlightCtrlTest {
    private static TrafficLightCtrl trafficLightCtrl = null;
    /**
     * Before using tests, initialize the controller
     */
    @BeforeAll
    public static void init() {
        System.out.println("Initializing the TrafficLightCtrl. Tests can begin.");
        trafficLightCtrl = TrafficLightCtrl.getInstance();
    }

    /**
     * Before each test, set the current and previous states of the traffic light to off
     */
    @BeforeEach
    public void setUp() {
        trafficLightCtrl.setPreviousState(new Off(trafficLightCtrl));
        trafficLightCtrl.setCurrentState(new Off(trafficLightCtrl));
    }
    @AfterAll
    public static void finish() {
        System.out.println("Finished testing the states of the TrafficLightCtrl.");
    }

    /**
     * The original state must be off. This test tests whether the original state of the TrafficLight is off.
     */
    @Test
    @DisplayName("Test 1: Testing for original state")
    void testForOriginalState() {
        Class expected = new Off(trafficLightCtrl).getClass();
        assertEquals(expected, trafficLightCtrl.getCurrentState().getClass(),"The original " +
                "state must be Off.");
    }

    @Test
    @DisplayName("Test 2: Testing for yellow after green")
    void testForYellowAfterGreen() {
        trafficLightCtrl.setCurrentState(trafficLightCtrl.getGreenState()); //set the current state green
        trafficLightCtrl.setPreviousState(trafficLightCtrl.getYellowState()); //set previous state yellow
        trafficLightCtrl.nextState(); //change the state of the controller to the next one

        Class expected = new Yellow(trafficLightCtrl).getClass();

        assertEquals(expected, trafficLightCtrl.getCurrentState().getClass(), "The current " +
                "state after Green must be Yellow.");
    }

    @Test
    @DisplayName("Test 3: Testing the red light after  yellow and its previous state green")
    void testYellowClass_Red() {
        Class expected = new Red(trafficLightCtrl).getClass();
        trafficLightCtrl.setPreviousState(trafficLightCtrl.getGreenState());
        trafficLightCtrl.setCurrentState(trafficLightCtrl.getYellowState());
        trafficLightCtrl.nextState();
        assertEquals(expected, trafficLightCtrl.getCurrentState().getClass());
    }

    @Test
    @DisplayName("Test 4: Testing the red light after  yellow and its previous state green")
    void testYellowClass_Green() {
        Class expected = new Green(trafficLightCtrl).getClass();
        trafficLightCtrl.setPreviousState(trafficLightCtrl.getRedState());
        trafficLightCtrl.setCurrentState(trafficLightCtrl.getYellowState());
        trafficLightCtrl.nextState();
        assertEquals(expected, trafficLightCtrl.getCurrentState().getClass());
    }

    @Test
    @DisplayName("Test 5: Testing for yellow after green")
    void testForYellowAfterRed() {
        trafficLightCtrl.setCurrentState(trafficLightCtrl.getRedState()); //set the current state red
        trafficLightCtrl.setPreviousState(trafficLightCtrl.getYellowState()); //set previous state yellow
        trafficLightCtrl.nextState(); //change the state of the controller to the next one (must be yellow)

        Class expected = new Yellow(trafficLightCtrl).getClass();

        assertEquals(expected, trafficLightCtrl.getCurrentState().getClass(), "The current " +
                "state after Red must be Yellow.");
    }



}
