import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) throws IOException {
		int userID = 0;
		int movieID, threshhold;
		Scanner in = new Scanner(System.in);
		PredictionCalculator pc;
		SimilarityCalculator sc;
		NeighborhoodCalculator nc;

		System.out.println("What file would you like to use?:");
		fileReader fr = new fileReader(in.next());
		//		int lines = fr.countLines();
		//		RatingMatrixCreator matrix = new RatingMatrixCreator(lines, lines);
		fr.readFile();
		in.nextLine();
		boolean movieAnalysis;
		System.out.println("Do you wish to use:"
				+ " 1. 10m movielens file with cosine similarity, OR"
				+ " , 2. Book Crossing file with the Baseline Predictor");
		if(in.next().contains("1")){
			movieAnalysis = true;
		} else if(in.next().contains("2")){
			movieAnalysis = false;
		}
				
				
				
		System.out.println("What would you like to know? Select from the following options: ");

		//user input prompt/selection
		String[] userChoice;
		userChoice = new String[2];
		userChoice[0] = "1. (a.) Given a user u and item i, what is the system's prediction for the user's likely"
				+ " preference of that item?";
		userChoice[1] = "2. (b.) Given a user u and a threshhold n, what are the n-highest predicted preferences "
				+ "for that user?";


		for (int i = 0; i < userChoice.length; i++)
		{		System.out.println(userChoice[i]); }

		boolean end = false;
		boolean valid = false;
		while (end == false){
			System.out.println("Which option would you like to choose?:\n");
			int userSelection = in.nextInt();
			System.out.println("**Loading**");
			switch (userSelection){

			//<<<<<<<<<<1ST CHOICE>>>>>>>>>>
			case 1:
				System.out.println("Which user would you like to choose?:\n");
				while (valid == false) {
					if (in.hasNextInt()) {
						userID = in.nextInt();
						valid = true;
					} else {
						System.out.println("Invalid input type-- please enter a numerical ID for a user :" + in.next());
						valid = false;
					}
				}	
				System.out.println("And what movie ID would you like to know the rating for?:");
				valid = false;
				movieID = 0;
				while (valid == false) {
					if (in.hasNextInt()) {
						movieID = in.nextInt();
						valid = true;
					} else {
						System.out.println("Invalid input type-- please enter a numerical ID for a movie :" + in.next());
						valid = false;
					}
				}	

				double prediction = 0;
				HashMap<String, User> neighborhood = new HashMap<String, User>();
				Collection<User> usersWAvgsAndPearson = new ArrayList<User>();
				sc = new SimilarityCalculator();
				usersWAvgsAndPearson = sc.calcAggSimilarity(fr.getUserList().get(userID), sc.calcAverage(fr.getUserList())); 
				nc = new NeighborhoodCalculator(usersWAvgsAndPearson);
				neighborhood = nc.createNeighborhood();
				pc = new PredictionCalculator();
				prediction = pc.calculatePrediction(fr.getUserList().get(userID), neighborhood);


				System.out.println("For user " + userID + " the predicted rating for item " 
						+ movieID + " is: " + prediction);

				break;

				//<<<<<<2ND CHOICE>>>>>>>>
			case 2: 
				System.out.println("Which user would you like to choose?:\n");
				while (valid == false) {
					if (in.hasNextInt()) {
						userID = in.nextInt();
						valid = true;
					} else {
						System.out.println("Invalid input type-- please enter a numerical ID for a user :" + in.next());
						valid = false;
					}
				}	
				System.out.println("And what threshhold would you like to set?:");
				valid = false;
				while (valid == false) {
					if (in.hasNextInt()) {
						threshhold = in.nextInt();
						valid = true;
					} else {
						System.out.println("Invalid input type-- please enter a numerical ID for a movie :" + in.next());
						valid = false;
					}
				}	

				HashMap<String, Movie> notYetSeenList = new HashMap<String, Movie>();
				for(Movie movie : fr.getMovieList().values()){
					if(!fr.userList.get(userID).ratedMovies.containsKey(movie.id)){
						notYetSeenList.put(movie.id, movie);
					}
				}
				
				Collection<User> userWPearsonAndAvg = new ArrayList<User>();


				break;



			default: 
				end = true;
				break;		
			}
		}
		in.close();
	}

}
