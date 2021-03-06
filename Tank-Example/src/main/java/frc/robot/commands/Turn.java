/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/*  TurnCommand uses DriveTrain subsystem  */
public class Turn extends CommandBase {
    private double angle;
    public Turn(double a) {
        angle = a;
        // Use requires() here to declare subsystem dependencies
       }
    
 // Called just before this Command runs the first time
 @Override
 public void initialize() {
   
 }

 // Called repeatedly when this Command is scheduled to run
 @Override
 public void execute() {
    if(angle > 0){
        DriveTrain.m_robotDrive.tankDrive(1.0, -1.0);
    } else {
        DriveTrain.m_robotDrive.tankDrive(-1.0, 1.0);
    }
    
 } 

 // Make this return true when this Command no longer needs to run execute()

 @Override
 public boolean isFinished() { 
  return Math.abs(Robot.m_driveTrain.getAngle()) >= Math.abs(angle);
  }

 // Called once after isFinished returns true
 @Override
public void end(boolean interrupted) {
    DriveTrain.m_robotDrive.stopMotor();
   }

//  // Called once after isFinished returns true
//  @Override
//  public void end(boolean interrupted) {
//     DriveTrain.m_robotDrive.stopMotor();
//    }
}