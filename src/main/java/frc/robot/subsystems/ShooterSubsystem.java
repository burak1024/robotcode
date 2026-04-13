package frc.robot.subsystems;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class ShooterSubsystem extends SubsystemBase{
private final TalonFX topMotor = new TalonFX(15);
private final TalonFX bottomMotor= new TalonFX(16);

private final VelocityVoltage m_velocityControl = new VelocityVoltage(0);
public ShooterSubsystem(){
TalonFXConfiguration config = new TalonFXConfiguration();


config.Slot0.kP = 0.11;
config.Slot0.kI = 0.5;
config.Slot0.kD = 0.001;
config.Slot0.kV = 0.12;


config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

topMotor.getConfigurator().apply(config);
bottomMotor.getConfigurator().apply(config);

bottomMotor.setControl(new com.ctre.phoenix6.controls.StrictFollower(topMotor.getDeviceID()));

}




public void setVelocity(double rps){
    topMotor.setControl(m_velocityControl.withVelocity(rps));
}

public void stop() {
    topMotor.stopMotor();
    bottomMotor.stopMotor();
}
public Command smartShooterCommand(){
    return run(()->{
        if(LimelightHelpers.getTV("limelight")){
            double ty = LimelightHelpers.getTY("limelight");

            double targetRPS = 65+(ty*-1.5);

            setVelocity(targetRPS);
        }
        else {
            setVelocity(40);
        }


    } 

      );    
}
@Override 
public void periodic(){
    SmartDashboard.putNumber("Shooter Gerçek RPS", topMotor.getVelocity().getValueAsDouble());
}

public Command autoShooterCommand(){
    return run(() ->{
        if(LimelightHelpers.getTV("limelight")){
            double ty = LimelightHelpers.getTY("limelight");

            double targetRPS = 70 + (ty*-1.2);


            this.setVelocity(targetRPS);
        }else{
            this.setVelocity(40);
        }
        

    }  );
    
}
}