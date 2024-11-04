package frc.robot.Swerve;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.AnalogEncoder;
import frc.robot.Children.CustomSpark;
import frc.robot.Values.Constants;

public class SwerveModule {
    public CustomSpark driveCustomSpark;
    public CustomSpark turnCustomSpark;

    public RelativeEncoder driveRelativeEncoder;
    public RelativeEncoder turnRelativeEncoder;

    public AnalogEncoder absoluteEncoder;
    public double absoluteEncoderOffset;
    public int absoluteEncoderInvert;

    public double previousTime;
    public double[] currentModulePosition;

    public double previousDrivePosition;

    public SwerveModule(int driveID, int turnID, int encoderID, 
        boolean driveInverted, boolean turnInverted, boolean absoluteEncoderInverted, 
        double encoderOffset) {
        driveCustomSpark = new CustomSpark(driveID, Constants.maxSparkChanges.drive, Constants.maxRPMs.drive, driveInverted);
        driveRelativeEncoder = driveCustomSpark.relativeEncoder;
        turnCustomSpark = new CustomSpark(turnID, Constants.maxSparkChanges.turn, Constants.maxRPMs.turn, turnInverted);
        turnRelativeEncoder = turnCustomSpark.relativeEncoder;
        absoluteEncoder = new AnalogEncoder(encoderID);

        absoluteEncoderInvert = absoluteEncoderInverted ? -1 : 1;

        absoluteEncoderOffset = encoderOffset;

        previousTime = System.currentTimeMillis();
        currentModulePosition = new double[2];

        previousDrivePosition = driveRelativeEncoder.getPosition() * Constants.Conversions.driveMetresPerRotation;
    }

    public void combinedLimitedSet(double driveSet, double turnSet) {
        driveCustomSpark.limitedSet(driveSet);
        turnCustomSpark.limitedSet(turnSet);
    }

    public void combinedRPMSet(double driveRPM, double turnRPM) {
        driveCustomSpark.RPMSet(driveRPM);
        turnCustomSpark.RPMSet(turnRPM);
    }

    public void stop() {
        driveCustomSpark.stopMotor();
        turnCustomSpark.stopMotor();
    }
    
    public double getAbsoluteModuleAngle() {
        double temp = -(180 + absoluteEncoderInvert * (absoluteEncoder.getAbsolutePosition()) * Constants.Conversions.turnEncoderDegreesPerRotation + absoluteEncoderOffset);

        while(temp <= 0) {
            temp += 360;
        }
        while(temp > 360) {
            temp -= 360;
        }

        return temp;
    }

    public double getWheelMetresPosition() {
        return driveRelativeEncoder.getPosition() * Constants.Conversions.driveMetresPerRotation;
    }

    public double getWheelMetresVelocity() {
        return driveRelativeEncoder.getVelocity() * Constants.Conversions.driveMetresPerRotation / 60d;
    }

    public void resetModulePosition(double[] xy) {
        currentModulePosition[0] = xy[0];
        currentModulePosition[1] = xy[1];
    }

    public double[] getModulePosition() {
        return currentModulePosition;
    }

    //Get field position of the motor relative to the start origin
    public double[] calculatePosition(double reducedAngle) {
        //Get the field relative angle that the wheel is pointing
        double moveAngle = getAbsoluteModuleAngle() - reducedAngle;
        while (moveAngle < 0) {
            moveAngle += 360;
        }

        //Get how far the wheel moved in that direction
        //Velocity is reversed to make x positive going right and y positive going forward
        double currTime = System.currentTimeMillis();
        double moveLength = -getWheelMetresVelocity() * ((currTime - previousTime) / 1000d);
        previousTime = currTime;

        //Calculate how x and y are related using the angle
        double factorY = Math.cos(moveAngle * Math.PI / 180);
        double factorX = Math.sin(moveAngle * Math.PI / 180);

        //Multiply factors by distance travelled to get x and y changes.
        double deltaY = factorY * moveLength;
        double deltaX = factorX * moveLength;

        //Update currX and currY
        currentModulePosition[0] += deltaX;
        currentModulePosition[1] += deltaY;

        //Return position
        return currentModulePosition;
    }

    //Realign the position of the wheels after figuring out the position of the center
    public void alignPosition(double[] center, double reducedAngle, double[] initial) {
        //Find out angle and distance from origin of initial
        //Polar coordinates
        double distance = Math.sqrt(Math.pow(initial[0], 2) + Math.pow(initial[1], 2));
        double angle = Math.atan2(initial[0], initial[1]) + Math.PI * 2 - Math.PI / 2;

        //Convert to degrees
        angle *= 180 / Math.PI;

        //Adjust angle 
        angle += reducedAngle;

        //Check for wrapping
        while (angle >= 360) {
            angle -= 360;
        }

        //Reconvert back to radians
        angle *= Math.PI / 180;

        //Reconvert back to cartesian coordinates
        double adjustedX = -Math.sin(angle) * distance;
        double adjustedY = Math.sin(angle) * distance;

        //Add robot values and set the new positions
        currentModulePosition[0] = center[0] + adjustedX;
        currentModulePosition[1] = center[1] + adjustedY;
    }
}
