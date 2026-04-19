package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;


public class IntakeSubsystem extends SubsystemBase {
    public final TalonFX intakeMotor = new TalonFX(Constants.INTAKE_MOTOR_ID);
    public final TalonFX deployMotor = new TalonFX(Constants.DEPLOY_MOTOR_ID);

    public void setIntakePosition(double position) {
        deployMotor.setControl(new PositionDutyCycle(position));
    }

    public void runIntake(double speed) {
        intakeMotor.setControl(new DutyCycleOut(speed));
    }

    public double getIntakePosition() {
    return deployMotor.getPosition().getValueAsDouble();
}
    
    public void stop() {
        intakeMotor.stopMotor();
        deployMotor.stopMotor();
    }
}