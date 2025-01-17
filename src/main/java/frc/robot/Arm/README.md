# Checklist:
## Constants:
1. Motor ids for the coral only wheels, the shared coral/algae wheels, and the algae only wheels
2. Ids for the coral switch and the algae switch
3. A motor speed to be used for intaking/outaking both the coral and algae. If one value is not usable for both, then create separate values for the coral and for the algae
4. A enum for coral/algae intake/outake

## Master:
1. Declare MotorController/Controller variables for the coral only wheels, shared coral/algae wheels, and algae only wheels
2. Declare DigitalInput variables for the coral switch and the algae switch
3. Declare a boolean variable for each switches' state
4. Create a constructor that instantiates **1**, **2**, and **3**
5. Create a robotPeriodic() method that updates the value of **3**
6. Create a method that accepts the (Constants **4**) as a parameter and sets **1** accordingly
7. Create a teleopPeriodic() method that reads the necessary buttons from Joystick/Master and calls **6** with the associated (Constants **4**)
8. Create a disabledPeriodic() method that calls the stop() method of **1**