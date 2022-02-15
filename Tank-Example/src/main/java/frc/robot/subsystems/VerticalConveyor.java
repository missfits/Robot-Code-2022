// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VerticalConveyor extends SubsystemBase {
  final MotorController m_verticalConveyorMotor = new PWMSparkMax(kPwmID_MotorVerticalConveyor);
  
  /** Creates a new ExampleSubsystem. */
  public VerticalConveyor() {}
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void verticalConveyorOn(){
    m_verticalConveyorMotor.set(0.5);
  }
  public void verticalConveyorOff(){
    m_verticalConveyorMotor.set(0);
  }
}