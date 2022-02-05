

package frc.robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeMotorCommand;
import frc.robot.commands.ConveyorMotorCommand;
import frc.robot. commands.ShooterMotorCommand;

public class OI {
    // Create the joysticks and controller and buttons
    Joystick XBOX1 = new Joystick(0);
    public Joystick leftJoy = new Joystick(1);
    public Joystick rightJoy = new Joystick(2);
    

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


      //Abutton.whenPressed((Command) new PrintCommand("PRESSED A"));
      Abutton.whileHeld((Command) new IntakeMotorCommand(Robot.m_intake), true);
      Bbutton.whileHeld((Command) new ConveyorMotorCommand(Robot.m_conveyor), true);
      Xbutton.whileHeld((Command) new ShooterMotorCommand(Robot.m_shooter), true);
      Ybutton.whenPressed((Command) new PrintCommand("PRESSED Y"));
    }
}
