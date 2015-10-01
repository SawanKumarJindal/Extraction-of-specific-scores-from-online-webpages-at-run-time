/*
 *XYZ.java
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
/*
 *This is the main class in which we are taking input from a certain file and 
 *remove all the html tags from the file, store it into the intermediate file and then
 *print out the data that is between two phrases.  
 *
 *@author Sawan
 *@author Prateek
 */
public class XYZ
{
    public static String pattern1,pattern2;
    public static boolean worda =false,wordb =false;
    /*
     *This is the main function where the execution begins.
     *
     *@param args no command required.
     */
    public static void main(String[] args)
    {
	try
	    {
		/*
		 *This is the section where all the File,FileWriter and BufferedReader objects are created. 
		 *
		 */
		File textFile = new File("intermediateFile.txt");
		String outputFileName=args[3];
		File outputFile = new File(outputFileName);
		Scanner sc=new Scanner(new FileInputStream(args[2]));
		FileWriter intermediateFW = new FileWriter(textFile);
		FileWriter outputFW = new FileWriter(outputFile);
		String pattern1=args[0];//The starting pattern 
		String pattern2=args[1];//The ending pattern
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
				    {intermediateFW.write('\n');}
				// This will print the new line character when the next line encounters within the comment.
				else if(i == line.length() && wordb == true)
				    {
					intermediateFW.write('\n');
				    }
			    }						
		    }
		Scanner sc1=new Scanner(textFile);
		String str,str1 = "";
		String a[]=new String[2];
		a[0]="";
		a[1]="";
		while(sc1.hasNext())
		    {
			a[0]=a[1];
			a[1]=sc1.next();
			str=a[0] + " " + a[1];
			// This loop will check the first pattern.If true then it will proceed further.
			if(str.compareTo(pattern1) == 0)
			    {
				while(sc1.hasNext())
				    {
					str1=sc1.nextLine();
					// This loop will break when it encounters the second pattern. 
					if(str1.matches(pattern2) == true)
					    {
						break;
					    }
					outputFW.write(str1);
					outputFW.flush();
					outputFW.write('\n');
					
				    }
			    }
		    }
	    }
	catch(Exception e)
	    {e.printStackTrace();}
    }
}
