// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

/** A command that turns the intake motor on that uses an the intake subsystem. */
public class IntakeReverseCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake m_intake;
  private final Timer timer = new Timer();
  private final double time;

  /**
   * Creates a new Timed Intake Command
   *
   * @param time The time this command runs (autonomous mode).
   * @param subsystem The subsystem used by this command.
   */
  public IntakeReverseCommand(Intake subsystem) {
    // call the timed constructor with invalid 'time'
    this(-1, subsystem);
  }

  public IntakeReverseCommand(double time, Intake subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_intake = subsystem;
    this.time = time;
  }

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
      if (!isFinished()){
        Robot.m_intake.intakeReverse();
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      Robot.m_intake.intakeOff();
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

