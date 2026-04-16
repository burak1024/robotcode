package frc.robot.commands;
import com.ctre.phoenix6.controls.DutyCycleOut;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.IndexerSubsystem;



public class IndexerturnCommand extends Command {
  //Creates a new command
  CommandSwerveDrivetrain drivetrain;
  IndexerSubsystem Indexer;
  public IndexerturnCommand(CommandSwerveDrivetrain drivetrain,IndexerSubsystem Indexer) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    addRequirements(Indexer);
    this.Indexer=Indexer;
  }

  @Override
  public void initialize() {}

 //while this code turned on this code starts the Indexer
  @Override
  public void execute() {
   Indexer.IndexerMotor.setControl(new DutyCycleOut(0.5));
  }
 
  @Override
  public void end(boolean interrupted) {
 
  }
  @Override
  public boolean isFinished() {
    return false;
  }
}