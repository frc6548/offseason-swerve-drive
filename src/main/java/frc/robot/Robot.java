package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;

    private RobotContainer m_robotContainer;

    // initializes this code on boot
    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer();
    }
    // runs every robot packet, no matter the mode
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    // when robot is disabled this code runs
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link RobotContainer} class
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    // this function is called periodically during autonomous
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stop running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
            
            
        }
    }

    // called during teleoperated control
    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        // vancels all running commands at the start of test mode
        CommandScheduler.getInstance().cancelAll();
    }

    //this function is called periodically during test mode
    @Override
    public void testPeriodic() {
    }
}
