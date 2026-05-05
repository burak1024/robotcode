package frc.robot.commands;

import static frc.robot.Constants.FEEDER_SPEED;
import static frc.robot.Constants.INDEXER_SPEED;
import static frc.robot.Constants.SHOOTER_SPEED;

import com.ctre.phoenix6.controls.DutyCycleOut;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class StartCommand extends Command{
    FeederSubsystem feeder;
    IndexerSubsystem Indexer;
    ShooterSubsystem Shooter;
    public StartCommand(FeederSubsystem feeder,IndexerSubsystem Indexer,ShooterSubsystem Shooter){
        addRequirements(feeder);
        this.feeder=feeder;
        addRequirements(Indexer);
        this.Indexer=Indexer;
        addRequirements(Shooter);
        this.Shooter=Shooter;
    }
    
    @Override
    public void execute(){
        feeder.feederMotor.setControl(new DutyCycleOut(FEEDER_SPEED));
        Indexer.IndexerMotor.setControl(new DutyCycleOut(INDEXER_SPEED));
        Shooter.topMotor.setControl(new DutyCycleOut(SHOOTER_SPEED));
        
    }
    

    @Override
    public void end(boolean interrupted) {
 
    }



    @Override
    public boolean isFinished() {
        return false;
    }

}

