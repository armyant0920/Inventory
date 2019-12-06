
import java.io.IOException;
import java.util.Scanner;
public class GetInputVer2 
{static String s;

 //static double d;
 //static int    i;
 //boolean       b; 
static int GetInt(int len) //
     //len:length limit for input
 {  while(true)
	 {System.out.println("plz input Int;max length="+len);	
	  Scanner key=new Scanner(System.in,"big5");
	  if (key.hasNextInt())		  
	     {s=key.next();
	      if (s.length()>len)
	      {s=s.substring(0,len);}
		  return Integer.parseInt(s);
	     }
	  else
	     {System.out.println("input<>Int,try again");}
	  }
 }
static double GetDouble(int len)//
//len:length limit for input
 { while(true)
 	{System.out.println("plz input double;max length="+len);	
 	Scanner key=new Scanner(System.in);
 	if (key.hasNextDouble())		  
    {s=key.next();
    if (s.length()>len)
    {s=s.substring(0,len);}
	  return Double.parseDouble(s);
    }
    
 	else
    {System.out.println("input<>Double,try again");}
 }
}

static String GetString(int len)//
//len:length limit for input
 { while(true)
	{	System.out.println("plz input;max length="+len);	
		Scanner key=new Scanner(System.in);
		if (key.hasNext())
		{s=key.next();
		if (s.length()>len)
	      {s=s.substring(0,len);}
		 return s;}
		else
		{System.out.println("input error,try again");}
    }
 }
	 
		 /*if (s.length()>len) 
		 {System.out.println("input length over limit of "+len);break;}*/
		  
		 /*case 2:
			  System.out.println("plz input double;max length="+len);		
			  if (key.hasNextDouble())
			  {d=key.nextDouble();
			   fine=true;
			   break;}
			  else
			  {System.out.println("input<>Int,try again");break;}
	      default: {System.out.println("System error");}*/
public static void main(String[] args) throws IOException
 {	int age;
	String name;
	double height,weight;
	while(true)
	{System.out.println("please enter your name");
	 name=GetString(5);//set limit value var type
	 System.out.println("please enter your age");
	 age=GetInt(3);//set limit value var type
	
	 System.out.println("please enter your height");
	 height=GetDouble(5);//
	 System.out.println("please enter your weight");
	 weight=GetDouble(4);//
	 System.out.println
	 ("your name is:"+name+" age is:"+age+" height is:"+height+" weight is:"+weight);
	 
	}
 }
}
