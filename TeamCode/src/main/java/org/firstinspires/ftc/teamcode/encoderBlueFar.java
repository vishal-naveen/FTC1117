package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.ColorDetector.centerRect;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Rect;


@Autonomous(name="encoderBlueFar", group="Blue")
public class encoderBlueFar extends LinearOpMode {
    HardwarePushbot         robot   = new HardwarePushbot();   // Use the hardware file
    private ElapsedTime     runtime = new ElapsedTime();

    ColorDetector detector;

    VisionPortal visionPoral;
    static final double     COUNTS_PER_MOTOR_REV    = 537.6 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.77953 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    static final double nientyTurn = 19.75;

    static final double fullTurn = 83.5;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        //Color Sensor PreStart
        float hsvValues[] = {0F, 0F, 0F};


        final float values[] = hsvValues;

        final double SCALE_FACTOR = 255;

        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();

        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Path0",  "Starting at %7d :%7d",
                robot.frontLeft.getCurrentPosition(),
                robot.frontRight.getCurrentPosition(),
                robot.backLeft.getCurrentPosition(),
                robot.backRight.getCurrentPosition()

        );

        telemetry.addData("Hue", hsvValues[0]);

        Rect leftZone = centerRect(0,0,0,0);
        Rect midZone = centerRect(190,280,140,150);
        Rect rightZone = centerRect(535,310,140,200);
        detector = new ColorDetector(telemetry, ColorDetector.TargetColor.BLUE, ColorDetector.ViewMode.RAW, leftZone, midZone, rightZone);
        visionPoral = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), detector);
        CameraStreamServer.getInstance().setSource(detector);

        telemetry.addData("Detection captured:",detector.getConfidentDetection());
        ColorDetector.Detection detection = detector.getConfidentDetection();

        telemetry.update();



        waitForStart();

        while(!detector.isDetectionConfident()) {}
        switch (detector.getDetection()){
            case NONE:
                encoderDrive(0.5,22,22,22,22,0.5);
                setZero(250);
                turnLeft(0.5,19.75,19.75,19.75,19.75,0.5);
                setZero(250);
                encoderDrive(0.4,8.5,8.5,8.5,8.5,0.2);
                setZero(250);
                encoderDrive(0.4,-8.5,-8.5,-8.5,-8.5,0.2);
                setZero(250);
                turnRight(0.5,19.75,19.75,19.75,19.75,0.5);
                setZero(250);
                encoderDrive(0.6,-19,-19,-19,-19,0.2);
                setZero(250);
                turnLeft(0.5,19.75,19.75,19.75,19.75,0.5);
                setZero(250);
                encoderDrive(0.8,87,87,87,87,0.2);
                break;
            case MIDDLE:
                encoderDrive(0.5,32.5,32.5,32.5,32.5,0.2);
                setZero(250);
                encoderDrive(0.5,-28,-28,-28,-28,0.2);
                setZero(250);
                turnLeft(0.5,19.75,19.75,19.75,19.75,0.2);
                setZero(250);
                encoderDrive(0.8,87,87,87,87,0.2);
                setZero(250);
                break;
            case RIGHT:
                encoderDrive(0.5,22,22,22,22,0.5);
                setZero(250);
                turnRight(0.5,19.75,19.75,19.75,19.75,0.5);
                setZero(250);
                encoderDrive(0.4,8.5,8.5,8.5,8.5,0.2);
                setZero(250);
                encoderDrive(0.4,-8.5,-8.5,-8.5,-8.5,0.2);
                setZero(250);
                turnLeft(0.5,19.75,19.75,19.75,19.75,0.5);
                setZero(250);
                encoderDrive(0.6,-19,-19,-19,-19,0.2);
                setZero(250);
                turnLeft(0.5,19.75,19.75,19.75,19.75,0.5);
                setZero(250);
                encoderDrive(0.8,87,87,87,87,0.2);
                break;
        }


        telemetry.addData("Path", "Complete");
        telemetry.update();
    }




    public void encoderDrive(double speed,
                             double frontleftInches, double frontrightInches, double backleftInches, double backrightInches,
                             double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = robot.frontLeft.getCurrentPosition() + (int)(frontleftInches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.frontRight.getCurrentPosition() + (int)(frontrightInches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.backLeft.getCurrentPosition() + (int)(backleftInches * COUNTS_PER_INCH);
            newBackRightTarget = robot.backRight.getCurrentPosition() + (int)(backrightInches * COUNTS_PER_INCH);

            robot.frontLeft.setTargetPosition(newFrontLeftTarget);
            robot.frontRight.setTargetPosition(newFrontRightTarget);
            robot.backLeft.setTargetPosition(newBackLeftTarget);
            robot.backRight.setTargetPosition(newBackRightTarget);

            // Turn On RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeft.setPower(Math.abs(speed));
            robot.frontRight.setPower(Math.abs(speed));
            robot.backLeft.setPower(Math.abs(speed));
            robot.backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newFrontLeftTarget, newFrontRightTarget, newBackLeftTarget, newBackRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.frontLeft.getCurrentPosition(),
                        robot.frontRight.getCurrentPosition(),
                        robot.backLeft.getCurrentPosition(),
                        robot.backRight.getCurrentPosition()
                );
                telemetry.update();
            }

            // Stop all motion;
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }



    public void turnRight(double speed,
                          double frontleftInches, double frontrightInches, double backleftInches, double backrightInches,
                          double timeoutS) {
        int frontLeftTarget;
        int frontRightTarget;
        int backLeftTarget;
        int backRightTarget;
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        if (opModeIsActive()) {

            frontLeftTarget = robot.frontLeft.getCurrentPosition() + (int)(frontleftInches * COUNTS_PER_INCH);
            frontRightTarget = robot.frontRight.getCurrentPosition() + (int)(frontrightInches * COUNTS_PER_INCH);
            backLeftTarget = robot.backLeft.getCurrentPosition() + (int)(backleftInches * COUNTS_PER_INCH);
            backRightTarget = robot.backRight.getCurrentPosition() + (int)(backrightInches * COUNTS_PER_INCH);

            newFrontLeftTarget = frontLeftTarget;
            newFrontRightTarget = frontRightTarget * -1;
            newBackLeftTarget = backLeftTarget;
            newBackRightTarget = backRightTarget * -1;

            robot.frontLeft.setTargetPosition(newFrontLeftTarget);
            robot.frontRight.setTargetPosition(newFrontRightTarget);
            robot.backLeft.setTargetPosition(newBackLeftTarget);
            robot.backRight.setTargetPosition(newBackRightTarget);

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            robot.frontLeft.setPower(Math.abs(speed));
            robot.frontRight.setPower(Math.abs(speed));
            robot.backLeft.setPower(Math.abs(speed));
            robot.backRight.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy())) {

                telemetry.addData("Path1",  "Running to %7d :%7d", newFrontLeftTarget, newFrontRightTarget, newBackLeftTarget, newBackRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",

                        robot.frontRight.getCurrentPosition(),
                        robot.backLeft.getCurrentPosition(),
                        robot.backRight.getCurrentPosition()
                );

                telemetry.addData("FL:", robot.frontLeft.getCurrentPosition());
                telemetry.addData("FR:", robot.frontRight.getCurrentPosition());
                telemetry.addData("BL:", robot.backLeft.getCurrentPosition());
                telemetry.addData("BR:", robot.backRight.getCurrentPosition());

                telemetry.update();
            }

            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    public void turnLeft(double speed,
                         double frontleftInches, double frontrightInches, double backleftInches, double backrightInches,
                         double timeoutS) {
        int frontLeftTarget;
        int frontRightTarget;
        int backLeftTarget;
        int backRightTarget;
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        if (opModeIsActive()) {

            frontLeftTarget = robot.frontLeft.getCurrentPosition() + (int)(frontleftInches * COUNTS_PER_INCH);
            frontRightTarget = robot.frontRight.getCurrentPosition() + (int)(frontrightInches * COUNTS_PER_INCH);
            backLeftTarget = robot.backLeft.getCurrentPosition() + (int)(backleftInches * COUNTS_PER_INCH);
            backRightTarget = robot.backRight.getCurrentPosition() + (int)(backrightInches * COUNTS_PER_INCH);

            newFrontLeftTarget = frontLeftTarget * -1;
            newFrontRightTarget = frontRightTarget;
            newBackLeftTarget = backLeftTarget * -1;
            newBackRightTarget = backRightTarget;

            robot.frontLeft.setTargetPosition(newFrontLeftTarget);
            robot.frontRight.setTargetPosition(newFrontRightTarget);
            robot.backLeft.setTargetPosition(newBackLeftTarget);
            robot.backRight.setTargetPosition(newBackRightTarget);

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            robot.frontLeft.setPower(Math.abs(speed));
            robot.frontRight.setPower(Math.abs(speed));
            robot.backLeft.setPower(Math.abs(speed));
            robot.backRight.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy())) {

                telemetry.addData("Path1",  "Running to %7d :%7d", newFrontLeftTarget, newFrontRightTarget, newBackLeftTarget, newBackRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",

                        robot.frontRight.getCurrentPosition(),
                        robot.backLeft.getCurrentPosition(),
                        robot.backRight.getCurrentPosition()
                );

                telemetry.addData("FL:", robot.frontLeft.getCurrentPosition());
                telemetry.addData("FR:", robot.frontRight.getCurrentPosition());
                telemetry.addData("BL:", robot.backLeft.getCurrentPosition());
                telemetry.addData("BR:", robot.backRight.getCurrentPosition());

                telemetry.update();
            }

            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }


    public void setZero(long timer){
        robot.frontLeft.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        sleep(timer);
    }

//    public void strafeLeft(double speed,
//                           double frontleftInches, double frontrightInches, double backleftInches, double backrightInches,
//                           double timeoutS) {
//        int newFrontLeftTarget;
//        int newFrontRightTarget;
//        int newBackLeftTarget;
//        int newBackRightTarget;
//
//        // Ensure that the opmode is still active
//        if (opModeIsActive()) {
//
//            // Determine new target position, and pass to motor controller
//            newFrontLeftTarget = robot.frontLeft.getCurrentPosition() + (int)(-frontleftInches * COUNTS_PER_INCH);
//            newFrontRightTarget = robot.frontRight.getCurrentPosition() + (int)(frontrightInches * COUNTS_PER_INCH);
//            newBackLeftTarget = robot.backLeft.getCurrentPosition() + (int)(-backleftInches * COUNTS_PER_INCH);
//            newBackRightTarget = robot.backRight.getCurrentPosition() + (int)(backrightInches * COUNTS_PER_INCH);
//
//            robot.frontLeft.setTargetPosition(newFrontLeftTarget);
//            robot.frontRight.setTargetPosition(newFrontRightTarget);
//            robot.backLeft.setTargetPosition(newBackLeftTarget);
//            robot.backRight.setTargetPosition(newBackRightTarget);
//
//            // Turn On RUN_TO_POSITION
//            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            // reset the timeout time and start motion.
//            runtime.reset();
//            robot.frontLeft.setPower(Math.abs(speed));
//            robot.frontRight.setPower(Math.abs(speed));
//            robot.backLeft.setPower(Math.abs(speed));
//            robot.backRight.setPower(Math.abs(speed));
//
//            // keep looping while we are still active, and there is time left, and both motors are running.
//            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
//            // its target position, the motion will stop.  This is "safer" in the event that the robot will
//            // always end the motion as soon as possible.
//            // However, if you require that BOTH motors have finished their moves before the robot continues
//            // onto the next step, use (isBusy() || isBusy()) in the loop test.
//            while (opModeIsActive() &&
//                    (runtime.seconds() < timeoutS) &&
//                    (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy())) {
//
//                // Display it for the driver.
//                telemetry.addData("Path1",  "Running to %7d :%7d", newFrontLeftTarget, newFrontRightTarget, newBackLeftTarget, newBackRightTarget);
//                telemetry.addData("Path2",  "Running at %7d :%7d",
//                        robot.frontLeft.getCurrentPosition(),
//                        robot.frontRight.getCurrentPosition(),
//                        robot.backLeft.getCurrentPosition(),
//                        robot.backRight.getCurrentPosition()
//                );
//                telemetry.update();
//            }
//
//            // Stop all motion;
//            robot.frontLeft.setPower(0);
//            robot.frontRight.setPower(0);
//            robot.backLeft.setPower(0);
//            robot.backRight.setPower(0);
//
//            // Turn off RUN_TO_POSITION
//            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//            //  sleep(250);   // optional pause after each move
//        }
//    }
//
//    public void strafeRight(double speed,
//                            double frontleftInches, double frontrightInches, double backleftInches, double backrightInches,
//                            double timeoutS) {
//        int newFrontLeftTarget;
//        int newFrontRightTarget;
//        int newBackLeftTarget;
//        int newBackRightTarget;
//
//        // Ensure that the opmode is still active
//        if (opModeIsActive()) {
//
//            // Determine new target position, and pass to motor controller
//            newFrontLeftTarget = robot.frontLeft.getCurrentPosition() + (int)(frontleftInches * COUNTS_PER_INCH);
//            newFrontRightTarget = robot.frontRight.getCurrentPosition() + (int)(-frontrightInches * COUNTS_PER_INCH);
//            newBackLeftTarget = robot.backLeft.getCurrentPosition() + (int)(backleftInches * COUNTS_PER_INCH);
//            newBackRightTarget = robot.backRight.getCurrentPosition() + (int)(-backrightInches * COUNTS_PER_INCH);
//
//            robot.frontLeft.setTargetPosition(newFrontLeftTarget);
//            robot.frontRight.setTargetPosition(newFrontRightTarget);
//            robot.backLeft.setTargetPosition(newBackLeftTarget);
//            robot.backRight.setTargetPosition(newBackRightTarget);
//
//            // Turn On RUN_TO_POSITION
//            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            // reset the timeout time and start motion.
//            runtime.reset();
//            robot.frontLeft.setPower(Math.abs(speed));
//            robot.frontRight.setPower(Math.abs(speed));
//            robot.backLeft.setPower(Math.abs(speed));
//            robot.backRight.setPower(Math.abs(speed));
//
//            // keep looping while we are still active, and there is time left, and both motors are running.
//            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
//            // its target position, the motion will stop.  This is "safer" in the event that the robot will
//            // always end the motion as soon as possible.
//            // However, if you require that BOTH motors have finished their moves before the robot continues
//            // onto the next step, use (isBusy() || isBusy()) in the loop test.
//            while (opModeIsActive() &&
//                    (runtime.seconds() < timeoutS) &&
//                    (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy())) {
//
//                // Display it for the driver.
//                telemetry.addData("Path1",  "Running to %7d :%7d", newFrontLeftTarget, newFrontRightTarget, newBackLeftTarget, newBackRightTarget);
//                telemetry.addData("Path2",  "Running at %7d :%7d",
//                        robot.frontLeft.getCurrentPosition(),
//                        robot.frontRight.getCurrentPosition(),
//                        robot.backLeft.getCurrentPosition(),
//                        robot.backRight.getCurrentPosition()
//                );
//                telemetry.update();
//            }
//
//            // Stop all motion;
//            robot.frontLeft.setPower(0);
//            robot.frontRight.setPower(0);
//            robot.backLeft.setPower(0);
//            robot.backRight.setPower(0);
//
//            // Turn off RUN_TO_POSITION
//            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//            //  sleep(250);   // optional pause after each move
//        }
//    }

}
