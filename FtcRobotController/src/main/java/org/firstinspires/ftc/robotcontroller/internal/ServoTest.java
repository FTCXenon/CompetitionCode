package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "ServoTest")
public class ServoTest extends LinearOpMode {
    Servo servo1;
    Servo servo2;

    @Override public void runOpMode() throws InterruptedException{
        waitForStart();

        servo1 = hardwareMap.servo.get("servo1");
        servo2 = hardwareMap.servo.get("servo2");


        while (opModeIsActive()){
            if (gamepad1.x){
                servo2.setPosition(0.4);
            } else {
                servo2.setPosition(1);
            }
            if (gamepad1.a){
                servo2.setPosition(1);
            }

            if (gamepad1.y){
                servo1.setPosition(1);
            } else {
                servo1.setPosition(.2);
            }
            if (gamepad1.b) {
                servo1.setPosition(.2);
            }
        }
    }
}
