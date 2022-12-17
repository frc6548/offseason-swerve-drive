package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class Robot extends TimedRobot {
    private AddressableLED m_led;
    private AddressableLEDBuffer m_ledBuffer;

    private Command m_autonomousCommand;
    private RobotContainer m_robotContainer;

    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer();
        
        m_led = new AddressableLED(6);
        m_ledBuffer = new AddressableLEDBuffer(150);
        m_led.setLength(m_ledBuffer.getLength());
        m_led.start();
        m_led.setData(m_ledBuffer);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        RedLED();
        m_led.setData(m_ledBuffer);
        Limelight.Enable();
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();

            BlueFlashLED();
            m_led.setData(m_ledBuffer);
            Limelight.Disable();
        }
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
            
            GreenLED();
            m_led.setData(m_ledBuffer);
            Limelight.Enable();
        }
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();

        WhiteLED();
        m_led.setData(m_ledBuffer);
        Limelight.Enable();
    }
    
    @Override
    public void testPeriodic() {
    }

    public void RedLED() {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setRGB(i, 255, 0, 0);
        }}

    public void GreenLED() {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setRGB(i, 0, 255, 0);
        }}

    public void BlueLED() {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setRGB(i, 0, 0, 255);
            
        }}

    public void BlackLED() {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setRGB(i, 0, 0, 0);
        }}

    public void WhiteLED() {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setRGB(i, 255, 255, 255);
        }}

        public void BlueFlashLED() {
            double t = Timer.getFPGATimestamp();
            for (var i = 0; i < m_ledBuffer.getLength(); i++) {
                      m_ledBuffer.setRGB(i, 0, 0, (int) (255*(Math.cos(t)*0.5+0.5)));
            }}
}
