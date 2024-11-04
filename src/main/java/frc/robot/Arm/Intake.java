package frc.robot.Arm;

import com.revrobotics.RelativeEncoder;

import frc.robot.Children.CustomSpark;
import frc.robot.Values.Constants;

public class Intake {
    public CustomSpark groundCustomSpark;
    public CustomSpark bottomCustomSpark;
    public CustomSpark topCustomSpark;

    public RelativeEncoder groundRelativeEncoder;
    public RelativeEncoder bottomRelativeEncoder;
    public RelativeEncoder topRelativeEncoder;

    public Intake() {
        groundCustomSpark = new CustomSpark(Constants.IDs.groundRoller, Constants.maxSparkChanges.groundRoller, Constants.maxRPMs.groundRoller, Constants.Inversions.groundRoller);
        groundRelativeEncoder = groundCustomSpark.relativeEncoder;
        bottomCustomSpark = new CustomSpark(Constants.IDs.bottomIntake, Constants.maxSparkChanges.bottomIntake, Constants.maxRPMs.bottomIntake, Constants.Inversions.bottomIntake);
        bottomRelativeEncoder = bottomCustomSpark.relativeEncoder;
        topCustomSpark = new CustomSpark(Constants.IDs.topIntake, Constants.maxSparkChanges.topIntake, Constants.maxRPMs.topIntake, Constants.Inversions.topIntake);
        topRelativeEncoder = topCustomSpark.relativeEncoder;
    }

    public void combinedLimitedSet(double intakeSet) {
        groundCustomSpark.limitedSet(intakeSet);
        bottomCustomSpark.limitedSet(intakeSet);
        topCustomSpark.limitedSet(intakeSet);
    }

    public void combinedLimitedSet(double[] intakeSets) {
        groundCustomSpark.limitedSet(intakeSets[0]);
        bottomCustomSpark.limitedSet(intakeSets[1]);
        topCustomSpark.limitedSet(intakeSets[2]);
    }

    public void combinedRPMSet(double intakeRPM) {
        groundCustomSpark.RPMSet(intakeRPM);
        bottomCustomSpark.RPMSet(intakeRPM);
        topCustomSpark.RPMSet(intakeRPM);
    }

    public void combinedRPMSet(double[] intakeRPMs) {
        groundCustomSpark.RPMSet(intakeRPMs[0]);
        bottomCustomSpark.RPMSet(intakeRPMs[1]);
        topCustomSpark.RPMSet(intakeRPMs[2]);
    }

    public void stop() {
        groundCustomSpark.stopMotor();
        bottomCustomSpark.stopMotor();
        topCustomSpark.stopMotor();
    }
}
