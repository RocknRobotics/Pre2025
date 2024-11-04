package frc.robot.Values;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PS4Controller;

public class Determinables {
    public static boolean blue;

    public static final class Controllers {
        public static PS4Controller driveController;
        public static PS4Controller armController;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static class fieldData {
        public static double speakerY;
        public static double ampY;
        public static double trapY1;
        public static double trapY2;
        public static double trapY3;
        public static double rapidY;

        public static double minSpeakerAngle;
        public static double maxSpeakerAngle;
        public static double minTrap1Angle;
        public static double maxTrap1Angle;
        public static double minTrap2Angle;
        public static double maxTrap2Angle;
        public static double minTrap3Angle;
        public static double maxTrap3Angle;
        public static double minRapidAngle;
        public static double maxRapidAngle;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------
    public static void construct() {
        try {
            blue = (DriverStation.getAlliance().get() == DriverStation.Alliance.Blue 
                ? true 
                : (DriverStation.getAlliance().get() == DriverStation.Alliance.Red 
                    ? false 
                    : true));
        } catch(Exception e) {
            e.printStackTrace();
            blue = true;
        }
        
        Determinables.Controllers.driveController = new PS4Controller(Constants.Controllers.driveControllerPort);
        Determinables.Controllers.armController = new PS4Controller(Constants.Controllers.armControllerPort);

        if(blue) {
            Determinables.fieldData.speakerY = Constants.fieldData.blueConstants.speakerY;
            Determinables.fieldData.ampY = Constants.fieldData.blueConstants.ampY;
            Determinables.fieldData.trapY1 = Constants.fieldData.blueConstants.trapY1;
            Determinables.fieldData.trapY2 = Constants.fieldData.blueConstants.trapY2;
            Determinables.fieldData.trapY3 = Constants.fieldData.blueConstants.trapY3;
            Determinables.fieldData.rapidY = Constants.fieldData.blueConstants.rapidY;

            Determinables.fieldData.minSpeakerAngle = Constants.fieldData.blueConstants.minSpeakerAngle;
            Determinables.fieldData.maxSpeakerAngle = Constants.fieldData.blueConstants.maxSpeakerAngle;
            Determinables.fieldData.minTrap1Angle = Constants.fieldData.blueConstants.minTrap1Angle;
            Determinables.fieldData.maxTrap1Angle = Constants.fieldData.blueConstants.maxTrap1Angle;
            Determinables.fieldData.minTrap2Angle = Constants.fieldData.blueConstants.minTrap2Angle;
            Determinables.fieldData.maxTrap2Angle = Constants.fieldData.blueConstants.maxTrap2Angle;
            Determinables.fieldData.minTrap3Angle = Constants.fieldData.blueConstants.minTrap3Angle;
            Determinables.fieldData.maxTrap3Angle = Constants.fieldData.blueConstants.maxTrap3Angle;
            Determinables.fieldData.minRapidAngle = Constants.fieldData.blueConstants.minRapidAngle;
            Determinables.fieldData.maxRapidAngle = Constants.fieldData.blueConstants.maxRapidAngle;
        } else {
            Determinables.fieldData.speakerY = Constants.fieldData.redConstants.speakerY;
            Determinables.fieldData.ampY = Constants.fieldData.redConstants.ampY;
            Determinables.fieldData.trapY1 = Constants.fieldData.redConstants.trapY1;
            Determinables.fieldData.trapY2 = Constants.fieldData.redConstants.trapY2;
            Determinables.fieldData.trapY3 = Constants.fieldData.redConstants.trapY3;
            Determinables.fieldData.rapidY = Constants.fieldData.redConstants.rapidY;

            Determinables.fieldData.minSpeakerAngle = Constants.fieldData.redConstants.minSpeakerAngle;
            Determinables.fieldData.maxSpeakerAngle = Constants.fieldData.redConstants.maxSpeakerAngle;
            Determinables.fieldData.minTrap1Angle = Constants.fieldData.redConstants.minTrap1Angle;
            Determinables.fieldData.maxTrap1Angle = Constants.fieldData.redConstants.maxTrap1Angle;
            Determinables.fieldData.minTrap2Angle = Constants.fieldData.redConstants.minTrap2Angle;
            Determinables.fieldData.maxTrap2Angle = Constants.fieldData.redConstants.maxTrap2Angle;
            Determinables.fieldData.minTrap3Angle = Constants.fieldData.redConstants.minTrap3Angle;
            Determinables.fieldData.maxTrap3Angle = Constants.fieldData.redConstants.maxTrap3Angle;
            Determinables.fieldData.minRapidAngle = Constants.fieldData.redConstants.minRapidAngle;
            Determinables.fieldData.maxRapidAngle = Constants.fieldData.redConstants.maxRapidAngle;
        }
    }
}
