package frc.robot.Arm;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.Children.CustomSpark;
import frc.robot.Values.Constants;

public class ArmAngler {
    public CustomSpark armAngleCustomSpark;

    public RelativeEncoder armAngleRelativeEncoder;

    public DutyCycleEncoder armAngleAbsoluteEncoder;
    public double armAngleAbsoluteEncoderOffset;
    public int armAngleAbsoluteEncoderInvert;

    public double armAngleSoftLimit;

    public ArmAngler() {
        armAngleCustomSpark = new CustomSpark(Constants.IDs.armAngleMotor, Constants.maxSparkChanges.armAngle, Constants.maxRPMs.armAngle, Constants.Inversions.armAngleMotor);
        armAngleRelativeEncoder = armAngleCustomSpark.relativeEncoder;
        armAngleAbsoluteEncoder = new DutyCycleEncoder(Constants.IDs.armAngleEncoder);

        armAngleAbsoluteEncoderInvert = Constants.Inversions.armAngleEncoder ? -1 : 1;

        armAngleAbsoluteEncoderOffset = Constants.Offsets.armAngleOffset;

        armAngleSoftLimit = Constants.armAngleSoftLimit;
    }

    public void limitedSet(double armAngleSet) {
        if(getAbsoluteAngle() > armAngleSoftLimit && armAngleSet > 0) {
            stop();
        } else {
            armAngleCustomSpark.limitedSet(armAngleSet);
        }
    }

    public void RPMSet(double armAngleRPM) {
        if(getAbsoluteAngle() > armAngleSoftLimit && armAngleRPM > 0) {
            stop();
        } else {
            armAngleCustomSpark.RPMSet(armAngleRPM);
        }
    }

    public void stop() {
        armAngleCustomSpark.stopMotor();
    }

    public double getAbsoluteAngle() {
        double temp = (armAngleAbsoluteEncoderInvert * armAngleAbsoluteEncoder.getAbsolutePosition() * Constants.Conversions.armAngleEncoderDegreesPerRotation - armAngleAbsoluteEncoderOffset);

        while(temp <= 0d) {
            temp += 360d;
        }
        while(temp > 360d) {
            temp -= 360d;
        }

        return temp;
    }

    public void angleSet(double desiredAngle) {
        armAngleCustomSpark.set(armAngleCustomSpark.get() 
            + (Math.abs((desiredAngle - getAbsoluteAngle())) / (Constants.armAngleRange)) > Constants.maxSparkChanges.armAngle 
            ? Math.signum(desiredAngle - getAbsoluteAngle()) * Constants.maxSparkChanges.armAngle 
            : (desiredAngle - getAbsoluteAngle()) / (Constants.armAngleRange));
    }
}
