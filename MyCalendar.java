//Jack Pate
//CS& 141
//Assingment 3
//Prints an	interactive	calendar 
//Approximately 12 hours


import java.util.*;
import java.io.*;


public class MyCalendar	{
   public static final int SCALE = 10;
   public static final int START_OF_YEAR = 7;
   
   public static void main(String[]	args) 
         throws FileNotFoundException {
      Scanner input = new Scanner(new File("calendarEvents.txt"));
      Scanner console = new Scanner(System.in);
      String eventDate = console.next();
      int month = 0;
      int day = 0; 
      int lines = 0;      
      String[][] event = new String[12][];
      event[0] = new String [31];
      event[1] = new String [28];
      event[2] = new String [31];
      event[3] = new String [30];
      event[4] = new String [31];
      event[5] = new String [30];
      event[6] = new String [31];
      event[7] = new String [31];
      event[8] = new String [30];
      event[9] = new String [31];
      event[10] = new String [30];
      event[11] = new String [31];
      while (input.hasNextLine()) {
         String date = input.next();
         day = dayFromDate(date);
         month = monthFromDate(date);
         String eventName = input.next();                                
         event[month - 1][day - 1] = eventName;
         lines++;
      }
      userMenu();    
      String answer = console.next();
      while(!answer.equals("q")) {         
         if(answer.equals("e")) {
            userDate(console);
         } else if(answer.equals("t")) {
            todaysDate();  
         } else if(answer.equals("n")) {            
            nextMonth(month, day);  
            displayDate(month, day);
         } else if(answer.equals("p")) {
            month = previousMonth(month);        
         } else if(answer.equals("ev")) {
            System.out.println("Please enter the event you would like to add.");
            eventDate = console.next();
            day = dayFromDate(eventDate);
            month = monthFromDate(eventDate);
            String eventName = console.next();                                
            event[month - 1][day - 1] = eventName;
            System.out.println("Event added!");
            System.out.println();      
         
         //Prints a file of the calander
         } else if(answer.equals("fp")) {               
            System.out.println("Please enter what month you would like printed.");
            String date = console.next();
            day = dayFromDate(date);
            month = monthFromDate(date);
            PrintStream output = new PrintStream(new File("Calendar.txt"));                     
            output.println(padded(month, SCALE * 4));
            int monthStart = monthStart(month);
            int daysInMonth = daysInMonth(month);                       
            for (int i = 1; i	<= SCALE / 4; i++) {
               output.print(" ");
            }	  
            output.print("SUN");
            for (int i = 1; i	<= SCALE - 3; i++) {
               output.print(" ");
            }	  
            output.print("MON");
            for (int i = 1; i	<= SCALE - 3; i++) {
               output.print(" ");
            }	  
            output.print("TUE");
            for (int i = 1; i	<= SCALE - 3; i++) {
               output.print(" ");
            }	  
            output.print("WED");
            for (int i = 1; i	<= SCALE - 3; i++) {
               output.print(" ");
            }	  
            output.print("THU");
            for (int i = 1; i	<= SCALE - 3; i++) {
               output.print(" ");
            }	  
            output.print("FRI");
            for (int i = 1; i	<= SCALE - 3; i++) {
               output.print(" ");
            }	  
            output.println("SAT");                        
            for (int row = 1;	(-monthStart + (row - 1) * 7) <= daysInMonth; row++) {       
               //row
               for (int i = 1; i	<= SCALE * 7; i++) {
                  output.print("=");
               }	  
               output.println();	
               output.print("|");	           
               for (int n = 1; n	<= 7; n++) {        
                  int currentDay = (n - monthStart + (row - 1) * 7);
                  if (currentDay == day) {
                     output.print("*");
                     output.print(padded(day, SCALE -	2));   	
                  }  else if (currentDay > 0 && currentDay <= daysInMonth) {
                     output.print(padded(currentDay, SCALE -	1));
                  } else { 
                     for (int a = 1; a	<= SCALE - 1; a++)	{
                        output.print(" ");
                     }
                  }
                  output.print("|");	              			
               }
               output.println();
             //void      
               for (int i = 1; i	<= SCALE / 3; i++) {
                  output.print("|");   
                  for (int n	= 1; n <= 7; n++) {
                     output.print(" ");
                     for (int a = 1; a	<= SCALE - 2; a++)	{
                        output.print(" ");
                     }                  
                     output.print("|");					   
                  }
                  output.println();			  
               }                        
            }
            for (int i = 1; i	<= SCALE * 7; i++) {
               output.print("=");
            }	  
            output.println();                                               
         } else {
            System.out.println("Please enter a valid command");
            System.out.println();
         }        
         userMenu(); 
         answer = console.next();       
      }
      System.out.print("Goodbye!");
   }
  

   
   //Displays the month	and day input by the user
   public static void displayDate(int month, int day) {
      System.out.println("Month:" +	month);
      System.out.println("Day:" + day);
   }
   
   //Checks	what month the user typed in
   public static int monthFromDate(String date)	{ 
      int index = date.indexOf("/");
      int month = Integer.parseInt(date.substring(0, index));
      return month;		   
   }
	
   //Checks what day the user typed in	 
   public static int dayFromDate(String date) {
      int index = date.indexOf("/");
      int day = Integer.parseInt(date.substring(index + 1));
      return day;
   }		

   //Prints a character a selected	amount of times		 
   public static void writeChars(char ch, int number) {
      for (int i = 1; i	<= number; i++)	{
         System.out.print(ch);
      }
   }
   	   
   //Fills	in the blank spaces	  
   public static void fillVoid() {
      for (int i = 1; i	<= SCALE / 3; i++) {
         writeChars('|', 1);   
         for (int n	= 1; n <= 7; n++) {
            System.out.print(" ");
            writeChars(' ', SCALE - 2);
            System.out.print("|");					   
         }
         System.out.println();			  
      }
   }

   //Prints a week	 
   public static void drawRow(int row, int monthStart, int daysInMonth, int day) {
      writeChars('=', SCALE * 7);
      System.out.println();	
      writeChars('|', 1);	      
      for (int n = 1; n	<= 7; n++) {        
         int currentDay = (n - monthStart + (row - 1) * 7);
         if (currentDay == day) {
            writeChars('*', 1);
            System.out.print(padded(day, SCALE -	2));   	
         }  else if (currentDay > 0 && currentDay <= daysInMonth) {
            System.out.print(padded(currentDay, SCALE -	1));
         //  } else if (eventDate == day) {
         // System.out.print(eventName);
         } else { 
            writeChars(' ', SCALE - 1);
         }
         System.out.print("|");	         					
      }
      System.out.println();
      fillVoid();		 
   }
       
   //Used to add spaces before a character	   
   public static String	padded(int n, int width) {
      String s = "" + n;
      for (int i = s.length(); i < width; i++) {
         s = " " + s;
      }
      return s;
   }
   
   //Counts the days in the month	
   public static int daysInMonth (int month) {
      if (month == 2) {
         return 28;
      } else if (month == 4 || month ==  6 || month == 9 || month == 11) {
         return 30;
      } else {
         return 31;
      }
   } 

   //Sets the first day of the month
   public static int monthStart (int month) {
      int absoluteDay = 1;
      for (int i = 1; i < month; i++) {
         absoluteDay += daysInMonth(i);
      }      
      return (((absoluteDay + START_OF_YEAR) - 2) % 7);    
   }
   
   //Prints Calendar
   public static int drawMonth(int month, int day) {
      System.out.println(padded(month, SCALE * 4));
      int monthStart = monthStart(month);
      int daysInMonth = daysInMonth(month);
      displayDays();
      for (int row = 1;	(-monthStart + (row - 1) * 7) <= daysInMonth; row++) {       
         drawRow(row, monthStart, daysInMonth, day);
      }
      for (int i = 1; i	<= SCALE * 7; i++) {
         System.out.print("=");
      }	  
      System.out.println();
      return month;
   }
   
   //Displays the days of the week
   public static void displayDays() {
      writeChars(' ', SCALE / 4);
      System.out.print("SUN");
      writeChars(' ', SCALE - 3);
      System.out.print("MON");
      writeChars(' ', SCALE - 3);
      System.out.print("TUE");
      writeChars(' ', SCALE - 3);
      System.out.print("WED");
      writeChars(' ', SCALE - 3);
      System.out.print("THU");
      writeChars(' ', SCALE - 3);
      System.out.print("FRI");
      writeChars(' ', SCALE - 3);
      System.out.println("SAT");   
   }
   
    //Prints a command menu for the user
   public static void userMenu() {
      System.out.println("Please type a command");
      System.out.println("   \"e\" to enter a date and display the corresponding calender");
      System.out.println("   \"t\" to get todays date and diplay today's calender");
      System.out.println("   \"n\" to display the next month");
      System.out.println("   \"p\" to display the previous month");
      System.out.println("   \"fp\" to print a selected month to a file");
      System.out.println("   \"ev\" to add a new event");
      System.out.println("   \"q\" to quit the program");
   }
   
   //Lets the user input a date to view
   public static int userDate(Scanner console) {
      System.out.println("Please enter a date");
      String date = console.next();
      int day = dayFromDate(date);
      int month = monthFromDate(date);
      drawMonth(month, day);
      System.out.println();
      displayDate(month, day);
      System.out.println();      
      return month;
   }
      
   //Displays the current date
   public static int todaysDate() {
      Calendar cal = Calendar.getInstance();
      int month = (cal.get(Calendar.MONTH) + 1);     
      int day = (cal.get(Calendar.DATE));
      System.out.println("This month:"); 
      drawMonth(month, day);          
      System.out.println("Month:" + month);	 
      System.out.println("Day:" + cal.get(Calendar.DATE));
      System.out.println();
      return month;
   }
   
   //Displays the next month
   public static int nextMonth(int month, int day) {       
      if(month == 0) {
         System.out.println("Please enter a date.");
         return 0;
      } else if(month < 12) {
         month++;
      } else if(month == 12) {
         month = 1;
      }
      drawMonth(month, day);
      return month;
   }
   
   //Displays the previous month
   public static int previousMonth(int month) { 
      if(month == 0) {
         System.out.println("Please enter a date.");
         return 0;
      } else if(month > 1) {
         month--;
      } else  if (month == 1) {
         month = 12;
      }
      return month;
   }

   //Adds a set number of black spaces	   
   public static void writeSpaces(int number) {
      for (int i = 1; i	<= number; i++)	{
         System.out.print(" ");
      }
   }
   
   //Draws the letter J in	ASCII	
   public static void drawJ() {
      drawTopJ();
      drawJBody();
      drawJBottom();
   }
  
   //Draws the top of the J	
   public static void drawTopJ() {
      for (int i = 1; i	<= 2; i++) {
         System.out.println();
         for (int j	= 1; j <= 20; j++) {
            System.out.print("J");
         }
      }			 
   }
   
   //Draws the middle of the J	   
   public static void drawJBody() {
      for (int i = 1; i	<= 4; i++) {
         System.out.println();
         for (int j	= 1; j <= 8; j++) {
            System.out.print(" ");        	
         }
         for (int k	= 1; k <= 4; k++) {
            System.out.print("J");
         }
      }
      System.out.println();	
   }

   //Draws the bottom of the J	   
   public static void drawJBottom()	{
      writeChars('J', 4);
      writeSpaces(4);
      writeChars('J', 4);
      System.out.println();
      writeSpaces(1);
      writeChars('J', 4);
      writeSpaces(2);
      writeChars('J', 4);
      System.out.println();
      writeSpaces(4);
      writeChars('J', 4);
      System.out.println();
   	 
   }
}