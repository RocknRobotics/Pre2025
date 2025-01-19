package frc.robot.ArmPivot;

import frc.robot.MotorController.Controller;

public class Master {
    Controller armPivotController;

    public Master(){
        armPivotController = new Controller(Constants.motorId);
    }

    public void pivot(double targetAngle){
        
    }

    public void teleopPeriodic(){
        pivot(0);
    }

    public void disabledPeriodic(){
        armPivotController.stopMotor();
    }
}