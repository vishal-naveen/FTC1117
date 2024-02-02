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



//@Autonomous(name = "RedNear", group="Auto")
public class RedNear extends LinearOpMode {

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
        detector = new ColorDetector(telemetry, TargetColor.RED, ColorDetector.ViewMode.RAW, leftZone, midZone, rightZone);
        visionPoral = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), detector);
        CameraStreamServer.getInstance().setSource(detector);

        telemetry.addData("Detection captured:",detector.getConfidentDetection());
        Detection detection = detector.getConfidentDetection();

        motoranglestart();

        waitForStart();

        if(opModeIsActive()){

            telemetry.addData("Detection captured:",detector.getConfidentDetection());
            telemetry.addData("FL: ", frontLeft.getCurrentPosition());
            telemetry.addData("FR: ", frontRight.getCurrentPosition());
            telemetry.addData("BL: ", backLeft.getCurrentPosition());
            telemetry.addData("BR: ", backRight.getCurrentPosition());
            telemetry.update();
        }




            while(!detector.isDetectionConfident()) {}
            switch (detector.getDetection()){
                case RIGHT:
                    moveForward(0.4,850);
                    setZero(250);
                    turnRight(0.4,750);
                    setZero(250);
                    moveForward(0.5,100);
                    setZero(250);
                    moveBackward(0.5,150);
                    setZero(250);
                    turnLeft(0.4,700);
                    setZero(250);
                    moveBackward(0.4,550);
                    setZero(250);
                    strafeLeft(0.5,1750);
                    setZero(250);
                    break;
                case MIDDLE:
                    moveForward(0.4,1200);
                    setZero(250);
                    moveBackward(0.5,300);
                    setZero(250);
                    strafeLeft(0.5,2000);
                    break;
                case NONE:
                    turnLeft(0.4,220);
                    setZero(250);
                    moveForward(0.5,850);
                    setZero(250);
                    moveBackward(0.2,500);
                    setZero(250);
                    strafeLeft(0.5,1600);
                    break;
            }



//        moveForward(0.1,100);





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
