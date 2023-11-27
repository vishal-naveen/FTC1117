package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Robot {
    private final OpMode opMode;

    public final DriveTrain driveTrain;

    public final Lift lift;

    public final Claws claws;





    public Robot(OpMode opMode) {
        this.opMode = opMode;

        driveTrain = new DriveTrain(opMode);
        lift = new Lift(opMode);
        claws = new Claws(opMode);
    }

    public void init() {
    }

    public void update(){
        lift.update();
    }

}