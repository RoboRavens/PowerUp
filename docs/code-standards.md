# Code standards
Coding standards keep our code consistent.
## Casing
```java
public class Example {
    public static final String CONSTANT_NAME = ""; // all caps with underscores

    public static String publicStaticProperty = ""; // public static properties are camelCase

    public String publicProperty = ""; // public properties camelCase
    private String _privateProperty = ""; // private properties _camelCase with underscore

    public void methodName(String val) { // method names are camelCase
        string variableName = "";
        this.publicProperty = val; // use 'this' to reference public properties
        _privateProperty = val; // don't use 'this' to reference private properties
    }

    private void methodName() {

    }
}

public enum AxisCode {
    LEFT_STICK_X, // same as constants, all caps with underscores
    LEFT_STICK_Y,
    RIGHT_STICK_X
}

```

## Brackets
 - Opening brackets on same line, with a space after the closing parenthesis
 - Closing brackets on new line

```java
public class Example {
    private void methodName() {
        if (condition) {
            // do stuff
        }

        if (condition) {
            // do stuff
        } else if (condition2) {
            // do stuff
        } else {
            // do stuff
        }

        try {
            // do stuff
        } catch (Exception e) {
            // cleanup
        } finally {
            // cleanup
        }
    }
}
```

## Subsystems
- Should contain their own hardware instances
- Should be instantiated as a `public static final` property in Robot.java
- Controllers should be referenced using `Robot.CONTROLLER_NAME`

```java
// ExampleSubsystem.java
public class ExampleSubsystem extends Subsystem {
    public void initDefaultCommand() {
        setDefaultCommand(new ExampleCommand());
    }
}

// Robot.java
public class Robot extends TimedRobot {
    public static final ExampleSubsystem EXAMPLE_SUBSYSTEM = new ExampleSubsystem();
}
```

## Commands
 - Require subsystem by referencing the static property on Robot, e.g. `Robot.subsystemName`
 - Always access hardware interfaces through methods on the subsystem.
 - When a command is only invoked only while a button is pressed, then have the `isFinished` method always return true. The logic to check the button and run the command should be located in `teleopPeriodic` method of Robot.java.
 - Controllers should be referenced using `Robot.controllerName`

```java
public class ExampleCommand extends Command {
    public ExampleCommand() {
        requires(Robot.EXAMPLE_SUBSYSTEM);
    }

    @Override
    protected void execute() {
        Robot.EXAMPLE_SUBSYSTEM.doSomething();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}

// Robot.java
// ...
public void teleopPeriodic() {
    // use whileHeld if the command's isFinished method always returns true
    driveController.getButton(ControlsMap.EXAMPLE_BUTTON).whileHeld(new ExampleCommand());

    // use whenPressed if the command's isFinished method returns true only when the command's objective has been completed.
    driveController.getButton(ControlsMap.SPECIFIC_OBJECTIVE_BUTTON).whenPressed(new CompleteSpecificObjectiveCommand());
}
```
