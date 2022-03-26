// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {
  //final MotorController m_conveyorMotor = new PWMSparkMax(kPwmID_MotorConveyor);
  final MotorController m_conveyorMotor = new CANSparkMax(kCANID_MotorConveyor, MotorType.kBrushless);
  
  /** Conveyor Subsystem */
  public Conveyor() {
    //m_conveyorMotor.setInverted(true);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  //set motor speed to maximum forward
  public void conveyorOn(){
    m_conveyorMotor.set(1);
  }

  //set motor speed to zero (motor off)
  public void conveyorOff(){
    m_conveyorMotor.set(0);
  }
}