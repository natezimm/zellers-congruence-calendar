package com.nathan;

import java.util.Scanner;

public class Main {
    public static boolean leapYear(int year){
        //Year is leap year if divisible by 400 or divisible by 4 and not 100
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
            return true;
        } else {
            return false;
        }
    }

    public static int daysInMonth(int month, int year){
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        //If leap year then February will have 29 days
        if(leapYear(year)){
            days[1] = 29;
        }
        return days[month-1];
    }

    public static int firstDayOfMonth(int month, int year){
        if(month == 1) {
            month = 13;
            year--;
        }
        if(month == 2){
            month = 14;
            year--;
        }

        //Initialize variables for Zeller's formula
        int mon = month;
        int yearOfCentury = year % 100;
        int century = year / 100;

        //Zeller's Congruence formula for finding day of the week
        int result = (26 * ( mon + 1) / 10 + yearOfCentury + yearOfCentury / 4 + century / 4 + 5 * century) % 7;

        return result;
    }

    public static void printCalendarDays(int daysInMonth, int month, int year){
        int dayOfWeek = 0;
        int dayOfMonth = 1;
        //Loop over the weeks to print out the calendar
        while(dayOfWeek <= 6){
            if(dayOfMonth==1){
                //Calculate first day of month and find where to print it
                dayOfWeek = firstDayOfMonth(month, year);
                for(int i = 1; i <= dayOfWeek; i++){
                    System.out.print("     ");
                }
            }
            //Once loop gets to Saturday, then a new line will start after
            if(dayOfWeek == 6){
                if(dayOfMonth < 10){
                    //Print extra space for single digit dates
                    System.out.print(" " + Integer.toString(dayOfMonth) + "\n");
                } else {
                    System.out.print(Integer.toString(dayOfMonth) + "\n");
                }
                dayOfWeek = 0;
            } else {
                if(dayOfMonth < 10){
                    //Print extra space for single digit dates
                    System.out.print(" " + Integer.toString(dayOfMonth) + "   ");
                } else {
                    System.out.print(Integer.toString(dayOfMonth) + "   ");
                }
                dayOfWeek++;
            }
            //End loop on once days equal total amount of days in the month
            if(dayOfMonth < daysInMonth){
                dayOfMonth++;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //User decides what month and year to print
        System.out.println("Enter an integer for the month (1-12): ");
        int month = scanner.nextInt();
        System.out.println("Enter a year: ");
        int year = scanner.nextInt();

        String[] monthArray = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        //Print header to the output
        System.out.println(monthArray[month - 1] + " " + year);
        System.out.println("Sun" + " Mon " + " Tue " + " Wed " + " Thu " + " Fri " + " Sat");

        //Find out how many days are in the month
        int days = daysInMonth(month, year);

        //Print calendar to the user
        printCalendarDays(days, month, year);

    }
}
