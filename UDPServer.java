import java.io.*;
import java.net.*;
 
public class UDPServer
{
    public static void main(String args[])
    {
        DatagramSocket sock = null;
         int port = 7477;
        try
        {
            //1. creating a server socket, parameter is local port number
            sock = new DatagramSocket(7477);
             InetAddress host = InetAddress.getByName("localhost");
			 BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
            //buffer to receive incoming data
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
             
            //2. Wait for an incoming data
            echo("Server socket created. Waiting for incoming data...");
             
            //communication loop
                sock.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());
                 
                //echo the details of incoming data - client ip : client port - client message
                echo(s);
				 echo("Enter message to send : ");
                String s1 = (String)cin.readLine();
                byte[] b1 = s1.getBytes();
              DatagramPacket  dp = new DatagramPacket(b1 , b1.length , host , port);    
               // DatagramPacket  dp = new DatagramPacket(b , b.length , host , port);
                sock.send(dp);
               // s = "OK : " + s;
               // DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
               // sock.send(dp);
				 sock.receive(incoming);
                data = incoming.getData();
                s = new String(data, 0, incoming.getLength());
                 
                //echo the details of incoming data - client ip : client port - client message
                echo(s);
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