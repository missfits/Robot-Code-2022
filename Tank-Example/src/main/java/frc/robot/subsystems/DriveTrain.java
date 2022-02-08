// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import static frc.robot.Constants.*;

public class DriveTrain extends SubsystemBase {
  private final MotorController m_leftMotor = new PWMSparkMax(kPwmID_MotorLeft1);
  private final MotorController m_rightMotor = new PWMSparkMax(kPwmID_MotorRight1);
  private final MotorController m_leftMotor2 = new PWMSparkMax(kPwmID_MotorLeft2);
  private final MotorController m_rightMotor2 = new PWMSparkMax(kPwmID_MotorRight2);
  private final MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_leftMotor, m_leftMotor2);
  private final MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_rightMotor, m_rightMotor2);
  public static DifferentialDrive m_robotDrive;

  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    m_robotDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);
    m_rightGroup.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}