

package frc.robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;


public class OI {
    // Create the joysticks and XBOX controller
    Joystick XBOX1 = new Joystick(kControllerID_Xbox);
    public Joystick leftJoy = new Joystick(kControllerID_DriveLeft);
    public Joystick rightJoy = new Joystick(kControllerID_DriveRight);
    DoubleSolenoid exampleDoublePCM = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
    DoubleSolenoid exampleDoublePH = new DoubleSolenoid(9, PneumaticsModuleType.REVPH, 4, 5);


    //creating buttons for the joysticks
    Button triggerLeft = new JoystickButton(leftJoy, kButtonID_Drive1);
    Button  button2Left = new JoystickButton(leftJoy, kButtonID_Drive2);
    Button  button3Left = new JoystickButton(leftJoy, kButtonID_Drive3);
    Button  button4Left = new JoystickButton(leftJoy, kButtonID_Drive4);
    Button  button5Left = new JoystickButton(leftJoy, kButtonID_Drive5);
    Button  button6Left = new JoystickButton(leftJoy, kButtonID_Drive6);
    Button  button7Left = new JoystickButton(leftJoy, kButtonID_Drive7);
    Button  button8Left = new JoystickButton(leftJoy, kButtonID_Drive8);
    Button  button9Left = new JoystickButton(leftJoy, kButtonID_Drive9);
    Button  button10Left = new JoystickButton(leftJoy, kButtonID_Drive10);
    Button  button11Left = new JoystickButton(leftJoy, kButtonID_Drive11);
    Button  button12Left = new JoystickButton(leftJoy, kButtonID_Drive12);
    Button  triggerRight = new JoystickButton(rightJoy, kButtonID_Drive1);

    //creating buttons for the XBOX
    Button  Abutton = new JoystickButton(XBOX1, kButtonID_XboxA);
    Button  Bbutton = new JoystickButton(XBOX1, kButtonID_XboxB);
    Button  Xbutton = new JoystickButton(XBOX1, kButtonID_XboxX);
    Button  Ybutton = new JoystickButton(XBOX1, kButtonID_XboxY);
    Button  LBbutton = new JoystickButton(XBOX1, kButtonID_XboxLB);
    Button  RBbutton = new JoystickButton(XBOX1, kButtonID_XboxRB);

    //public Button leftTrigger = new buttonText();
    public OI(){
      //to test the buttons
      /*
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
      */

      /*Example of a button command which causes something to print when the button is pressed
      Abutton.whenPressed((Command) new PrintCommand("PRESSED A"));
      */

      //connecting buttons to commands to coninuously execute when the button is held down

      Abutton.whileHeld((Command) new IntakeMotorCommand(Robot.m_intake), true);
      Bbutton.whileHeld((Command) new ConveyorMotorCommand(Robot.m_conveyor), true);
      RBbutton.whileHeld((Command) new ShooterMotorCommand(Robot.m_shooter), true);
      LBbutton.whileHeld((Command) new ClimberMotorCommand(Robot.m_climber), true);
      //Xbutton.whileHeld((Command) new PneumaticReverse(Robot.m_intake), true);
      //Ybutton.whileHeld((Command) new PneumaticForward(Robot.m_intake), true);

      //triggerLeft.whenPressed((Command) new PrintCommand(Double.toString(Robot.m_vision.getDistance())));
      triggerRight.whileHeld((Command) new DriveStraight(Robot.m_driveTrain), true);

    }
}
 