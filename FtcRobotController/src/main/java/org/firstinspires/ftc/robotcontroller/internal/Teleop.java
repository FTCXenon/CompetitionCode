package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

@TeleOp(name = "TeleOp")
public class Teleop extends LinearOpMode {
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    DcMotor ballShooter;
    DcMotor intakeMechanism;

    GyroSensor gyroSensor;
    ColorSensor colorSensor;

    double rightPower = 0;
    double leftPower = 0;

    @Override public void runOpMode() throws InterruptedException{
        waitForStart();

        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        ballShooter = hardwareMap.dcMotor.get("BS");
        intakeMechanism = hardwareMap.dcMotor.get("IM");


        LF.setDirection(DcMotor.Direction.REVERSE);
        LB.setDirection(DcMotor.Direction.REVERSE);

        colorSensor = hardwareMap.colorSensor.get("CS");
        gyroSensor = hardwareMap.gyroSensor.get("GY");

        telemetry.addData("", "Gyro Calibrating. Do Not move!");
        telemetry.update();
        while (gyroSensor.isCalibrating()) {
            Thread.sleep(50);
            idle();
        }

        while (opModeIsActive()){

            if (gamepad2.left_trigger < 0.2){
                ballShooter.setPower(1);
            }else {
                ballShooter.setPower(0);
            }

            if (gamepad2.right_trigger < 0.2){
                intakeMechanism.setPower(1);
            }else {
                intakeMechanism.setPower(0);
            }

            leftPower = gamepad1.left_stick_y;
            rightPower = gamepad1.right_stick_y;

            if (rightPower < 0.0) {
                rightPower = 0.0;
            }
            if (leftPower < 0.0) {
                leftPower = 0.0;
            }
            if (rightPower > 1.0) {
                rightPower = 1.0;
            }
            if (leftPower > 1.0) {
                leftPower = 1.0;
            }

            LF.setPower(leftPower);
            RF.setPower(rightPower);
            LB.setPower(leftPower);
            RB.setPower(rightPower);
        }



    }
}
