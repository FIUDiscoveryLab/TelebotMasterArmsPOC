package discoverylab.telebot.master.arms;

import discoverylab.util.DataParser;
import discoverylab.util.ServerConnection;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * Created by Shadeh Ferris on 7/2/2015.
 */
public class MasterArmsServer
{
    public static void main(String[] args)
    {
        final int port = 6666;

        ServoConfig configure = new ServoConfig();
        final int number_of_servos = configure.getNumberOfServos();
        configure.configureServos();

        long[][] servo_maxmin = new long [number_of_servos][3];

        for(int i = 0; i < number_of_servos; i++)
        {
            servo_maxmin[i][0] = configure.getServoID(i);
            servo_maxmin[i][1] = configure.getServoMaxValue(i);
            servo_maxmin[i][2] = configure.getServoMinValue(i);
        }

        ServerConnection connection = new ServerConnection();
        connection.connect(port);

        if(connection.isConnected())
        {
            Socket clientSocket = connection.getClientSocket();
            DataParser parse = new DataParser(clientSocket);
        }
        else
        {

        }

        boolean sensor_mapped_to_servo = false;

        long[][] sensor_maxmin = new long[number_of_servos][2];

        Console console = System.console();
        String input;

        do
        {
            if (sensor_mapped_to_servo == false)
            {
                input = console.readLine("Initializing Sensor to Servo Mapping Configuration ...\n " +
                        "When ready, press 's' and 'Enter'.\n Flex all joints in their natural range until " +
                        "'DONE' is displayed.\n");

                final int timer = 1000000;

                do
                {
                    if (input.equals("s"))
                    {
                        int count = 0;

                        while (count < timer)
                        {
                            DataParser parseData = new DataParser();
                            parseData.parse();

                                        String jointName = tkn.nextToken();
                                        String value_x = tkn.nextToken();
                                        long axis_angle_x = Long.parseLong(value_x);
                                        String value_y = tkn.nextToken();
                                        long axis_angle_y = Long.parseLong(value_y);
                                        String value_z = tkn.nextToken();
                                        long axis_angle_z = Long.parseLong(value_z);

                                        if (jointName.equals("head")) {
                                            SensorData x = new SensorData();
                                            SensorData y = new SensorData();

                                            x.setSensorMaxMin(axis_angle_x);
                                            y.setSensorMaxMin(axis_angle_y);

                                            sensor_maxmin[0][0] = x.getSensorMax();
                                            sensor_maxmin[0][1] = x.getSensorMin();
                                            sensor_maxmin[1][0] = y.getSensorMax();
                                            sensor_maxmin[1][1] = y.getSensorMin();

                                        } else if (jointName.equals("left_shoulder")) {
                                            SensorData x = new SensorData();
                                            SensorData y = new SensorData();
                                            SensorData z = new SensorData();

                                            x.setSensorMaxMin(axis_angle_x);
                                            y.setSensorMaxMin(axis_angle_y);
                                            z.setSensorMaxMin(axis_angle_z);

                                            sensor_maxmin[2][0] = x.getSensorMax();
                                            sensor_maxmin[2][1] = x.getSensorMin();
                                            sensor_maxmin[3][0] = y.getSensorMax();
                                            sensor_maxmin[3][1] = y.getSensorMin();
                                            sensor_maxmin[4][0] = z.getSensorMax();
                                            sensor_maxmin[4][1] = z.getSensorMin();
                                        } else if (jointName.equals("left_elbow")) {
                                            SensorData z = new SensorData();

                                            z.setSensorMaxMin(axis_angle_z);

                                            sensor_maxmin[5][0] = z.getSensorMax();
                                            sensor_maxmin[5][1] = z.getSensorMin();
                                        } else if (jointName.equals("left_wrist")) {
                                            SensorData x = new SensorData();
                                            SensorData z = new SensorData();

                                            x.setSensorMaxMin(axis_angle_x);
                                            z.setSensorMaxMin(axis_angle_z);

                                            sensor_maxmin[6][0] = x.getSensorMax();
                                            sensor_maxmin[6][1] = x.getSensorMin();
                                            sensor_maxmin[7][0] = z.getSensorMax();
                                            sensor_maxmin[7][1] = z.getSensorMin();
                                        } else if (jointName.equals("right_shoulder")) {
                                            SensorData x = new SensorData();
                                            SensorData y = new SensorData();
                                            SensorData z = new SensorData();

                                            x.setSensorMaxMin(axis_angle_x);
                                            y.setSensorMaxMin(axis_angle_y);
                                            z.setSensorMaxMin(axis_angle_z);

                                            sensor_maxmin[8][0] = x.getSensorMax();
                                            sensor_maxmin[8][1] = x.getSensorMin();
                                            sensor_maxmin[9][0] = y.getSensorMax();
                                            sensor_maxmin[9][1] = y.getSensorMin();
                                            sensor_maxmin[10][0] = z.getSensorMax();
                                            sensor_maxmin[10][1] = z.getSensorMin();
                                        } else if (jointName.equals("right_elbow")) {
                                            SensorData z = new SensorData();

                                            z.setSensorMaxMin(axis_angle_z);

                                            sensor_maxmin[11][0] = z.getSensorMax();
                                            sensor_maxmin[11][1] = z.getSensorMin();
                                        } else if (jointName.equals("right_wrist")) {
                                            SensorData x = new SensorData();
                                            SensorData z = new SensorData();

                                            x.setSensorMaxMin(axis_angle_x);
                                            z.setSensorMaxMin(axis_angle_z);

                                            sensor_maxmin[12][0] = x.getSensorMax();
                                            sensor_maxmin[12][1] = x.getSensorMin();
                                            sensor_maxmin[13][0] = z.getSensorMax();
                                            sensor_maxmin[13][1] = z.getSensorMin();
                                        }
                                    }
                            }

                            input = console.readLine("To restart synchronization, press 'r' and 'Enter' or press any other key and 'Enter' to " +
                                    "continue.\n");
                        }
                    }
                } while (input.equals("r"));

                sensor_mapped_to_servo = true;
            }

            }while(true);
    }
}
