package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib2.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.SensorColor;

public class SpindexerSystem extends SubsystemBase {
    private final ServoEx servo;
    private final SensorColor sensor1, sensor2, sensor3;
    private final SensorRevTOFDistance sensorDist1, sensorDist2, sensorDist3;

    /**
     * Creates a new ExampleSubsystem.
     */
    public SpindexerSystem() {
        servo = new SimpleServo(hardwareMap, "Spinny", SpindexerConstants.MIN_ANGLE, SpindexerConstants.MAX_ANGLE, AngleUnit.DEGREES);
        sensor1 = new SensorColor(hardwareMap, "Color1");
        sensor2 = new SensorColor(hardwareMap, "Color2");
        sensor3 = new SensorColor(hardwareMap, "Color3");
        sensorDist1 = new SensorRevTOFDistance(hardwareMap, "Color1");
        sensorDist2 = new SensorRevTOFDistance(hardwareMap, "Color2");
        sensorDist3 = new SensorRevTOFDistance(hardwareMap, "Color3");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
