package frc.robot.MotorController;

import com.revrobotics.CANSparkFlex;

public class Controller extends CANSparkFlex {
    public Controller(int controllerID) {
        this(controllerID, false);
    }

    public Controller(int controllerID, boolean inverted) {
        this(controllerID, inverted, MotorType.fromId(controllerID));
    }

    public Controller(int controllerID, boolean inverted, MotorType motorType) {
        super(controllerID, motorType);
        this.setInverted(inverted);
    }

    public void set(double targetSpeed) {
        this.set(Math.abs(targetSpeed - this.get()) > Constants.maxSpeedChange 
            ? Math.signum(targetSpeed - this.get()) * Constants.maxSpeedChange + this.get() 
            : targetSpeed);
    }
}
