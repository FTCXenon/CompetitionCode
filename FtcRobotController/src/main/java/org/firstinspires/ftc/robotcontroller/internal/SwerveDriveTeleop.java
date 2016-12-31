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


    DcMotor ballShooter;
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

        ballShooter = hardwareMap.dcMotor.get("BS");
        intakeMechanism = hardwareMap.dcMotor.get("IM");

        while(opModeIsActive()){

            Joystick_X = gamepad1.left_stick_x;
            Joystick_Y = gamepad1.left_stick_y;
            magnitude = Math.sqrt(Joystick_X*Joystick_X + Joystick_Y * Joystick_Y);

            if(magnitude > 0) {
                if(Joystick_X == 0){
                    if(Joystick_Y > 0){



                    }
                    else if(Joystick_Y < 0){



                    }

                }
                else if(Joystick_Y == 0){
                    if(Joystick_X > 0){

                    }
                    else if(Joystick_X < 0){



                    }

                }
                else {
                    angle = Math.atan(Joystick_Y / Joystick_X);
                }

            }



        }

    }




}
