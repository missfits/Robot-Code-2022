package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

/** IntakeDown command uses Intake subsystem */
public class IntakeDown extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake m_intake;
  private final Timer timer = new Timer();
  private final double time;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeDown(Intake subsystem) {
    m_intake = subsystem;
    time = 0.3;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake);
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
      Robot.m_intake.pneumaticForward();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      Robot.m_intake.pneumaticOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (isTimed() && timer.get() > time){
      return true;
    }
    return false;
  }
}
