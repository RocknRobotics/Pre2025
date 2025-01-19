package frc.robot.CageLift;

import frc.robot.MotorController.Controller;

public class Master {
    private Controller cageLiftController;

    public Master() {
        cageLiftController = new Controller(Constants.motorId);
    }

    public void lift() {
        cageLiftController.set(Constants.motorSpeed);
    }

    public void teleopPeriodic() {
        lift();
    }

    public void disabledPeriodic() {
        cageLiftController.stopMotor();
    }
}
