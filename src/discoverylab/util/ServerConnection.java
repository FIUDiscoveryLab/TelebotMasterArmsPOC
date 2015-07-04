package discoverylab.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Shadeh Ferris on 7/2/2015.
 */
public class ServerConnection
{
    private Socket clientSocket;

    public ServerConnection(){}

    public void connect(int port)
    {
        System.out.println("Connecting ...");

        try
        {
            ServerSocket server = new ServerSocket(port);
            clientSocket = server.accept();
            System.out.println("ServerConnection Successful.");
        }
        catch(IOException e)
        {
            System.out.println("ServerConnection Failed.");
            e.printStackTrace();
        }
    }

    public Socket getClientSocket()
    {
        return clientSocket;
    }

    public boolean isConnected()
    {
        if(getClientSocket() != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
