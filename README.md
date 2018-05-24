[PC and Robot Setup](docs/setup.md)  
[Code Standards](docs/code-standards.md)  
[Shuffleboard](shuffleboard/README.md)

### Cloning The repository
1. Create folder `C:\git\github\RoboRavens`
2. Open a command prompt or powershell window
3. run `cd C:\git\github\RoboRavens` to navigate to the `RoboRavens` folder
4. run `git clone https://github.com/RoboRavens/PowerUp.git`

### GradleRIO
After cloning the repo open a powershell window in the root of the repository and run `./gradlew build` followed by `./gradlew eclipse`. If eclipse was already open make sure to refresh the project through the eclipse interface.

### Troubleshooting
##### Git credentials for ... not found.
1. Open a command prompt or powershell window
2. run `git config --global credential.helper wincred`

source: https://github.com/git-lfs/git-lfs/issues/1485
