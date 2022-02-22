// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import static frc.robot.Constants.*;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  //final MotorController m_shooterMotor = new PWMSparkMax(kPwmID_MotorShooter);
  final MotorController m_shooterMotorTest = new PWMSparkMax(kPwmID_MotorShooterTest);

  /** Creates the subsystem that interfaces with the intake motor. */
  public Shooter() {}
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  // Turns on intake motor
  // public void shooterOn(){
  //   m_shooterMotor.set(0.1);
  // }

  //Turns off intake motor
  public void shooterOff(){
    m_shooterMotorTest.set(0);
  }

  //Safety
  protected void finalize(){
      shooterOff();
  }


}
