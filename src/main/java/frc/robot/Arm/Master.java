package frc.robot.Arm;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.MotorController.Controller;

public class Master {
    private Controller algaeController;
    private Controller sharedController;
    private Controller coralController;

    private DigitalInput coralSwitch;
    private DigitalInput algaeSwitch;

    private boolean coralState;
    private boolean algaeState;

    public Master(){
        algaeController = new Controller(Constants.algaeID);
        sharedController = new Controller(Constants.sharedID);
        coralController = new Controller(Constants.coralID);

        coralSwitch = new DigitalInput(Constants.coralSwitchId);
        algaeSwitch = new DigitalInput(Constants.algaeSwitchId);

        coralState = coralSwitch.get();
        algaeState = algaeSwitch.get();
    }

    public void robotPeriodic(){
        coralState = coralSwitch.get();
        algaeState = algaeSwitch.get();
    }

    public void setMotorController(double coralMotorSpeed, double algaeMotorSpeed, double sharedMotorSpeed){
        algaeController.set(algaeMotorSpeed);
        coralController.set(coralMotorSpeed);
        sharedController.set(sharedMotorSpeed);
    }

    //change from void
    public void teleopPeriodic(){
        setMotorController(0, 0, 0);
    }

    public void disabledPeriodic(){
        algaeController.stopMotor();
        coralController.stopMotor();
        sharedController.stopMotor();
    }


}
