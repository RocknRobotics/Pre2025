package frc.robot.MotorController;

public class ControllerGroup {
    private Controller[] group;

    public ControllerGroup(Controller[] group) {
        this.group = group;
    }

    public void set(double targetSpeed) {
        double[] targetSpeeds = new double[group.length];

        for(int i = 0; i < group.length; ++i) {
            targetSpeeds[i] = targetSpeed;
        }

        set(targetSpeeds);
    }

    public void set(double[] targetSpeeds) {
        for(int i = 0; i < group.length; ++i) {
            group[i].set(targetSpeeds[i]);
        }
    }

    public void stopMotor() {
        for(int i = 0; i < group.length; ++i) {
            group[i].stopMotor();
        }
    }
}
