/*
             Nahom Worku
             EECS 1021
             MAJOR PROJECT
 */
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.IOException;

import jm.JMC;
import jm.music.data.*;
import jm.util.Play;

public class myGUI_Frame extends JFrame implements ChangeListener {
    static String myPort = "COM9";
    static IODevice myGroveBoard = new FirmataDevice(myPort);

    JButton button0, button, button2, button3, button4;
    JSlider slider0, slider, slider2, slider3, slider4;
    JLabel label0, label, label2, label3, label4;

    myGUI_Frame() {
        // button0 is used for rotating the conveyor belt counterclockwise(CCW)
        button0 = new JButton("ON (CCW)");
        button0.setBounds(50, 510, 100, 50);
        button0.setFocusable(false);
        button0.setBackground(Color.green);
        button0.addActionListener(e -> {

            Play.midi(new Note(JMC.C4, 0.1*JMC.TN));

            var IN3 = myGroveBoard.getPin(4);
            var IN4 = myGroveBoard.getPin(5);
            var INB = myGroveBoard.getPin(6);

            try {
                IN3.setMode(Pin.Mode.OUTPUT);
                IN4.setMode(Pin.Mode.OUTPUT);
                INB.setMode(Pin.Mode.PWM);
                IN3.setValue(1);
                IN4.setValue(0);
                INB.setValue((short) 255);
                System.out.println("ON");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // button is used for rotating the conveyor belt clockwise(CW)
        button = new JButton("ON (CW)");
        button.setBounds(200, 510, 100, 50);
        button.setFocusable(false);
        button.setBackground(Color.green);
        button.addActionListener(e -> {

            Play.midi(new Note(JMC.C4, 0.1*JMC.TN));

            var IN3 = myGroveBoard.getPin(4);
            var IN4 = myGroveBoard.getPin(5);
            var INB = myGroveBoard.getPin(6);

            try {
                IN3.setMode(Pin.Mode.OUTPUT);
                IN4.setMode(Pin.Mode.OUTPUT);
                INB.setMode(Pin.Mode.PWM);
                IN3.setValue(0);
                IN4.setValue(1);
                INB.setValue((short) 255);
                System.out.println("ON");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // button2 is used for turning OFF the conveyor belt
        button2 = new JButton("OFF");
        button2.setBounds(425, 590, 100, 50);
        button2.setBackground(Color.red);
        button2.addActionListener(e -> {

            // Longer note for turning off the DC motors for the conveyor belt
            Play.midi(new Note(JMC.F2, 1*JMC.TN));

            var IN3 = myGroveBoard.getPin(4);
            var IN4 = myGroveBoard.getPin(5);
            var INB = myGroveBoard.getPin(6);

            try {
                IN3.setMode(Pin.Mode.OUTPUT);
                IN4.setMode(Pin.Mode.OUTPUT);
                INB.setMode(Pin.Mode.PWM);
                IN3.setValue(0);
                IN4.setValue(0);
                INB.setValue((short) 255);
                System.out.println("OFF");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // button3 is used for rotating the conveyor belt counterclockwise(CCW) for 100 millisecond (0.1 second)
        button3 = new JButton("ON / OFF (CCW)");
        button3.setBounds(40, 590, 120, 50);
        button3.setFocusable(false);
        button3.setBackground(Color.cyan);
        button3.addActionListener(e -> {

            Play.midi(new Note(JMC.A4, 0.1*JMC.TN));

            var IN3 = myGroveBoard.getPin(4);
            var IN4 = myGroveBoard.getPin(5);
            var INB = myGroveBoard.getPin(6);

            try {
                IN3.setMode(Pin.Mode.OUTPUT);
                IN4.setMode(Pin.Mode.OUTPUT);
                INB.setMode(Pin.Mode.PWM);
                IN3.setValue(1);
                IN4.setValue(0);
                Thread.sleep(100);
                IN3.setValue(0);
                IN4.setValue(0);
                INB.setValue((short) 255);
                System.out.println("ON/OFF...(Counterclockwise)");

            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        // button4 is used for rotating the conveyor belt clockwise(CW) for 100 millisecond (0.1 second)
        button4 = new JButton("ON / OFF (CW)");
        button4.setBounds(190, 590, 120, 50);
        button4.setFocusable(false);
        button4.setBackground(Color.cyan);
        button4.addActionListener(e -> {

            Play.midi(new Note(JMC.A4, 0.1*JMC.TN));

            var IN3 = myGroveBoard.getPin(4);
            var IN4 = myGroveBoard.getPin(5);
            var INB = myGroveBoard.getPin(6);

            try {
                IN3.setMode(Pin.Mode.OUTPUT);
                IN4.setMode(Pin.Mode.OUTPUT);
                INB.setMode(Pin.Mode.PWM);
                IN3.setValue(0);
                IN4.setValue(1);
                Thread.sleep(100);
                IN3.setValue(0);
                IN4.setValue(0);
                INB.setValue((short) 255);
                System.out.println("ON/OFF...(Clockwise)");

            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        // Slider 0 -> used for controlling the speed of the conveyor belt motors
        // It's initialized at 255 which is its maximum speed
        slider0 = new JSlider(0, 255, 255);
        slider0.setPaintTicks(true);
        slider0.setMinorTickSpacing(25);
        slider0.setPaintTrack(true);
        slider0.setMajorTickSpacing(50);
        slider0.setPaintLabels(true);
        slider0.setFont(new Font("MV Boli", Font.PLAIN, 15));
        slider0.setOrientation(SwingConstants.HORIZONTAL);
        slider0.addChangeListener(this);
        slider0.setBounds(330, 490, 250, 100);


        // Slider 1 -> used for rotating the bottom servo (Servo A) initialized at 100 degrees
        slider = new JSlider(0, 180, 100);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(30);
        slider.setPaintLabels(true);
        slider.setFont(new Font("MV Boli", Font.PLAIN, 15));
        slider.setOrientation(SwingConstants.HORIZONTAL);
        slider.addChangeListener(this);
        slider.setBounds(50, 100, 300, 100);

        // Slider 2 -> used for rotating Servo B initialized at 90 degrees
        slider2 = new JSlider(0, 180, 90);
        slider2.setPaintTicks(true);
        slider2.setMinorTickSpacing(10);
        slider2.setPaintTrack(true);
        slider2.setMajorTickSpacing(30);
        slider2.setPaintLabels(true);
        slider2.setFont(new Font("MV Boli", Font.PLAIN, 15));
        slider2.setOrientation(SwingConstants.HORIZONTAL);
        slider2.addChangeListener(this);
        slider2.setBounds(50, 200, 300, 100);

        // Slider 3 -> used for rotating servo C initialized at 70 degrees
        slider3 = new JSlider(0, 180, 70);
        slider3.setPaintTicks(true);
        slider3.setMinorTickSpacing(10);
        slider3.setPaintTrack(true);
        slider3.setMajorTickSpacing(30);
        slider3.setPaintLabels(true);
        slider3.setFont(new Font("MV Boli", Font.PLAIN, 15));
        slider3.setOrientation(SwingConstants.HORIZONTAL);
        slider3.addChangeListener(this);
        slider3.setBounds(50, 300, 300, 100);

        // Slider 4 -> used for rotating Servo D which is used to open and close the gripper, initialized at 90 degrees
        // Min value 60 because the gripper can't open below 60 degrees
        // Same reasoning for the max value
        slider4 = new JSlider(60, 130, 90);
        slider4.setPaintTicks(true);
        slider4.setMinorTickSpacing(10);
        slider4.setPaintTrack(true);
        slider4.setMajorTickSpacing(30);
        slider4.setPaintLabels(true);
        slider4.setFont(new Font("MV Boli", Font.PLAIN, 15));
        slider4.setOrientation(SwingConstants.HORIZONTAL);
        slider4.addChangeListener(this);
        slider4.setBounds(50, 400, 300, 100);

        // Label 0 -> Used to display the title
        label0 = new JLabel();
        label0.setFont(new Font("MV Boli", Font.PLAIN, 50));
        label0.setBounds(60, 25, 600, 50);
        label0.setText("Robot Arm Controller");
        label0.setForeground(Color.BLUE);

        // Label 1 -> used to display the position of Servo A (bottom servo)
        label = new JLabel();
        label.setFont(new Font("MV Boli", Font.PLAIN, 25));
        label.setBounds(400, 120, 300, 30);
        label.setText("Servo A: " + (slider.getValue()) + "°");

        // Label 2 -> used to display the position of Servo B
        label2 = new JLabel();
        label2.setFont(new Font("MV Boli", Font.PLAIN, 25));
        label2.setBounds(400, 220, 300, 30);
        label2.setText("Servo B: " + (slider2.getValue()) + "°");

        // Label 3 -> used to display the position of Servo C
        label3 = new JLabel();
        label3.setFont(new Font("MV Boli", Font.PLAIN, 25));
        label3.setBounds(400, 320, 300, 30);
        label3.setText("Servo C: " + (slider3.getValue()) + "°");

        // Label 4 -> used to display the position of Servo D
        label4 = new JLabel();
        label4.setFont(new Font("MV Boli", Font.PLAIN, 25));
        label4.setBounds(400, 420, 300, 30);
        label4.setText("Servo D: " + (slider4.getValue()) + "°");

        // Adding all the stuff above in the frame ("My Frame")
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(650, 725);
        this.setVisible(true);
        this.add(button0);
        this.add(button);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(slider0);
        this.add(slider);
        this.add(slider2);
        this.add(slider3);
        this.add(slider4);
        this.add(label0);
        this.add(label);
        this.add(label2);
        this.add(label3);
        this.add(label4);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new myGUI_Frame();
        try {
            myGroveBoard.start();
            System.out.println("Board started.");
            myGroveBoard.ensureInitializationIsDone();
        } catch (Exception ex) {
            System.out.println("couldn't connect to board.");
        } finally {
            // Initialize the Servos Position
            var servoA = myGroveBoard.getPin(9);
            var servoB = myGroveBoard.getPin(10);
            var servoC = myGroveBoard.getPin(11);
            var servoD = myGroveBoard.getPin(12);

            servoA.setMode(Pin.Mode.SERVO);
            servoA.setValue((short) 100);
            servoB.setMode(Pin.Mode.SERVO);
            servoB.setValue((short) 90);
            Thread.sleep(1000);
            servoC.setMode(Pin.Mode.SERVO);
            servoC.setValue((short) 70);
            servoD.setMode(Pin.Mode.SERVO);
            servoD.setValue((short) 90);

            // Adding the JAVA GUI (Frame)
            //new myGUI_Frame();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        var servoA = myGroveBoard.getPin(9);
        var servoB = myGroveBoard.getPin(10);
        var servoC = myGroveBoard.getPin(11);
        var servoD = myGroveBoard.getPin(12);

        var INB = myGroveBoard.getPin(6);

        try {
            servoA.setMode(Pin.Mode.SERVO);
            servoB.setMode(Pin.Mode.SERVO);
            servoC.setMode(Pin.Mode.SERVO);
            servoD.setMode(Pin.Mode.SERVO);

            INB.setMode(Pin.Mode.PWM);
            INB.setValue((byte) slider0.getValue());

            servoA.setValue((short) slider.getValue());
            servoB.setValue((short) slider2.getValue());
            servoC.setValue((short) slider3.getValue());
            servoD.setValue((short) slider4.getValue());

            label.setText("Servo A: " + (slider.getValue()) + "°");
            label2.setText("Servo B: " + (slider2.getValue()) + "°");
            label3.setText("Servo C: " + (slider3.getValue()) + "°");
            label4.setText("Servo D: " + (slider4.getValue()) + "°");
            System.out.print("                                                                      ");
            System.out.print("Servo A: " + slider.getValue() + " | " + "Servo B: " + slider2.getValue() + " | " );
            System.out.print("Servo C: " + slider3.getValue() + " | " + "Servo D: " + slider4.getValue() + " ||| ");
            System.out.println(" Conveyor Belt Speed : " + slider0.getValue());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}