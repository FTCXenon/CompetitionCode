package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by David Mindlin on 2/4/2017.
 */
@TeleOp(name = "Use This One(for two)")
public class TwoControllerMecanum extends LinearOpMode {
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    Servo servoLeft;
    Servo servoRight;
    CRServo intakeServo;

    DcMotor ballShooterLeft;
    DcMotor ballShooterRight;
    DcMotor intakeMechanism;

    double rightX;
    double speed;
    double pi = Math.PI;
    double angle;
    double dchange;
    double posRF, posLF, posRB, posLB, maxValue;
    double leftX;
    double leftY;
    double motorPower;
    double drivePower = .70;

    public void runOpMode() throws InterruptedException {
        waitForStart();
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        servoLeft = hardwareMap.servo.get("SL");
        servoRight = hardwareMap.servo.get("SR");
        intakeServo = hardwareMap.crservo.get("IS");
        ballShooterLeft = hardwareMap.dcMotor.get("BSL");
        ballShooterRight = hardwareMap.dcMotor.get("BSR");
        intakeMechanism = hardwareMap.dcMotor.get("IM");
        LB.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.REVERSE);
        while (opModeIsActive()) {
            leftX = gamepad1.left_stick_x;
            leftY = gamepad1.left_stick_y;
            angle = Math.atan2(leftX, -leftY) - (pi / 2);
            speed = Math.sqrt(leftX * leftX + leftY * leftY);
            dchange = -(leftX * leftX) / 3.33;
            posRF = speed * Math.sin(angle + (Math.PI / 4)) + dchange;
            posLF = speed * Math.cos(angle + (Math.PI / 4)) + dchange;
            posRB = speed * Math.cos(angle + (Math.PI / 4)) - dchange;
            posLB = speed * Math.sin(angle + (Math.PI / 4)) - dchange;
            //Set maxValue to pos1 absolute
            maxValue = Math.abs(posRF);

//If pos2 absolute is greater than maxValue, then make maxValue equal to pos2 absolute
            if (Math.abs(posLF) > maxValue) {
                maxValue = Math.abs(posLF);
            }

//If pos3 absolute is greater than maxValue, then make maxValue equal to pos3 absolute
            if (Math.abs(posRB) > maxValue) {
                maxValue = Math.abs(posRB);
            }
//If pos4 absolute is greater than maxValue, then make maxValue equal to pos4 absolute
            if (Math.abs(posLB) > maxValue) {
                maxValue = Math.abs(posLB);
            }

//Check if need to scale -- if not set to 1 to nullify scale
            if (maxValue <= 1) {
                maxValue = 1;
            }
            RF.setPower(posRF / maxValue);
            LF.setPower(posLF / maxValue);
            RB.setPower(posRB / maxValue);
            LB.setPower(posLB / maxValue);
            //Rotate
            //Clockwise
            while (gamepad1.right_stick_x > 0) {
                motorPower = Math.abs(gamepad1.right_stick_x);
                LF.setPower(motorPower);
                RF.setPower(motorPower);
                LB.setPower(-motorPower);
                RB.setPower(-motorPower);
            }
            //Counter-Clockwise
            while (gamepad1.right_stick_x < 0) {
                motorPower = Math.abs(gamepad1.right_stick_x);
                LF.setPower(-motorPower);
                LB.setPower(motorPower);
                RF.setPower(-motorPower);
                RB.setPower(motorPower);
            }
            /*
            if(gamepad1.dpad_right && gamepad1.dpad_up){
                LF.setPower(drivePower);
                RF.setPower(0.0);
                LB.setPower(0.0);
                RB.setPower(drivePower);
            }

            //Back Left
            else if(gamepad1.dpad_left && gamepad1.dpad_down){
                LF.setPower(-drivePower);
                RF.setPower(0.0);
                LB.setPower(0.0);
                RB.setPower(-drivePower);
            }
            //Forward Left
            else if(gamepad1.dpad_left && gamepad1.dpad_up){
                LF.setPower(0.0);
                RF.setPower(drivePower);
                LB.setPower(drivePower);
                RB.setPower(0.0);
            }
            //Back Right
            else if(gamepad1.dpad_right && gamepad1.dpad_down){
                LF.setPower(0.0);
                RF.setPower(-drivePower);
                LB.setPower(-drivePower);
                RB.setPower(0.0);
            }
            //Forward
            else if(gamepad1.dpad_up){
                LF.setPower(drivePower);
                RF.setPower(-drivePower);
                LB.setPower(-drivePower);
                RB.setPower(drivePower);
            }
            //Backwards
            else if(gamepad1.dpad_down){
                LF.setPower(-drivePower);
                RF.setPower(drivePower);
                LB.setPower(drivePower);
                RB.setPower(-drivePower);
            }
            //Right
            else if (gamepad1.dpad_right){
                LF.setPower(drivePower);
                RF.setPower(drivePower);
                LB.setPower(drivePower);
                RB.setPower(drivePower);
            }
            //Left
            else if(gamepad1.dpad_left) {
                LF.setPower(-drivePower);
                RF.setPower(-drivePower);
                LB.setPower(-drivePower);
                RB.setPower(-drivePower);
            }
            //Stop
            else{
                LF.setPower(0.0);
                RF.setPower(0.0);
                LB.setPower(0.0);
                RB.setPower(0.0);
            }
            */
            if (gamepad2.right_trigger > 0.2) {
                ballShooterLeft.setPower(.7);
                ballShooterRight.setPower(-.7);
                //intakeServo.setPower(2.0);
            } else if (gamepad2.right_bumper) {
                ballShooterLeft.setPower(-1.0);
                ballShooterRight.setPower(1.0);
            } else {
                ballShooterLeft.setPower(0.0);
                ballShooterRight.setPower(0.0);
                //intakeServo.setPower(0.0);
            }
            if (gamepad2.a) intakeServo.setPower(2.0);
            else intakeServo.setPower(0.0);
            if (gamepad2.b) intakeServo.setPower(-2.0);
            else intakeServo.setPower(0.0);
            if (gamepad2.left_trigger > 0.2) {
                intakeMechanism.setPower(-1);
            } else if (gamepad2.left_bumper) {
                intakeMechanism.setPower(.60);
            } else {
                intakeMechanism.setPower(0.0);
            }
            if (gamepad2.x) {
                servoLeft.setPosition(1.0);
            } else {
                servoLeft.setPosition(0.4);
            }


            if (gamepad2.y) {
                servoRight.setPosition(.40);
            } else {
                servoRight.setPosition(1.0);
            }
        }
    }
}
