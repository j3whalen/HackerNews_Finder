/*
 * @author Joshua Whalen
 * This is a program to search through Hacker news using their API.
 * User inputs one Keyword and a point limit and the API gives back all
 * articles relevant to the keyword in Json from the past week. Then the 
 * program grabs thetitles, scores, and links using regular expressions. 
 * Then it creates objects out of that information and sorts them from 
 * greatest to least. Then the program notifies the user using DS desktop 
 * notification for ALL of the articles that are above the point limit.
 */
package hackerNewsFinder;

import java.io.IOException;
import java.util.Scanner;

public class Main {	
	
    public static void main(String[] args) throws InterruptedException, IOException{
    	System.out.println("Keyword:");
    	Scanner input = new Scanner(System.in);
    	String query = input.nextLine();
    	System.out.println("Point Limit:");
    	int limit = input.nextInt();
    	input.close();
    	HackerNews.run(query, limit);
    }
   

}