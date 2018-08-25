package org.firstinspires.ftc.robotcontroller.internal;

        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.*;
        import com.qualcomm.robotcore.util.Range;

/**
 * Updated by Alex on 6/1/2017.
 */

@TeleOp(name = "RangerOp", group = "Default")
//@Disabled
public class RangerOp extends OpMode {
    //Declare any motors
    DcMotor leftMotor;
    DcMotor rightMotor;

    //Declare any variables & constants pertaining to drive train
    final double DRIVE_PWR_MAX = 0.80;
    double currentLeftPwr = 0.0;
    double currentRightPwr = 0.0;

    public RangerOp() {}

    @Override public void init() {
        //Initialize motors & set direction
        leftMotor = hardwareMap.dcMotor.get("lm");
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor = hardwareMap.dcMotor.get("rm");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry();
    }
    @Override public void loop() {
        //Update all the data based on driver input
        updateData();

        /* Clip Variables to make sure they don't exceed their
         * ranged values and Set them to the Motors/Servos */
        initialization();

        //Show the Real Values of the Data Using Telemetry
        telemetry();
    }

    void updateData() {
        //Add in update methods for specific robot mechanisms
        updateDriveTrain();
    }

    void initialization() {
        //Clip and Initialize Drive Train
        currentLeftPwr = Range.clip (currentLeftPwr,-DRIVE_PWR_MAX,DRIVE_PWR_MAX);
        leftMotor.setPower(currentLeftPwr);
        currentRightPwr = Range.clip (currentRightPwr,-DRIVE_PWR_MAX,DRIVE_PWR_MAX);
        rightMotor.setPower(currentRightPwr);

    }
    void telemetry() {
        //Show Data for Drive Train
        telemetry.addData("LM Pwr",leftMotor.getPower());
        telemetry.addData("RM Pwr",rightMotor.getPower());
    }

    //Create Methods that will update the driver data

 /*
     All update methods should be commented with:
         //Controlled by Driver (1 or 2)
         //Step 1: (Physical Instructions on how to control specific robot mechanism using controller buttons)
         //Step 2: (Physical Instructions on how to control specific robot mechanism using controller buttons)
         //Step ...: (Physical Instructions on how to control specific robot mechanism using controller buttons)
  */

    //Controlled by Driver 1
    //step 1: Push up/down the left/right stick to control the left/right drive motors
    void updateDriveTrain() {
        currentLeftPwr = -gamepad1.left_stick_y * DRIVE_PWR_MAX;
        currentRightPwr = -gamepad1.right_stick_y * DRIVE_PWR_MAX;
    }


    //Create variables/methods that will be used in ALL autonomous programs for this specific robot

    double setTime; //used to measure the time period of each step in autonomous
    int state = 0; //used to control the steps taken during autonomous
    String stateName = ""; //Overwrite this as the specific step used in Autonomous

    void resetEncoders() {

    }
    void runEncoders() {

    }
    void runWithoutEncoders() {

    }
    void resetSensors() {

    }
    //used to measure the amount of time passed since a new step in autonomous has started
    boolean waitSec(double elapsedTime) { return (this.time - setTime >= elapsedTime); }

}

