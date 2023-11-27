package org.firstinspires.ftc.teamcode;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveTrain {

    OpMode opMode;

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    public DriveTrain(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        frontLeft = opMode.hardwareMap.dcMotor.get("FL");
        frontRight = opMode.hardwareMap.dcMotor.get("FR");
        backLeft = opMode.hardwareMap.dcMotor.get("BL");
        backRight = opMode.hardwareMap.dcMotor.get("BR");


        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public void drive(double x, double y, double r) {
        double denom = Math.max(Math.max(Math.abs(y), Math.max(Math.abs(x), Math.abs(r))), 1);

        double frontLeftPower = (y + x + r) / denom;
        double frontRightPower = (y - x - r) / denom;
        double backLeftPower = (y - x + r) / denom;
        double backRightPower = (y + x - r) / denom;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    public void stop(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

}
