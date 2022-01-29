// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
//package edu.wpi.first.wpilibj.buttons;
import java.lang.Object;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
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

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private DifferentialDrive m_robotDrive;
  //private Joystick m_leftStick;
  //private Joystick m_rightStick;
  private RobotContainer m_robotContainer;
  public OI oi = new OI();
  private final MotorController m_leftMotor = new PWMSparkMax(0);
  private final MotorController m_rightMotor = new PWMSparkMax(1);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    //m_leftStick = new Joystick(1);
    //m_rightStick = new Joystick(2);
    m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    m_rightMotor.setInverted(true);
    
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
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.tankDrive(oi.leftJoy.getY(), oi.rightJoy.getY());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    oi.pollButtons();
  }

  // public class OI {
  //   // Create the joystick and the 8 buttons on it
  //   Joystick leftJoy = new Joystick(1);
  //   Button button1 = new JoystickButton(leftJoy, 1);
  //         //  button2 = new JoystickButton(leftJoy, 2),
  //         //  button3 = new JoystickButton(leftJoy, 3),
  //         //  button4 = new JoystickButton(leftJoy, 4),
  //         //  button5 = new JoystickButton(leftJoy, 5),
  //         //  button6 = new JoystickButton(leftJoy, 6),
  //         //  button7 = new JoystickButton(leftJoy, 7),
  //         //  button8 = new JoystickButton(leftJoy, 8);

  //   //public Button leftTrigger = new buttonText();
  //   public OI(){
  //     button1.whenPressed((Command) new buttonText());
  //   }
  // }
}



