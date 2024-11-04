package frc.robot.Arm;

import com.revrobotics.RelativeEncoder;

import frc.robot.Children.CustomSpark;
import frc.robot.Values.Constants;

public class Launcher {
    public CustomSpark backCustomSpark;
    public CustomSpark frontCustomSpark;

    public RelativeEncoder backRelativeEncoder;
    public RelativeEncoder frontRelativeEncoder;

    public Launcher() {
        backCustomSpark = new CustomSpark(Constants.IDs.backLauncher, Constants.maxSparkChanges.launcher, Constants.maxRPMs.launcher, Constants.Inversions.backLauncher);
        backRelativeEncoder = backCustomSpark.relativeEncoder;
        frontCustomSpark = new CustomSpark(Constants.IDs.frontLauncher, Constants.maxSparkChanges.launcher, Constants.maxRPMs.launcher, Constants.Inversions.frontLauncher);
        frontRelativeEncoder = frontCustomSpark.relativeEncoder;
    }

    public void combinedLimitedSet(double launcherSet) {
        backCustomSpark.limitedSet(1.25 * launcherSet);
        frontCustomSpark.limitedSet(launcherSet);
    }

    public void combinedLimitedSet(double[] launcherSets) {
        backCustomSpark.limitedSet(1.25 * launcherSets[0]);
        frontCustomSpark.limitedSet(launcherSets[1]);
    }

    public void combinedRPMSet(double launcherRPM) {
        backCustomSpark.RPMSet(launcherRPM);
        frontCustomSpark.RPMSet(launcherRPM);
    }

    public void combinedRPMSet(double[] launcherRPMs) {
        backCustomSpark.RPMSet(launcherRPMs[0]);
        frontCustomSpark.RPMSet(launcherRPMs[1]);
    }

    public void stop() {
        backCustomSpark.stopMotor();
        frontCustomSpark.stopMotor();
    }
}
