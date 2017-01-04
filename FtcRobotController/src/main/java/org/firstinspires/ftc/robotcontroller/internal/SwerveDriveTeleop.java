package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.Math;

/**
 * Created by David Mindlin on 12/28/2016.
 */

public class SwerveDriveTeleop extends LinearOpMode{
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    Servo SLF;
    Servo SRF;
    Servo SLB;
    Servo SRB;


    Servo servo1;
    Servo servo2;

    double Joystick_Y;
    double Joystick_X;
    double magnitude;
    double angle;
    double motorPower;

    DcMotor ballShooterLeft;
    DcMotor ballShooterRight;
    DcMotor intakeMechanism;

    @Override public void runOpMode() throws InterruptedException {
        waitForStart();

        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");

        servo1 = hardwareMap.servo.get("servo1");
        servo2 = hardwareMap.servo.get("servo2");

        SLF = hardwareMap.servo.get("SLF");
        SRF = hardwareMap.servo.get("SRF");
        SLB = hardwareMap.servo.get("SLB");
        SRB = hardwareMap.servo.get("SRB");

        ballShooterLeft = hardwareMap.dcMotor.get("BSL");
        ballShooterRight = hardwareMap.dcMotor.get("BSR");
        intakeMechanism = hardwareMap.dcMotor.get("IM");

        while(opModeIsActive()){
            //Joystick Strafing
            Joystick_X = gamepad1.left_stick_x;
            Joystick_Y = gamepad1.left_stick_y;
            magnitude = Math.sqrt(Joystick_X*Joystick_X + Joystick_Y * Joystick_Y);

            if(magnitude > 0) {
                if(Joystick_X == 0){
                    if(Joystick_Y > 0){
                        motorPower = magnitude;
                    }
                    else if(Joystick_Y < 0){
                        motorPower = -magnitude;
                    }

                }
                else if(Joystick_Y == 0){
                    if(Joystick_X > 0){
                        motorPower = magnitude;
                    }
                    else if(Joystick_X < 0){
                        motorPower = -magnitude;

                    }
                }
                else {
                    //Figuring out the tire angle based on joystick position.

                    angle = Math.atan(Joystick_Y / Joystick_X);

                    //If the hypotenuse is in the II or III quadrant, add 180 degrees, otherwise, do nothing.

                    if(Joystick_X < 0 && Joystick_Y > 0){

                        angle += 180;

                    }
                    else if(Joystick_X < 0 && Joystick_Y < 0){

                        angle += 180;

                    }
                }

            }


            if(gamepad1.right_trigger > 0) {
                ballShooterLeft.setPower(.60);
                ballShooterRight.setPower(.60);
            }
            else {
                ballShooterLeft.setPower(0.0);
                ballShooterRight.setPower(0.0);
            }


            if(magnitude == 0)motorPower = 0;

            LF.setPower(motorPower);
            RF.setPower(motorPower);
            LB.setPower(motorPower);
            RB.setPower(motorPower);

        }

    }




}
