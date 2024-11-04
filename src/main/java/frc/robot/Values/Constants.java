package frc.robot.Values;

public final class Constants {
    public static final double armAngleSoftLimit = 97.5d;
    public static final double armAngleHardLimit = 100.7d;
    public static final double armAngleRange = 75d;


    public static final class Presets {
        public static final class Intake {
            public static final double intaking = 0.4d;
            public static final double outtaking = -intaking;
            public static final double sourceTop = -0.4;
            public static final double sourceBack = 0.4;
            public static final double sourceGround = 0.4;
        }

        public static final class Middle {
            public static final double intaking = 0.15d;
            public static final double outtaking = -intaking;
            public static final double sourceIntake = -0.3;
        }

        public static final class Launcher {
            public static final double intaking = -0.3d;
            public static final double outtaking = intaking;

            public static final double ampLaunch = 0.11;
            public static final double sourceIntake = -0.3;
            public static final double rapidLaunch = 0.35;//0.64;
        }

        public static final class Hooks {
            public static final double extending = 1d;
            public static final double retracting = -extending;
        }

        public static final class ArmAngler {
            public static final double ampAngle = 65.0;
            public static final double sourceAngle = 72.0;//66.0;//73.1;
            public static final double rapidAngle = 60.0;//65.4;
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static final class Controllers {
        public static final int driveControllerPort = 0;
        public static final int armControllerPort = 1;

        public static final double driveControllerDeadband = 0.15;
        public static final double armControllerDeadband = 0.15;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static final class Measurements {
        public static final double driveWheelDiameter = 0.1016d;

        public static final double leftToRightLengthMetres = 0.685d;
        public static final double upToDownDistanceMetres = 0.685d;

        public static final double[] leftUpModulePos = new double[]{-leftToRightLengthMetres / 2d, upToDownDistanceMetres / 2d};
        public static final double[] leftDownModulePos = new double[]{-leftToRightLengthMetres / 2d, -upToDownDistanceMetres / 2d};
        public static final double[] rightUpModulePos = new double[]{leftToRightLengthMetres / 2d, upToDownDistanceMetres / 2d};
        public static final double[] rightDownModulePos = new double[]{leftToRightLengthMetres / 2d, -upToDownDistanceMetres / 2d};

        public static final double maxTranslationalSpeed = 3.420296449459403d;
        public static final double maxAngularSpeed = 2 * Math.PI;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static final class IDs {
        //Drive Motors
        public static final int leftUpDrive = 2;
        public static final int leftDownDrive = 8;
        public static final int rightUpDrive = 4;
        public static final int rightDownDrive = 6;

        //Turn Motors
        public static final int leftUpTurn = 17;
        public static final int leftDownTurn = 7;
        public static final int rightUpTurn = 3;
        public static final int rightDownTurn = 5;

        //Turn Encoders
        public static final int leftUpEncoder = 0;
        public static final int leftDownEncoder = 2;
        public static final int rightUpEncoder = 1;
        public static final int rightDownEncoder = 3;

        //Pigeon 2
        public static final int pigeon2 = 18;

        //Intake Motors
        public static final int groundRoller = 10;
        public static final int bottomIntake = 13;
        public static final int topIntake = 9;

        //Middle Motors
        public static final int backMiddleRoller = 16;
        public static final int frontMiddleRoller = 15;

        //Launcher Motors
        public static final int backLauncher = 11;
        public static final int frontLauncher = 12;

        //Arm Angle Motor
        public static final int armAngleMotor = 14;

        //Arm Angle Encoder
        public static final int armAngleEncoder = 0;

        //Hook Motors
        public static final int leftHook = 19;
        public static final int rightHook = 20;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static final class Inversions {
        //Drive Motors
        public static final boolean leftUpDrive = true;
        public static final boolean leftDownDrive = true;
        public static final boolean rightUpDrive = true;
        public static final boolean rightDownDrive = true;

        //Turn Motors
        public static final boolean leftUpTurn = true;
        public static final boolean leftDownTurn = true;
        public static final boolean rightUpTurn = true;
        public static final boolean rightDownTurn = true;

        //Turn Encoders
        public static final boolean leftUpEncoder = true;
        public static final boolean leftDownEncoder = true;
        public static final boolean rightUpEncoder = true;
        public static final boolean rightDownEncoder = true;

        //Pigeon2
        public static final boolean pigeon2 = false;

        //Intake Motors
        public static final boolean groundRoller = false;
        public static final boolean bottomIntake = false;
        public static final boolean topIntake = false;

        //Middle Motors
        public static final boolean backMiddleRoller = false;
        public static final boolean frontMiddleRoller = true;

        //Launcher Motors
        public static final boolean backLauncher = true;
        public static final boolean frontLauncher = false;

        //Arm Angle Motor
        public static final boolean armAngleMotor = true;

        //Arm Angle Encoder
        public static final boolean armAngleEncoder = false;

        //Hook Motors
        public static final boolean leftHook = false;
        public static final boolean rightHook = false;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static final class Offsets {
        //Turn Encoder Offsets
        public static final double leftUpOffset = 123.9;
        public static final double leftDownOffset = 254.8;
        public static final double rightUpOffset = 305.2;
        public static final double rightDownOffset = 276.6;

        //Arm Angle Encoder Offset
        public static final double armAngleOffset = 204.2;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static final class maxSparkChanges {
        //Ranges from 0 to 2 (since 1 - (-1) == 2)
        //Maximum change allowed in one time period between newSet in SparkMax.set(double newSet) and the double returned by SparkMax.get()
        //Drive Motors
        public static final double drive = 0.25;

        //Turn Motors
        public static final double turn = 0.25;

        //Intake Motors
        public static final double groundRoller = 0.25;
        public static final double bottomIntake = 0.25;
        public static final double topIntake = 0.25;

        //Middle Motors
        public static final double middle = 0.25;

        //Launcher Motors
        public static final double launcher = 0.25;

        //Arm Angle Motor
        public static final double armAngle = 0.25;

        //Hook Motors
        public static final double hook = 0.1;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static final class maxRPMs {
        //Drive Motors
        public static final double drive = 5000;

        //Turn Motors
        public static final double turn = 5000;

        //Intake Motors
        public static final double groundRoller = 5000;
        public static final double bottomIntake = 5000;
        public static final double topIntake = 5000;

        //Middle Motors
        public static final double middle = 5000;

        //Launcher Motors
        public static final double launcher = 5000;

        //Arm Angle Motor
        public static final double armAngle = 5000;

        //Hook Motors
        public static final double hook = 5000;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static final class Conversions {
        //Drive Motors
        public static final double driveGearRatio = 1d / 6.12d;
        public static final double driveMetresPerRotation = driveGearRatio * Measurements.driveWheelDiameter * Math.PI;

        //Turn Motors
        public static final double turnGearRatio = 1d / 1d;
        public static final double turnRadsPerRotation = turnGearRatio * 2 * Math.PI;
        public static final double turnDegreesPerRotation = turnRadsPerRotation * 180 / Math.PI;

        //Turn Encoders
        public static final double turnEncoderRatio = 1d / 1d;
        public static final double turnEncoderRadsPerRotation = turnEncoderRatio * 2 * Math.PI;
        public static final double turnEncoderDegreesPerRotation = turnEncoderRadsPerRotation * 180 / Math.PI;

        //Arm Angle Encoder
        public static final double armAngleEncoderRatio = 1d / 1d;
        public static final double armAngleEncoderRadsPerRotation = armAngleEncoderRatio * 2 * Math.PI;
        public static final double armAngleEncoderDegreesPerRotation = armAngleEncoderRadsPerRotation * 180 / Math.PI;

        //Hook Motors
        public static final double hookMetresPerRotation = 1d;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static final class fieldData {
        //Assumes southwest corner from blue alliance pov is (0, 0), with moving north +y and moving east +x
        public static final double speakerX = 2.663;
        public static final double ampX = 0.0;
        public static final double trapX1 = 4.093;
        public static final double trapX2 = 3.740;
        public static final double trapX3 = 4.445;
        public static final double rapidX = 1.235;

        public static final double minAmpAngle = 0.0;
        public static final double maxAmpAngle = 360.0;

        public static final class blueConstants {
            public static final double speakerY = 0.0;
            public static final double ampY = 1.84;
            public static final double trapY1 = 5.275;
            public static final double trapY2 = 4.664;
            public static final double trapY3 = 4.664;
            public static final double rapidY = 5.873;

            public static final double minSpeakerAngle = 0.0;
            public static final double maxSpeakerAngle = 360.0;
            public static final double minTrap1Angle = 0.0;
            public static final double maxTrap1Angle = 360.0;
            public static final double minTrap2Angle = 0.0;
            public static final double maxTrap2Angle = 360.0;
            public static final double minTrap3Angle = 0.0;
            public static final double maxTrap3Angle = 360.0;
            public static final double minRapidAngle = 0.0;
            public static final double maxRapidAngle = 360.0;
        }
        
        public static final class redConstants {
            public static final double speakerY = 16.541;
            public static final double ampY = 14.701;
            public static final double trapY1 = 11.266;
            public static final double trapY2 = 11.877;
            public static final double trapY3 = 11.877;
            public static final double rapidY = 10.719;

            public static final double minSpeakerAngle = 0.0;
            public static final double maxSpeakerAngle = 360.0;
            public static final double minTrap1Angle = 0.0;
            public static final double maxTrap1Angle = 360.0;
            public static final double minTrap2Angle = 0.0;
            public static final double maxTrap2Angle = 360.0;
            public static final double minTrap3Angle = 0.0;
            public static final double maxTrap3Angle = 360.0;
            public static final double minRapidAngle = 0.0;
            public static final double maxRapidAngle = 360.0;
        }
    }
}
