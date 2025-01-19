package frc.robot.Joystick;

import edu.wpi.first.wpilibj.Joystick;

public class Master {
    public static Joystick joystick;

    public Master(int id) {
        joystick = new Joystick(id);
    }
}
