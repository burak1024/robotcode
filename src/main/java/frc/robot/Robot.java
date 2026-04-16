package frc.robot;

import com.ctre.phoenix6.HootAutoReplay;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot{
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  private final HootAutoReplay m_timeAndJoystickReplay=new HootAutoReplay()
  .withTimestampReplay()
  .withJoystickReplay();


  private final boolean kUseLimelight=false;


  public Robot() {
    m_robotContainer=new RobotContainer();
    
  }

  @Override
  public void robotPeriodic() {
    m_timeAndJoystickReplay.update();
    CommandScheduler.getInstance().run();
    
    //burayı ai dan almıştım sorgulamamışım hiç
    if (kUseLimelight){
      var driveState =m_robotContainer.drivetrain.getState();
      double headingDeg = driveState.Pose.getRotation().getDegrees();
      double omegaRps = Units.radiansToRotations(driveState.Speeds.omegaRadiansPerSecond);


      LimelightHelpers.SetRobotOrientation("limelight",headingDeg,0,0,0,0,0);
      var llMeasurement = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("limelight ");
      if(llMeasurement != null && llMeasurement.tagCount > 0 && Math.abs(omegaRps) <2.0){
      m_robotContainer.drivetrain.addVisionMeasurement(llMeasurement.pose,llMeasurement.timestampSeconds);
      }
    }
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand =m_robotContainer.getAutonomousCommand();

    if(m_autonomousCommand != null){
      CommandScheduler.getInstance().schedule(m_autonomousCommand);
  }
  }
  
  
  
  @Override
  public void autonomousPeriodic() {}





  @Override
  public void teleopInit() {
    if(m_autonomousCommand != null){
    CommandScheduler.getInstance().cancel(m_autonomousCommand);
}
}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}