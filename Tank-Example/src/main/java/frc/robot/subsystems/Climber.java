// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private final MotorController m_climberMotor1 = new PWMSparkMax(kPwmID_MotorClimber1);
  private final MotorController m_climberMotor2 = new PWMSparkMax(kPwmID_MotorClimber2);
  private final MotorControllerGroup m_climberGroup = new MotorControllerGroup(m_climberMotor1, m_climberMotor2);
  
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

  public void climberOn(){
      m_climberGroup.set(0.5);
  }
  public void climberOff(){
    m_climberGroup.set(0);
  }
}