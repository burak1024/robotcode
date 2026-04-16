package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class TurretSubsystem extends SubsystemBase{
    public final TalonFX turretMotor =new TalonFX(17);
    
    public TurretSubsystem(){

        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; 
        turretMotor.getConfigurator().apply(config);
    }

    //The code that stops the turret
    public void stop(){
        turretMotor.stopMotor();
    }
}