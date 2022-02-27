// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

/** An example command that uses an example subsystem. */
public class ShooterMotorCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;
  private final Timer timer = new Timer();
  private final int time;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterMotorCommand(Shooter subsystem) {
    m_shooter = subsystem;
    time = -1;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
  }
  public ShooterMotorCommand(int time, Shooter subsystem) {
    m_shooter = subsystem;
    this.time = time;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
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
      Robot.m_shooter.shooterOn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_shooter.shooterOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time > 0 && timer.get() > time){
      return true;
    }
    return false;
  }
}
