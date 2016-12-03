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

    GyroSensor gyroSensor;

    ColorSensor colorSensor;

    double speed = 0.2;
    double targetHeading = 0.0;
    double gain = 0.007;
    double angleDifference;
    double leftPower;
    double rightPower;
    double turnPower = 0;

    double initialvalueLF;
    double initialvalueRF;
    double targetValue;
    double currentRight;
    double currentLeft;
    double currentHeading = 0.0;

    double red;
    double blue;
    double green;
    boolean teamcolor;
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
            telemetry.addData("left front", LF.getCurrentPosition());
        }


    }

    void TurnDegrees(int angle){
        currentHeading = gyroSensor.getHeading();
        targetHeading = currentHeading + angle;
        while(currentHeading != targetHeading){
            if(targetHeading > currentHeading){
                angleDifference = targetHeading - currentHeading;
                turnPower = angleDifference *  gain;

                RF.setPower(-turnPower);
                LF.setPower(turnPower);

            }
            else if(targetHeading < currentHeading){
                angleDifference = currentHeading - targetHeading;
                turnPower = angleDifference *  gain;

                RF.setPower(turnPower);
                LF.setPower(-turnPower);
            }
            telemetry.addData("gyro", gyroSensor.getHeading());
        }


    }
    boolean SenseColor(String color){
        blue = colorSensor.blue();
        red  = colorSensor.red();
        if(color == "blue") {
            if(blue > 0.0) {

                teamcolor = true;

            }
            else {
                teamcolor = false;
            }
        }
        if(color== "red"){
            if(red > 0.0){

                teamcolor = true;

            }
            else{
                teamcolor = false;
            }


        }
        return teamcolor;
    }

    @Override public void runOpMode() throws InterruptedException {
        waitForStart();
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");
        gyroSensor = hardwareMap.gyroSensor.get("GY");
        colorSensor = hardwareMap.colorSensor.get("CS");

        DriveDistance(5);
        TurnDegrees(45);
        TurnDegrees(-45);
    }


}


