package hackerNewsFinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ds.desktop.notify.DesktopNotify;

public class HackerNews {
	 public static void run(String query, int limit) throws IOException, InterruptedException{
	    	
	    	ArrayList<String> listoflinks = new ArrayList<String>();
	    	ArrayList<String> listoftitles = new ArrayList<String>();
	    	ArrayList<Integer> listofscores = new ArrayList<Integer>();
	    	ArrayList<Article> listofarticles = new ArrayList<Article>();

	    	Long currentTime = System.currentTimeMillis() / 1000;	    	
	    	Long oneWeek = currentTime - 604800;	    	
	    	
	    	URL HN = new URL("http://hn.algolia.com/api/v1/search_by_date?query="+query+"&tags=story&numericFilters=created_at_i>" + oneWeek + ",created_at_i<" + currentTime);
	    	URLConnection hn = HN.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(hn.getInputStream()));
	        String inputLine;
	        String x = null;

	        while ((inputLine = in.readLine()) != null) 
	        	x  = inputLine;
	        
	        in.close();
	        
	        String regexTitle = "\"title\":\"[a-z0-9A-Z- ?!@#$%^&*()_=+/<>‘,:.]+";
	        String regexLinks = "\"url\":\"https?:[//]+[a-z.0-9]+[/0-9-a-zA-Z.]+";
	        String regexPoints = "\"points\":[0-9]+";
	       
	        regExCheckerForStringLists(regexTitle, x,9,listoftitles);
	        regExCheckerForStringLists(regexLinks, x,7, listoflinks);
	        regExCheckerForIntegerLists(regexPoints, x, listofscores,9);        
	        createArticles(listoflinks, listofscores, listoftitles,listofarticles);
	        Collections.sort(listofarticles);
	        displayNotifications(listofarticles,limit);
	                
	    	DesktopNotify.showDesktopMessage("No more articles matching your search","I will check again in 30 minutes");
	        TimeUnit.MINUTES.sleep(30);

	        run(query, limit);	        
	        }
	 public static void displayNotifications(ArrayList<Article> listofarticles, int limit) throws InterruptedException{
		 for (int i = 0; i< listofarticles.size(); i++){
	        	if (listofarticles.get(i).getScore()>=limit)
	        	System.out.println();
	        	DesktopNotify.showDesktopMessage(listofarticles.get(i).getTitle(), listofarticles.get(i).getLink());
	        	i++;
	        	TimeUnit.SECONDS.sleep(1);
	        }	
	 }
	    public static void regExCheckerForStringLists(String theRegEx, String StringtoCheck, int formatnumber, ArrayList<String> list){
	    	Pattern checkRegEx = Pattern.compile(theRegEx);
	    	
	    	Matcher RegExMatcher = checkRegEx.matcher(StringtoCheck);
	    	
	    	while(RegExMatcher.find()){
	    		String item = RegExMatcher.group();
	    		item = Correctformat(RegExMatcher.group(), formatnumber);
	    		list.add(item);    		
	    	}	    	
	    }
	    
	    public static void regExCheckerForIntegerLists(String theRegEx, String StringtoCheck, ArrayList<Integer> list,int formatnumber){
	    	Pattern checkRegEx = Pattern.compile(theRegEx);
	    	
	    	Matcher RegExMatcher = checkRegEx.matcher(StringtoCheck);
	    	
	    	while(RegExMatcher.find()){
	    		String item = RegExMatcher.group();
	    		item = Correctformat(RegExMatcher.group(), formatnumber);
	    		int newitem =Integer.parseInt(item);
	    		list.add((Integer)newitem);	    		
	    	}	    	
	    }	
	    
		public static String Correctformat (String token, int formatnumber){
			return token.substring(formatnumber);	    	
	    }
		
	    public static void createArticles(ArrayList<String>listoflinks, ArrayList<Integer>listofscores, ArrayList<String>listoftitles,ArrayList<Article>listofarticles){
	    	for(int i= 0; i<listoflinks.size();i++){
	    		listofarticles.add(new Article(listoflinks.get(i), listoftitles.get(i), listofscores.get(i)));
	    	}
	    }
}
