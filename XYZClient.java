/*
 *XYZClient.java
 *
 *Version 1.0
 *   $Id$
 *
 * Revisions: 
 * $ Log$
 *
 */
import java.io.*;
import java.util.*;
import java.net.*;
/*
 *This is the main class in which we will send the request to the server regarding the 
 *record of the team and then it will reply back with the data..  
 *
 *@author Sawan
 *@author Prateek
 */
public class XYZClient
{
    public static void main(String[] args) throws Exception
    {
	try
	    {
		// This is the socket connection with the server.
		Socket s=new Socket("localhost",1800);
		File textFile = new File("file.txt");
		FileWriter intermediateF = new FileWriter(textFile);// File writer stream.
		BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);
		String msg="";
		
		msg=kb.readLine();
		out.println(msg);
		msg=in.readLine();
		System.out.println(msg);
		intermediateF.flush();
		intermediateF.write(msg);// This will write the data into the desired file.
		intermediateF.flush();
		msg="Thank you";
		out.println(msg);
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }
}
