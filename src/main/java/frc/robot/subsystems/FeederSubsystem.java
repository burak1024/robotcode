package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class FeederSubsystem extends SubsystemBase {
    public final TalonFX feederMotor = new TalonFX(19);

        //The code that enables the Feeder to rotate clockwise
        public FeederSubsystem(){
            TalonFXConfiguration config = new TalonFXConfiguration();
            config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
            feederMotor.getConfigurator().apply(config);

        }
        
        
        
        
        public void stop(){
            feederMotor.stopMotor();
        }
}
