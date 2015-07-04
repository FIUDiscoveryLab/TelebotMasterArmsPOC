package discoverylab.telebot.master.arms;

/**
 * Created by Shadeh Ferris on 6/22/2015.
 */
public class ServoData
{
    private String jointName;
    private long position;
    private long speed;

    public ServoData(String joint_name, long pos, long sp)
    {
        this.jointName = joint_name;
        this.position = pos;
        this.speed = sp;
    }

    public String getJointName()
    {
        return jointName;
    }

    public long getServoPosition()
    {
        return position;
    }

    public long getServoSpeed()
    {
        return speed;
    }

}
