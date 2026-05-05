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
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.generated.TunerConstants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
    private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); 
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); 
    
    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();


    
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();  
    private final IndexerSubsystem m_IndexerSubsystem = new IndexerSubsystem();
    private final FeederSubsystem m_FeederSubsystem = new FeederSubsystem();
    private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
    private final TurretSubsystem m_TurretSubsystem=new TurretSubsystem();



    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) 
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

    private final Telemetry logger = new Telemetry(MaxSpeed);
        
   
    private final CommandPS4Controller dualshock=
    new CommandPS4Controller(0);
    
    //The part to identify xbox controller
    private final CommandXboxController joystick = 
    new CommandXboxController(0); 
    
    private final SendableChooser<Command> autoChooser;



    public RobotContainer() {
        autoChooser = AutoBuilder.buildAutoChooser("Tests");
        SmartDashboard.putData("Auto Mode", autoChooser);

        configureBindings();
  
        CommandScheduler.getInstance().schedule(FollowPathCommand.warmupCommand());
    }

    //key bindings
    private void configureBindings() {
        
        
        
        dualshock.R1().whileTrue(new IntakeCommand(m_IntakeSubsystem)).onFalse(new InstantCommand(()->m_IntakeSubsystem.stop()));
        dualshock.L1().whileTrue(new OuttakeCommand(m_IntakeSubsystem)).onFalse(new InstantCommand(()->m_IntakeSubsystem.stop()));
        dualshock.circle().onTrue(new StartCommand(m_FeederSubsystem, m_IndexerSubsystem, m_shooterSubsystem)).onFalse(new InstantCommand(()->m_FeederSubsystem.stop())).onFalse(new InstantCommand(()->m_IndexerSubsystem.stop())).onFalse(new InstantCommand(()->m_shooterSubsystem.stop()));
        dualshock.L3().onTrue(new RangeMeasurement(drivetrain, m_shooterSubsystem)).onFalse(new InstantCommand(()->m_shooterSubsystem.stop()));
        dualshock.circle().onTrue(new ShootOnMove(m_TurretSubsystem, drivetrain)).onFalse(new InstantCommand(()->m_TurretSubsystem.stop()));
        
        //The code that binds right trigger to starting shooter (starts to shoot)
        joystick.rightTrigger().whileTrue(new RangeMeasurement(drivetrain, m_shooterSubsystem)).onFalse(new InstantCommand(()->m_shooterSubsystem.stop()));
        //The code that binds right bumper to open Intake
        joystick.rightBumper().whileTrue(new IntakeCommand(m_IntakeSubsystem)).onFalse(new InstantCommand(()->m_IntakeSubsystem.stop()));
        //the code that binds left bumper outtake
        joystick.leftBumper().whileTrue(new OuttakeCommand(m_IntakeSubsystem)).onFalse(new InstantCommand(()->m_IntakeSubsystem.stop()));      
        
        joystick.a().whileTrue(new StartCommand( m_FeederSubsystem, m_IndexerSubsystem, m_shooterSubsystem)).onFalse(new InstantCommand(()->m_FeederSubsystem.stop())).onFalse(new InstantCommand(()->m_IndexerSubsystem.stop())).onFalse(new InstantCommand(()->m_shooterSubsystem.stop()));

        drivetrain.setDefaultCommand(
            
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) 
                    .withVelocityY(-joystick.getLeftX() * MaxSpeed) 
                    .withRotationalRate(-joystick.getRightX() * MaxAngularRate) 
                    .withVelocityX(-dualshock.getLeftY()*MaxSpeed)
                    .withVelocityY(-dualshock.getLeftX()*MaxSpeed)
                    .withRotationalRate(-dualshock.getRightX()*MaxAngularRate)
            
            
                    )
        );

        drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() {
        
        return autoChooser.getSelected();
    }
    
}