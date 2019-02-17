package frc.robot;


/** 
 * This class is used for storing port values and constants so they can be easily changed across the whole program
 * @author Team 4910
 */

public class RobotMap{

    /* MOTOR PORT DEFINITIONS
     * 
     * For PWM controllers, these numbers are the corresponding PWM ports on the roboRIO
     * 
     * For CAN controllers, these numbers are the CAN IDs assigned to each controller
     *      -This can be changed via the roboRIO WebDashboard
    */
    public static int rightMotor1 = 0;
    public static int rightMotor2 = 1;
    public static int leftMotor1 = 2;
    public static int leftMotor2 = 3;

    /* JOYSTICKS
     * 
     * These numbers correspond to the USB port assignments on the Driver Station
     * 
     * Note: this can also work for Xbox Controllers
    */
    public static int leftJoy1 = 0;
    public static int rightJoy1 = 1;
    //public static int xBoxPort = 0;

}