import com.arcrobotics.ftclib.command.SubsystemBase;

public class SpindexerSystem extends SubsystemBase {
    private ServoEx servo;

    /**
     * Creates a new SpindexerSystem.
     */
    public SpindexerSystem() {
        // servo = new SimpleServo(hardwareMap, SpindexerConstants.SERVO_NAME, SpindexerConstants.SERVO_MIN_ANGLE, SpindexerConstants.SERVO_MAX_ANGLE);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}