// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
//import java.lang.Object;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.cscore.CvSink;
// import edu.wpi.first.cscore.CvSource;
// import edu.wpi.first.cscore.MjpegServer;
// import edu.wpi.first.cscore.UsbCamera;
// import edu.wpi.first.cscore.VideoMode.PixelFormat;
// import edu.wpi.first.wpilibj.TimedRobot;
//import java.util.function.BooleanSupplier;
//import edu.wpi.first.wpilibj2.command.button.Button;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.math.controller.PIDController;
//import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.commands.*;
import frc.robot.subsystems.*;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
// Add low power mode for climber at 0.1
public class Robot extends TimedRobot {
  //initalize autonomousCommands, RobotContainer, OI, and Timer
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer = new RobotContainer();
  public Timer t = new Timer();
  
  //creating all the motors 
  public static Intake m_intake = new Intake();
  public static Conveyor m_conveyor = new Conveyor();
  public static Climber m_climber = new Climber();
  public static Shooter m_shooter = new Shooter();
  public static DriveTrain m_driveTrain = new DriveTrain();

  public static OI oi = new OI();
  
  //private final PIDController PID = new PIDController(0.3, 0, 0); //unused PID Controller

  //set encoder inital values to zero
  public int rightEncoderStart = 0;
  public int leftEncoderStart = 0;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our autonomous chooser on the dashboard.

  
    // Sendable Chooser for autonomous - allows us to select a routine
    // Add commands to the autonomous command chooser


    //adding options for autonomous viewable on the dashboard of driverstation
    m_robotContainer.m_chooser.addOption("Two Ball Auto (Shoot Last)", RobotContainer.m_autoStraightDriveIntake); // intake ball, drives, shoots
    m_robotContainer.m_chooser.addOption("Drive Forward", RobotContainer.m_autoJustDrive); // drives only
    m_robotContainer.m_chooser.addOption("Delayed Drive", RobotContainer.m_autoDelayDrive);  // waits then drives
    m_robotContainer.m_chooser.addOption("Shoot then Drive", RobotContainer.m_autoBasicDrive); // drive + shoot
    m_robotContainer.m_chooser.addOption("Do Nothing", RobotContainer.m_autoDoNothing); //does nothing
    m_robotContainer.m_chooser.addOption("Taxi", RobotContainer.m_autoTaxi); //shoots, taxis out
    m_robotContainer.m_chooser.addOption("Towards Wall", RobotContainer.m_autoTowardsWall); //shoot, drive (twards wall), intake, drive back, shoot
    m_robotContainer.m_chooser.addOption("3 Ball Auto", RobotContainer.m_threeBallAuto); // shoots, gets ball 1, gets ball 2, shoots
    m_robotContainer.m_chooser.addOption("5 Ball Auto", RobotContainer.m_fiveBallAuto); //shoots preload, gets ball 1, gets ball 2, shoots, goes to terminal + intakes 2, drive back to hub + shoot
    m_robotContainer.m_chooser.addOption("3 Ball terminal", RobotContainer.m_threeBallTerminal); // shoots preload, gets ball 1, gets ball 2, shoots, goes to terminal + intakes 2
    //default option when code is deployed will be the one that runs 
    m_robotContainer.m_chooser.addOption("Two Ball Auto (Shoot First)", RobotContainer.m_autoShootFirst);   //(RELIABLE TWO BALL AUTO) shoot, drive, intake, drive back, shoot
    
    // Put the chooser on the dashboard
    SmartDashboard.putData(m_robotContainer.m_chooser);
    CameraServer.startAutomaticCapture();
    //CameraServer.startAutomaticCapture();


    SmartDashboard.putNumber("Shooter Speed", m_shooter.getSpeed());
    SmartDashboard.putNumber("Left Encoder", m_driveTrain.left1Encoder.getPosition());
    SmartDashboard.putNumber("Right Encoder", m_driveTrain.right1Encoder.getPosition());
    SmartDashboard.putNumber("Climber Encoder", m_climber.climberEncoder.getPosition());
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Shooter Speed", m_shooter.getSpeed());
    SmartDashboard.putNumber("Left Encoder", m_driveTrain.left1Encoder.getPosition());
    SmartDashboard.putNumber("Right Encoder", m_driveTrain.right1Encoder.getPosition());
    SmartDashboard.putNumber("Climber Encoder", m_climber.climberEncoder.getPosition());
    SmartDashboard.putData(m_robotContainer.m_chooser);
  }
//example edit for stella! <3
  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    t.reset();
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    t.start();
    m_driveTrain.left1Encoder.setPosition(0);
    m_driveTrain.left2Encoder.setPosition(0);
    m_driveTrain.right1Encoder.setPosition(0);
    m_driveTrain.right2Encoder.setPosition(0);

    // schedule the autonomous command
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Shooter Speed", m_shooter.getSpeed());
    SmartDashboard.putNumber("Left Encoder", m_driveTrain.left1Encoder.getPosition());
    SmartDashboard.putNumber("Right Encoder", m_driveTrain.right1Encoder.getPosition());
    SmartDashboard.putNumber("Climber Encoder", m_climber.climberEncoder.getPosition());
    //m_driveTrain.m_robotDrive.tankDrive(-0.5, -0.5);
    //DriveTrain.m_robotDrive.tankDrive(PID.calculate(m_driveTrain.left1Encoder.getPosition(), 2), PID.calculate(-(m_driveTrain.right1Encoder.getPosition()), 2));
    //System.out.println("("+m_driveTrain.left1Encoder.getVelocity()+","+(-(m_driveTrain.right1Encoder.getVelocity()))+")");
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    m_driveTrain.left1Encoder.setPosition(0);
    m_driveTrain.left2Encoder.setPosition(0);
    m_driveTrain.right1Encoder.setPosition(0);
    m_driveTrain.right2Encoder.setPosition(0);
  }  

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Shooter Speed", m_shooter.getSpeed());
    SmartDashboard.putNumber("Left Encoder", m_driveTrain.left1Encoder.getPosition());
    SmartDashboard.putNumber("Right Encoder", m_driveTrain.right1Encoder.getPosition());
    SmartDashboard.putNumber("Climber Encoder", m_climber.climberEncoder.getPosition());
    //SmartDashboard.putBoolean("Compressor On", m_intake.compressorOn());
    //System.out.println("("+m_driveTrain.left1Encoder.getPosition()+","+(-(m_driveTrain.right1Encoder.getPosition()))+")");
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}



