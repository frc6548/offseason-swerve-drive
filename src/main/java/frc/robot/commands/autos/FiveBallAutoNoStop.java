package frc.robot.commands.autos;


import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.SwerveSubsystem;

public class FiveBallAutoNoStop extends SequentialCommandGroup {
  public FiveBallAutoNoStop(SwerveSubsystem swerveDrive) {
    PathPlannerTrajectory trajectory1 =
        PathPlanner.loadPath("FiveBallAuto", Units.feetToMeters(2), Units.feetToMeters(2), false);
        new PPSwerveControllerCommand(
            trajectory1,
            swerveDrive::getPose,
                    DriveConstants.kDriveKinematics,
            swerveDrive.getXPidController(),
            swerveDrive.getYPidController(),
            swerveDrive.getThetaPidController(),
            swerveDrive::setModuleStates,
            swerveDrive);
    }
}