package frc.robot.Hook;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.networktables.DoubleArrayPublisher;
import edu.wpi.first.networktables.DoubleArraySubscriber;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Children.CustomSpark;
import frc.robot.Values.Constants;
import frc.robot.Values.Determinables;

public class HookMaster {
    public CustomSpark leftCustomSpark;
    public CustomSpark rightCustomSpark;

    public RelativeEncoder leftRelativeEncoder;
    public RelativeEncoder rightRelativeEncoder;

    public double leftStartPosition;
    public double rightStartPosition;

    //left, right
    public DoubleArrayPublisher hookGetsPublisher;
    public DoubleArrayPublisher hookRPMsPublisher;
    public DoubleArrayPublisher hookPositionsPublisher;

    public DoubleArraySubscriber hookSetsSubscriber;

    public HookMaster(NetworkTableInstance inst) {
        leftCustomSpark = new CustomSpark(Constants.IDs.leftHook, Constants.maxSparkChanges.hook, Constants.maxRPMs.hook, Constants.Inversions.leftHook);
        leftRelativeEncoder = leftCustomSpark.relativeEncoder;
        rightCustomSpark = new CustomSpark(Constants.IDs.rightHook, Constants.maxSparkChanges.hook, Constants.maxRPMs.hook, Constants.Inversions.rightHook);
        rightRelativeEncoder = rightCustomSpark.relativeEncoder;
        leftStartPosition = leftRelativeEncoder.getPosition();
        rightStartPosition = rightRelativeEncoder.getPosition();

        hookGetsPublisher = inst.getDoubleArrayTopic("rio/HookMaster/hookGets").publish();
        hookRPMsPublisher = inst.getDoubleArrayTopic("rio/HookMaster/hookRPMs").publish();
        hookPositionsPublisher = inst.getDoubleArrayTopic("rio/HookMaster/hookPositions").publish();

        hookSetsSubscriber = inst.getDoubleArrayTopic("jetson/HookMaster/hookSets").subscribe(new double[]{0d, 0d});
    }

    public double getLeftMetresExtension() {
        return Math.abs(leftRelativeEncoder.getPosition() - leftStartPosition) * Constants.Conversions.hookMetresPerRotation;
    }

    public double getRightMetresExtension() {
        return Math.abs(rightRelativeEncoder.getPosition() - rightStartPosition) * Constants.Conversions.hookMetresPerRotation;
    }

    public void combinedLimitedSet(double hookSet) {
        leftCustomSpark.limitedSet(hookSet);
        rightCustomSpark.limitedSet(hookSet);
    }

    public void combinedLimitedSet(double[] hookSets) {
        leftCustomSpark.limitedSet(hookSets[0]);
        rightCustomSpark.limitedSet(hookSets[1]);
    }

    public void combinedRPMSet(double targetRPM) {
        leftCustomSpark.RPMSet(targetRPM);
        rightCustomSpark.RPMSet(targetRPM);
    }

    public void combinedRPMSet(double[] targetRPMs) {
        leftCustomSpark.RPMSet(targetRPMs[0]);
        rightCustomSpark.RPMSet(targetRPMs[1]);
    }

    public void stop() {
        leftCustomSpark.stopMotor();
        rightCustomSpark.stopMotor();
    }

    public void updateNetwork() {
        combinedLimitedSet(hookSetsSubscriber.get());

        hookGetsPublisher.set(new double[]{leftCustomSpark.get(), rightCustomSpark.get()});
        hookRPMsPublisher.set(new double[]{leftRelativeEncoder.getVelocity(), rightRelativeEncoder.getVelocity()});
        hookPositionsPublisher.set(new double[]{leftRelativeEncoder.getPosition() - leftStartPosition, rightRelativeEncoder.getPosition() - rightStartPosition});
    }

    public void teleopUpdate() {
        if(Determinables.Controllers.driveController.getPOV() == 0) {
            combinedRPMSet(Constants.Presets.Hooks.extending * Constants.maxRPMs.hook);
        } else if(Determinables.Controllers.driveController.getPOV() == 180) {
            combinedRPMSet(Constants.Presets.Hooks.retracting * Constants.maxRPMs.hook);
        } else {
            stop();
        }
    }
}