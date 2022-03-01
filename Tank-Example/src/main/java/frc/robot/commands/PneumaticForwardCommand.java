// package frc.robot.commands;

// import frc.robot.subsystems.Intake;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Robot;

// /** An example command that uses an example subsystem. */
// public class PneumaticForward extends CommandBase {
//   @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
//   private final Intake m_intake;

//   /**
//    * Creates a new ExampleCommand.
//    *
//    * @param subsystem The subsystem used by this command.
//    */
//   public PneumaticForward(Intake subsystem) {
//     m_intake = subsystem;
//     // Use addRequirements() here to declare subsystem dependencies.
//     addRequirements(m_intake);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {}

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//       Robot.m_intake.pneumaticForward();
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//       Robot.m_intake.pneumaticOff();
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
