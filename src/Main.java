import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<Movie> movieList = new ArrayList<>();
		ArrayList<String> movieStrings = new ArrayList<>();
		ArrayList<String> users = new ArrayList<>();
		boolean done = false;
		Scanner s = new Scanner(System.in);
		while (!done) {
			
			System.out.println("Current user's name?");

			users.add(s.nextLine());
			
			System.out.println("Please enter a movie for consideration. Type \"done\" when done.");
			while (true) {
				String input = s.nextLine();
				if (input.equalsIgnoreCase("done")) {
					break;
				}
				movieStrings.add(input);
				System.out.println("Please enter a movie for consideration. Type \"done\" when done.");
			}
			
			System.out.println("If you would like to add another user, type yes.");
			
			String yaya = s.nextLine();
			
			if (!yaya.equalsIgnoreCase("yes")) {
				break;
			}
		}
		
		int i = 0;
		
				
		System.out.println("Currently loading movies from IMDb... please wait a second..." + "\n" + "\n");
		
		for (String movie : movieStrings) {
			Movie item;
			item = Webscraper.scrapeMovie(movie);
			movieList.add(item);
		}
				
		for (String user : users) {
			
			i = 0;
			
			for (Movie movie : movieList) {
				
				System.out.println((i++ + 1) + ": " + movie);
			}
			
			
			System.out.println(user + ", please select your three most wanted movies in order by entering their respective numbers.");
			System.out.println("First most wanted movie?");
			

			while (true) {
				String input = s.nextLine();
				System.out.println("I got here.");
				
				if (!isInteger(input)) {
					System.out.println("Please enter a number.");
					continue;
				} else if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > movieList.size()) {
					System.out.println("Please enter a valid number within the specific range.");
					continue;
				} else {
					movieList.get(Integer.parseInt(input) - 1).addVote(3);
					break;
				}
			}
			
			System.out.println("Second most wanted movie?");
			while (true) {
				String input = s.nextLine();
				if (!isInteger(input)) {
					System.out.println("Please enter a number.");
					continue;
				} else if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > movieList.size()) {
					System.out.println("Please enter a valid number within the specific range.");
					continue;
				} else {
					movieList.get(Integer.parseInt(input) - 1).addVote(2);
					break;
				}
			}
			
			System.out.println("Third most wanted movie?");
			
			while (true) {
				String input = s.nextLine();
				if (!isInteger(input)) {
					System.out.println("Please enter a number.");
					continue;
				} else if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > movieList.size()) {
					System.out.println("Please enter a valid number within the specific range.");
					continue;
				} else {
					System.out.println("movie chose: " + movieList.get(Integer.parseInt(input) - 1));
					System.out.println(movieList.get(Integer.parseInt(input) - 1).getVotes());
					movieList.get(Integer.parseInt(input) - 1).addVote(1);
					break;
				}
			}
		}
		
		Collections.sort(movieList);
		
		
		if (movieList.get(0).getVotes() > ((movieList.size() * 6)/2)) {
			System.out.println("Movie chosen is " + movieList.get(0) + ". Congratulations.");
		} else {
			System.out.println("A single movie has not receieved the majority of the points.");
		}	
		
		boolean concluded = false;
		
			while (!concluded) {
				for (int j = 1; j < movieList.size() - 2; j++) {
					if (movieList.get(j).getVotes() > movieList.get(j + 1).getVotes()) {
						for (int k = j + 1; k < movieList.size() - 1; k++) {
							movieList.remove(k);
						}
					}
				}
				
				for (Movie movie : movieList) {
					movie.zeroVote();
				}
				
				Collections.sort(movieList);
				
				for (String user : users) {
					
					i = 0;
					
					for (Movie movie : movieList) {
						System.out.println((i++ + 1) + ": " + movie);
					}
					
					System.out.println(user + ", please vote from the list above.");
					
					while (true) {
						String input = s.nextLine();
						if (!isInteger(input)) {
							System.out.println("Please enter a number.");
							continue;
						} else if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > movieList.size()) {
							System.out.println("Please enter a valid number within the specific range.");
							continue;
						} else {
							movieList.get(Integer.parseInt(input) - 1).addVote(6);
							break;
						}
					}
					
				}
				if (movieList.get(0).getVotes() > ((movieList.size() * 6/2))) {
					System.out.println("Movie chosen is " + movieList.get(0) + ". Congratulations.");
					concluded = true;
					break;
				} else {
					System.out.println("A single movie has not receieved the majority of the points.");
				}
				
				
			}
			
			s.close();
			
		}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
}
