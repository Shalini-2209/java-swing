import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DnsServer
{
    private static int indexOf(String[] arr, String str)
    {
        str = str.trim();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals(str))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException
    {

        String host[] = {"google.com", "amazon.com", "myntra.com" ,"gmail.com", "yahoo.com"};
        String ip[] = {"192.168.1.1", "192.168.10.1", "192.168.10.11", "192.168.11.10", "192.168.120.1"};

        while(true)
        {
            DatagramSocket ss = new DatagramSocket(9875);

            //Receive
            byte[] in = new byte[1024];
            DatagramPacket dpReceive = new DatagramPacket(in, in.length);
            ss.receive(dpReceive);
            String inStr = new String(dpReceive.getData());

            //Process
            String outStr;
            System.out.println("Request for host : " + inStr);
            if(indexOf(host, inStr) != -1)
                outStr = ip[indexOf(host, inStr)];
            else
                outStr = "Host not found";

            //Send
            byte[] out = outStr.getBytes();
            InetAddress address = dpReceive.getAddress();
            int port = dpReceive.getPort();
            DatagramPacket dpSend = new DatagramPacket(out, out.length, address, port);
            ss.send(dpSend);

            ss.close();

        }
    }
}
