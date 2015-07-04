package discoverylab.telebot.master.arms;

/**
 * Created by Shadeh Ferris on 6/18/2015.
 */
public class ServoConfig
{
    private int[][] servoTable;
    private final int number_of_servos = 12;
    private final int table_fields = 4;

    public ServoConfig()
    {
        servoTable = new int [number_of_servos][table_fields];
    }

    public int getNumberOfServos(){return number_of_servos;}

    public int getServoID(int idPos)
    {
        return servoTable[idPos][0];
    }

    public int getServoInitValue(int idPos)
    {
        return servoTable[idPos][3];
    }

    public int getServoMaxValue(int idPos)
    {
        return servoTable[idPos][2];
    }

    public int getServoMinValue(int idPos)
    {
        return servoTable[idPos][1];
    }

    public void setServoID(int idPos, int servo_id)
    {
        servoTable[idPos][0] = servo_id;
    }

    public void setServoInitValue(int idPos, int value)
    {
        servoTable[idPos][3] = value;
    }

    public void setServoMaxValue(int idPos, int value)
    {
        servoTable[idPos][2] = value;
    }

    public void setServoMinValue(int idPos, int value)
    {
        servoTable[idPos][1] = value;
    }

    public void configureServos()
    {
        /*Servo ID / Position in Table
        Left:           Right:
        20 = 0          30 = 6
        21 = 1          31 = 7
        22 = 2          32 = 8
        23 = 3          33 = 9
        24 = 4          34 = 10
        25 = 5          35 = 11
         */

        //head
        setServoID(0, 10);
        setServoMaxValue(0, 1700);
        setServoMinValue(0, 2300);
        setServoInitValue(0, 2000);

        setServoID(1, 11);
        setServoMaxValue(0, 1500);
        setServoMinValue(0, 1500);
        setServoInitValue(0, 1500);

        //left arm
        setServoID(2, 20);
        setServoMaxValue(0, 3600);
        setServoMinValue(0, 2048);
        setServoInitValue(0, 2048);

        setServoID(3, 21);
        setServoMaxValue(1, 3600);
        setServoMinValue(1, 2048);
        setServoInitValue(1, 2048);

        setServoID(4, 22);
        setServoMaxValue(2, 3072);
        setServoMinValue(2, 1024);
        setServoInitValue(2, 2048);

        setServoID(5, 23);
        setServoMaxValue(3, 3072);
        setServoMinValue(3, 2048);
        setServoInitValue(3, 2048);

        setServoID(6, 24);
        setServoMaxValue(4, 3072);
        setServoMinValue(4, 1024);
        setServoInitValue(4, 2048);

        setServoID(7, 25);
        setServoMaxValue(5, 3072);
        setServoMinValue(5, 1024);
        setServoInitValue(5, 2048);


        //right arm
        setServoID(8, 30);
        setServoMaxValue(6, 2048);
        setServoMinValue(6, 600);
        setServoInitValue(6, 2048);

        setServoID(9, 31);
        setServoMaxValue(7, 2048);
        setServoMinValue(7, 600);
        setServoInitValue(7, 2048);

        setServoID(10, 32);
        setServoMaxValue(8, 3072);
        setServoMinValue(8, 1024);
        setServoInitValue(8, 2048);

        setServoID(11, 33);
        setServoMaxValue(9, 2048);
        setServoMinValue(9, 1024);
        setServoInitValue(9, 2048);

        setServoID(12, 34);
        setServoMaxValue(10, 3072);
        setServoMinValue(10, 1024);
        setServoInitValue(10, 2048);

        setServoID(13, 35);
        setServoMaxValue(11, 3072);
        setServoMinValue(11, 1024);
        setServoInitValue(11, 2048);
    }

}