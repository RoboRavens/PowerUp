### Cloning The repository
1. Create folder `C:\git\github\RoboRavens`
2. Open a command prompt or powershell window
3. run `cd C:\git\github\RoboRavens` to navigate to the `RoboRavens` folder
4. run `git clone https://github.com/RoboRavens/PowerUp.git`

### GradleRIO
After cloning the repo run `cd PowerUp` to navigate into the repository folder and run `./gradlew build` to build the project.

#### Eclipse Setup
After building the project run `./gradlew eclipse` to generate the project files used by Eclipse a file named `.classpath` will appear in the root of the project. Open the `.classpath` file with a text editor and add two classpathentry elements for paths "src" and "testsrc" as shown below.
```
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
  <classpathentry kind="src" path="src"/>
  <classpathentry kind="src" path="testsrc"/>
  ...
```

If eclipse was already open make sure to refresh the project through the eclipse interface.

### Additional Reading
[Legacy PC and Robot Setup](docs/setup.md)  
[Code Standards](docs/code-standards.md)  
[Shuffleboard](shuffleboard/README.md)

### Troubleshooting
##### Git credentials for ... not found.
1. Open a command prompt or powershell window
2. run `git config --global credential.helper wincred`

source: https://github.com/git-lfs/git-lfs/issues/1485
