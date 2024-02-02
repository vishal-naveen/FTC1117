package org.firstinspires.ftc.teamcode;

import  static org.firstinspires.ftc.teamcode.ColorDetector.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Rect;



@Autonomous(name = "BlueFarNoPixel", group="Auto")
public class BlueFarNoPixel extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    private DcMotor actuatorMotor = null;

    private DcMotor actuatorMotor1 = null;

    private Servo clawServo1 = null;
    private  Servo clawServo2 = null;
    private  Servo wristServo = null;

    ColorDetector detector;

    VisionPortal visionPoral;




    @Override
    public void runOpMode() throws InterruptedException{

        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        backRight = hardwareMap.get(DcMotor.class, "BR");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        actuatorMotor = hardwareMap.get(DcMotor.class, "actuatorMotor");
        actuatorMotor1 = hardwareMap.get(DcMotor.class, "actuatorMotor1");

        actuatorMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        clawServo1 = hardwareMap.get(Servo.class, "clawServo1");
        clawServo2 = hardwareMap.get(Servo.class, "clawServo2");
        wristServo = hardwareMap.get(Servo.class, "wristServo");

        actuatorMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        actuatorMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        Rect leftZone = centerRect(0,0,0,0);
        Rect midZone = centerRect(190,280,140,150);
        Rect rightZone = centerRect(535,310,140,200);
        detector = new ColorDetector(telemetry, TargetColor.BLUE, ColorDetector.ViewMode.RAW, leftZone, midZone, rightZone);
        visionPoral = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), detector);
        CameraStreamServer.getInstance().setSource(detector);

        telemetry.addData("Detection captured:",detector.getConfidentDetection());
        Detection detection = detector.getConfidentDetection();

        motoranglestart();

        waitForStart();

        moveForward(1,100);



        while(!detector.isDetectionConfident()) {}
        switch (detector.getDetection()){
            case RIGHT:
                moveForward(0.4,150);
                setZero(450);
                strafeRight(0.4,300);
                setZero(450);
                moveForward(0.5,400);
                setZero(450);
                moveBackward(0.5,400);
                setZero(450);
                strafeLeft(0.4,300);
                setZero(450);
                moveBackward(0.4,100);
                setZero(450);
                motorangleclosed();
                setZero(7500);
                strafeLeft(0.4,1700);
                setZero(450);
                clawServo1.setPosition(0.5);
                setZero(1000);
                clawServo2.setPosition(0.5);
                setZero(1000);
                clawServo1.setPosition(0.3);
                setZero(1000);
                clawServo2.setPosition(0.3);
                setZero(1000);
                break;
            case MIDDLE:
                moveForward(0.5,1200);
                setZero(450);
                moveBackward(0.5,1150);
                setZero(450);
                motorangleclosed();
                setZero(7500);
                strafeLeft(0.4,1700);
                setZero(450);
                clawServo1.setPosition(0.5);
                setZero(1000);
                clawServo2.setPosition(0.5);
                setZero(1000);
                clawServo1.setPosition(0.3);
                setZero(1000);
                clawServo2.setPosition(0.3);
                setZero(1000);
                break;
            case NONE:
                moveForward(0.4,850);
                setZero(450);
                motorangleclosed();
                setZero(450);
                turnLeft(0.4,750);
                setZero(450);
                moveForward(0.5,100);
                setZero(450);
                moveBackward(0.6,100);
                setZero(450);
                strafeLeft(0.4,725);
                setZero(450);
                moveForward(0.4,1600);
                setZero(1000);
                clawServo1.setPosition(0.5);
                setZero(450);
                clawServo2.setPosition(0.5);
                setZero(450);
                clawServo1.setPosition(0.3);
                setZero(450);
                clawServo2.setPosition(0.3);
                setZero(1000);
                break;
        }

//        switch (detector.getConfidentDetection()){
//            case RIGHT:
//                turnRight(0.2,1000);
//                setZero();
//                break;
//            case MIDDLE:
//                moveForward(0.5,1000);
//                setZero();
//                turnLeft(0.5,1000);
//                setZero();
//                turnRight(0.5,1000);
//                setZero();
//                moveBackward(0.5,1000);
//                setZero();
//                break;
//        }
//
//        switch (detector.getUnconfidentDetection()){
//            case NONE:
//                moveForward(0.5,1000);
//                setZero();
//                turnLeft(0.2,1000);
//                setZero();
//                turnRight(0.5,1000);
//        }


    }

    public void motorangle(){
        actuatorMotor1.setTargetPosition(-725);
        actuatorMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        actuatorMotor1.setPower(0.4);
    }
    public void motorangleclosed(){
        actuatorMotor1.setTargetPosition(-30);
        actuatorMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        actuatorMotor1.setPower(0.1);
    }
    public void motoranglestart(){
        actuatorMotor1.setTargetPosition(-500);
        actuatorMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        actuatorMotor1.setPower(0.4);
    }

    public void strafeLeft(double power, long time){
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);

        sleep(time);
    }
    public void strafeRight(double power, long time){
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        sleep(time);
    }


    public void moveForward(double power, long time){
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        sleep(time);
    }

    public void moveBackward(double power, long time){
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);

        sleep(time);
    }

    public void turnLeft(double power, long time){
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        sleep(time);
    }

    public void turnRight(double power, long time){
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);

        sleep(time);
    }

    public void setZero(long time){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        sleep(time);
    }


}
