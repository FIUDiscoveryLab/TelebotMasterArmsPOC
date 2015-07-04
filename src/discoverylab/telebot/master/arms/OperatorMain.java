package discoverylab.telebot.master.arms;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by Shadeh Ferris on 6/22/2015.
 */
public class OperatorMain
{
    public static void main(String[] args)
    {
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

        try {
            ServerSocket server = new ServerSocket(6666);
            Socket clientSocket = server.accept();

            boolean sensor_mapped_to_servo = false;

            long[][] sensor_maxmin = new long[number_of_servos][2];

            Console console = System.console();
            String input;


            do
            {
                Vector vectorBufferedReader = new Vector();

                if (sensor_mapped_to_servo == false) {
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
                                vectorBufferedReader.add(new BufferedReader(new InputStreamReader((clientSocket.getInputStream()))));
                                String delimiter = " ";

                                for (int i = 0; i < vectorBufferedReader.size(); i++) {
                                    BufferedReader in = (BufferedReader) vectorBufferedReader.elementAt(i);
                                    String str = null;

                                    try {
                                        str = in.readLine();

                                        if (str != null) {
                                            StringTokenizer tkn = new StringTokenizer(str, delimiter);
                                            String jointName = tkn.nextToken();
                                            String value_x = tkn.nextToken();
                                            long axis_angle_x = Integer.parseInt(value_x);
                                            String value_y = tkn.nextToken();
                                            long axis_angle_y = Integer.parseInt(value_y);
                                            String value_z = tkn.nextToken();
                                            long axis_angle_z = Integer.parseInt(value_z);

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

                                    } catch (IOException e) {
                                        if (str == null) {
                                            System.out.println("Str is null");
                                            vectorBufferedReader.remove(i);
                                            System.out.println("Str removed");
                                            System.out.println(e);
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
                else
                {
                    vectorBufferedReader.add(new BufferedReader(new InputStreamReader((clientSocket.getInputStream()))));
                    String delimiter = " ";

                    final long default_speed = 100;

                    ServoMap map = new ServoMap();

                    ServoData[] servo_data = new ServoData[number_of_servos];

                    for (int i = 0; i < vectorBufferedReader.size(); i++)
                    {
                        BufferedReader in = (BufferedReader) vectorBufferedReader.elementAt(i);
                        String str = null;

                        try
                        {
                            str = in.readLine();

                            if (str != null)
                            {
                                StringTokenizer tkn = new StringTokenizer(str, delimiter);
                                String jointName = tkn.nextToken();
                                String value_x = tkn.nextToken();
                                long axis_angle_x = Integer.parseInt(value_x);
                                String value_y = tkn.nextToken();
                                long axis_angle_y = Integer.parseInt(value_y);
                                String value_z = tkn.nextToken();
                                long axis_angle_z = Integer.parseInt(value_z);

                                if (jointName.equals("head"))
                                {
                                    ServoMap x = new ServoMap();
                                    ServoMap y = new ServoMap();

                                    long position_x = x.mapPosition(axis_angle_x, sensor_maxmin[0][0], sensor_maxmin[0][1],
                                            servo_maxmin[0][1], servo_maxmin[0][2]);
                                    long position_y = y.mapPosition(axis_angle_y, sensor_maxmin[1][0], sensor_maxmin[1][1],
                                            servo_maxmin[1][1], servo_maxmin[1][2]);

                                    servo_data[0] = new ServoData("head_x", position_x, default_speed);
                                    servo_data[1] = new ServoData("head_y", position_y, default_speed);
                                }
                                else if (jointName.equals("left_shoulder"))
                                {
                                    ServoMap x = new ServoMap();
                                    ServoMap y = new ServoMap();
                                    ServoMap z = new ServoMap();

                                    long position_x = x.mapPosition(axis_angle_x, sensor_maxmin[2][0], sensor_maxmin[2][1],
                                            servo_maxmin[2][1], servo_maxmin[2][2]);
                                    long position_y = y.mapPosition(axis_angle_y, sensor_maxmin[3][0], sensor_maxmin[3][1],
                                            servo_maxmin[3][1], servo_maxmin[3][2]);
                                    long position_z = z.mapPosition(axis_angle_y, sensor_maxmin[4][0], sensor_maxmin[4][1],
                                            servo_maxmin[4][1], servo_maxmin[4][2]);

                                    servo_data[2] = new ServoData("left_shoulder_x", position_x, default_speed);
                                    servo_data[3] = new ServoData("left_shoulder_y", position_y, default_speed);
                                    servo_data[4] = new ServoData("left_shoulder_z", position_z, default_speed);
                                }
                                else if (jointName.equals("left_elbow"))
                                {
                                    ServoMap z = new ServoMap();

                                    long position_z = z.mapPosition(axis_angle_z, sensor_maxmin[5][0], sensor_maxmin[5][1],
                                            servo_maxmin[5][1], servo_maxmin[5][2]);

                                    servo_data[5] = new ServoData("left_elbow_z", position_z, default_speed);
                                }
                                else if (jointName.equals("left_wrist"))
                                {
                                    ServoMap x = new ServoMap();
                                    ServoMap z = new ServoMap();

                                    long position_x = x.mapPosition(axis_angle_x, sensor_maxmin[6][0], sensor_maxmin[6][1],
                                            servo_maxmin[6][1], servo_maxmin[6][2]);
                                    long position_z = z.mapPosition(axis_angle_y, sensor_maxmin[7][0], sensor_maxmin[7][1],
                                            servo_maxmin[7][1], servo_maxmin[7][2]);

                                    servo_data[6] = new ServoData("left_wrist_x", position_x, default_speed);
                                    servo_data[7] = new ServoData("left_wrist_z", position_z, default_speed);
                                }
                                else if (jointName.equals("right_shoulder"))
                                {
                                    ServoMap x = new ServoMap();
                                    ServoMap y = new ServoMap();
                                    ServoMap z = new ServoMap();

                                    long position_x = x.mapPosition(axis_angle_x, sensor_maxmin[8][0], sensor_maxmin[8][1],
                                            servo_maxmin[8][1], servo_maxmin[8][2]);
                                    long position_y = y.mapPosition(axis_angle_y, sensor_maxmin[9][0], sensor_maxmin[9][1],
                                            servo_maxmin[9][1], servo_maxmin[9][2]);
                                    long position_z = z.mapPosition(axis_angle_y, sensor_maxmin[10][0], sensor_maxmin[10][1],
                                            servo_maxmin[10][1], servo_maxmin[10][2]);

                                    servo_data[8] = new ServoData("right_shoulder_x", position_x, default_speed);
                                    servo_data[9] = new ServoData("right_shoulder_y", position_y, default_speed);
                                    servo_data[10] = new ServoData("right_shoulder_z", position_z, default_speed);
                                }
                                else if (jointName.equals("right_elbow"))
                                {
                                    ServoMap z = new ServoMap();

                                    long position_z = z.mapPosition(axis_angle_y, sensor_maxmin[11][0], sensor_maxmin[11][1],
                                            servo_maxmin[11][1], servo_maxmin[11][2]);

                                    servo_data[11] = new ServoData("right_elbow_z", position_z, default_speed);
                                }
                                else if (jointName.equals("right_wrist"))
                                {
                                    ServoMap x = new ServoMap();
                                    ServoMap z = new ServoMap();

                                    long position_x = x.mapPosition(axis_angle_x, sensor_maxmin[12][0], sensor_maxmin[12][1],
                                            servo_maxmin[12][1], servo_maxmin[12][2]);
                                    long position_z = z.mapPosition(axis_angle_y, sensor_maxmin[13][0], sensor_maxmin[13][1],
                                            servo_maxmin[13][1], servo_maxmin[13][2]);

                                    servo_data[12] = new ServoData("right_wrist_x", position_x, default_speed);
                                    servo_data[13] = new ServoData("right_wrist_z", position_z, default_speed);
                                }
                            }
                        }
                        catch (IOException e)
                        {
                            if (str == null)
                            {
                                System.out.println("Str is null");
                                vectorBufferedReader.remove(i);
                                System.out.println("Str removed");
                                System.out.println(e);
                            }
                        }
                    }
                }

            } while (true);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        //PUBLISH TO TOPIC USING DDS PROTOCOL TO SEND DATA TO SLAVEARMS


    }
}
