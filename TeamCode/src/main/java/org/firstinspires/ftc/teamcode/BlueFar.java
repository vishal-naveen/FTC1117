package org.firstinspires.ftc.teamcode;

import  static org.firstinspires.ftc.teamcode.ColorDetector.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Rect;



//@Autonomous(name = "BlueFar", group="Auto")
public class BlueFar extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

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

        Rect leftZone = centerRect(0,0,0,0);
        Rect midZone = centerRect(190,280,140,150);
        Rect rightZone = centerRect(535,310,140,200);
        detector = new ColorDetector(telemetry, TargetColor.BLUE, ColorDetector.ViewMode.RAW, leftZone, midZone, rightZone);
        visionPoral = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), detector);
        CameraStreamServer.getInstance().setSource(detector);

        telemetry.addData("Detection captured:",detector.getConfidentDetection());
        Detection detection = detector.getConfidentDetection();

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        moveForward(1,100);



        while(!detector.isDetectionConfident()) {}
        switch (detector.getDetection()){
            case RIGHT:
                moveForward(0.4,850);
                setZero(250);
                turnRight(0.5,750);
                setZero(250);
                moveForward(0.2,100);
                setZero(250);
                moveBackward(0.6,150);
                setZero(250);
//                turnLeft(0.4,700);
//                setZero(250);
//                moveBackward(0.4,650);
//                setZero(250);
//                strafeRight(0.5,3500);
//                setZero(250);

                break;
            case MIDDLE:
                moveForward(0.5,1200);
                setZero(250);
                moveBackward(0.5,950);
                setZero(250);
//                strafeRight(0.5,3750);
                break;
            case NONE:
                frontLeft.setTargetPosition(5000);
                frontRight.setTargetPosition(1000);
                backLeft.setTargetPosition(-1000);
                backRight.setTargetPosition(1000);

                frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                frontLeft.setPower(0.1);
                frontRight.setPower(0.5);
                backLeft.setPower(0.5);
                backRight.setPower(0.5);

//                moveForward(0.3,100);
//                setZero(250);
//                turnLeft(0.3,330);
//                setZero(250);
//                moveForward(0.6,800);
//                setZero(250);
//                moveBackward(0.3,500);
//                setZero(250);
//                turnRight(0.3,330);
//                setZero(250);
//                moveBackward(0.6,450);
////                setZero(250);
////                strafeRight(0.5,3850);
////                setZero(250);
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
