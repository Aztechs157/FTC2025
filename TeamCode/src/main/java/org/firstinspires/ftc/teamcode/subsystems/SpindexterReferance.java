package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "spindexerTest2 (Blocks to Java)", group = "Sensor")
public class spindexerTest extends LinearOpMode {

  private ColorSensor Color1;
  private ColorSensor Color2;
  private ColorSensor Color3;
  private CRServo Spinny;
  private DistanceSensor Color1_DistanceSensor;
  private DistanceSensor Color2_DistanceSensor;
  private DistanceSensor Color3_DistanceSensor;

  int spinnyToggle2;
  boolean isFull2;
  double distance1;
  NormalizedRGBA ColorValue1;
  int shootPurple;
  int shootGreen;
  int aprilTag;
  double distance2;
  NormalizedRGBA ColorValue2;
  String ball1;
  double distance3;
  NormalizedRGBA ColorValue3;
  String ball2;
  String ball3;
  double detectDistance;
  float value1;

  /**
   * This OpMode shows how to use a color sensor in a generic way, regardless
   * of which particular make or model of color sensor is used. The OpMode
   * assumes that the color sensor is configured with a name of "sensor_color".
   *
   * There will be some variation in the values measured depending on the specific sensor you are using.
   *
   * If the color sensor supports adjusting the gain, you can increase the gain
   * (a multiplier to make the sensor report higher values) by holding down the
   * A button on the gamepad, and decrease the gain by holding down the B button
   * on the gamepad. The AndyMark Proximity & Color Sensor does not support this.
   *
   * If the color sensor has a light which is controllable from software, you can use the
   * X button on the gamepad to toggle the light on and off. The REV sensors don't support
   * this, but instead have a physical switch on them to turn the light on and off, beginning
   * with REV Color Sensor V2. The AndyMark Proximity & Color Sensor does not support this.
   *
   * If the color sensor also supports short-range distance measurements (usually
   * via an infrared proximity sensor), the reported distance will be written to
   * telemetry. As of September 2025, the only color sensors that support this
   * are the ones from REV Robotics and the AndyMark Proximity & Color Sensor.
   * These infrared proximity sensor measurements are only useful at very small
   * distances, and are sensitive to ambient light and surface reflectivity.
   * You should use a different sensor if you need precise distance measurements.
   */
  @Override
  public void runOpMode() {
    double spinnySpeed;
    int gain;
    boolean xButtonCurrentlyPressed;
    boolean xButtonPreviouslyPressed;
    String isFullDisp;
    double hue;
    double saturation;

    Color1 = hardwareMap.get(ColorSensor.class, "Color1");
    Color2 = hardwareMap.get(ColorSensor.class, "Color2");
    Color3 = hardwareMap.get(ColorSensor.class, "Color3");
    Spinny = hardwareMap.get(CRServo.class, "Spinny");
    Color1_DistanceSensor = hardwareMap.get(DistanceSensor.class, "Color1");
    Color2_DistanceSensor = hardwareMap.get(DistanceSensor.class, "Color2");
    Color3_DistanceSensor = hardwareMap.get(DistanceSensor.class, "Color3");

    // Put initialization blocks here.
    spinnyToggle2 = 0;
    spinnySpeed = 0.5;
    // You can give the sensor a gain value, will be multiplied by the sensor's raw value
    // before the normalized color values are calculated. Color sensors (especially the REV
    // Color Sensor V3) can give very low values (depending on the lighting conditions),
    // which only use a small part of the 0-1 range that is available for the red,
    // green, and blue values. In brighter conditions, you should use a smaller
    // gain than in dark conditions. If your gain is too high, all of the
    // colors will report at or near 1, and you won't be able to determine what
    // color you are actually looking at. For this reason, it's better to err
    // on the side of a lower gain (but always greater than or equal to 1).
    gain = 5;
    aprilTag = 20;
    detectDistance = 3.2;
    // Tell the sensor our desired gain value (normally you would do this during initialization, not during the loop)
    ((NormalizedColorSensor) Color1).setGain(gain);
    // Tell the sensor our desired gain value (normally you would do this during initialization, not during the loop)
    ((NormalizedColorSensor) Color2).setGain(gain);
    // Tell the sensor our desired gain value (normally you would do this during initialization, not during the loop)
    ((NormalizedColorSensor) Color3).setGain(gain);
    xButtonCurrentlyPressed = false;
    // xButtonPreviouslyPressed and xButtonCurrentlyPressed keep track
    // of the previous and current state of the X button on the gamepad.
    xButtonPreviouslyPressed = false;
    // If supported by the sensor, turn the light on in the beginning (it
    // might already be on anyway, we just make sure it is if we can).
    Color1.enableLed(true);
    // If supported by the sensor, turn the light on in the beginning (it
    // might already be on anyway, we just make sure it is if we can).
    Color2.enableLed(true);
    // If supported by the sensor, turn the light on in the beginning (it
    // might already be on anyway, we just make sure it is if we can).
    Color3.enableLed(true);
    isFull2 = false;
    waitForStart();
    if (opModeIsActive()) {
      Spinny.setDirection(CRServo.Direction.REVERSE);
      // Once per loop we read the color sensor data, calculate the HSV colors
      // (Hue, Saturation and Value), and report all these values via telemetry.
      while (opModeIsActive()) {
        // Put loop blocks here.
        if (gamepad1.y_was_pressed()) {
          spinnyToggle();
        }
        if (spinnyToggle2 == 1) {
          Spinny.setPower(spinnySpeed);
        } else if (spinnyToggle2 == 0) {
          Spinny.setPower(0);
        }
        if (gamepad1.x_was_pressed()) {
          incrementAprilTag();
        }
        getDistanceValues();
        getColorValues();
        if (shootPurple == 1 || gamepad1.right_bumper_was_pressed()) {
          shootPurpleAt3();
        }
        if (shootGreen == 1 || gamepad1.left_bumper_was_pressed()) {
          shootGreenAt3();
        }
        getBallColorAtPosition(1);
        getBallColorAtPosition(2);
        getBallColorAtPosition(3);
        isFull();
        telemetry.addLine(ball1);
        telemetry.addLine(ball2);
        telemetry.addLine(ball3);
        telemetry.addLine(spinnyToggle2);
        telemetry.addLine(aprilTag);
        // Use telemetry to display feedback on the driver station. We show the red,
        // green, and blue normalized values from the sensor (in the range of 0 to
        // 1), as well as the equivalent HSV (hue, saturation and value) values.
        telemetry.addLine("Red 1 " + JavaUtil.formatNumber(ColorValue1.red, 3) + " | Green 1 " + JavaUtil.formatNumber(ColorValue1.green, 3) + " | Blue 1 " + JavaUtil.formatNumber(ColorValue1.blue, 3));
        // Use telemetry to display feedback on the driver station. We show the red,
        // green, and blue normalized values from the sensor (in the range of 0 to
        // 1), as well as the equivalent HSV (hue, saturation and value) values.
        telemetry.addLine("Red 2 " + JavaUtil.formatNumber(ColorValue2.red, 3) + " | Green 2 " + JavaUtil.formatNumber(ColorValue2.green, 3) + " | Blue 2 " + JavaUtil.formatNumber(ColorValue2.blue, 3));
        // Use telemetry to display feedback on the driver station. We show the red,
        // green, and blue normalized values from the sensor (in the range of 0 to
        // 1), as well as the equivalent HSV (hue, saturation and value) values.
        telemetry.addLine("Red 3 " + JavaUtil.formatNumber(ColorValue3.red, 3) + " | Green 3 " + JavaUtil.formatNumber(ColorValue3.green, 3) + " | Blue 3 " + JavaUtil.formatNumber(ColorValue3.blue, 3));
        if (isFull2 == true) {
          isFullDisp = "True";
        } else if (isFull2 == false) {
          isFullDisp = "False";
        }
        telemetry.addLine(isFullDisp);
        // If this color sensor also has a distance sensor, display the measured distance.
        // Note that the reported distance is only useful at very close
        // range, and is impacted by ambient light and surface reflectivity.
        telemetry.addData("Distance (cm)", Double.parseDouble(JavaUtil.formatNumber(Color1_DistanceSensor.getDistance(DistanceUnit.CM), 3)));
        telemetry.update();
      }
    }
  }

  /**
   * Describe this function...
   */
  private void getSensorValue(
      // TODO: Enter the type for argument named x
      UNKNOWN_TYPE x) {
  }

  /**
   * Describe this function...
   */
  private void spinnyToggle() {
    if (spinnyToggle2 != 1) {
      spinnyToggle2 = 1;
    } else {
      spinnyToggle2 = 0;
    }
    Spinny.setPower(spinnyToggle2);
  }

  /**
   * Describe this function...
   */
  private void toggleSpindexer() {
    if (spinnyToggle2 != 1) {
      spinnyToggle2 = 1;
    } else {
      spinnyToggle2 = 0;
    }
  }

  /**
   * Describe this function...
   */
  private void getDistanceValues() {
    distance1 = Color1_DistanceSensor.getDistance(DistanceUnit.CM);
    distance2 = Color2_DistanceSensor.getDistance(DistanceUnit.CM);
    distance3 = Color3_DistanceSensor.getDistance(DistanceUnit.CM);
  }

  /**
   * Describe this function...
   */
  private void getColorValues() {
    int myColor1;
    int myColor2;
    int myColor3;
    float value2;
    float value3;

    // Save the color sensor data as a normalized color value. It's recommended
    // to use Normalized Colors over color sensor colors is because Normalized
    // Colors consistently gives values between 0 and 1, while the direct
    // Color Sensor colors are dependent on the specific sensor you're using.
    ColorValue1 = ((NormalizedColorSensor) Color1).getNormalizedColors();
    // Save the color sensor data as a normalized color value. It's recommended
    // to use Normalized Colors over color sensor colors is because Normalized
    // Colors consistently gives values between 0 and 1, while the direct
    // Color Sensor colors are dependent on the specific sensor you're using.
    ColorValue2 = ((NormalizedColorSensor) Color2).getNormalizedColors();
    // Save the color sensor data as a normalized color value. It's recommended
    // to use Normalized Colors over color sensor colors is because Normalized
    // Colors consistently gives values between 0 and 1, while the direct
    // Color Sensor colors are dependent on the specific sensor you're using.
    ColorValue3 = ((NormalizedColorSensor) Color3).getNormalizedColors();
    // Convert the normalized color values to an Android color value.
    myColor1 = ColorValue1.toColor();
    // Convert the normalized color values to an Android color value.
    myColor2 = ColorValue2.toColor();
    // Convert the normalized color values to an Android color value.
    myColor3 = ColorValue3.toColor();
    value1 = JavaUtil.colorToValue(myColor1);
    value2 = JavaUtil.colorToValue(myColor2);
    value3 = JavaUtil.colorToValue(myColor3);
  }

  /**
   * mrrrp mroaw
   */
  private void getBallColorAtPosition(int position) {
    if (position == 1) {
      if (distance1 < detectDistance) {
        if (ColorValue1.green > ColorValue1.blue) {
          ball1 = "Green";
        } else {
          ball1 = "Purple";
        }
      } else {
        ball1 = "Empty";
      }
    } else if (position == 2) {
      if (distance2 < detectDistance) {
        if (ColorValue2.green > ColorValue2.blue) {
          ball2 = "Green";
        } else {
          ball2 = "Purple";
        }
      } else {
        ball2 = "Empty";
      }
    } else if (position == 3) {
      if (distance3 < detectDistance) {
        if (ColorValue3.green > ColorValue3.blue) {
          ball3 = "Green";
        } else {
          ball3 = "Purple";
        }
      } else {
        ball3 = "Empty";
      }
    }
  }

  /**
   * Describe this function...
   */
  private void shootPurpleAt3() {
    shootPurple = 1;
    if (shootPurple == 1 && ball3.equals("Purple")) {
      spinnyToggle2 = 0;
      shootPurple = 0;
      isFull2 = false;
    }
  }

  /**
   * Describe this function...
   */
  private void shootGreenAt3() {
    shootGreen = 1;
    if (shootGreen == 1 && ball3.equals("Green")) {
      spinnyToggle2 = 0;
      shootGreen = 0;
      isFull2 = false;
    }
  }

  /**
   * Describe this function...
   */
  private void isFull() {
    if (isFull2 == false) {
      if (!ball1.equals("Empty") && !ball2.equals("Empty") && !ball3.equals("Empty")) {
        isFull2 = true;
      }
    }
  }

  /**
   * Describe this function...
   */
  private void incrementAprilTag() {
    aprilTag += 1;
    if (aprilTag > 24) {
      aprilTag = 20;
    }
  }
}