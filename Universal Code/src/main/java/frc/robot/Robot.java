/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// import edu.wpi.first.wpilibj.PWMVictorSPX;
// import edu.wpi.first.wpilibj.PWMTalonSRX;
 import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
// import edu.wpi.first.wpilibj.Spark;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {

  Command m_autonomousCommand;
  // XboxController xboxPad = new XboxController(RobotMap.XboxPort); //xbox controller
  Joystick rightStick = new Joystick(RobotMap.stickRight); //right logitech joystick
  Joystick leftStick = new Joystick(RobotMap.stickLeft); //left logitech joystick

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private static SpeedControllerGroup rightDrive;
  private static SpeedControllerGroup leftDrive;
  private static DifferentialDrive drive;

  // PWMVictorSPX rightMotor2;
  // PWMVictorSPX rightMotor1;
  // WPI_VictorSPX leftMotor1;
  // PWMVictorSPX leftMotor2;

  // PWMTalonSRX rightMotor1;
  // PWMTalonSRX rightMotor2;
  // PWMTalonSRX leftMotor1;
  // PWMTalonSRX leftMotor2;

  WPI_TalonSRX rightMotor1;
  WPI_TalonSRX leftMotor1;
  // WPI_TalonSRX rightMotor2;
  // WPI_TalonSRX leftMotor2;

  //  WPI_VictorSPX rightMotor1;
  //  WPI_VictorSPX leftMotor1;
  //  WPI_VictorSPX rightMotor2;
  //  WPI_VictorSPX leftMotor2;

  //  Spark rightMotor1;
  //  Spark rightMotor2;
  //  Spark leftMotor1;
  //  Spark leftMotor2;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit() {
    // rightMotor1 = new WPI_VictorSPX(RobotMap.rightMotor1);
    // rightMotor2 = new WPI_VictorSPX(RobotMap.rightMotor2);
    // leftMotor1 = new WPI_VictorSPX(RobotMap.leftMotor1);
    // leftMotor2 = new WPI_VictorSPX(RobotMap.leftMotor2);

    // rightMotor1 = new Spark(RobotMap.rightMotor1);
    // rightMotor2 = new Spark(RobotMap.rightMotor2);
    // leftMotor1 = new Spark(RobotMap.leftMotor1);
    // leftMotor2 = new Spark(RobotMap.leftMotor2);

    // leftMotor1 = new PWMVictorSPX(RobotMap.leftMotor1);
    // rightMotor1 = new PWMVictorSPX(RobotMap.rightMotor1);
    // leftMotor2 = new PWMVictorSPX(RobotMap.leftMotor2);
    // rightMotor2 = new PWMVictorSPX(RobotMap.rightMotor2);

    //rightMotor1 = new PWMTalonSRX(RobotMap.rightMotor1);
    // rightMotor2 = new PWMTalonSRX(RobotMap.rightMotor2);
    // leftMotor1 = new PWMTalonSRX(RobotMap.leftMotor1);
    // leftMotor2 = new PWMTalonSRX(RobotMap.leftMotor2);

    rightMotor1 = new WPI_TalonSRX(RobotMap.rightMotor1);
    leftMotor1 = new WPI_TalonSRX(RobotMap.leftMotor1);
    // rightMotor2 = new WPI_TalonSRX(RobotMap.rightMotor2);
    // leftMotor2 = new WPI_TalonSRX(RobotMap.leftMotor2);

     rightDrive = new SpeedControllerGroup(rightMotor1, rightMotor1);
     rightDrive.setInverted(true);
     leftDrive = new SpeedControllerGroup(leftMotor1, leftMotor1);
     leftDrive.setInverted(false);
     drive = new DifferentialDrive(leftDrive, rightDrive);

    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    // double rightSpeed = xboxPad.getRawAxis(5); // change to joystick value
    // double leftSpeed = xboxPad.getY(GenericHID.Hand.kLeft); // change to joystick value;
    // boolean buttonA = xboxPad.getAButton(); // "A" refers to the A button on the xbox controller, can be changed to X, Y, B, or back
    // //refer to back buttons with GenericHID kRight and kLeft
    // // other button indicators can be getAButtonPressed or Released

    double rightSpeed = rightStick.getY(); 
    double leftSpeed = leftStick.getY(); 
    boolean button1 = rightStick.getRawButton(1); // 1 refers to button placement on the joystick

    double deadband = 0.07; // fudge factor for accidental nudge
    if (Math.abs(rightSpeed) <= deadband) {
      rightSpeed = 0; 

    }

    if (Math.abs(leftSpeed) <= deadband) {
      leftSpeed = 0;

    }
    
    double speedLimit = 1.0;

    if (button1) { //hypothetical low gear
      speedLimit = 0.5;

    }

    rightSpeed *= speedLimit; //reduces robot speeds
    leftSpeed *= speedLimit;

    //rightMotor1.set(rightSpeed);

    drive.tankDrive(leftSpeed, rightSpeed);
    //drive.arcadeDrive(leftStick.getY(), leftStick.getX());

    System.out.println("Left speed " + leftSpeed + " Right Speed " + rightSpeed);

  }

  

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }

}
