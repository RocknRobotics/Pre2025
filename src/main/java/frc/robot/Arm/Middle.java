package frc.robot.Arm;

import com.revrobotics.RelativeEncoder;

import frc.robot.Children.CustomSpark;
import frc.robot.Values.Constants;

public class Middle {
    public CustomSpark backCustomSpark;
    public CustomSpark frontCustomSpark;

    public RelativeEncoder backRelativeEncoder;
    public RelativeEncoder frontRelativeEncoder;

    public Middle() {
        backCustomSpark = new CustomSpark(Constants.IDs.backMiddleRoller, Constants.maxSparkChanges.middle, Constants.maxRPMs.middle, Constants.Inversions.backMiddleRoller);
        backRelativeEncoder = backCustomSpark.relativeEncoder;
        frontCustomSpark = new CustomSpark(Constants.IDs.frontMiddleRoller, Constants.maxSparkChanges.middle, Constants.maxRPMs.middle, Constants.Inversions.frontMiddleRoller);
        frontRelativeEncoder = frontCustomSpark.relativeEncoder;
    }

    public void combinedLimitedSet(double middleSet) {
        backCustomSpark.limitedSet(middleSet);
        frontCustomSpark.limitedSet(middleSet);
    }

    public void combinedLimitedSet(double[] middleSets) {
        backCustomSpark.limitedSet(middleSets[0]);
        frontCustomSpark.limitedSet(middleSets[1]);
    }

    public void combinedRPMSet(double middleRPM) {
        backCustomSpark.RPMSet(middleRPM);
        frontCustomSpark.RPMSet(middleRPM);
    }

    public void combinedRPMSet(double[] middleRPMs) {
        backCustomSpark.RPMSet(middleRPMs[0]);
        frontCustomSpark.RPMSet(middleRPMs[1]);
    }

    public void stop() {
        backCustomSpark.stopMotor();
        frontCustomSpark.stopMotor();
    }
}
