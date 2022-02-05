// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;


public class DriveTrain extends SubsystemBase {
  private final MotorController m_leftMotor = new PWMSparkMax(0);
  private final MotorController m_rightMotor = new PWMSparkMax(1);
  public static DifferentialDrive m_robotDrive;
  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    m_rightMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    m_robotDrive.tankDrive(Robot.oi.leftJoy.getY(), Robot.oi.rightJoy.getY());
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}