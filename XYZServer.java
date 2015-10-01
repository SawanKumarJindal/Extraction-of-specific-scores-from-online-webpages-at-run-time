/*
 *XYZServer.java
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
import java.math.*;
/*
 *This is the main class in which we are taking input from a URL and 
 *first converting it into html file,removing all the html tags from the file,
 *store it into the intermediate file and then
 *print out the data that the client wants.  
 *
 *@author Sawan
 *@author Prateek
 */
public class XYZServer
{
    public static String pattern1,result="";
    public static boolean worda =false,wordb =false;
    /*
     * This function will extract HTML code from the URL provided and 
     * will write that tag file into some file.
     */
    public static void extractHtmlCode() throws Exception
    {
	File textFile = new File("NFL1.txt");
	FileWriter intermediateF = new FileWriter(textFile);
	URL u = new URL(" http://sagarin.com/sports/nflsend.htm");
	BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
	
	String inputLine;
	
	while ((inputLine = in.readLine()) != null)
	    intermediateF.write(inputLine);
	
	in.close();
    }
    /*
     * This function will extract HTML code from the the temperary file and 
     * firstly convert that HTML file into normal file excluding the tags and then
     * it will take the pattern that the client gave and will check for the data
     * and will store the data related to the requested name in a string.
     */
    public static void extract(String msg) throws Exception
    {
	try{
	    File textFile = new File("intermediateFile.txt");
	    Scanner sc=new Scanner(new FileInputStream("NFL1.txt"));
	    FileWriter intermediateFW = new FileWriter(textFile);
	    String pattern1=msg.trim();//The pattern 
	    while ( sc.hasNext() )
		{
		    String line = sc.nextLine().trim();
		    int i=0;
		    wordb = false;
		    //This loop will continue working until the length of line.
		    while(i<line.length())
			{
			    // This loop will mark the boolean variable true when the "<" encounters.
			    if((int) line.charAt(i)==60)
				{
				    worda = true;
				}
			    // This loop will mark the boolean variable false when the ">" encounters.
			    if((int) line.charAt(i)==62)
				{
				    worda = false;		
				}
			    // This loop will print the characters.
			    else
				{
				    // This loop will check the boolean variable false and will print the value of the character that encountes.
				    if(worda == false)
					{
					    intermediateFW.write(line.charAt(i));
					    intermediateFW.flush();
					    wordb = true;
					}
				}
			    i++;
			    // This will print the new line character when the next line encounters.
			    if(i == line.length() && worda == false)
				{
				    intermediateFW.write('\n');
				}
			    // This will print the new line character when the next line encounters within the comment.
			    else if(i == line.length() && wordb == true)
				{
				    intermediateFW.write('\n');
				}
			}						
		}
	    int i=pattern1.split(" ").length;//It will take the no of words in the request.
	    Scanner sc1=new Scanner(textFile);		
	    String str,str1 = "";
	    String a[]=new String[i];
	    for(int k=0;k<i;k++)
		a[k]="";
	    while(sc1.hasNext())
		{
		    str="";			
		    int k = 0;
		    // This loop will assign the first three values in the array as the next element's value.
		    for(k=0;k<=i-2;k++)
			{
			    a[k] = a[k+1];
			}
		    a[k]=sc1.next();// Assign last value as the next value.
		    // This will concatenate all the array values in the string.
		    for(k=0;k<=i-1;k++)
			{
			    str=str + a[k] + " ";
			}	
		    str=str.trim();
		    // This loop will compare the string with the pattern.
		    if(str.compareTo(pattern1) == 0)
			{
			    result=str;
			    str1=sc1.nextLine();                        
			    int x=0;
			    while(x<str1.length())
				{
				    // This loop will store the data in a string based on the ascii values.
				    if((int)str1.charAt(x)<65 || ( (int)str1.charAt(x)>91 && (int)str1.charAt(x) <97 ) || (int)str1.charAt(x) >122)
					{
					    result=result+str1.charAt(x);
					}
				    else
					{
					    break;
					}
				    x++;
				}
			}
		}
	}
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }
    /*
     *This is the main function where the execution begins.
     *
     *@param args no command required.
     */
    public static void main(String[] args) throws Exception
    {
	try
	    {
		// This is the server socket and the socket connection.
		ServerSocket s=new ServerSocket(1800);
		Socket s1=s.accept();
		System.out.println("Connected");
		extractHtmlCode();// This will extract the code from the URL. 
		BufferedReader in=new BufferedReader(new InputStreamReader(s1.getInputStream()));// This stream will take the values from the client.
		PrintWriter out=new PrintWriter(s1.getOutputStream(),true);// This stream will send the data as characters to the client.
		String msg="";
		msg=in.readLine();
		extract(msg); // This will get the desired data into the string.
		out.println(result);
		msg=in.readLine();
		System.out.println(msg);
	    }
	catch(Exception e)
	    {
		e.printStackTrace();
	    }
    }
}
