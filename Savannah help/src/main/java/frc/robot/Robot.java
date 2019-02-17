/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;

  Command m_autonomousCommand;
  XboxController xboxPad = new XboxController(RobotMap.XboxPort);
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private static SpeedControllerGroup rightDrive;
  private static SpeedControllerGroup leftDrive;
  //Links right motors in one and left motors in one system each

  private static DifferentialDrive drive;
  //for tankdrive
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
   public static GenericHID.Hand kLeft;
   public static GenericHID.Hand kRight;



   WPI_VictorSPX rightMotor1;
   WPI_VictorSPX leftMotor1;
   WPI_VictorSPX rightMotor2;
   WPI_VictorSPX leftMotor2;
   
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    rightMotor1 = new WPI_VictorSPX(RobotMap.rightMotor1);
    leftMotor1 = new WPI_VictorSPX(RobotMap.leftMotor1);
    rightMotor2 = new WPI_VictorSPX(RobotMap.rightMotor2);
    leftMotor2 = new WPI_VictorSPX(RobotMap.leftMotor2);
    
    rightDrive = new SpeedControllerGroup(rightMotor1, rightMotor2);
    rightDrive.setInverted(true);
    leftDrive = new SpeedControllerGroup(leftMotor1, leftMotor2);
    leftDrive.setInverted(false);
    SmartDashboard.putData("Auto mode", m_chooser);

    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", MyAutoCommand());
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
    double rightSpeed = xboxPad.getTriggerAxis(GenericHID.Hand.kRight);
    double leftSpeed = xboxPad.getY(GenericHID.Hand.kLeft); //change to joystick value
    //   m_robotdrive.arcadeDrive(m_stick.getY(), m_stick.getX());

    double deadband = 0.07;
    if (Math.abs(rightSpeed) <= deadband) {
      rightSpeed = 0;
    }
    if (Math.abs(leftSpeed) <= deadband) {
      leftSpeed = 0;
    }

    rightSpeed *= 0.8;
    leftSpeed *= 0.8;

    System.out.println("Left speed " + leftSpeed + " Right speed " + rightSpeed);

    drive.tankDrive(leftSpeed, rightSpeed);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  /**
   * @return the m_autonomousCommand
   */
  public Command getM_autonomousCommand() {
    return m_autonomousCommand;
  }

  /**
   * @param m_autonomousCommand the m_autonomousCommand to set
   */
  public void setM_autonomousCommand(Command m_autonomousCommand) {
    this.m_autonomousCommand = m_autonomousCommand;
  }

  /**
   * @return the m_chooser
   */
  public SendableChooser<Command> getM_chooser() {
    return m_chooser;
  }

  /**
   * @param m_chooser the m_chooser to set
   */
  public void setM_chooser(SendableChooser<Command> m_chooser) {
    this.m_chooser = m_chooser;
  }
}
