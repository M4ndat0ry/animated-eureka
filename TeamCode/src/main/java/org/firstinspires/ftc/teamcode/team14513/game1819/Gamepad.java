package org.firstinspires.ftc.teamcode.team14513.game1819;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Gamepad Drive", group="Training")
public class Gamepad extends OpMode {

    DcMotor FrontLeft;
    DcMotor FrontRight;
    DcMotor RearLeft;
    DcMotor RearRight;
    DcMotor myHandMotor;

    double FrontLeftPower;
    double FrontRightPower;
    double RearRightPower;
    double RearLeftPower;
    double myHandMotorPower;
    @Override
    public void init() {
        FrontLeft = hardwareMap.dcMotor.get("FrontLeft");
        FrontRight = hardwareMap.dcMotor.get("FrontRight");
        RearRight = hardwareMap.dcMotor.get("RearRight");
        RearLeft = hardwareMap.dcMotor.get("RearLeft");
        myHandMotor = hardwareMap.dcMotor.get("MyHandMotor");


        FrontLeft.setDirection(DcMotor.Direction.REVERSE);
        RearLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop(){
        FrontLeftPower = gamepad1.left_stick_y;
        FrontRightPower = gamepad1.right_stick_y;
        RearLeftPower = gamepad1.left_stick_y;
        RearRightPower = gamepad1.right_stick_y;
        boolean myHandMotorPower = gamepad1.left_bumper;

        FrontLeft.setPower(FrontLeftPower);
        FrontRight.setPower(FrontRightPower);
        RearLeft.setPower(RearLeftPower);
        RearRight.setPower(RearRightPower);
    }
}
