package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOp")
public class Teleop extends LinearOpMode {
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    Servo servo1;
    Servo servo2;

    DcMotor ballShooter;
    DcMotor intakeMechanism;


    double rightPower = 0;
    double leftPower = 0;

    @Override public void runOpMode() throws InterruptedException{
        waitForStart();

        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        servo1 = hardwareMap.servo.get("servo1");
        servo2 = hardwareMap.servo.get("servo2");

        ballShooter = hardwareMap.dcMotor.get("BS");
        intakeMechanism = hardwareMap.dcMotor.get("IM");

        LF.setDirection(DcMotor.Direction.REVERSE);
        LB.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("", "Gyro Calibrating. Do Not move!");
        telemetry.update();


        while (opModeIsActive()){

            if (gamepad1.right_trigger > 0.2){
                ballShooter.setPower(-1);
            }else if (gamepad1.right_bumper){
                ballShooter.setPower(1);
            } else {
                ballShooter.setPower(0);
            }

            if (gamepad1.left_trigger > 0.2){
                intakeMechanism.setPower(-1);
            }else if (gamepad1.left_bumper){
                intakeMechanism.setPower(1);
            } else {
                intakeMechanism.setPower(0);
            }

            leftPower = -gamepad1.left_stick_y;
            rightPower = -gamepad1.right_stick_y;

            if(leftPower>1.0) leftPower = 1.0;
            if(rightPower>1.0) rightPower = 1.0;

            LF.setPower(leftPower);
            RF.setPower(rightPower);
            LB.setPower(leftPower);
            RB.setPower(rightPower);


            if (gamepad1.x){
                servo2.setPosition(1);
            } else {
                servo2.setPosition(0.4);
            }
            if (gamepad1.a){
                servo2.setPosition(0.4);
            }

            if (gamepad1.y){
                servo1.setPosition(0.2);
            } else {
                servo1.setPosition(1);
            }
            if (gamepad1.b) {
                servo1.setPosition(1);
            }

        }



    }
}
