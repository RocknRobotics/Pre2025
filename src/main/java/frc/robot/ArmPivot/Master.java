package frc.robot.ArmPivot;

//import com.revrobotics.AbsoluteEncoder;

import frc.robot.MotorController.Controller;

public class Master {
    Controller armPivotController;
    //AbsoluteEncoder armPivotAbsoluteEncoder;

    public Master(){
        armPivotController = new Controller();
        //armPivotAbsoluteEncoder = new AbsoluteEncoder();
    }

    public void pivot(double targetAngle){
        
    }

    public void teleopPeriodic(){
        pivot(0);
    }

    public void disabledPeriodic(){
        //armPivotController.stop();
    }
}