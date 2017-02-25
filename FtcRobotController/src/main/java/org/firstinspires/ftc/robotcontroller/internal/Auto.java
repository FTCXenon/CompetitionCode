package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

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
    DcMotor ballShooterLeft;
    DcMotor ballShooterRight;
    Servo rightServo;
    Servo leftServo;
    CRServo intakeServo;
    //GyroSensor gyroSensor;

    //ColorSensor colorSensor;

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
        RB.setPower(.8);
        LB.setPower(.8);
        currentRight = RF.getCurrentPosition();
        currentLeft = LF.getCurrentPosition();
        while((Math.abs(currentLeft)<targetValue) && (Math.abs(currentRight)<targetValue)){

            currentRight = RF.getCurrentPosition();
            currentLeft = LF.getCurrentPosition();

            LF.setPower(.80);
            RF.setPower(.80);
            LB.setPower(.80);
            RB.setPower(.80);


            telemetry.update();
            telemetry.addData("right front", RF.getCurrentPosition());
            telemetry.addData("left front", LF.getCurrentPosition());
        }
        RF.setPower(0.0);
        LF.setPower(0.0);
        RB.setPower(0.0);
        LB.setPower(0.0);

    }

    void TurnDegrees(int angle){
        //currentHeading = gyroSensor.getHeading();
        targetHeading = currentHeading + angle;
        while(currentHeading != targetHeading){
            if(targetHeading > currentHeading){
                angleDifference = targetHeading - currentHeading;
                turnPower = angleDifference *  gain;

                RF.setPower(-turnPower);
                LF.setPower(turnPower);
                RB.setPower(-turnPower);
                LB.setPower(turnPower);

            }
            else if(targetHeading < currentHeading){
                angleDifference = currentHeading - targetHeading;
                turnPower = angleDifference *  gain;

                RF.setPower(turnPower);
                LF.setPower(-turnPower);
                RB.setPower(turnPower);
                LB.setPower(-turnPower);
            }
            //telemetry.addData("gyro", gyroSensor.getHeading());
        }


    }
    boolean SenseColor(String color){
        //blue = colorSensor.blue();
        //red  = colorSensor.red();
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
    void DriveTime(long time)throws InterruptedException{
        double motorPower = .70;
        RF.setPower(-motorPower);
        LF.setPower(motorPower);
        RB.setPower(-motorPower);
        LB.setPower(motorPower);
        Thread.sleep(time-1000);
        for(int i = 0; i < 1000; i += 100) {
            motorPower -= .07;

            RF.setPower(-motorPower);
            LF.setPower(motorPower);
            RB.setPower(-motorPower);
            LB.setPower(motorPower);
            Thread.sleep(100);
        }
        RF.setPower(0.0);
        LF.setPower(0.0);
        RB.setPower(0.0);
        LB.setPower(0.0);
    }
    void ShootBall()throws InterruptedException{

        ballShooterLeft.setPower(.65);
        ballShooterRight.setPower(-.65);
        Thread.sleep(2000);
        intakeServo.setPower(2.0);
        Thread.sleep(1500);
        ballShooterLeft.setPower(0.0);
        ballShooterRight.setPower(0.0);
        intakeServo.setPower(0.0);

    }
    void PushButton(){

    }
    @Override public void runOpMode() throws InterruptedException {
        waitForStart();
        LF = hardwareMap.dcMotor.get("LF");
        RF = hardwareMap.dcMotor.get("RF");
        LB = hardwareMap.dcMotor.get("LB");
        RB = hardwareMap.dcMotor.get("RB");
        ballShooterLeft = hardwareMap.dcMotor.get("BSL");
        ballShooterRight = hardwareMap.dcMotor.get("BSR");
        //gyroSensor = hardwareMap.gyroSensor.get("GY");
        //colorSensor = hardwareMap.colorSensor.get("CS");
        intakeServo = hardwareMap.crservo.get("IS");
        ShootBall();
        DriveTime(4600);
    }


}