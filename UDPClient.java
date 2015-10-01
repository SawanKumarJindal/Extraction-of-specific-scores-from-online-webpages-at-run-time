import java.io.*;
import java.net.*;
 
public class UDPClient
{
    public static void main(String args[])
    {
        DatagramSocket sock = null;
        int port = 7477;
        String s;
         
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
         
        try
        {
            sock = new DatagramSocket();
             
            InetAddress host = InetAddress.getByName("localhost");
             
                //take input and send the packet
                echo("Enter message to send : ");
                s = (String)cin.readLine();
                byte[] b = s.getBytes();
                 
                DatagramPacket  dp = new DatagramPacket(b , b.length , host , port);
                sock.send(dp);
                 
                //now receive reply
                //buffer to receive incoming data
              //  byte[] buffer = new byte[65536];
               // DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				//	sock.receive(reply);
                sock.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());
                 
                //echo the details of incoming data - client ip : client port - client message
                echo(s); 
               // byte[] data = reply.getData();
               // s = new String(data, 0, reply.getLength());
                 
                //echo the details of incoming data - client ip : client port - client message
                //echo(s);
            echo("Enter message to send : ");
                s = (String)cin.readLine();
                b = s.getBytes();
                 
                dp = new DatagramPacket(b , b.length , host , port);
                sock.send(dp);
        }
         
        catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
    }
     
    //simple function to echo data to terminal
    public static void echo(String msg)
    {
        System.out.println(msg);
    }
}