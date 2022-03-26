package frc.robot.subsystems;
//import static frc.robot.Constants.*;
//import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;

public class Vision extends SubsystemBase{
  //initalize variable for input from the ultrasonic sensor
    private AnalogInput ultrasonic = new AnalogInput(0);

    public Vision(){

    }

    //returns distance value from the ultrasonic sensor data
    public double getDistance(){
        return (ultrasonic.getAverageValue()*5)/100.0;      // change to 20.0??
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
