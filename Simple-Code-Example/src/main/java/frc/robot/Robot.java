package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private static WPI_TalonSRX rightMotor1;
  private static WPI_TalonSRX rightMotor2;
  private static WPI_TalonSRX leftMotor1;
  private static WPI_TalonSRX leftMotor2;

  /* private static Spark rightMotor1;
   * private static Spark rightMotor2;
   * private static Spark leftMotor1;
   * private static Spark leftMotor2;
   */

   
    private static WPI_VictorSPX rightMotor1;
    private static WPI_VictorSPX rightMotor2;
    private static WPI_VictorSPX leftMotor1;
    private static WPI_VictorSPX leftMotor2;
  

  private static SpeedControllerGroup rightDrive;
  private static SpeedControllerGroup leftDrive;

  private static DifferentialDrive drive;

  private static Joystick leftJoy1;
  private static Joystick rightJoy1;

  //private static XboxController xBox;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.addDefault("Default Auto", kDefaultAuto);
    m_chooser.addObject("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //Replace "WPI_TalonSRX" with whatever motor controller, and change definition as needed
    rightMotor1 = new WPI_VictorSPX(RobotMap.rightMotor1);
    rightMotor2 = new WPI_TalonSRX(RobotMap.rightMotor2);
    leftMotor1 = new WPI_TalonSRX(RobotMap.leftMotor1);
    leftMotor2 = new WPI_TalonSRX(RobotMap.leftMotor2);

    //If the bot is moving "backwards" then flip true and false for inverted on each side
    rightDrive = new SpeedControllerGroup(rightMotor1, rightMotor2);
    rightDrive.setInverted(false);
    leftDrive = new SpeedControllerGroup(leftMotor1, leftMotor2);
    leftDrive.setInverted(true);

    drive = new DifferentialDrive(leftDrive, rightDrive);

    leftJoy1 = new Joystick(RobotMap.leftJoy1);
    rightJoy1 = new Joystick(RobotMap.rightJoy1);

    //xBox = new XboxController(RobotMap.xBoxPort);
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
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    //THIS IS SO THE TANK DRIVE METHOD ONLY HAS TO BE CALLED ONCE
    //setSafetyEnabled makes it so the method has to be periodically called in order for the motors to run
    drive.setSafetyEnabled(false);
    drive.tankDrive(0.25, 0.25);
    Timer.delay(5);
    drive.tankDrive(0, 0);
    //REENABLE THIS AFTER AUTO ENDS
    drive.setSafetyEnabled(true);
  }

  /**
   * This function is called periodically during autonomous.
   */
  
  //For simple code, you don't really have to put anything here
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    //This section adds a deadband, so the bot doesn't respond to sticky joysticks
    double rightSpeed = rightJoy1.getY(); //xBox.getY(GenericHID.Hand.kRight);
    double leftSpeed = leftJoy1.getY(); //xBox.getY(GenericHID.Hand.kLeft);

    //This variable is the minimum ABSOLUTE value that the joysticks will respond
    double deadband = 0.07;

    if(Math.abs(rightSpeed) <= deadband){
      rightSpeed = 0;
    }

    if(Math.abs(leftSpeed) <= deadband){
      leftSpeed = 0;
    }

    drive.tankDrive(leftSpeed, rightSpeed);
    
    /* double forwardSpeed = leftJoy1.getY();
     * double rotation = leftJoy1.getX();
     * 
     * (Deadband)
     * 
     * drive.arcadeDrive(forwardSpeed, rotation);
     */ 
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
