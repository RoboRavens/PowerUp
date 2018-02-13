## Table of Contents
- [PC Setup](#pc-setup)
  - [Update Eclipse wpilib Plugins](#update-eclipse-wpilib-plugins)
  - [Update or Install FRC Software Suite](#update-or-install-FRC-software-suite)
  - [Install Phoenix Framework](#install-phoenix-framework)

## PC Setup
### Update Eclipse wpilib Plugins
If Eclipse and the "WPILib Robot Development" software are already installed then you can update the software using the following steps:
1. Open Eclipse
2. Click Help at the top
3. Choose Check For Updates
4. Click "Deselect All"
5. Find "WPILib Robot Development" near the bottom of the list and select it
6. Proceed through the update steps

If Eclipse is installed but you are not sure if the "WPILib Robot Development" is installed then:
1. Open eclipse
2. Click File at the top left
3. Choose New->Other
4. If a "WPILib Robot Development" folder is visible then it is installed

Otherwise follow the instructions at the following website to install eclipse and the "WPILib Robot Development" software: https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599681-installing-eclipse-c-java

### Update or Install FRC Software Suite
(decryption key: pLaY&4%R3aL!)  
https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599671-installing-the-frc-update-suite-all-languages

### Install Phoenix Framework
There is a lot of text at the following links but just worry about the two sections listed:  
[Installing Phoenix Framework onto PC](https://github.com/CrossTheRoadElec/Phoenix-Documentation#installing-phoenix-framework-onto-pc)  
[Add Javadoc if using Java](https://github.com/CrossTheRoadElec/Phoenix-Documentation#add-javadoc-if-using-java)

### [Optional] Install navX software - if this is a new PC...
The navX MXP project contains the following components:

setup.exe:

- This is the navX MXP Windows installation program, which installs RoboRIO Libraries and Examples, the navXUI and the navX Tools.
- RoboRIO Libaries and Examples are installed within the "navx-mxp" sub-directory of your current user home directory.  E.g., if your use name is Robot, the Libraries and Examples are installed to C:\Users\Robot\navx-mxp.

enclosure: (Directory)

- This directory contains design files for a 3d-printed enclosure for the navX MXP.

The navX MXP Github repository is at https://github.com/kauailabs/navxmxp.

This repository includes:

- navX MXP firmware source code (C, C++) and project files for Eclipse
- navX MXP hardware schematics/layout files (Eagle PCB 6.5 format)
- navX MXP Tools source code (C#)
- navX MXP UI source code (Processing)
- navX MXP Setup Program (Innosetup)

For more information please also see the online guide to the sources in the "Advanced" section at http://navx-mxp.kauailabs.com.


## RoboRIO Setup
### Flash the RoboRIO
1. Run the "roboRIO Imaging Tool" installed as part of the FRC Software Suite
2. Reformat using the 2018 image

### Talon SRX Update
[Installing Phoenix Framework onto your FRC robot](https://github.com/CrossTheRoadElec/Phoenix-Documentation#installing-phoenix-framework-onto-your-frc-robot)

## Helpful Links
[Overview of FRC Software](http://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/144981-frc-software-component-overview)
[Phoenix Documentation](https://github.com/CrossTheRoadElec/Phoenix-Documentation)
[Gryo Software](https://github.com/kauailabs/navxmxp)

## Troubleshooting
### CAN components not showing up
Did you install the Pheonix Framework onto roboRIO?
