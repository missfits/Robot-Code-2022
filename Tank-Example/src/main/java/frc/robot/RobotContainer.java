// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.*;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.OI;

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
  

// ENOCDER VALUE NOTES
      //40 is 6ft
      //35 out, 42 back
      //Other 40, 47
      //Taxi - 40

// intake ball (not from wall), drives to hub and shoots
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

  // drives only
  public static SequentialCommandGroup m_autoJustDrive = new SequentialCommandGroup(  
    new  DriveStraightCommand(1.0, Robot.m_driveTrain)
  );

  // waits then drives
  public static SequentialCommandGroup m_autoDelayDrive = new SequentialCommandGroup( 
    new  DriveStraightCommand(1.0, Robot.m_driveTrain),
    new  DelayCommand(5.0),
    new  DriveStraightCommand(1.0, Robot.m_driveTrain)
  );

  // drive + shoot
  public static SequentialCommandGroup m_autoBasicDrive = new SequentialCommandGroup(  
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter)),
      new  DriveStraightCommand(1.0, Robot.m_driveTrain)
  );

  //shoot from hub, drives out + intake, drive back, shoot
  public static SequentialCommandGroup m_autoShootFirst = new SequentialCommandGroup(
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake)),
    new  IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightDistance(40, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true),
      new IntakeMotorCommand(2.0, Robot.m_intake)),
      new DriveStraightDistance(47, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, false),
    new ShooterMotorCommand(0.5, Robot.m_shooter),
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake))
  );

  //shoot, drive (towards wall), intake, drive back, shoot
  public static SequentialCommandGroup m_autoTowardsWall = new SequentialCommandGroup(
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake)),
    new  IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightDistance(35, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true),
      new IntakeMotorCommand(2.5, Robot.m_intake)),
    new DriveStraightDistance(42, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, false),
    new ShooterMotorCommand(0.5, Robot.m_shooter),
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake))
  );

  //does nothing
  public static SequentialCommandGroup m_autoDoNothing = new SequentialCommandGroup();
  
  //shoots, taxis out
  public static SequentialCommandGroup m_autoTaxi = new SequentialCommandGroup(
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake)),
      new  IntakeDown(Robot.m_intake),
      new DriveStraightDistance(40, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true)
  );

  //shoots, drives to wall + intake, turns clockwise + drive + intake, turns to hub, drive + shoot 
  public static SequentialCommandGroup m_threeBallAuto = new SequentialCommandGroup(
    // shoots, gets ball 1
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake)),
    new  IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightDistance(35, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true), // NEED TO TEST ENCODER + TURN VALUES
      new IntakeMotorCommand(2.0, Robot.m_intake)),
    // gets ball 2
    new IntakeUp(Robot.m_intake),
    new Turn(100),
    new IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightDistance(40, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true),
      new IntakeMotorCommand(2.0, Robot.m_intake)),
    // shoots
    new IntakeUp(Robot.m_intake),
    new Turn(-60),
    new DriveStraightDistance(50, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, false),
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake))
  );

  //NEED TO DECREASE MOTOR RUN TIMES TO SAVE TIME IN AUTO 
  public static SequentialCommandGroup m_fiveBallAuto = new SequentialCommandGroup(
    // first 3 balls = identical to 3ball auto
    // shoots preload, gets ball 1
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake)),
    new  IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightDistance(35, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true), // NEED TO TEST ENCODER VALUES
      new IntakeMotorCommand(2.0, Robot.m_intake)),
    // gets ball 2
    new IntakeUp(Robot.m_intake),
    new Turn(110),  
    new IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightDistance(40, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true),   // true = away from hub
      new IntakeMotorCommand(2.0, Robot.m_intake)
    ),
    // shoots
    new IntakeUp(Robot.m_intake),
    new Turn(-60),
    new DriveStraightDistance(50, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, false),
    new ShooterMotorCommand(0.5, Robot.m_shooter),
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake)),
    // goes to terminal + intakes 2
    new DriveStraightDistance(30, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true),   // drives to edge of tarmac (ideally on corner)
    new Turn(70),
    new DriveStraightDistance(70, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true),
    new Turn(-45),  
    new IntakeDown(Robot.m_intake), 
    new IntakeMotorCommand(3.0, Robot.m_intake),
    // drive back to hub + shoot
    new IntakeUp(Robot.m_intake),
    new Turn(45),
    new DriveStraightDistance(70, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, false),
    new Turn(-70),
    new DriveStraightDistance(35, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, false),
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake))
  );

  // 3 ball auto + goes to terminal and intakes 2 (DOES NOT SHOOT LAST 2 BALLS)
  public static SequentialCommandGroup m_threeBallTerminal = new SequentialCommandGroup(
    // shoots preload, gets ball 1
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake)),
    new  IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightDistance(35, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true), // NEED TO TEST ENCODER VALUES
      new IntakeMotorCommand(2.0, Robot.m_intake)),
    // gets ball 2
    new IntakeUp(Robot.m_intake),
    new Turn(110),  
    new IntakeDown(Robot.m_intake),
    new ParallelCommandGroup(
      new DriveStraightDistance(40, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true),   // true = away from hub
      new IntakeMotorCommand(2.0, Robot.m_intake)
    ),
    // shoots
    new IntakeUp(Robot.m_intake),
    new Turn(-60),
    new DriveStraightDistance(50, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, false),
    new ShooterMotorCommand(0.5, Robot.m_shooter),
    new ParallelCommandGroup(
      new ConveyorMotorCommand(3.0, Robot.m_conveyor),
      new ShooterMotorCommand(3.0,Robot.m_shooter), 
      new IntakeMotorCommand(1.0, Robot.m_intake)),
    // goes to terminal + intakes 2
    new DriveStraightDistance(30, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true),   // drives to edge of tarmac (ideally on corner)
    new Turn(70),
    new DriveStraightDistance(70, Robot.m_driveTrain.left1Encoder, Robot.m_driveTrain.right1Encoder, Robot.m_driveTrain, true),
    new Turn(-45),  
    new IntakeDown(Robot.m_intake), 
    new IntakeMotorCommand(3.0, Robot.m_intake)
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
    //Poll Sendable Chooser
    return m_chooser.getSelected();
  }
}
