// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  private final CANSparkMax m_climberMotor1 = new CANSparkMax(kCANID_MotorClimber1, MotorType.kBrushless);
  //private final MotorController m_climberMotor2 = new CANSparkMax(kCANID_MotorClimber2, MotorType.kBrushless);
  //private final MotorControllerGroup m_climberGroup = new MotorControllerGroup(m_climberMotor1, m_climberMotor2);
  public final SparkMaxRelativeEncoder climberEncoder = (SparkMaxRelativeEncoder) m_climberMotor1.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);

/** Creates a new ExampleSubsystem. */
  public Climber() {}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void climberUp(){
    if(climberEncoder.getPosition() < -20){
      m_climberMotor1.set(0.5);
    }
    else{
      m_climberMotor1.set(0);
    }
  }

  public void climberDown(){
    if(climberEncoder.getPosition() > -112){
      m_climberMotor1.set(-0.5);
    }
    else{
      m_climberMotor1.set(0);
    }
  } 
  public void climberOff(){
    m_climberMotor1.set(0);
  }

  public void climberLow(){
    m_climberMotor1.set(0.1);
  }

}