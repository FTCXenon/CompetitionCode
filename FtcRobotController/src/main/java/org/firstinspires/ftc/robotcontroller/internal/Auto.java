package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import java.lang.Math;
/**
 * Created by David Mindlin on 11/16/2016.
 */
@Autonomous (name = "Auto")
public class Auto extends LinearOpMode {
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    double speed = 0.2;
    double targetHeading = 0.0;
    double gain = 0.007;
    double steeringError;
    double leftPower;
    double rightPower;
    int currentHeading = 0;
    double steeringAdjustment = 0;

    double initialvalueLF;
    double initialvalueRF;
    double targetValue;
    double currentRight;
    double currentLeft;
    void DriveDistance(double distance) {

        double targetValue = (distance / (3.141592653589793238462 * 4)) * 1440;
        RF.setPower(.8);
        LF.setPower(.8);
        currentRight = RF.getCurrentPosition();
        currentLeft = LF.getCurrentPosition();
        while((Math.abs(currentLeft)<targetValue) && (Math.abs(currentRight)<targetValue)){

            currentRight = RF.getCurrentPosition();
            currentLeft = LF.getCurrentPosition();

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
            telemetry.update();
            telemetry.addData("right front", RF.getCurrentPosition());
        }


    }

    void TurnDegrees(int angle){



    }
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");
        double initialvalueRF = RF.getCurrentPosition();
        double initialvalueLF = LF.getCurrentPosition();
    }


}


