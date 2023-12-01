package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;


@TeleOp
public class testdrive  extends OpMode {


    Robot robot = new Robot(this);

    Gamepad prevGamepad1;

    double speed = 0.5;
//    double liftcon = 0;
//
//    double clawPower = 0;



    @Override
    public void init() {
        robot.init();
    }


    @Override
    public void loop() {

        if(gamepad1.dpad_down && !prevGamepad1.dpad_down) {
            if (speed == 1) {
                speed = 0.5;
            } else {
                speed = 1;
            }
        }

//        if(gamepad1.a){
//            liftcon = 0.5;
//        }
//        if(gamepad1.b){
//            liftcon = -0.5;
//        }
//        if(gamepad1.x){
//            clawPower = 0.5;
//        }
//        if(gamepad1.y){
//            clawPower = -0.5;
//        }




        double y = -gamepad1.left_stick_y * speed;
        double x = gamepad1.left_stick_x * speed;
        double r = gamepad1.right_stick_x* speed;

        robot.driveTrain.drive(x, y, r);
//        robot.lift.liftMove(liftcon);
//        robot.claws.clawMove(clawPower);

        gamepad1.copy(prevGamepad1);
    }
}

