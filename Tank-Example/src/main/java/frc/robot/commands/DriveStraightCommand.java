// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/** An example command that uses an example subsystem. */
public class DriveStraightCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_driveTrain;
  private final Timer timer = new Timer();
  private final int time;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveStraightCommand(DriveTrain subsystem) {
    m_driveTrain = subsystem;
    time = -1;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);
  }

  public DriveStraightCommand(int time, DriveTrain subsystem){
    m_driveTrain = subsystem;
    this.time = time;
    addRequirements(m_driveTrain);
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
    System.out.println(timer.get());
    double timeSoFar = timer.get();
    double multiplier = (timeSoFar < 0.5)? 2*timeSoFar : 1.0;
    DriveTrain.m_robotDrive.tankDrive(multiplier * -0.7, multiplier * -0.7);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      DriveTrain.m_robotDrive.stopMotor();
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
