package JavaLab9;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DnsClient
{
    public static void main(String[] args) throws IOException
    {
        DatagramSocket ds = new DatagramSocket();
        Scanner input = new Scanner(System.in);

        //Send
        System.out.println("Enter Host name : ");
        String outStr = input.nextLine();
        byte[] out = outStr.getBytes();
        InetAddress address = InetAddress.getLocalHost();
        int port = 9875;
        DatagramPacket dpSend = new DatagramPacket(out, out.length, address, port);
        ds.send(dpSend);

        //Receive
        byte[] in = new byte[1024];
        DatagramPacket dpReceive = new DatagramPacket(in, in.length);
        ds.receive(dpReceive);
        String inStr = new String(dpReceive.getData());
        System.out.println("IP Address : " + inStr);

        input.close();
        ds.close();

    }
}


