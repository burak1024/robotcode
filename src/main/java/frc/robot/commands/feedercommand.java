package frc.robot.commands;

import com.ctre.phoenix6.controls.DutyCycleOut;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.FeederSubsystem;

public class feedercommand extends Command{
    CommandSwerveDrivetrain drivetrain;
    FeederSubsystem feeder;
    public feedercommand(CommandSwerveDrivetrain drivetrain,FeederSubsystem feeder){
        addRequirements(drivetrain);
        this.drivetrain=drivetrain;
        addRequirements(feeder);
        this.feeder=feeder;
    }
    
    @Override
    public void execute(){
        feeder.feederMotor.setControl(new DutyCycleOut(0.5));
    }
    




    @Override
    public void end(boolean interrupted) {
 
    }



    @Override
    public boolean isFinished() {
        return false;
    }

}

