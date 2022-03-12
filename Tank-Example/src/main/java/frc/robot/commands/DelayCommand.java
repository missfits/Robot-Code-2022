package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DelayCommand extends CommandBase{
    private final double time;
    private final Timer timer = new Timer();

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }
    
    public DelayCommand(double time){
        // Use addRequirements() here to declare subsystem dependencies.
        this.time = time;
    }
    protected boolean isTimed() {
        return this.time > 0.0;
      }
      @Override
      public void execute() {
        System.out.println(timer.get());
      }
    
    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        if (isTimed() && timer.get() > this.time){
          return true;
        }
        return false;
      }
}
