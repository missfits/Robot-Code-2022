// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Conveyor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

/** An example command that uses an example subsystem. */
public class ConveyorMotorCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Conveyor m_conveyor;
  private final Timer timer = new Timer();
  private final double time;

  
  public ConveyorMotorCommand(Conveyor subsystem) {
    // call the timed constructor with invalid 'time'
    this(-1, subsystem);
  }

     /**
   * Creates a new Timed Conveyor Command
   *
   * @param time The time this command runs (autonomous mode).
   * @param subsystem The subsystem used by this command.
   */

  public ConveyorMotorCommand(double time, Conveyor subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_conveyor = subsystem;
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
      m_conveyor.conveyorOn();
      System.out.println(timer.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      m_conveyor.conveyorOff();
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
