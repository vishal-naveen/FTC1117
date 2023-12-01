package org.firstinspires.ftc.teamcode;

import  static org.firstinspires.ftc.teamcode.ColorDetector.*;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Rect;



@Autonomous(name = "BlueAuto", group="test")
public class BlueAuto extends LinearOpMode {

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

        Rect leftZone = centerRect(120,280,150,200);
        Rect midZone = centerRect(320,320,150,200);
        Rect rightZone = centerRect(420,280,150,200);
        detector = new ColorDetector(telemetry, TargetColor.RED, ColorDetector.ViewMode.RAW, leftZone, midZone, rightZone);
        visionPoral = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), detector);
        CameraStreamServer.getInstance().setSource(detector);

        telemetry.addData("Detection captured:",detector.getConfidentDetection());
        Detection detection = detector.getConfidentDetection();

        waitForStart();

        moveForward(0.1,100);


        while (!detector.isDetectionConfident()){}

        switch (detector.getConfidentDetection()){
            case LEFT:
                turnLeft(0.2,1000);
                setZero();
                break;
            case RIGHT:
                turnRight(0.2,1000);
                setZero();
                break;
            case MIDDLE:
                moveBackward(0.1,100);
                setZero();
                break;
            case NONE:
                //park
                break;
            default:
                //park
                break;

        }


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
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);

        sleep(time);
    }

    public void turnRight(double power, long time){
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        sleep(time);
    }

    public void setZero(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }


}
