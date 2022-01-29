

package frc.robot;
//package edu.wpi.first.wpilibj.buttons;
import java.lang.Object;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.function.BooleanSupplier;
//import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj.command.button.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.Button;
//import frc.robot.commands.buttonText;

public class OI {
    // Create the joysticks and controller and buttons
    Joystick XBOX1 = new Joystick(0);
    Joystick leftJoy = new Joystick(1);
    Joystick rightJoy = new Joystick(2);
    private final MotorController m_intakeMotor = new PWMSparkMax(2);

    final int XBOXbuttonA = 1;
    final int XBOXbuttonB = 2;
    final int XBOXbuttonX = 3;
    final int XBOXbuttonY = 4;

    Button triggerLeft = new JoystickButton(leftJoy, 1);
    Button  button2Left = new JoystickButton(leftJoy, 2);
    Button  button3Left = new JoystickButton(leftJoy, 3);
    Button  button4Left = new JoystickButton(leftJoy, 4);
    Button  button5Left = new JoystickButton(leftJoy, 5);
    Button  button6Left = new JoystickButton(leftJoy, 6);
    Button  button7Left = new JoystickButton(leftJoy, 7);
    Button  button8Left = new JoystickButton(leftJoy, 8);
    Button  button9Left = new JoystickButton(leftJoy, 9);
    Button  button10Left = new JoystickButton(leftJoy, 10);
    Button  button11Left = new JoystickButton(leftJoy, 11);
    Button  button12Left = new JoystickButton(leftJoy, 12);
    Button  triggerRight = new JoystickButton(rightJoy, 1);

    Button  Abutton = new JoystickButton(XBOX1, XBOXbuttonA);
    Button  Bbutton = new JoystickButton(XBOX1, XBOXbuttonB);
    Button  Xbutton = new JoystickButton(XBOX1, XBOXbuttonX);
    Button  Ybutton = new JoystickButton(XBOX1, XBOXbuttonY);

    //public Button leftTrigger = new buttonText();
    public OI(){
      //to test the buttons
      triggerLeft.whenPressed((Command) new PrintCommand("pressed"));
      button2Left.whenPressed((Command) new PrintCommand("pressed2"));
      button3Left.whenPressed((Command) new PrintCommand("pressed3"));
      button4Left.whenPressed((Command) new PrintCommand("pressed4"));
      button5Left.whenPressed((Command) new PrintCommand("pressed5"));
      button6Left.whenPressed((Command) new PrintCommand("pressed6"));
      button7Left.whenPressed((Command) new PrintCommand("pressed7"));
      button8Left.whenPressed((Command) new PrintCommand("pressed8"));
      button9Left.whenPressed((Command) new PrintCommand("pressed9"));
      button10Left.whenPressed((Command) new PrintCommand("pressed10"));
      button11Left.whenPressed((Command) new PrintCommand("pressed11"));
      button12Left.whenPressed((Command) new PrintCommand("pressed12"));
      triggerRight.whenPressed((Command) new PrintCommand("pressedRight1"));

      Abutton.whenPressed((Command) new PrintCommand("PRESSED A"));
      Bbutton.whenPressed((Command) new PrintCommand("PRESSED B"));
      Xbutton.whenPressed((Command) new PrintCommand("PRESSED X"));
      Ybutton.whenPressed((Command) new PrintCommand("PRESSED Y"));
    }
    //reading the button states and for executing button comands
    public void pollButtons(){
      boolean isApressed = XBOX1.getRawButton(XBOXbuttonA);
      boolean isBpressed = XBOX1.getRawButton(XBOXbuttonB);
      boolean isXpressed = XBOX1.getRawButton(XBOXbuttonX);
      boolean isYpressed = XBOX1.getRawButton(XBOXbuttonY);

      if (isApressed) {
        m_intakeMotor.set(0.5);
      }
      else {
        m_intakeMotor.set(0);
      }
    }
}
