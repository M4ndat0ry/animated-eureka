/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.team14513.game1920;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="ArmHandTeleOp", group="game1920")
//@Disabled
public class ArmHandTeleOp extends OpMode
{
    public static final double UP_MAX = 1.0;
    public static final double DOWN_MAX = 0.4;
    public static final double IN_MAX = 1.0;
    public static final double OUT_MAX = 1.0;
    public static final double OPEN_MAX = 1.0;
    public static final double CLOSE_MAX = 0.3;

    public static final double getMax(double origin, double cap) {
        double max = origin * cap;
        if (max > cap) {
            max = cap;
        }
        return max;
    }

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor armDrive = null;
    private DcMotor handDrive = null;
    private DcMotor flipDrive = null;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "ArmHandTeleOp Initializing");

        armDrive  = hardwareMap.get(DcMotor.class, "Arm");
        handDrive = hardwareMap.get(DcMotor.class, "Hand");
        flipDrive  = hardwareMap.get(DcMotor.class, "Flip");

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "ArmHandTeleOp Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        upDown(-gamepad2.left_stick_y);
        openClose(-gamepad2.right_stick_y);
        flip(gamepad2.dpad_up);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("DriveTrainTeleOp", "l(%.2f), r(%.2f)", gamepad2.left_stick_y, gamepad2.right_stick_y);
        telemetry.addData("DriveTrainTeleOp", "u:" + gamepad1.dpad_up + ", d:" + gamepad1.dpad_down);
    }

    void upDown(double howMuch) {
        if (howMuch >= 0.0) { // up
            armDrive.setPower(getMax(howMuch, UP_MAX));
        } else { // down slow
            armDrive.setPower(getMax(howMuch, DOWN_MAX));
        }
    }

    void flip(boolean inOut) {
        if (inOut) { // in
            flipDrive.setPower(getMax(1.0, IN_MAX));
        } else { // out
            flipDrive.setPower(getMax(1.0, OUT_MAX));
        }
    }

    void openClose(double howMuch) {
        if (howMuch >= 0.0) { // open
            handDrive.setPower(getMax(howMuch, OPEN_MAX));
        } else { // close slow
            handDrive.setPower(getMax(howMuch, CLOSE_MAX));
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
