package discoverylab.telebot.master.arms;

/**
 * Created by Shadeh Ferris on 6/25/2015.
 */
public class SensorData
{
    private long sensor_max;
    private long sensor_min;

    public SensorData()
    {
        this.sensor_max = -360;
        this.sensor_min = 360;
    }

    public long getSensorMax()
    {
        return sensor_max;
    }

    public long getSensorMin()
    {
        return sensor_min;
    }

    public void setSensorMaxMin(long val)
    {
        if(val >= sensor_max)
        {
            sensor_max = val;
        }

        if(val <= sensor_min)
        {
            sensor_min = val;
        }
    }
}
