package frc.robot.Arm;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.MotorController.Controller;

public class Master {
    Controller algaeController;
    Controller sharedController;
    Controller coralController;

    DigitalInput coralSwitch;
    DigitalInput algaeSwitch;

    boolean coralState;
    boolean algaeState;

    public Master(){
        algaeController = new Controller();
        sharedController = new Controller();
        coralController = new Controller();

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
        //algaeController.set(algaeMotorSpeed);
        //coralController.set(coralMotorSpeed);
        //sharedController.set(sharedMotorSpeed);
    }

    //change from void
    public void teleopPeriodic(){
        setMotorController(0, 0, 0);
    }

    public void disabledPeriodic(){
        //algaeController.stop();
        //coralController.stop();
        //bothController.stop();
    }


}
