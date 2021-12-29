
public class Movie implements Comparable<Movie> {
	private String title;
	private String description;
	private int votes;
	
	public Movie(String title, String description) {
		this.title = title;
		this.description = description;
		votes = 0;
	}

	public int getVotes() {
		return votes;
	}
	
	public void addVote(int num) {
		votes += num;
	}
	
	public void zeroVote() {
		votes = 0;
	}
	
	public int compareTo(Movie other) {
		return other.votes - this.votes;
	}
	
	public String toString() {
		return (title + "\n" + description + "\n" + "\n");
	}
}
