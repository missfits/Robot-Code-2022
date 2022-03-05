// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.commands.TeleopDriveTrainCommand;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.kauailabs.navx.frc.AHRS;
import static frc.robot.Constants.*;

public class DriveTrain extends SubsystemBase {
  

  private final CANSparkMax left1 = new CANSparkMax(kCANID_MotorLeft1, MotorType.kBrushless);
  private final CANSparkMax left2 = new CANSparkMax(kCANID_MotorLeft2, MotorType.kBrushless);
  private final CANSparkMax right1 = new CANSparkMax(kCANID_MotorRight1, MotorType.kBrushless);
  private final CANSparkMax right2 = new CANSparkMax(kCANID_MotorRight2, MotorType.kBrushless);

  public final SparkMaxRelativeEncoder left1Encoder = (SparkMaxRelativeEncoder) left1.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
  public final SparkMaxRelativeEncoder left2Encoder = (SparkMaxRelativeEncoder) left2.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
  public final SparkMaxRelativeEncoder right1Encoder = (SparkMaxRelativeEncoder) right1.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
  public final SparkMaxRelativeEncoder right2Encoder = (SparkMaxRelativeEncoder) right2.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);

  private final MotorController m_leftMotor = left1;
  private final MotorController m_leftMotor2 = left2;
  private final MotorController m_rightMotor = right1;
  private final MotorController m_rightMotor2 = right2;


  private final MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_leftMotor, m_leftMotor2);
  private final MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_rightMotor, m_rightMotor2);
  public static DifferentialDrive m_robotDrive;

  
  

  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    m_robotDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);
    m_leftGroup.setInverted(true);
    setDefaultCommand(new TeleopDriveTrainCommand(this));
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