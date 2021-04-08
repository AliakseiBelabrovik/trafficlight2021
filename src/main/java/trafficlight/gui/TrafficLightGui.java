package trafficlight.gui;

import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.states.TrafficLightColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficLightGui extends JFrame implements ActionListener {

    public static final String ACTION_COMMAND_NEXT = "next";

    public static final String ACTION_COMMAND_AUTO_MODE = "autoMode";
    public static final String NAME_OF_THE_GAME = "Traffic Light";

    private JButton buttonNextState;
    private JCheckBox checkBoxAutoMode;
    private JLabel labelAutoMode;

    private Light green = null;
    private Light yellow = null;
    private Light red = null;

    private TrafficLightCtrl trafficLightCtrl = null;

    private boolean isAutoMode = false;
    private boolean doExit = false;

    private int yellowIntervall = 500;

    private int intervall = 1500;

    public TrafficLightGui(TrafficLightCtrl ctrl){
        super(NAME_OF_THE_GAME);
        trafficLightCtrl = ctrl;
        initLights();
        init();
    }

    private void initLights() {
        green  = new Light(Color.green);
        yellow = new Light(Color.yellow);
        red    = new Light(Color.red);
    }

    private void init() {
        getContentPane().setLayout(new GridLayout(2, 1));
        buttonNextState = new JButton("next State");
        buttonNextState.setActionCommand(ACTION_COMMAND_NEXT);
        buttonNextState.addActionListener(this);

        labelAutoMode = new JLabel("AutoMode ");

        checkBoxAutoMode = new JCheckBox();
        checkBoxAutoMode.setActionCommand("autoMode");
        checkBoxAutoMode.addActionListener(this);

        JPanel p1 = new JPanel(new GridLayout(3,1));
        p1.add(red);
        p1.add(yellow);
        p1.add(green);


        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(buttonNextState);
        p2.add(labelAutoMode);
        p2.add(checkBoxAutoMode);

        getContentPane().add(p1);
        getContentPane().add(p2);
        pack();
    }

    public void run() {
        while (!doExit) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                JOptionPane pane = new JOptionPane();
                JDialog dialog = pane.createDialog(this,"Traffic Light Problem");
                JOptionPane.showMessageDialog(dialog, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
             while (isAutoMode) {
                 //TODO call the controller
                 trafficLightCtrl.nextState();
                try {
                    if (yellow.isOn) {
                        Thread.sleep(yellowIntervall);

                    } else {
                        Thread.sleep(intervall);
                    }
                } catch (InterruptedException e) {
                    JOptionPane pane = new JOptionPane();
                    JDialog dialog = pane.createDialog(this,"Traffic Light Problem");
                    JOptionPane.showMessageDialog(dialog, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    isAutoMode = false;
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        if (ACTION_COMMAND_NEXT.equals(e.getActionCommand())){
           trafficLightCtrl.nextState();
        } else if (ACTION_COMMAND_AUTO_MODE.equals(e.getActionCommand())){
            isAutoMode = !isAutoMode;
            System.out.println("set Auto mode to "+isAutoMode);
        }
    }

    /**
     * This method changes the light of the traffic light and
     * uses a thread to make the yellow color blink.
     * @param trafficLightColor - takes a colour of each state as a parameter to know
     * which light to turn on.
     */
    public void setLight(TrafficLightColor trafficLightColor){
        //TODO setLight

        //thread to make the yellow light blink
        Thread flashingYellowLight = new Thread(new Runnable() {
            @Override
            public void run() {

                //as long as the color is yellow, make the light blink
                while (trafficLightCtrl.getCurrentState().getState() == TrafficLightColor.YELLOW) {
                    try {
                        Thread.sleep(yellowIntervall);
                        yellow.turnOn(false);
                        Thread.sleep(yellowIntervall);
                        yellow.turnOn(true);
                    } catch (InterruptedException e) {
                        System.out.println("Something went wrong in the thread. -> leave the tread.");
                        break;
                    }

                    //if the current state is not yellow - don't blink and leave the thread using break statement
                    if (trafficLightCtrl.getCurrentState().getState() != TrafficLightColor.YELLOW) {
                        yellow.turnOn(false);
                        break;
                    }
                }
            }
        });

        //if the color must be yellow, turn it on and make it blink
        if (trafficLightColor == TrafficLightColor.YELLOW) {
            red.turnOn(false);
            green.turnOn(false);
            yellow.turnOn(true);

            //start thread to make the yellow light blink
            flashingYellowLight.start();

            //if green, turn the green light on
        } else if (trafficLightColor == TrafficLightColor.GREEN) {
            yellow.turnOn(false);
            green.turnOn(true);

            //if red -> turn the red light on
        } else if (trafficLightColor == TrafficLightColor.RED) {
            yellow.turnOn(false);
            red.turnOn(true);
        }
    }
}
