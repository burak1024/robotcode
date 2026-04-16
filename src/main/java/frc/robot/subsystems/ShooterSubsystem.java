package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ShooterSubsystem extends SubsystemBase{

private final TalonFX topMotor = new TalonFX(15);
private final TalonFX bottomMotor= new TalonFX(16);   
private final VelocityVoltage m_velocityControl = new VelocityVoltage(0);


    public ShooterSubsystem(){
        //yeni konfigürasyon ayarlaması (boş sayfa gibi)
        TalonFXConfiguration config = new TalonFXConfiguration();

        //PID ve watt ayarlaması
        config.Slot0.kP = 0.11;
        config.Slot0.kI = 0.5;
        config.Slot0.kD = 0.001;
        config.Slot0.kV = 0.12;

        //saat yönünde dönmesini sağlayan kod
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

        //boş konfigürasyonu saat yönünde dönsün diye atayan kodlar(ayrıca 16. motorun 15.motorla aynı çalışmasını sağlayan kod)
        topMotor.getConfigurator().apply(config);
        bottomMotor.getConfigurator().apply(config);
        bottomMotor.setControl(new com.ctre.phoenix6.controls.StrictFollower(topMotor.getDeviceID()));
    }

        //topMotor'un hızıyla alakalı (AI)
        public void setVelocity(double rps){
            topMotor.setControl(m_velocityControl.withVelocity(rps));
    }

        //motoru durduran kod
        public void stop() {
            topMotor.stopMotor();
            bottomMotor.stopMotor();
    }
        //kerem yazdı neydi unuttum ama shooterın hızıyla alakalı
        public void setTopMotor(ControlRequest val){
            topMotor.setControl(val);
    }
        //SmartDashboard'da RPS'i(Rotation Per Second) gösteren komut
    @Override 
    public void periodic(){
        SmartDashboard.putNumber("Shooter Gerçek RPS", topMotor.getVelocity().getValueAsDouble());}

}






