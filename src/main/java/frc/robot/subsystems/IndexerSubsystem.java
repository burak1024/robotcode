package frc.robot.subsystems;

import static frc.robot.Constants.INDEXER_MOTOR_ID;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSubsystem extends SubsystemBase{
    public final TalonFX IndexerMotor=new TalonFX(INDEXER_MOTOR_ID);
        
        //The code that enables the turret to rotate clockwise
        public IndexerSubsystem(){
            TalonFXConfiguration config = new TalonFXConfiguration();
            config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive; 
            IndexerMotor.getConfigurator().apply(config);
        }

         //The code that stops Indexer
         public void stop() {
            IndexerMotor.stopMotor();   
         }

}