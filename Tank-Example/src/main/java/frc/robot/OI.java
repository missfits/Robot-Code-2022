

package frc.robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeMotorCommand;
import frc.robot.commands.ClimberMotorCommand;
import frc.robot.commands.ConveyorMotorCommand;
import frc.robot. commands.ShooterMotorCommand;

public class OI {
    // Create the joysticks and XBOX controller
    Joystick XBOX1 = new Joystick(kControllerID_Xbox);
    public Joystick leftJoy = new Joystick(kControllerID_DriveLeft);
    public Joystick rightJoy = new Joystick(kControllerID_DriveRight);

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
      Xbutton.whileHeld((Command) new ShooterMotorCommand(Robot.m_shooter), true);
      Ybutton.whileHeld((Command) new ClimberMotorCommand(Robot.m_climber), true);
    }
}
