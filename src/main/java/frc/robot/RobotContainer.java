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
import frc.robot.commands.feedercommand;
import frc.robot.commands.IndexerturnCommand;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.commands.ShootOnMove;

public class RobotContainer {
    private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); 
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); 
    
    private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();  
    private final IndexerSubsystem m_IndexerSubsystem = new IndexerSubsystem();
    private final FeederSubsystem m_FeederSubsystem = new FeederSubsystem();

    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) 
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

    private final Telemetry logger = new Telemetry(MaxSpeed);
        
   

    
    //The part to identify xbox controller
    private final CommandXboxController joystick = 
    new CommandXboxController(0); 
    private final CommandXboxController m_keyboard=new CommandXboxController(1);


    public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();


  
    private final SendableChooser<Command> autoChooser;



    public RobotContainer() {
        autoChooser = AutoBuilder.buildAutoChooser("Tests");
        SmartDashboard.putData("Auto Mode", autoChooser);

        configureBindings();
  
        //CommandScheduler.getInstance().schedule(FollowPathCommand.warmupCommand());
    }

    //key bindings
    private void configureBindings() {
        
        m_keyboard.button(1).onTrue(new ShootOnMove(m_turretSubsystem,drivetrain)).onFalse(new InstantCommand(()->m_turretSubsystem.stop()));
        m_keyboard.button(2).onTrue(new autoLockCommand(drivetrain, m_shooterSubsystem)).onFalse(new InstantCommand(()->m_shooterSubsystem.stop()));
        m_keyboard.button(3).onTrue(new IndexerturnCommand(drivetrain, m_IndexerSubsystem)).onFalse(new InstantCommand(()->m_IndexerSubsystem.stop()));   
        m_keyboard.button(3).onTrue(new feedercommand(drivetrain,m_FeederSubsystem)).onFalse(new InstantCommand(()->m_FeederSubsystem.stop()));
        
        
        
        //The code that binds right trigger to starting shooter (starts to shoot)
        joystick.rightTrigger().whileTrue(new autoLockCommand(drivetrain, m_shooterSubsystem)).onFalse(new InstantCommand(()->m_shooterSubsystem.stop()));

        //The code that binds a button to align robot to hub
        joystick.a().onTrue(new ShootOnMove(m_turretSubsystem,drivetrain)).onFalse(new InstantCommand(()->m_turretSubsystem.stop()));        
        //The code that binds b button to start Indexer
        joystick.b().onTrue(new IndexerturnCommand(drivetrain, m_IndexerSubsystem)).onFalse(new InstantCommand(()->m_IndexerSubsystem.stop()));
        //The code that binds b button to start feeder 
        joystick.b().onTrue(new feedercommand(drivetrain,m_FeederSubsystem)).onFalse(new InstantCommand(()->m_FeederSubsystem.stop()));

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