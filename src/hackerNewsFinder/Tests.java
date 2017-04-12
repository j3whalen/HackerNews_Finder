package hackerNewsFinder;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Tests {
	String query = "google";
	int limit = 60;
	ArrayList<String> listoflinks = new ArrayList<String>();
	ArrayList<String> listoftitles = new ArrayList<String>();
	ArrayList<Integer> listofscores = new ArrayList<Integer>();
	ArrayList<Article> listofarticles = new ArrayList<Article>();
	String token = "This is random Data";
	int formatnumber = 3;
    String regexTitle = "\"title\":\"[a-z0-9A-Z- ?!@#$%^&*()_=+/<>‘,:.]+";
    String regexLinks = "\"url\":\"https?:[//]+[a-z.0-9]+[/0-9-a-zA-Z.]+";
    String regexPoints = "\"points\":[0-9]+";
    String ValidLink = "\"url\":\"https://github.com/netgusto/upndown";
    String ValidScore = "\"points\":2,\"";
	
	public void AddValidDataToLists(){
		listoflinks.add("link");
		listofscores.add(78);
		listoftitles.add("title");
		
	}
	public void clearData(){
		listoflinks.clear();
		listofscores.clear();
		listoftitles.clear();
		listofarticles.clear();
		
	}

	@Test
	public void test_CorrectFormat(){
		assertNotEquals(null, HackerNews.Correctformat(token, formatnumber));		
	}
	
	@Test
	public void test_CorrectFormat2(){
		assertEquals("s is random Data", HackerNews.Correctformat(token, formatnumber));		
	}
	
	@Test
	public void test_CreateArticles(){
		AddValidDataToLists();
		HackerNews.createArticles(listoflinks, listofscores, listoftitles, listofarticles);
		assertNotEquals(null, listofarticles.get(0));
		clearData();
	}
	
	@Test
	public void test_CreateArticles2(){
		AddValidDataToLists();
		HackerNews.createArticles(listoflinks, listofscores, listoftitles, listofarticles);
		assertEquals("link", listofarticles.get(0).getLink());
		clearData();
	}
	
	@Test
	public void test_CreateArticles3(){
		AddValidDataToLists();
		HackerNews.createArticles(listoflinks, listofscores, listoftitles, listofarticles);
		assertEquals(78, listofarticles.get(0).getScore());
		clearData();
	}
	
	@Test
	public void test_CreateArticles4(){
		AddValidDataToLists();
		HackerNews.createArticles(listoflinks, listofscores, listoftitles, listofarticles);
		assertEquals("title", listofarticles.get(0).getTitle());
		clearData();
	}
	
	@Test
	public void test_regExCheckerForStringLists(){
		AddValidDataToLists();
		HackerNews.regExCheckerForStringLists(regexLinks, ValidLink,  formatnumber, listoflinks);
		assertNotEquals(null, listoflinks.get(0));
		clearData();
	}
	
	@Test
	public void test_regExCheckerForIntegerLists(){
		AddValidDataToLists();
		HackerNews.regExCheckerForIntegerLists(regexLinks, ValidScore, listofscores,  formatnumber);
		assertNotEquals(null, listofscores.get(0));
		clearData();
	}

}
