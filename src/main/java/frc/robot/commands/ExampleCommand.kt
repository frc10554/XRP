package frc.robot.commands

import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.Command
import frc.robot.subsystems.XRPDrivetrain

/** An example command that uses the [XRPDrivetrain] subsystem. */
class ExampleCommand() : Command() {
    private var timeSinceInit : Double? = null

    init {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(XRPDrivetrain)
    }

    // Called when the command is initially scheduled.
    override fun initialize() {
        timeSinceInit = Timer.getFPGATimestamp() // Reset the timer
    }

    // Called every time the scheduler runs while the command is scheduled.
    override fun execute() {
        XRPDrivetrain.arcadeDrive(1.0, 0.0) // Drives forward at full speed
    }

    // Called once the command ends or is interrupted.
    override fun end(interrupted: Boolean) {
    }

    // Returns true when the command should end.
    override fun isFinished(): Boolean {
        return timeSinceInit?.let { Timer.getFPGATimestamp() - it > 2.0 } == true // If 2 seconds have passed, stop
        XRPDrivetrain.arcadeDrive(0.0, 0.0) // Drives forward at full speed
    }
}