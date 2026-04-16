package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSubsystem extends SubsystemBase{
    public final TalonFX IndexerMotor=new TalonFX(18);
        //indexer'ın motorunun saat yönüne göre dönmesini sağlayan kod
        public IndexerSubsystem(){
            TalonFXConfiguration config = new TalonFXConfiguration();
            config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; 
            IndexerMotor.getConfigurator().apply(config);
        }
        //indexer'ı durduran kod
        public void stop(){
            IndexerMotor.stopMotor();
        }

}