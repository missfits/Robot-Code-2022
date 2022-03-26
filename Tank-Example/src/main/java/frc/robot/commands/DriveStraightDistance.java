// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import com.revrobotics.SparkMaxRelativeEncoder;
import java.lang.Math;

/** DriveStraightDistance command uses DriveTrain subsystem */
public class DriveStraightDistance extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_driveTrain; //although it is unused, the class cannot function without it
  private final Timer timer = new Timer();
  //create variable for the class
  private final double targetDistance;
  private double leftDistance;
  private double rightDistance;
  private SparkMaxRelativeEncoder _right;
  private SparkMaxRelativeEncoder _left;
  private boolean _isForward;
  /**
   * Creates a new Driving Command
   *
   * @param subsystem The subsystem used by this command.
   */
 
   //drive straight for specified distance
  public DriveStraightDistance(double distance, SparkMaxRelativeEncoder left, SparkMaxRelativeEncoder right, DriveTrain subsystem, boolean isForward){
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_driveTrain = subsystem;
    targetDistance = distance;
    _right = right;
    _left = left;
    leftDistance = _left.getPosition();
    rightDistance = _right.getPosition();
    _isForward = isForward;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    leftDistance = _left.getPosition();
    rightDistance = _right.getPosition();
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double timeSoFar = timer.get();
    //Creates ramp for motors
    double multiplier = (timeSoFar < 0.5)? 2 * timeSoFar : 1.0;
    if(_isForward == true){
        DriveTrain.m_robotDrive.tankDrive(multiplier * -0.7, multiplier * -0.7);
    }
    else{
        DriveTrain.m_robotDrive.tankDrive(multiplier * 0.7, multiplier * 0.7);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      DriveTrain.m_robotDrive.stopMotor();
  }

  // Returns true when the command should end.
  //command ends when target distance is traveled
  @Override
  public boolean isFinished() {
    double leftDistanceTraveled = Math.abs(_left.getPosition() - leftDistance);
    double rightDistanceTraveled = Math.abs(_right.getPosition() - rightDistance);
    if (leftDistanceTraveled > targetDistance &&  rightDistanceTraveled > targetDistance){
      return true;
    }
    return false;
  }
}
