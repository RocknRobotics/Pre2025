# Checklist:
## Constants:
1. Motor id
2. Soft limits (degrees) for the minimum and maximum rotation
3. Absolute encoder id
4. Preset enum for:\
    -L1, L2, L3, L4\
    -Algae off floor, off coral, off first branch, off second branch\
    -Processor scoring\
    -Intaking from coral station\
    -Soft limit minimum/maximum

## Master:
1. Declare MotorController/Controller variable
2. Declare the absolute encoder variable
3. Create a constructor that instantiates **1** and **2**
4. Create a method that takes an angle and makes the pivot rotate to it
5. Create a teleopPeriodic() method that reads the necessary buttons from Joystick/Master and calls **4** with the associated (Constants **4**)
6. Create a disabledPeriodic() method that calls the stop() method of **1**