// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /** Creates the subsystem that interfaces with the intake motor. */
  public Intake() {}
  final MotorController m_intakeMotor = new PWMSparkMax(kPwmID_MotorIntake);
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  // Turns on intake motor
  public void intakeOn(){
    m_intakeMotor.set(0.5);
  }

  //Turns off intake motor
  public void intakeOff(){
    m_intakeMotor.set(0);
  }

  //Safety
  protected void finalize(){
      intakeOff();
  }


}
