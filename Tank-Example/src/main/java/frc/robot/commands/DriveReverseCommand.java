// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/** DriveReverseCommand uses DriveTrain subsystem. */
public class DriveReverseCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_driveTrain; //although this is not used the class cant function without it
  private final Timer timer = new Timer();
  private final double time;

  /**
   * Creates a new Driving Command
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveReverseCommand(DriveTrain subsystem) {
    // call the timed constructor with invalid 'time'
    this(-1, subsystem);
  }

  /**
   * Creates a new Timed Driving Command
   *
   * @param time The time this command runs (autonomous mode).
   * @param subsystem The subsystem used by this command.
   */
  public DriveReverseCommand(double time, DriveTrain subsystem){
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_driveTrain = subsystem;
    this.time = time;
  }

  //for timing
  protected boolean isTimed() {
    return this.time > 0.0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double timeSoFar = timer.get();
    //Creates ramp for motors
    double multiplier = (isTimed() && timeSoFar < 0.5)? 2 * timeSoFar : 1.0;
    DriveTrain.m_robotDrive.tankDrive(multiplier * 0.7, multiplier * 0.7);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      DriveTrain.m_robotDrive.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (isTimed() && timer.get() > this.time){
      return true;
    }
    return false;
  }
}
