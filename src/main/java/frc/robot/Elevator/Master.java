package frc.robot.Elevator;

import frc.robot.MotorController.Controller;

public class Master {
    Controller elevatorController;

    public Master(){
        elevatorController = new Controller(Constants.motorId);
    }

    public void extend(double targetHeight){
        
    }

    public void teleopPeriodic(){
        extend(0);
    }

    public void disabledPeriodic(){
        elevatorController.stopMotor();
    }
}
