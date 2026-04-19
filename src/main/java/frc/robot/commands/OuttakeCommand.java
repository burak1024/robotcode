package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class OuttakeCommand extends Command {

    private final IntakeSubsystem intake;

    public OuttakeCommand(IntakeSubsystem intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setIntakePosition(Constants.INTAKE_POSITION);
        intake.runIntake(Constants.OUTTAKE_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
    }
}