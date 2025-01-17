# **Conventions**:
-No hardcoding.\
-camelCase style naming for variables and functions, and PascalCase style naming for folders, files, and class names.\
-Make variable names meaningful. No "temp", "wow", or similar placeholder-esque names.\
-As much as possible, make it so your commits only modify the files within a single folder (for example, if you're working on swerve, then your commit should only change files in the Swerve folder).\
-Every member in Master files (and the MotorController/Controller file) should be private. Every member in Constants files should not have any access modifier, as no modifier defaults it to package-private level access.\
-The only exception to the above is Joystick/Master, where for now all the possible joystick inputs should have corresponding public level variables.\
-Publish info to SmartDashboard when necessary. No System.out.print()s, except when debugging.\
-When necessary (mostly for Network Publishers), use file system syntax, e.g. "/rio/Swerve/Module/angle" ("rio" refers to Roborio, "Swerve/Module" means Swerve folder, Module class, and "angle" would be the variable in the class).\