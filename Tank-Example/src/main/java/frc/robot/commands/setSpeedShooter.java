// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

/** setSpeedShooter command uses Shooter subsystem */
public class setSpeedShooter extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;
  private final Timer timer = new Timer();
  private final double time;
  private final double speed;

  /**
   * Creates a new ShooterCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public setSpeedShooter(Double speed, Shooter subsystem) {
    this(speed, -1, subsystem);
  }

   /**
   * Creates a new Timed Shooter Command
   *
   * @param time The time this command runs (autonomous mode).
   * @param subsystem The subsystem used by this command.
   */
  
  public setSpeedShooter(double Speed, double time, Shooter subsystem) {
    m_shooter = subsystem;
    this.time = time;
    speed = Speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
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
      Robot.m_shooter.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_shooter.shooterOff();
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

