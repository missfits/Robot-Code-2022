// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import java.lang.Object;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import java.util.function.BooleanSupplier;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
// Add low power mode for climber at 0.1
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  
  //creating all the motors 
  public static Intake m_intake = new Intake();
  public static Conveyor m_conveyor = new Conveyor();
  public static Climber m_climber = new Climber();
  public static Shooter m_shooter = new Shooter();
  public static DriveTrain m_driveTrain = new DriveTrain();
  private RobotContainer m_robotContainer;
  public static OI oi = new OI();
  private final PIDController PID = new PIDController(0.3, 0, 0);
  public Timer t = new Timer();
  


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    m_robotContainer = new RobotContainer();
    // autonomous chooser on the dashboard.
    // Sendable Chooser for autonomous - allows us to select a routine
    // Add commands to the autonomous command chooser
    /*
    m_robotContainer.m_chooser.setDefaultOption("testAuto1", RobotContainer.m_autoCommand1);
    m_robotContainer.m_chooser.addOption("testAuto2", RobotContainer.m_autoCommand2);
    m_robotContainer.m_chooser.addOption("testAuto3", RobotContainer.m_autoCommand3);
    m_robotContainer.m_chooser.addOption("testAuto4", RobotContainer.m_autoCommand4);
    m_robotContainer.m_chooser.addOption("testAuto5", RobotContainer.m_autoCommand5);
    */
    m_robotContainer.m_chooser.setDefaultOption("Two Ball Auto (Shoot Last)", RobotContainer.m_autoStraightDriveIntake);
    m_robotContainer.m_chooser.setDefaultOption("Drive Forward", RobotContainer.m_autoJustDrive);
    m_robotContainer.m_chooser.setDefaultOption("Delayed Drive", RobotContainer.m_autoDelayDrive);
    m_robotContainer.m_chooser.setDefaultOption("Shoot then Drive", RobotContainer.m_autoBasicDrive);
    m_robotContainer.m_chooser.setDefaultOption("Two Ball Auto (Shoot First)", RobotContainer.m_autoShootFirst);

    // Put the chooser on the dashboard
    SmartDashboard.putData(m_robotContainer.m_chooser);
    CameraServer.startAutomaticCapture();
    CameraServer.startAutomaticCapture();
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
  }

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
    // schedule the autonomous command
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
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



