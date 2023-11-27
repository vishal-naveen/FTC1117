package org.firstinspires.ftc.teamcode;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Lift {
    private final OpMode opMode;

    private DcMotor liftMotor;
    private static final double LIFT_MAX = 15;

    private double liftPos;
    public Lift(OpMode opMode){
        this.opMode = opMode;


    };

    public void init(){
        liftMotor = opMode.hardwareMap.dcMotor.get("LM");
    }

    public void liftMove(double liftcon) {

        liftMotor.setPower(liftcon);

    }

    public void update(){
        if(liftMotor.getCurrentPosition()> LIFT_MAX){

        }
    }
}
