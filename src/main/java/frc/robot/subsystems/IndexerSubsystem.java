package frc.robot.subsystems;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSubsystem extends SubsystemBase{
    public final TalonFX IndexerMotor=new TalonFX(18);

    public IndexerSubsystem(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; 
        IndexerMotor.getConfigurator().apply(config);
    }

    public void stop(){
        IndexerMotor.stopMotor();
    }

}