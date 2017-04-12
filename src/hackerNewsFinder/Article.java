package hackerNewsFinder;

public class Article implements Comparable<Article>{
	private String link = "";
	private String title = "";
	private int score = 0;
	
	public Article(String link, String title, int score){
		super();
		this.link = link;
		this.title = title;
		this.score = score;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public int compareTo(Article compareArticleScores) {

		int compareQuantity = ((Article) compareArticleScores).getScore();
		return compareQuantity - this.score;

	}


}
