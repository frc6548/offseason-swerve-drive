package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.autos.FiveBallAuto;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotContainer {
    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();

    private final XboxController m_controller = new XboxController(OIConstants.kDriverControllerPort);

    private final SendableChooser<Command> m_autoChooser = new SendableChooser<Command>(); 
    final SwerveSubsystem m_robotDrive = new SwerveSubsystem();

    public RobotContainer() {
            
        SmartDashboard.putData("Scheduler", CommandScheduler.getInstance()); 
        initializeAutoChooser();

        swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                swerveSubsystem,
                // in teleop use xbox controller to move
                () -> -m_controller.getRawAxis(OIConstants.kDriverYAxis),
                () -> m_controller.getRawAxis(OIConstants.kDriverXAxis),
                () -> m_controller.getRawAxis(OIConstants.kDriverRotAxis),
                () -> !m_controller.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

        configureButtonBindings();
    }

    private void initializeAutoChooser() {
        m_autoChooser.setDefaultOption("Do Nothing", new WaitCommand(0)); 
        m_autoChooser.addOption("5 Ball Auto", new FiveBallAuto(m_robotDrive));
    
        SmartDashboard.putData("Auto Selector", m_autoChooser); 
    
      }

    private void configureButtonBindings() {
        new JoystickButton(m_controller, 2).whenPressed(() -> swerveSubsystem.zeroHeading());
    }

    public Command getAutonomousCommand() {
        return m_autoChooser.getSelected(); 
    }
}