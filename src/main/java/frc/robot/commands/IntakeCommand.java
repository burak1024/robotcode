package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {

    private final IntakeSubsystem intake;

    public IntakeCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setIntakePosition(Constants.INTAKE_POSITION);
        intake.runIntake(Constants.INTAKE_SPEED);
        
        if (intake.getIntakePosition() < 5.0) {
        intake.setIntakePosition(Constants.INTAKE_POSITION);
    }
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
    }
}