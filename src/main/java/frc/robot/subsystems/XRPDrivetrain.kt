package frc.robot.subsystems

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.xrp.XRPMotor
import edu.wpi.first.wpilibj2.command.SubsystemBase

// By making a subsystem an object, we ensure there is only ever one instance of it
object XRPDrivetrain : SubsystemBase("Drivetrain") {
    private const val GEAR_RATIO = 30.0 / 14.0 * (28.0 / 16.0) * (36.0 / 9.0) * (26.0 / 8.0) // 48.75:1
    private const val COUNTS_PER_MOTOR_SHAFT_REV = 12.0
    private const val COUNTS_PER_REVOLUTION = COUNTS_PER_MOTOR_SHAFT_REV * GEAR_RATIO // 585.0
    private const val WHEEL_DIAMETER_INCH = 2.3622 // 60 mm

    // The XRP has the left and right motors set to
    // channels 0 and 1 respectively
    private val leftMotor = XRPMotor(0)
    private val rightMotor = XRPMotor(1)

    // The XRP has onboard encoders that are hardcoded
    // to use DIO pins 4/5 and 6/7 for the left and right
    private val leftEncoder = Encoder(4, 5)
    private val rightEncoder = Encoder(6, 7)

    // Set up the differential drive controller
    private val diffDrive = DifferentialDrive(
        { value -> leftMotor.set(value) },
        { value -> rightMotor.set(value) }
    )

    init {
        // Use inches as unit for encoder distances
        leftEncoder.distancePerPulse = Math.PI * WHEEL_DIAMETER_INCH / COUNTS_PER_REVOLUTION
        rightEncoder.distancePerPulse = Math.PI * WHEEL_DIAMETER_INCH / COUNTS_PER_REVOLUTION
        resetEncoders()

        // Invert right side since motor is flipped
        rightMotor.inverted = true
    }

    val leftDistanceInch: Double
        get() = leftEncoder.distance

    val rightDistanceInch: Double
        get() = rightEncoder.distance

    fun arcadeDrive(xAxisSpeed: Double, zAxisRotate: Double) {
        diffDrive.arcadeDrive(xAxisSpeed, zAxisRotate)
    }

    fun resetEncoders() {
        leftEncoder.reset()
        rightEncoder.reset()
    }

    override fun periodic() {
        // This method will be called once per scheduler run
    }

    override fun simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}