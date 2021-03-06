// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.*;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  //initalize the motor which powers intake belts
  final MotorController m_intakeMotor = new CANSparkMax(kCANID_MotorIntake, MotorType.kBrushless);

  //initalize compressor and solenoid for retracting and deploying the intake arm
  final Compressor m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);
  final DoubleSolenoid exampleDoublePCM = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 7, 6);

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
    m_intakeMotor.set(1.0);
  }

  //runs intake motor in reverse
  public void intakeReverse(){
    m_intakeMotor.set(-1.0);
  }

  //Turns off intake motor
  public void intakeOff(){
    m_intakeMotor.set(0);
  }

  //runs the pneumatics forward
  public void pneumaticForward(){
    exampleDoublePCM.set(kForward);
  }

  //runs the pneumatics in reverse
  public void pneumaticReverse(){
    exampleDoublePCM.set(kReverse);
  }

  //turns off the pneumatics
  public void pneumaticOff(){
    exampleDoublePCM.set(kOff);
  }

  // public boolean compressorOn(){
  //   if(m_compressor.getPressureSwitchValue()){
  //     return false;
  //   }
  //   return true;
  // }
  //Safety
  protected void finalize(){
      intakeOff();
  }
}
