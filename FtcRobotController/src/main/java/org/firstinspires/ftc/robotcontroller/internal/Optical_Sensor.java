
package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

@TeleOp(name = "Sensor: MR ODS", group = "Sensor")
public class Optical_Sensor extends LinearOpMode {

    OpticalDistanceSensor ODS;  // Hardware Device Object

    @Override
    public void runOpMode() throws InterruptedException {

        // get a reference to our Light Sensor object.
        ODS = hardwareMap.opticalDistanceSensor.get("ODS");

        // wait for the start button to be pressed.
        waitForStart();

        // while the op mode is active, loop and read the light levels.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        while (opModeIsActive()) {

            // send the info back to driver station using telemetry function.
            telemetry.addData("Raw",    ODS.getRawLightDetected());
            telemetry.addData("Normal", ODS.getLightDetected());

            telemetry.update();
            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }
}