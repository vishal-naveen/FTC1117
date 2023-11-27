package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

public class Claws {

    OpMode opMode;

    public CRServo clawservo;

    public Claws (OpMode opMode){
        this.opMode = opMode;
    }

    public void init(){
        clawservo = (CRServo) opMode.hardwareMap.servo.get("CS");
    }

    public void clawMove(double clawpower){
        clawservo.setPower(clawpower);
    }

}
