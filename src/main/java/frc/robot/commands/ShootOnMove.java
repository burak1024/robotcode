package frc.robot.commands;



import static frc.robot.Constants.HUB_POSITIONX;
import static frc.robot.Constants.HUB_POSITIONY;

import com.ctre.phoenix6.sim.ChassisReference;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.math.controller.PIDController;

public class ShootOnMove extends Command{
    private final TurretSubsystem turret;
    private final CommandSwerveDrivetrain drivetrain;
    private final PIDController PID=new PIDController(0.05, 0, 0.002);

    public ShootOnMove(TurretSubsystem turret,CommandSwerveDrivetrain drivetrain){
    this.turret=turret;
    this.drivetrain=drivetrain;
    addRequirements(turret);
}


@Override
public void execute(){
    double tx=LimelightHelpers.getTX( "limelight ");
    boolean tv=LimelightHelpers.getTV("limelight ");
    if(tv){
        var ChassisSpeeds=drivetrain.getState().Speeds;
        double chassisvy=ChassisSpeeds.vyMetersPerSecond;
        double hpx=HUB_POSITIONX;
        double hpy=HUB_POSITIONY;

    
    }
        
}



}
    