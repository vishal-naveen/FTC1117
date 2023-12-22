//package org.firstinspires.ftc.teamcode;
//
//import  static org.firstinspires.ftc.teamcode.ColorDetector.*;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
//import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.opencv.core.Rect;
//
//
//
//
//@Autonomous(name = "testauto", group="test")
//public class testauto extends LinearOpMode {
//
//    private DcMotor frontLeft;
//    private DcMotor frontRight;
//    private DcMotor backLeft;
//    private DcMotor backRight;
//
//    ColorDetector detector;
//
//    VisionPortal visionPoral;
//
//
//    private int frontRightPos;
//    private int frontLeftPos;
//    private int backRightPos;
//    private int backLeftPos;
//
//
//
//    @Override
//    public void runOpMode() throws InterruptedException{
//
//        frontLeft = hardwareMap.get(DcMotor.class, "FL");
//        frontRight = hardwareMap.get(DcMotor.class, "FR");
//        backLeft = hardwareMap.get(DcMotor.class, "BL");
//        backRight = hardwareMap.get(DcMotor.class, "BR");
//
//        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//
//        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        Rect leftZone = centerRect(70,280,140,150);
//        Rect midZone = centerRect(260,280,140,150);
//        Rect rightZone = centerRect(535,290,140,150);
//        detector = new ColorDetector(telemetry, TargetColor.RED, ColorDetector.ViewMode.RAW, leftZone, midZone, rightZone);
//        visionPoral = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), detector);
//        CameraStreamServer.getInstance().setSource(detector);
//
//        telemetry.addData("Detection captured:",detector.getConfidentDetection());
//        Detection detection = detector.getConfidentDetection();
//
//        frontLeftPos = 0;
//        frontRightPos = 0;
//        backLeftPos = 0;
//        backRightPos = 0;
//
//        waitForStart();
//
//        moveForward(0.1,100);
//
//
//
//        while(!detector.isDetectionConfident()) {}
//        switch (detector.getDetection()){
//            case RIGHT:
//                driveRight(100,100,100,100,0.2, 100);
//                setZero();
//                driveForward(100,100,100,100,0.2, 100);
//                setZero();
//                break;
//            case MIDDLE:
//                driveForward(100,100,100,100,0.2, 100);
//                setZero();
//                driveLeft(100,100,100,100,0.2, 100);
//                setZero();
//                break;
//            case NONE:
//                driveBackward(100,100,100,100,0.2, 100);
//                setZero();
//                driveRight(100,100,100,100,0.2, 100);
//                setZero();
//                break;
//        }
//
////        switch (detector.getConfidentDetection()){
////            case RIGHT:
////                turnRight(0.2,1000);
////                setZero();
////                break;
////            case MIDDLE:
////                moveForward(0.5,1000);
////                setZero();
////                turnLeft(0.5,1000);
////                setZero();
////                turnRight(0.5,1000);
////                setZero();
////                moveBackward(0.5,1000);
////                setZero();
////                break;
////        }
////
////        switch (detector.getUnconfidentDetection()){
////            case NONE:
////                moveForward(0.5,1000);
////                setZero();
////                turnLeft(0.2,1000);
////                setZero();
////                turnRight(0.5,1000);
////        }
//
//
//    }
//
//
//
//
//    private void driveForward(int frontLeftT, int frontRightT, int backLeftT, int backRightT, double power, long time){
//        frontLeftPos += frontLeftT;
//        frontRightPos += frontRightT;
//        backLeftPos += backLeftT;
//        backRightPos += backRightT;
//
//        frontLeft.setTargetPosition(frontLeftPos);
//        frontRight.setTargetPosition(frontRightPos);
//        backLeft.setTargetPosition(backLeftPos);
//        backRight.setTargetPosition(backRightPos);
//
//        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        frontLeft.setPower(power);
//        frontRight.setPower(power);
//        backLeft.setPower(power);
//        backRight.setPower(power);
//
//        sleep(time);
//
//        while(opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){
//            idle();
//        }
//
//    }
//
//    private void driveBackward(int frontLeftT, int frontRightT, int backLeftT, int backRightT, double power, long time){
//        frontLeftPos += frontLeftT;
//        frontRightPos += frontRightT;
//        backLeftPos += backLeftT;
//        backRightPos += backRightT;
//
//        frontLeft.setTargetPosition(-frontLeftPos);
//        frontRight.setTargetPosition(-frontRightPos);
//        backLeft.setTargetPosition(-backLeftPos);
//        backRight.setTargetPosition(-backRightPos);
//
//        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        frontLeft.setPower(-power);
//        frontRight.setPower(-power);
//        backLeft.setPower(-power);
//        backRight.setPower(-power);
//
//        sleep(time);
//
//        while(opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){
//            idle();
//        }
//
//    }
//
//    private void driveLeft(int frontLeftT, int frontRightT, int backLeftT, int backRightT, double power, long time){
//        frontLeftPos += frontLeftT;
//        frontRightPos += frontRightT;
//        backLeftPos += backLeftT;
//        backRightPos += backRightT;
//
//        frontLeft.setTargetPosition(-frontLeftPos);
//        frontRight.setTargetPosition(frontRightPos);
//        backLeft.setTargetPosition(-backLeftPos);
//        backRight.setTargetPosition(backRightPos);
//
//        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        frontLeft.setPower(-power);
//        frontRight.setPower(power);
//        backLeft.setPower(-power);
//        backRight.setPower(power);
//
//        sleep(time);
//
//        while(opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){
//            idle();
//        }
//
//    }
//
//    public void setZero(){
//        frontLeft.setPower(0);
//        frontRight.setPower(0);
//        backLeft.setPower(0);
//        backRight.setPower(0);
//    }
//
//    private void driveRight(int frontLeftT, int frontRightT, int backLeftT, int backRightT, double power, long time){
//        frontLeftPos += frontLeftT;
//        frontRightPos += frontRightT;
//        backLeftPos += backLeftT;
//        backRightPos += backRightT;
//
//        frontLeft.setTargetPosition(frontLeftPos);
//        frontRight.setTargetPosition(-frontRightPos);
//        backLeft.setTargetPosition(backLeftPos);
//        backRight.setTargetPosition(-backRightPos);
//
//        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        frontLeft.setPower(power);
//        frontRight.setPower(-power);
//        backLeft.setPower(power);
//        backRight.setPower(-power);
//
//        sleep(time);
//
//        while(opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){
//            idle();
//        }
//
//    }
//
//
//
//
//
//    public void moveForward(double power, long time){
//        frontLeft.setPower(power);
//        frontRight.setPower(power);
//        backLeft.setPower(power);
//        backRight.setPower(power);
//
//        sleep(time);
//    }
//
//    public void moveBackward(double power, long time){
//        frontLeft.setPower(-power);
//        frontRight.setPower(-power);
//        backLeft.setPower(-power);
//        backRight.setPower(-power);
//
//        sleep(time);
//    }
//
//    public void turnLeft(double power, long time){
//        frontLeft.setPower(-power);
//        frontRight.setPower(power);
//        backLeft.setPower(-power);
//        backRight.setPower(power);
//
//        sleep(time);
//    }
//
//    public void turnRight(double power, long time){
//        frontLeft.setPower(power);
//        frontRight.setPower(-power);
//        backLeft.setPower(power);
//        backRight.setPower(-power);
//
//        sleep(time);
//    }
//
//
//
//
//}
