package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by David Mindlin on 11/16/2016.
 */
@Autonomous(name = "ColorCalibrate")
public class ColorCalibrate extends LinearOpMode{

    ColorSensor CSS;
    double red;
    double blue;
    double green;
    @Override public void runOpMode ()throws InterruptedException {
        waitForStart();
        CSS = hardwareMap.colorSensor.get("CSS");

        while(opModeIsActive()){

            red = CSS.red();
            blue = CSS.blue();
            green = CSS.green();

            telemetry.update();
            telemetry.addData("Red", red);
            telemetry.addData("Blue", blue);
            telemetry.addData("Green", green);

        }
    }



}
