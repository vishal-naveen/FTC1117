package org.firstinspires.ftc.teamcode;

import  static org.firstinspires.ftc.teamcode.ColorDetector.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Rect;




//@Autonomous(name = "testauto1", group="test")
public class testauto extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    ColorDetector detector;

    VisionPortal visionPoral;


    private int frontRightPos;
    private int frontLeftPos;
//    private int backRightPos;
//    private int backLeftPos;






    @Override
    public void runOpMode(){

        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        backRight = hardwareMap.get(DcMotor.class, "BR");

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


//        Rect leftZone = centerRect(70,280,140,150);
//        Rect midZone = centerRect(260,280,140,150);
//        Rect rightZone = centerRect(535,290,140,150);
//        detector = new ColorDetector(telemetry, TargetColor.RED, ColorDetector.ViewMode.RAW, leftZone, midZone, rightZone);
//        visionPoral = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), detector);
//        CameraStreamServer.getInstance().setSource(detector);
//
//        telemetry.addData("Detection captured:",detector.getConfidentDetection());
//        Detection detection = detector.getConfidentDetection();





//        frontLeft
//        frontRight
//        backLeft
//        backRight


//        frontLeft.setTargetPosition(1000);
//        frontRight.setTargetPosition(2000);
//        backLeft.setTargetPosition(-5000);
//        backRight.setTargetPosition(15000);
//
//        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        drive(1500,1500,1500,1500,0.5);
//        setZero(250);
        driveLeft(1000,0.3);
//        setZero(250);
        drive(1500,1500,1500,1500,0.4);
//        setZero(250);
        driveRight(1000,0.4);
//        setZero(250);

//        frontLeft.setPower(1);
//        frontRight.setPower(1);
//        backLeft.setPower(1);
//        backRight.setPower(1);

        while (opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy()) {
            // Wait for the motors to reach their target positions
            telemetry.addData("Encoder PositionFL", frontLeft.getCurrentPosition());
            telemetry.addData("Encoder PositionFR", frontRight.getCurrentPosition());
            telemetry.update();
        }


//        while(!detector.isDetectionConfident()) {}
//        switch (detector.getDetection()){
//            case RIGHT:
//                break;
//            case MIDDLE:
//                break;
//            case NONE:
////                drive(500,500,500,500,0.5);
//                break;
//        }





    }


    public void drive(int frontLeftT, int frontRightT, int backLeftT, int backRightT, double power){
//        frontLeftPos = frontLeftT;
//        frontRightPos = frontRightT;
//        backLeftPos = backLeftT;
//        backRightPos = backRightT;



        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setTargetPosition(frontLeftT);
        frontRight.setTargetPosition(frontRightT);
        backLeft.setTargetPosition(backLeftT);
        backRight.setTargetPosition(backRightT);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);



    }

    public void driveLeft(int frontRightT, double power){
//        frontLeftPos = frontLeftT;
//        frontRightPos = frontRightT;
//        backLeftPos = backLeftT;
//        backRightPos = backRightT;



//        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//        frontLeft.setTargetPosition(frontLeftT);/**/
        frontRight.setTargetPosition(frontRightT);
//        backLeft.setTargetPosition(backLeftT);
//        backRight.setTargetPosition(backRightT);

//        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        frontLeft.setPower(power);
        frontRight.setPower(power);
//        backLeft.setPower(power);
//        backRight.setPower(power);



    }

    public void driveRight(int frontLeftT, double power){
//        frontLeftPos = frontLeftT;
//        frontRightPos = frontRightT;
//        backLeftPos = backLeftT;
//        backRightPos = backRightT;



        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setTargetPosition(frontLeftT);
//        frontRight.setTargetPosition(frontRightT);
//        backLeft.setTargetPosition(backLeftT);
//        backRight.setTargetPosition(backRightT);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
//        frontRight.setPower(power);
//        backLeft.setPower(power);
//        backRight.setPower(power);



    }

    public void setZero(long time){
        frontLeft.setPower(0);
        frontRight.setPower(0);

        sleep(time);
    }




}
