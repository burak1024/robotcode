// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix6.controls.VelocityVoltage;


import edu.wpi.first.wpilibj2.command.Command;
import static frc.robot.Constants.*;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.ShooterSubsystem;

public class RangeMeasurement extends Command {
 
  CommandSwerveDrivetrain drivetrain;
  ShooterSubsystem shooter;
  public RangeMeasurement(CommandSwerveDrivetrain drivetrain,ShooterSubsystem shooter) {
    addRequirements(shooter);
    this.shooter=shooter;
  }


  

  //this code takes the cooordinates of robot and hub and uses this for adjusting speed of the shooter motor
  @Override
  public void execute() {
    var currentPose= this.drivetrain.getState().Pose;

    double rx=currentPose.getX();
    double ry=currentPose.getY();

    double gx=rx-HUB_POSITIONX;
    double gy=ry-HUB_POSITIONY;

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
