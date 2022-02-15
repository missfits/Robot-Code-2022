// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.VerticalConveyor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

/** An example command that uses an example subsystem. */
public class VerticalConveyorMotorCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final VerticalConveyor m_verticalConveyor;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public VerticalConveyorMotorCommand(VerticalConveyor subsystem) {
    m_verticalConveyor = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_verticalConveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      Robot.m_verticalConveyor.verticalConveyorOn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      Robot.m_verticalConveyor.verticalConveyorOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
