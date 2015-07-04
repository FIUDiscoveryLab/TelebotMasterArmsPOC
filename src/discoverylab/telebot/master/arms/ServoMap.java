package discoverylab.telebot.master.arms;

/**
 * Created by Shadeh Ferris on 6/22/2015.
 */
public class ServoMap
{
    public ServoMap() {}

    public long mapPosition(long value, long sensor_max, long sensor_min, long servo_max, long servo_min)
    {
        long position;

        long mapped_value = map(value, sensor_max, sensor_min,
                servo_max, servo_min);

        position = constrain(mapped_value, servo_max, servo_min);

        return position;
    }

    public long map(long val, long sensor_max, long sensor_min, long servo_max, long servo_min)
    {
        long mapped = (val - sensor_min) * (servo_max - servo_min) / (sensor_max - sensor_min) + servo_min;

        return mapped;
    }

    public long constrain(long val, long max, long min)
    {
        if(val >= max)
        {
            val = max;
        }
        else if(val <= min)
        {
            val = min;
        }

        return val;
    }
}
