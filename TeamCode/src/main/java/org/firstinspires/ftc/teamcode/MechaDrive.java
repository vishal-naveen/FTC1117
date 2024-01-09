
package org.firstinspires.ftc.teamcode;


import static org.opencv.core.Core.max;
import static java.lang.Math.abs;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.annotations.MotorType;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="MechaDrive")
public class MechaDrive extends OpMode {

    public Servo droneServo;

    public Servo hangServo;

    public Servo armServo1;

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    public DcMotor hangMotor = null;

    public DcMotor liftHMotor = null;



//    private Servo liftServo1 = null;
//    private Servo liftServo2 = null;



    double speed = 0.72;





    Gamepad prevGamepad1 = new Gamepad();

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "FL");
        frontRight = hardwareMap.get(DcMotor.class, "FR");
        backLeft = hardwareMap.get(DcMotor.class, "BL");
        backRight = hardwareMap.get(DcMotor.class, "BR");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        droneServo = hardwareMap.get(Servo.class, "droneServo");

        hangServo = hardwareMap.get(Servo.class, "hangServo");

        armServo1 = hardwareMap.get(Servo.class, "armServo1");

        hangMotor = hardwareMap.get(DcMotor.class, "hangMotor");

        liftHMotor = hardwareMap.get(DcMotor.class, "liftHMotor");



//        liftServo1 = hardwareMap.get(Servo.class, "liftServo1");
//        liftServo2 = hardwareMap.get(Servo.class, "liftServo2");

        droneServo.setPosition(0.4);

        hangServo.setPosition(0.5);

        armServo1.setPosition(0.2);

//        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    @Override
    public void loop() {

//        double CPR = 537.6;
//
//
        int positionFL = frontLeft.getCurrentPosition();
//        double revolutionsFL = positionFL/CPR;
//        double angleFL = revolutionsFL * 360;
//        double angleNormalizedFL = angleFL % 360;
//
        int positionFR = frontRight.getCurrentPosition();
//        double revolutionsFR = positionFR/CPR;
//        double angleFR = revolutionsFR * 360;
//        double angleNormalizedFR = angleFR % 360;
//
        int positionBL = backLeft.getCurrentPosition();
//        double revolutionsBL = positionBL/CPR;
//        double angleBL = revolutionsBL * 360;
//        double angleNormalizedBL = angleBL % 360;
//
        int positionBR = backRight.getCurrentPosition();
//        double revolutionsBR = positionBR/CPR;
//        double angleBR = revolutionsBR * 360;
//        double angleNormalizedBR = angleBR % 360;
//
//        double diameter = 3.77953;
//        double circumference = Math.PI * diameter;
//
//        double distanceFL = circumference * revolutionsFL;
//        double distanceFR = circumference * revolutionsFR;
//        double distanceBL = circumference * revolutionsBL;
//        double distanceBR = circumference * revolutionsBR;





        if (gamepad2.left_bumper){
            droneServo.setPosition(0.7);
        }


        if(gamepad2.a){
            armServo1.setPosition(0.9);
        }

        if(gamepad2.b){
            armServo1.setPosition(0.1);
        }




        if(gamepad2.dpad_right){
            hangMotor.setPower(1);
        }
        hangMotor.setPower(0);

        if(gamepad2.dpad_left){
            hangMotor.setPower(-1);
        }
        hangMotor.setPower(0);

        if(gamepad2.dpad_up){
            liftHMotor.setPower(-1);
        }
        liftHMotor.setPower(0);

        if(gamepad2.dpad_down){
            liftHMotor.setPower(1);
        }
        liftHMotor.setPower(0);





//        if(gamepad1.dpad_down) {
//            if (speed == 0.5) {
//                speed = 1;
//            } else if (speed == 1) {
//                speed = 0.5;
//            } else {
//                speed = 0.5;
//            }
//        }





//        double y = -gamepad1.left_stick_y;
//        double x = gamepad1.left_stick_x;
//        double rx = gamepad1.right_stick_x;
//
//        double denominator = Math.max(abs(y) + abs(x) + abs(rx), 1);
//        double frontLeftPower = ((y + x + rx) / (denominator)*speed);
//        double backLeftPower = ((y - x + rx) / (denominator)*speed);
//        double frontRightPower = ((y - x - rx) / (denominator)*speed);
//        double backRightPower = ((y + x - rx) / (denominator)*speed);

        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;





        double dronePos = droneServo.getPosition();

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

        if(gamepad1.left_bumper){
            frontLeft.setPower(-0.4);
            frontRight.setPower(0.4);
            backLeft.setPower(-0.4);
            backRight.setPower(0.4);
        }
        if(gamepad1.right_bumper){
            frontLeft.setPower(0.4);
            frontRight.setPower(-0.4);
            backLeft.setPower(0.4);
            backRight.setPower(-0.4);
        }

        if(gamepad1.dpad_up){
            frontLeft.setPower(0.65);
            frontRight.setPower(0.65);
            backLeft.setPower(0.65);
            backRight.setPower(0.65);
        }

        if(gamepad1.dpad_left){
            frontLeft.setPower(-0.65);
            frontRight.setPower(0.65);
            backLeft.setPower(0.65);
            backRight.setPower(-0.65);
        }

        if(gamepad1.dpad_right){
            frontLeft.setPower(0.65);
            frontRight.setPower(-0.65);
            backLeft.setPower(-0.65);
            backRight.setPower(0.65);
        }

        if(gamepad1.dpad_down){
            frontLeft.setPower(-0.65);
            frontRight.setPower(-0.65);
            backLeft.setPower(-0.65);
            backRight.setPower(-0.65);
        }

        if(gamepad1.y){
            frontLeft.setPower(0.35);
            frontRight.setPower(0.35);
            backLeft.setPower(0.35);
            backRight.setPower(0.35);
        }

        if(gamepad1.x){
            frontLeft.setPower(-0.35);
            frontRight.setPower(0.35);
            backLeft.setPower(0.35);
            backRight.setPower(-0.35);
        }

        if(gamepad1.b){
            frontLeft.setPower(0.35);
            frontRight.setPower(-0.35);
            backLeft.setPower(-0.35);
            backRight.setPower(0.35);
        }

        if(gamepad1.a){
            frontLeft.setPower(-0.35);
            frontRight.setPower(-0.35);
            backLeft.setPower(-0.35);
            backRight.setPower(-0.35);
        }



        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);





        // Show motor power on telemetry
        telemetry.addData("FL Power", frontLeftPower);
        telemetry.addData("FR Power", frontRightPower);
        telemetry.addData("BL Power", backLeftPower);
        telemetry.addData("BR Power", backRightPower);
        telemetry.addData("drone: ", dronePos);


        telemetry.addData("Encoder PositionFL", positionFL);
//        telemetry.addData("Encoder RevolutionsFL", revolutionsFL);
//        telemetry.addData("Encoder Angle (Degrees)FL", angleFL);
//        telemetry.addData("Encoder Angle - Normalized (Degrees)FL", angleNormalizedFL);
//        telemetry.addData("Linear DistanceFL", distanceFL);

        telemetry.addData("Encoder PositionFR", positionFR);
//        telemetry.addData("Encoder RevolutionsFR", revolutionsFR);
//        telemetry.addData("Encoder Angle (Degrees)FR", angleFR);
//        telemetry.addData("Encoder Angle - Normalized (Degrees)FR", angleNormalizedFR);
//        telemetry.addData("Linear DistanceFR", distanceFR);

        telemetry.addData("Encoder PositionBL", positionBL);
//        telemetry.addData("Encoder RevolutionsBL", revolutionsBL);
//        telemetry.addData("Encoder Angle (Degrees)BL", angleBL);
//        telemetry.addData("Encoder Angle - Normalized (Degrees)BL", angleNormalizedBL);
//        telemetry.addData("Linear DistanceBL", distanceBL);

        telemetry.addData("Encoder PositionBR", positionBR);
//        telemetry.addData("Encoder RevolutionsBR", revolutionsBR);
//        telemetry.addData("Encoder Angle (Degrees)BR", angleFL);
//        telemetry.addData("Encoder Angle - Normalized (Degrees)BR", angleNormalizedBR);
//        telemetry.addData("Linear DistanceBR", distanceBR);


        telemetry.update();







//
//        double droneServoPos = droneServo.getPosition();
//
//        if(gamepad2.x && droneServoPos == 0.2){
//            droneServo.setPosition(0);
//        }

//        if(gamepad1.dpad_left){
//            liftServo1.setPosition(0.5);
//            liftServo2.setPosition(0.5);
//        }

    }
}
