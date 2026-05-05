package frc.robot.commands;



import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.TurretSubsystem;


public class ShootOnMove extends Command{
    private final TurretSubsystem turret;
    private final CommandSwerveDrivetrain drivetrain;
    
    public ShootOnMove(TurretSubsystem turret,CommandSwerveDrivetrain drivetrain){
    this.turret=turret;
    this.drivetrain=drivetrain;
    addRequirements(turret);
}


@Override
public void execute(){

double tx=LimelightHelpers.getTX("limelight");
boolean hastarget =LimelightHelpers.getTV("limelight");

if (hastarget){

 double currentAngle=turret.getTurretRotation();


var ChassisSpeeds=drivetrain.getState().Speeds;
double robotVy=ChassisSpeeds.vyMetersPerSecond;



double target=currentAngle+(tx/360.0)-(robotVy*0.05);
turret.setTurretAngle(target);


SmartDashboard.putNumber("Turret/MeasuredAngle", currentAngle * 360);
}

}



}
    