package discoverylab.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by Shadeh Ferris on 7/2/2015.
 */
public class DataParser
{
    public DataParser() {}

    public String[] parse(Socket clientSocket)
    {
        Vector vectorBufferedReader = new Vector();

        String[] data = new String[1];

        try
        {
            vectorBufferedReader.add(new BufferedReader(new InputStreamReader((clientSocket.getInputStream()))));
            String delimiter = " ";

            for (int i = 0; i < vectorBufferedReader.size(); i++)
            {
                BufferedReader in = (BufferedReader) vectorBufferedReader.elementAt(i);
                String str = null;

                try {
                    str = in.readLine();

                    if (str != null) {
                        StringTokenizer tkn = new StringTokenizer(str, delimiter);
                        int token_count = tkn.countTokens();

                        data = new String[token_count];

                        for(int j = 0; j < token_count; j++)
                        {
                            data[j] = tkn.nextToken();
                        }

                        vectorBufferedReader.remove(i);

                        return data;
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
        }
        catch(IOException e)
        {
            e.printStackTrace();
            data[0] = "Parsing failed.";
        }

        return data;
    }
}
