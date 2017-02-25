package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by David Mindlin on 2/4/2017.
 */
@Autonomous(name = "MecAuto")

public class MecanumAutonomous extends LinearOpMode{
    DcMotor LF;
    DcMotor RF;
    DcMotor LB;
    DcMotor RB;

    Servo servoLeft;
    Servo servoRight;
    CRServo intakeServo;

    ColorSensor colorSensor;

    DcMotor ballShooterLeft;
    DcMotor ballShooterRight;
    DcMotor intakeMechanism;
    double pi = Math.PI;
    double driveSpeed = 0.5;
    double wheelDiameter = 4.0;
    double gearReduction = 40.0;
    double countsPerRev = 1120.0;
    double countsPerInch = (countsPerRev*gearReduction)/(wheelDiameter * pi);
    public void encoderDrive(double speed,
                             double LFInches, double LBInches, double RFInches,double RBInches){
        int LFTarget = LF.getCurrentPosition() + (int)(LBInches * countsPerInch);
        int RFTarget = RF.getCurrentPosition() + (int)(RBInches * countsPerInch);
        int LBTarget = LB.getCurrentPosition() + (int)(LBInches * countsPerInch);
        int RBTarget = RB.getCurrentPosition() + (int)(RBInches * countsPerInch);

        LF.setTargetPosition(LFTarget);
        RF.setTargetPosition(RFTarget);
        LB.setTargetPosition(LBTarget);
        RB.setTargetPosition(RBTarget);

        LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        LF.setPower(speed);
        RF.setPower(speed);
        LB.setPower(speed);
        RB.setPower(speed);

        while (opModeIsActive() &&
                ( LF.isBusy() && RF.isBusy()  && LB.isBusy() && RB.isBusy())){
            telemetry.addData("Path1",  "Running to %7d :%7d :%7d :%7d", LFTarget,  RFTarget, LBTarget, RBTarget);
            telemetry.addData("Path2",  "Running at %7d :%7d :%7d :%7d",
                    LF.getCurrentPosition(),
                    RF.getCurrentPosition(),
                    LB.getCurrentPosition(),
                    RB.getCurrentPosition());
            telemetry.update();
        }
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);

        // Turn off RUN_TO_POSITION
        LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sleep(250);
    }
    public void pushButton(String color){
        if((color == "blue" && colorSensor.blue() >= 5.0) || (color == "red" && colorSensor.red() == 5.0)){
            servoRight.setPosition(1.0);
            encoderDrive(3.0, 3.0, 3.0, 3.0, .20);

        }
        else{
            servoLeft.setPosition(1.0);
            encoderDrive(3.0, 3.0, 3.0, 3.0, -.20);
        }
        encoderDrive(3.0, 3.0, 3.0, 3.0, .20);
        servoLeft.setPosition(0);

    }
    void ShootBall() throws InterruptedException{
        ballShooterLeft.setPower(.65);
        ballShooterRight.setPower(-.650);
        Thread.sleep(1000);
        intakeServo.setPower(2.0);
        Thread.sleep(3000);

    }
    void MoveNRotations(double n, double speed){
        int revolutions = (int)(n * countsPerRev);

        LF.setMode(DcMotor.RunMode.RESET_ENCODERS);
        RF.setMode(DcMotor.RunMode.RESET_ENCODERS);
        LB.setMode(DcMotor.RunMode.RESET_ENCODERS);
        RB.setMode(DcMotor.RunMode.RESET_ENCODERS);

        while (opModeIsActive() &&
                (Math.abs(LF.getCurrentPosition()) < revolutions && Math.abs(RF.getCurrentPosition()) < revolutions && Math.abs(LB.getCurrentPosition()) < revolutions && Math.abs(RB.getCurrentPosition()) < revolutions)){
            LF.setPower(speed);
            RF.setPower(speed);
            LB.setPower(speed);
            RB.setPower(speed);
            telemetry.addData("LF encoder value:", LF.getCurrentPosition());
            telemetry.addData("RF encoder value:", RF.getCurrentPosition());
            telemetry.addData("LB encoder value:", LB.getCurrentPosition());
            telemetry.addData("RB encoder value:", RB.getCurrentPosition());
            telemetry.update();
        }
        LF.setPower(0);
        RF.setPower(0);
        LB.setPower(0);
        RB.setPower(0);

    }
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

        colorSensor = hardwareMap.colorSensor.get("CS");
        colorSensor.enableLed(false);

        LB.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.REVERSE);
        while(opModeIsActive()){
            MoveNRotations(1.0, .2);
        }
    }

}
