package frc.robot.commands;

import com.ctre.phoenix6.controls.DutyCycleOut;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.LimelightHelpers;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.commands.autoaimcommand;


public class autoaimcommand extends Command {
  CommandSwerveDrivetrain drivetrain;
  TurretSubsystem turret;
  public autoaimcommand(CommandSwerveDrivetrain drivetrain,TurretSubsystem turret) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    addRequirements(turret);
    this.turret=turret;
  }


  @Override
  public void initialize() {}



  //limelight'tan aldığı veriyle motorun ne tarafa ne kadar dönmesi gerektiğini anlayıp,o kadar döndüren kod
  @Override
  public void execute(){
      if (LimelightHelpers.getTV("limelight")){
        double tx=LimelightHelpers.getTX("limelight");
        double speed=tx*0.02;
        turret.turretMotor.setControl(new DutyCycleOut(speed));
      }
    }
    
  

  @Override
  public void end(boolean interrupted) {}



  @Override
  public boolean isFinished() {
    return false;
  }
}

