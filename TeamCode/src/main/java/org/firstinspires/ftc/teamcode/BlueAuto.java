package org.firstinspires.ftc.teamcode;

import  static org.firstinspires.ftc.teamcode.ColorDetector.*;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Rect;



@Autonomous
public class BlueAuto extends OpMode {

    ColorDetector detector;

    VisionPortal visionPoral;

    Robot robot = new Robot(this);

    @Override
    public void init() {
        Rect leftZone = centerRect(120,280,150,150);
        Rect midZone = centerRect(320,280,150,150);
        Rect rightZone = centerRect(520,280,150,150);
        detector = new ColorDetector(telemetry, TargetColor.BLUE, ColorDetector.ViewMode.RAW, leftZone, midZone, rightZone);
        visionPoral = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcame 1"), detector);
        CameraStreamServer.getInstance().setSource(detector);
    }


    @Override
    public void init_loop(){
        handle_input();
        telemetry.addData("Detection captured:",detector.getConfidentDetection());
        Detection detection = detector.getConfidentDetection();

        while (!detector.isDetectionConfident()){}

        switch (detection){
            case LEFT:
                robot.driveTrain.drive(0,0.5,0);
                robot.driveTrain.stop();
                robot.driveTrain.drive(0,0,0.5);
                robot.driveTrain.stop();
        }



    }

    @Override
    public void loop() {
        handle_input();
    }

    void handle_input(){
        if(gamepad1.x){
            detector.targetColor = TargetColor.BLUE;
        } else  if (gamepad1.b){
            detector.targetColor = TargetColor.RED;
        }
        if(gamepad1.a){
            detector.viewMode = ViewMode.RAW;
        } else if (gamepad1.y){
            detector.viewMode = ViewMode.THRESHOLD;
        }
    }
}
