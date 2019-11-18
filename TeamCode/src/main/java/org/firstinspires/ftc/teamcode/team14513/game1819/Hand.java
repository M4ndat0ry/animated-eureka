package org.firstinspires.ftc.teamcode.team14513.game1819;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Gamepad


@TeleOp(name="Hand", group="Linear Opmode")
public class Hand extends OpMode{
    DcMotor myHandMotor;

    boolean myHandMotorPower;
    boolean open;


    @Override
    public void init() {
        myHandMotor = hardwareMap.get(DcMotor.class, "hand_motor");
    }

    @Override
    public void loop() {
        open(gamepad1.left_bumper);
        close(gamepad1.right_bumper);

    }

    private void close(boolean right_bumper) {
        myHandMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        myHandMotor.setPower(0.7);
        timing();
    }


    private void open(boolean left_bumper) {
        myHandMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        myHandMotor.setPower(-0.5);
        timing();
    }


    private void timing() {
        try{
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            myHandMotor.setPower(0.0);
        }
    }
    }