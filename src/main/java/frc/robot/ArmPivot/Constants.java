package frc.robot.ArmPivot;

public final class Constants {
    final int motorId = 0;
    final double minRotation = -30;
    final double maxRotation = 30;
    final int absoluteEncoderId = 0;

    enum presetIndex {
        L1,
        L2,
        L3,
        L4,
        AlgaeOffFloor,
        AlgaeOffCoral,
        AlgaeOffFirst,
        AlgaeOffSecond,
        ProcessorScoring,
        CoralIntaking,
        SoftLimitMin,
        SoftLimitMax
    }

    final double [] presets = {
        0, //L1
        0, //L2
        0, //L3
        0, //L4
        0, //AlgaeOffFloor
        0, //AlgaeOffCoral
        0, //AlgaeOffFirst
        0, //AlgaeOffSecond
        0, //ProcessorScoring
        0, //CoralIntaking
        0, //SoftLimitMin
        0  //SoftLimitMax
    };
}
