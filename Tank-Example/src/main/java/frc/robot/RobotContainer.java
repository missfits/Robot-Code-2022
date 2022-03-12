// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.ConveyorMotorCommand;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.commands.DriveReverseCommand;
import frc.robot.commands.DelayCommand;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeMotorCommand;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.ShooterMotorCommand;
import frc.robot.commands.Turn;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.OI;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public SendableChooser<Command> m_chooser = new SendableChooser<>();
  /*
  public static SequentialCommandGroup m_autoCommand1 = new SequentialCommandGroup(
      new DriveStraightCommand(2.0, Robot.m_driveTrain),
      new ShooterMotorCommand(1.0, Robot.m_shooter));
  
  public static SequentialCommandGroup m_autoCommand2 = new SequentialCommandGroup(
    new DriveStraightCommand(2.0, Robot.m_driveTrain),
    new IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightCommand(0.5, Robot.m_driveTrain),
      new IntakeMotorCommand(3.0, Robot.m_intake),
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0, Robot.m_shooter)),
    new IntakeUp(Robot.m_intake));
    
  public static Command m_autoCommand3 = new IntakeMotorCommand(1.0, Robot.m_intake);

  public static SequentialCommandGroup m_autoCommand4 = new SequentialCommandGroup(
    new DriveStraightCommand(1.0, Robot.m_driveTrain),
    new IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightCommand(1.0, Robot.m_driveTrain),
      new IntakeMotorCommand(1.0, Robot.m_intake)),
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0, Robot.m_shooter)));

  public static SequentialCommandGroup m_autoCommand5 = new SequentialCommandGroup(
    new DriveStraightCommand(1.0, Robot.m_driveTrain),
    new Turn(180),
    new DriveStraightCommand(1.0, Robot.m_driveTrain));
*/
  public static SequentialCommandGroup m_autoStraightDriveIntake = new SequentialCommandGroup(
    new  IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightCommand(1.0, Robot.m_driveTrain),
      new IntakeMotorCommand(1.0, Robot.m_intake)),
    new DriveReverseCommand(0.5, Robot.m_driveTrain),
    new ShooterMotorCommand(0.5, Robot.m_shooter),
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake))
  );
  public static SequentialCommandGroup m_autoJustDrive = new SequentialCommandGroup(
    new  DriveStraightCommand(1.0, Robot.m_driveTrain)
    );
  public static SequentialCommandGroup m_autoDelayDrive = new SequentialCommandGroup(
    new  DriveStraightCommand(1.0, Robot.m_driveTrain),
    new  DelayCommand(5.0),
    new  DriveStraightCommand(1.0, Robot.m_driveTrain)
    );
  public static SequentialCommandGroup m_autoBasicDrive = new SequentialCommandGroup(
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter)),
      new  DriveStraightCommand(1.0, Robot.m_driveTrain)
  );
  public static SequentialCommandGroup m_autoShootFirst = new SequentialCommandGroup(
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake)),
      new  IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightCommand(1.5, Robot.m_driveTrain),
      new IntakeMotorCommand(1.5, Robot.m_intake)),
    new DriveReverseCommand(1.0, Robot.m_driveTrain),
    new ShooterMotorCommand(0.5, Robot.m_shooter),
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake))
  );
    
  // The container for the robot. Contains subsystems, OI devices, and commands. 
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    // An ExampleCommand will run in autonomous
    //return m_autoCommand1;
    //Poll Sendable Chooser
    return m_chooser.getSelected();
  }
}
