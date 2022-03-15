

package frc.robot;
import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
    Button  Backbutton = new JoystickButton(XBOX1, kButtonID_XboxBack);
    Button  Startbutton = new JoystickButton(XBOX1, kButtonID_XboxStart);
    Button  RBbutton = new JoystickButton(XBOX1, kButtonID_XboxRB);

    public static SequentialCommandGroup shootCommand = new SequentialCommandGroup(
      new ShooterMotorCommand(0.5, Robot.m_shooter),
      new ParallelCommandGroup(
      new ConveyorMotorCommand(Robot.m_conveyor),
      new ShooterMotorCommand(Robot.m_shooter))
      );
    
      //for the purpuses of testing why the robot keeps disabling
    public static ParallelCommandGroup allCommands = new ParallelCommandGroup (
      shootCommand, new IntakeMotorCommand(Robot.m_intake)
    );
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

      //Bbutton.whileHeld((Command) new ConveyorMotorCommand(Robot.m_conveyor), true);
  
      //RBbutton.whileHeld((Command) new ShooterMotorCommand(Robot.m_shooter), true);
     
      //LBbutton.whileHeld((Command) new Turn(180), true);
  

      //triggerLeft.whenPressed((Command) new PrintCommand(Double.toString(Robot.m_vision.getDistance())));
      triggerRight.whileHeld((Command) new DriveStraightCommand(Robot.m_driveTrain), true);
      triggerLeft.whileHeld((Command) new DriveReverseCommand(Robot.m_driveTrain), true);
      
      //buttons according to Elizabeth
      //intake motor
      Abutton.whileHeld((Command) new IntakeMotorCommand(Robot.m_intake), true);
      
      //shooting motor + vertical conveyor
      Bbutton.whileHeld((Command) new ParallelCommandGroup(
        new ConveyorMotorCommand(3.0, Robot.m_conveyor),
        new ShooterMotorCommand(3.0,Robot.m_shooter)), true);
      
      //reverse intake
      Startbutton.whileHeld((Command) new IntakeReverseCommand(Robot.m_intake), true);
      
      //climber up
      LBbutton.whileHeld((Command) new ClimberUpCommand(Robot.m_climber), true);
      
      //climber down
      RBbutton.whileHeld((Command) new ClimberDownCommand(Robot.m_climber), true);
      
      //intake down
      Xbutton.whileHeld((Command) new IntakeDown(Robot.m_intake), true);
      
      //intake up
      Ybutton.whileHeld((Command) new IntakeUp(Robot.m_intake), true);
      

      button2Left.whileHeld((Command) new IntakeMotorCommand(Robot.m_intake), true);
      button3Left.whileHeld((Command) new ParallelCommandGroup(
        new ConveyorMotorCommand(3.0, Robot.m_conveyor),
        new ShooterMotorCommand(3.0,Robot.m_shooter)), true);
      button4Left.whileHeld((Command) new IntakeReverseCommand(Robot.m_intake), true);
      button5Left.whileHeld((Command) new ClimberUpCommand(Robot.m_climber), true);
      button6Left.whileHeld((Command) new ClimberDownCommand(Robot.m_climber), true);
      button7Left.whileHeld((Command) new IntakeDown(Robot.m_intake), true);
      button8Left.whileHeld((Command) new IntakeUp(Robot.m_intake), true);
      button9Left.whileHeld((Command) new ParallelCommandGroup(
        new ConveyorMotorCommand(3.0, Robot.m_conveyor),
        new setSpeedShooter(0.4, 3.0, Robot.m_shooter)), true);
      button10Left.whileHeld((Command) new Turn(180), true);
    }
}
 