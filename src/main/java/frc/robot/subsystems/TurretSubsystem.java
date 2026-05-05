package frc.robot.subsystems;

import static frc.robot.Constants.TURRET_MOTOR_ID;

import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;


public class TurretSubsystem extends SubsystemBase {
    
    // Subsystem içinde tanımla
private final Mechanism2d mech = new Mechanism2d(3, 3);
private final MechanismRoot2d root = mech.getRoot("turret", 1.5, 1.5);
private final MechanismLigament2d turretDial = root.append(new MechanismLigament2d("TurretAngle", 1, 0));

@Override
public void simulationPeriodic() {
    // Motorun anlık pozisyonunu (tur) dereceye çevirip çizime aktar
    turretDial.setAngle(turretMotor.getPosition().getValueAsDouble() * 360);
    SmartDashboard.putData("Turret Mechanism", mech);
}
    
    
    
  public double getTurretRotation() {
    // Motorun o anki tur değerini döndürür
    return turretMotor.getPosition().getValueAsDouble();
}  
    
    
public void setTurretAngle(double target) {
    // target değerini motora gönderir
    turretMotor.setControl(new PositionDutyCycle(target));
}    
    
    
    public final TalonFX turretMotor = new TalonFX(TURRET_MOTOR_ID); 

  
    public TurretSubsystem(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted=InvertedValue.Clockwise_Positive;
        turretMotor.getConfigurator().apply(config);
    }
   
    
    public void stop() {
        turretMotor.stopMotor();
    }

    
}