// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix6.controls.VelocityVoltage;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.ShooterSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class autoLockCommand extends Command {
  /** Creates a new autoLockCommand. */
  CommandSwerveDrivetrain drivetrain;
  ShooterSubsystem shooter;
  public autoLockCommand(CommandSwerveDrivetrain drivetrain,ShooterSubsystem shooter) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    addRequirements(shooter);
    this.shooter=shooter;
  }

  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    var currentPose= this.drivetrain.getState().Pose;
    double rx=currentPose.getX();
    double ry=currentPose.getY();
    double hx=4.575;double hy=4;double gx=rx-hx;double gy=ry-hy;
    double pisagor=Math.hypot(gx, gy);
    double autoRPS= 65+(pisagor*-1.5);

    shooter.setTopMotor(new VelocityVoltage(autoRPS));
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
