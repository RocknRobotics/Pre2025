package frc.robot.Swerve;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.DoubleArrayPublisher;
import edu.wpi.first.networktables.DoubleArraySubscriber;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Values.Constants;
import frc.robot.Values.Determinables;

public class SwerveMaster {
    public SwerveModule[] mySwerveModules;
    public Pigeon2 myPigeon;
    public double pigeonHeadingOffset;

    public PIDController continousController;

    //X, Y, and Heading of the robot
    public double[] XYH;

    //X, Y, and heading change using wheels and pigeon
    public DoubleArrayPublisher rioXYHPublisher;
    //Current values returned by the get of each wheel
    public DoubleArrayPublisher motorDriveGetsPublisher;
    public DoubleArrayPublisher motorTurnGetsPublisher;
    //Above but for RPM
    public DoubleArrayPublisher motorDriveRPMsPublisher;
    public DoubleArrayPublisher motorTurnRPMsPublisher;
    //The absolute position of each module
    public DoubleArrayPublisher moduleAbsoluteAnglesPublisher;

    //Gets the sets of the motors as prescribed by the jetson (the jetson accounts for the rpm formula)
    public DoubleArraySubscriber motorDriveSetsSubscriber;
    public DoubleArraySubscriber motorTurnSetsSubscriber;
    //The x, y, and heading of the robot as calculated by the jetson (in case it's needed for wheel stuff)
    public DoubleArraySubscriber jetsonXYHSubscriber;

    public SwerveMaster(NetworkTableInstance inst) {
        mySwerveModules = new SwerveModule[4];
        mySwerveModules[0] = new SwerveModule(Constants.IDs.leftUpDrive, Constants.IDs.leftUpTurn, Constants.IDs.leftUpEncoder, 
            Constants.Inversions.leftUpDrive, Constants.Inversions.leftUpTurn, Constants.Inversions.leftUpEncoder, 
            Constants.Offsets.leftUpOffset);
        mySwerveModules[1] = new SwerveModule(Constants.IDs.leftDownDrive, Constants.IDs.leftDownTurn, Constants.IDs.leftDownEncoder, 
            Constants.Inversions.leftDownDrive, Constants.Inversions.leftDownTurn, Constants.Inversions.leftDownEncoder, 
            Constants.Offsets.leftDownOffset);
        mySwerveModules[2] = new SwerveModule(Constants.IDs.rightUpDrive, Constants.IDs.rightUpTurn, Constants.IDs.rightUpEncoder, 
            Constants.Inversions.rightUpDrive, Constants.Inversions.rightUpTurn, Constants.Inversions.rightUpEncoder, 
            Constants.Offsets.rightUpOffset);
        mySwerveModules[3] = new SwerveModule(Constants.IDs.rightDownDrive, Constants.IDs.rightDownTurn, Constants.IDs.rightDownEncoder, 
            Constants.Inversions.rightDownDrive, Constants.Inversions.rightDownTurn, Constants.Inversions.rightDownEncoder, 
            Constants.Offsets.rightDownOffset);

        myPigeon = new Pigeon2(Constants.IDs.pigeon2, "rio");
        myPigeon.optimizeBusUtilization();
        myPigeon.setYaw(0d);
        myPigeon.getYaw().setUpdateFrequency(50);

        pigeonHeadingOffset = 0d;

        continousController = new PIDController(1d, 0d, 0d);
        continousController.enableContinuousInput(0d, 360d);

        XYH = new double[]{0d, 0d, 0d};

        rioXYHPublisher = inst.getDoubleArrayTopic("rio/SwerveMaster/rioXYH").publish();
        motorDriveGetsPublisher = inst.getDoubleArrayTopic("rio/SwerveMaster/motorDriveGets").publish();
        motorTurnGetsPublisher = inst.getDoubleArrayTopic("rio/SwerveMaster/motorTurnGets").publish();
        motorDriveRPMsPublisher = inst.getDoubleArrayTopic("rio/SwerveMaster/motorDriveRPMs").publish();
        motorTurnRPMsPublisher = inst.getDoubleArrayTopic("rio/SwerveMaster/motorTurnRPMs").publish();
        moduleAbsoluteAnglesPublisher = inst.getDoubleArrayTopic("rio/SwerveMaster/moduleAbsoluteAngles").publish();

        motorDriveSetsSubscriber = inst.getDoubleArrayTopic("jetson/SwerveMaster/motorDriveSets").subscribe(new double[]{0d, 0d, 0d, 0d});
        motorTurnSetsSubscriber = inst.getDoubleArrayTopic("jetson/SwerveMaster/motorTurnSets").subscribe(new double[]{0d, 0d, 0d, 0d});
        jetsonXYHSubscriber = inst.getDoubleArrayTopic("jetson/XYH").subscribe(new double[]{0d, 0d, 0d});
    }

    public double getHeading() {
        double temp = -myPigeon.getAngle() - pigeonHeadingOffset;

        while(temp <= 0d) {
            temp += 360d;
        }
        while(temp > 360d) {
            temp -= 360d;
        }

        return temp;
    }

    public double getHeadingNoOffset() {
        double temp = -myPigeon.getAngle() - pigeonHeadingOffset;

        while(temp <= 0d) {
            temp += 360d;
        }
        while(temp > 360d) {
            temp -= 360d;
        }

        return temp;
    }

    public void resetHeading(double newHeading) {
        myPigeon.setYaw(newHeading);
        /*pigeonHeadingOffset = getHeadingNoOffset() - newHeading;

        while(pigeonHeadingOffset <= 0d) {
            pigeonHeadingOffset += 360d;
        }
        while(pigeonHeadingOffset > 360d) {
            pigeonHeadingOffset -= 360d;
        }*/
    }

    public void combinedLimitedSet(double[] driveSets, double[] turnSets) {
        for(int i = 0; i < 4; i++) {
            mySwerveModules[i].combinedLimitedSet(driveSets[i], turnSets[i]);
        }
    }

    public void combinedRPMSet(double[] driveRPM, double[] turnRPM) {
        for(int i = 0; i < 4; i++) {
            mySwerveModules[i].combinedRPMSet(driveRPM[i], turnRPM[i]);
        }
    }

    public void stop() {
        for(int i = 0; i < 4; i++) {
            mySwerveModules[i].stop();
        }
    }

    public void updateNetwork() {
        double[] tempXYH = jetsonXYHSubscriber.get();
        combinedLimitedSet(motorDriveSetsSubscriber.get(), motorTurnSetsSubscriber.get());
        double[] currXY = getOdometryPosition(XYH[2]);

        rioXYHPublisher.set(new double[]{currXY[0] - XYH[0], currXY[1] - XYH[1], continousController.calculate(XYH[2], getHeading())});
        motorDriveGetsPublisher.set(new double[]{mySwerveModules[0].driveCustomSpark.get(), mySwerveModules[1].driveCustomSpark.get(), 
            mySwerveModules[2].driveCustomSpark.get(), mySwerveModules[3].driveCustomSpark.get()});
        motorDriveRPMsPublisher.set(new double[]{mySwerveModules[0].driveRelativeEncoder.getVelocity(), mySwerveModules[1].driveRelativeEncoder.getVelocity(), 
            mySwerveModules[2].driveRelativeEncoder.getVelocity(), mySwerveModules[3].driveRelativeEncoder.getVelocity()});
        motorDriveGetsPublisher.set(new double[]{mySwerveModules[0].turnCustomSpark.get(), mySwerveModules[1].turnCustomSpark.get(), 
            mySwerveModules[2].turnCustomSpark.get(), mySwerveModules[3].turnCustomSpark.get()});
        motorDriveRPMsPublisher.set(new double[]{mySwerveModules[0].turnRelativeEncoder.getVelocity(), mySwerveModules[1].turnRelativeEncoder.getVelocity(), 
            mySwerveModules[2].turnRelativeEncoder.getVelocity(), mySwerveModules[3].turnRelativeEncoder.getVelocity()});
        moduleAbsoluteAnglesPublisher.set(new double[]{mySwerveModules[0].getAbsoluteModuleAngle(), mySwerveModules[1].getAbsoluteModuleAngle(), 
            mySwerveModules[2].getAbsoluteModuleAngle(), mySwerveModules[3].getAbsoluteModuleAngle()});

        XYH[0] = tempXYH[0];
        XYH[1] = tempXYH[1];
        XYH[2] = tempXYH[2];
        alignModules();
        resetHeading(XYH[2]);
    }

    //Should return the change in x, y, since the last time period as calculated via wheel odometry
    public double[] getOdometryPosition(double heading) {
        double[] output = new double[2];

        double[][] positions = new double[4][2];

        for(int i = 0; i < 4; i++) {
            positions[i] = mySwerveModules[i].calculatePosition(heading);
            output[0] += positions[i][0] / 4d;
            output[1] += positions[i][1] / 4d;
        }

        return output;
    }

    public void alignModules() {
        mySwerveModules[0].alignPosition(XYH, XYH[2], Constants.Measurements.leftUpModulePos);
        mySwerveModules[1].alignPosition(XYH, XYH[2], Constants.Measurements.leftDownModulePos);
        mySwerveModules[2].alignPosition(XYH, XYH[2], Constants.Measurements.rightUpModulePos);
        mySwerveModules[3].alignPosition(XYH, XYH[2], Constants.Measurements.rightDownModulePos);
    }

    public void teleopUpdate() {
        if(Determinables.Controllers.driveController.getL1Button()) {
            Robot.swerveTranslationalFactor = Math.max(0.05d, Robot.swerveTranslationalFactor - 0.005d);
        } else if(Determinables.Controllers.driveController.getL2Button()) {
            Robot.swerveTranslationalFactor = Math.min(1d, Robot.swerveTranslationalFactor + 0.005d);
        }

        if(Determinables.Controllers.driveController.getR1Button()) {
            Robot.swerveRotationalFactor = Math.max(0.05d, Robot.swerveRotationalFactor - 0.005d);
        } else if(Determinables.Controllers.driveController.getR2Button()) {
            Robot.swerveRotationalFactor = Math.min(1d, Robot.swerveRotationalFactor + 0.005d);
        }

        if(Determinables.Controllers.driveController.getShareButtonPressed()) {
            resetHeading(180d);
        }

        SmartDashboard.putNumber("Translational Factor: ", Robot.swerveTranslationalFactor);
        SmartDashboard.putNumber("Rotational Factor: ", Robot.swerveRotationalFactor);

        double currentHeading = getHeading();
        double[] currentPosition = getOdometryPosition(currentHeading);
        XYH[0] = currentPosition[0];
        XYH[1] = currentPosition[1];
        XYH[2] = currentHeading;
        alignModules();

        SmartDashboard.putNumber("Current Heading: ", XYH[2]);

        double inputX = Math.abs(Determinables.Controllers.driveController.getLeftX()) < Constants.Controllers.driveControllerDeadband 
            ? 0d 
            : Determinables.Controllers.driveController.getLeftX() * Robot.swerveTranslationalFactor;
        double inputY = Math.abs(Determinables.Controllers.driveController.getLeftY()) < Constants.Controllers.driveControllerDeadband 
            ? 0d 
            : Determinables.Controllers.driveController.getLeftY() * Robot.swerveTranslationalFactor;
        double inputH = Math.abs(Determinables.Controllers.driveController.getRightX()) < Constants.Controllers.driveControllerDeadband 
            ? 0d 
            : Determinables.Controllers.driveController.getRightX() * Robot.swerveRotationalFactor;

        double adjustedX = -inputX / (Math.abs(inputY) + Math.abs(inputH) + 1);
        double adjustedY = inputY / (Math.abs(inputX) + Math.abs(inputH) + 1);
        double adjustedH = -inputH / (Math.abs(inputX) + Math.abs(inputY) + 1);

        if(adjustedX == 0 && adjustedY == 0 && adjustedH == 0) {
            stop();
        }

        double[] angleListOne = new double[]{7 * Math.PI / 4, 5 * Math.PI / 4, Math.PI / 4, 3 * Math.PI / 4};
        double[] angleListTwo = new double[]{3 * Math.PI / 4, Math.PI / 4, 5 * Math.PI / 4, 7 * Math.PI / 4};

        for(int i = 0; i < 4; i++) {
            double translationalAngle = Math.atan2(-adjustedY, adjustedX);
            translationalAngle = translationalAngle + currentHeading * Math.PI / 180;

            while(translationalAngle <= 0) {
                translationalAngle += 2 * Math.PI;
            }
            while(translationalAngle > 2 * Math.PI) {
                translationalAngle -= 2 * Math.PI;
            }

            double translationalMagnitude = Math.sqrt(Math.pow(adjustedX, 2) + Math.pow(adjustedY, 2));

            double rotationalMagnitude = Math.abs(adjustedH);
            double rotationalAngle = adjustedH > 0 ? angleListOne[i] : angleListTwo[i];

            while(rotationalAngle <= 0) {
                rotationalAngle += 2 * Math.PI;
            }
            while(rotationalAngle > 2 * Math.PI) {
                rotationalAngle -= 2 * Math.PI;
            }

            double tempX = translationalMagnitude * Math.cos(translationalAngle) 
                + rotationalMagnitude * Math.cos(rotationalAngle);
            double tempY = translationalMagnitude * Math.sin(translationalAngle) 
                + rotationalMagnitude * Math.sin(rotationalAngle);

            double desiredAngle = Math.atan2(tempX, tempY) * 180 / Math.PI;
            double desiredMagnitude = Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));

            while(desiredAngle <= 0) {
                desiredAngle += 360;
            }
            while(desiredAngle > 360) {
                desiredAngle -= 360;
            }

            double anglePlus = (desiredAngle + 180) > 360 ? desiredAngle : desiredAngle + 180;
            double angleMinus = (desiredAngle - 180) <= 0 ? desiredAngle : desiredAngle - 180;

            double moduleAbsoluteAngle = mySwerveModules[i].getAbsoluteModuleAngle();

            double desiredTurnSet = continousController.calculate(moduleAbsoluteAngle, desiredAngle) / 90d;
            double desiredPlusSet = continousController.calculate(moduleAbsoluteAngle, anglePlus) / 90d;
            double desiredMinusSet = continousController.calculate(moduleAbsoluteAngle, angleMinus) / 90d;

            if(Math.abs(desiredTurnSet) > Math.abs(desiredPlusSet)) {
                //mySwerveModules[i].combinedRPMSet(-desiredMagnitude * Constants.maxRPMs.drive, desiredPlusSet * Constants.maxRPMs.turn);
                mySwerveModules[i].combinedLimitedSet(-desiredMagnitude, desiredPlusSet);
            } else if(Math.abs(desiredTurnSet) > Math.abs(desiredMinusSet)) {
                //mySwerveModules[i].combinedRPMSet(-desiredMagnitude * Constants.maxRPMs.drive, desiredMinusSet * Constants.maxRPMs.turn);
                mySwerveModules[i].combinedLimitedSet(-desiredMagnitude, desiredMinusSet);
            } else {
                //mySwerveModules[i].combinedRPMSet(desiredMagnitude * Constants.maxRPMs.drive, desiredTurnSet * Constants.maxRPMs.turn);
                mySwerveModules[i].combinedLimitedSet(desiredMagnitude, desiredTurnSet);
            }
        }
    }
}
