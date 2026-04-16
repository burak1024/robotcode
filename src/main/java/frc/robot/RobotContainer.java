// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;  
import static edu.wpi.first.units.Units.*;



import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.FollowPathCommand;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.commands.autoLockCommand;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.commands.autoaimcommand;
import frc.robot.commands.IndexerturnCommand;






public class RobotContainer {
    private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); 
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); 
    
    private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();  
    private final IndexerSubsystem m_IndexerSubsystem = new IndexerSubsystem();
    
    
    
    
    
    
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) 
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage);
        

    private final Telemetry logger = new Telemetry(MaxSpeed);
        
    
    
    
    private final CommandXboxController joystick = 
    new CommandXboxController(0); 

    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
  
    private final SendableChooser<Command> autoChooser;

    public RobotContainer() {
        autoChooser = AutoBuilder.buildAutoChooser("Tests");
        SmartDashboard.putData("Auto Mode", autoChooser);

        configureBindings();
  
        CommandScheduler.getInstance().schedule(FollowPathCommand.warmupCommand());
    }

    private void configureBindings() {
        joystick.rightTrigger().whileTrue(new autoLockCommand(drivetrain, m_shooterSubsystem)).onFalse(new InstantCommand(()->m_shooterSubsystem.stop()));
        
        joystick.a().onTrue(new autoaimcommand(drivetrain, m_turretSubsystem)).onFalse(new InstantCommand(()->m_turretSubsystem.stop()));        
        
        joystick.b().onTrue(new IndexerturnCommand(drivetrain, m_IndexerSubsystem)).onFalse(new InstantCommand(()->m_IndexerSubsystem.stop()));



        drivetrain.setDefaultCommand(
            
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) 
                    .withVelocityY(-joystick.getLeftX() * MaxSpeed) 
                    .withRotationalRate(-joystick.getRightX() * MaxAngularRate) 
            )
        );

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    
    
    
    
    
    
    public Command getAutonomousCommand() {
        
        return autoChooser.getSelected();
    }
    
}