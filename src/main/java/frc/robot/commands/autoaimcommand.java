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

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
      if (LimelightHelpers.getTV("limelight")){
        double tx=LimelightHelpers.getTX("limelight");
        double speed=tx*0.02;
        turret.turretMotor.setControl(new DutyCycleOut(speed));
      }
    }
    
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

