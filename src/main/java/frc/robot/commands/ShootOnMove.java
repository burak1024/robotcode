package frc.robot.commands;



import com.ctre.phoenix6.controls.DutyCycleOut;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.LimelightHelpers;


public class ShootOnMove extends Command {
    
    private final TurretSubsystem m_turret;
    private final CommandSwerveDrivetrain m_drive;


    private final double kP_Translation = 0.02;
    private final double kP_Rotation = 0.01;
    public ShootOnMove(TurretSubsystem turret,CommandSwerveDrivetrain drive){
        m_turret = turret;
        m_drive=drive;
        addRequirements(m_turret);
    }
@Override
public void execute(){
 if (LimelightHelpers.getTV("limelight")){
    double tx = LimelightHelpers.getTX("limelight");

    ChassisSpeeds speeds =m_drive.getCurrentChassisSpeeds();

    double translationOffSet = speeds.vyMetersPerSecond *kP_Translation;
    double rotationOffset = speeds.omegaRadiansPerSecond *kP_Rotation;


    double adjustedTX =tx- translationOffSet - rotationOffset;

    double output =adjustedTX*0.05;

    m_turret.turretMotor.setControl(new DutyCycleOut(output));

 }
}
@Override
public void end(boolean interrupted){

    m_turret.turretMotor.setControl(new DutyCycleOut(0));
}
}