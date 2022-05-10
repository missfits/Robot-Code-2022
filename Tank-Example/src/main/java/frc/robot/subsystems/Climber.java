// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  private final CANSparkMax m_climberMotor1 = new CANSparkMax(kCANID_MotorClimber1, MotorType.kBrushless);
  public final SparkMaxRelativeEncoder climberEncoder = (SparkMaxRelativeEncoder) m_climberMotor1.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);

  private double pFac = 0.1; //random pFac, will have to tune
  private double iFac = 0.0; //leave this at 0.0,usually
  private double dFac = 0.0; //most likely can leave at 0.0

  private double m_climberUpPos = 100.0; //just turned hard coded values into variables
  private double m_climberDownPos = 0.0;

  private final PIDController m_climberPID = new PIDController(pFac, iFac, dFac);

  private double m_currPos = 0.0;
  private double m_error = 0.0;

  private final double maxSpeed = 0.5; //can potentially increase this


/** Climber Subsystem */
  public Climber() {

    m_climberPID.disableContinuousInput(); //disable this because we don't want max encoder to be equal to min encoder
    m_climberPID.setTolerance(5.0); //this is just a random number of encoder ticks, might have to tune

    SmartDashboard.putNumber("pFac", pFac);
    SmartDashboard.putNumber("iFac", iFac);
    SmartDashboard.putNumber("dFac", dFac);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_currPos = climberEncoder.getPosition();
    m_error = m_climberPID.getPositionError();
    SmartDashboard.putNumber("climber position", m_currPos);
    SmartDashboard.putNumber("climber error", m_error);

    pFac = SmartDashboard.getNumber("pFac", 0.1);
    iFac = SmartDashboard.getNumber("iFac", 0.0);
    dFac = SmartDashboard.getNumber("dFac", 0.0);
    
    SmartDashboard.putBoolean("at setpoint", m_climberPID.atSetpoint());

    m_climberPID.setP(pFac);
    m_climberPID.setI(iFac);
    m_climberPID.setD(dFac);


  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  
  
  public double clamp(double in, double min, double max) {
    //using this function to make sure that we don't spin the motor way too fast
    if (in < min) {
      return min;
    }
    if (in > max) {
      return max;
    }
    return in;
  }
  
  //extends climber to full length determined by encoder value (continues motor speed until the maximum encoder value is reached)
  public void climberUp(){
    if(climberEncoder.getPosition() < 100){
      m_climberMotor1.set(0.5);
    }
    else{
      m_climberMotor1.set(0);
    }
  }

  //retracts climber to shortest length determined by encoder value (reverses motor speed until the minimum encoder value is reached)
  public void climberDown(){
    if(climberEncoder.getPosition() > 0){
      m_climberMotor1.set(-0.5);
    }
    else{
      m_climberMotor1.set(0);
    }
  } 

  public void climberUpPID(){
    m_climberMotor1.set(clamp(m_climberPID.calculate(m_climberUpPos, m_currPos), -maxSpeed, maxSpeed));
  }

  public void climberDownPID(){
    m_climberMotor1.set(clamp(m_climberPID.calculate(m_climberDownPos, m_currPos), -maxSpeed, maxSpeed));
  }

  //set the climber speed to zero (the climber is off)
  public void climberOff(){
    m_climberMotor1.set(0);
    m_climberPID.setSetpoint(m_currPos); //adding this so the PID doesn't try to run after you kill motor output
  }

  //set the climber speed at a slower speed than normal
  public void climberLow(){
    m_climberMotor1.set(0.1);
  }

}