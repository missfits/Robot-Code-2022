// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import static frc.robot.Constants.*;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  final MotorController m_intakeMotor = new PWMSparkMax(kPwmID_MotorIntake);
  //final Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);
  //final DoubleSolenoid exampleDoublePCM = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);

  /** Creates the subsystem that interfaces with the intake motor. */
  public Intake() {
    m_intakeMotor.setInverted(true);
  }

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
    m_intakeMotor.set(0.23);
  }

  //Turns off intake motor
  public void intakeOff(){
    m_intakeMotor.set(0);
  }

  // public void pneumaticForward(){
  //   exampleDoublePCM.set(kForward);
  // }

  // public void pneumaticReverse(){
  //   exampleDoublePCM.set(kReverse);
  // }

  // public void pneumaticOff(){
  //   exampleDoublePCM.set(kOff);
  // }
  //Safety
  // protected void finalize(){
  //     intakeOff();
  // }




}
