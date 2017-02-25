package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by David Mindlin on 2/5/2017.
 */
@Autonomous(name = "Ball Only")
public class BallOnly extends LinearOpMode{
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;
    DcMotor ballShooterLeft;
    DcMotor ballShooterRight;
    DcMotor intakeServo;
    void ShootBall() throws InterruptedException {
        ballShooterLeft.setPower(.65);
        ballShooterRight.setPower(-.650);
        Thread.sleep(1000);
        intakeServo.setPower(2.0);
        Thread.sleep(3000);
        ballShooterLeft.setPower(0.0);
        ballShooterRight.setPower(0.0);
        ballShooterRight.setPower(0.0);
        intakeServo.setPower(0.0);


    }
    @Override public void runOpMode() throws InterruptedException {
        waitForStart();
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");
        intakeServo = hardwareMap.dcMotor.get("IS");
        ballShooterLeft = hardwareMap.dcMotor.get("BSL");
        ballShooterRight = hardwareMap.dcMotor.get("BSR");
        //gyroSensor = hardwareMap.gyroSensor.get("GY");
        //colorSensor = hardwareMap.colorSensor.get("CS");
        ShootBall();
    }
}
