import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class QAndA {
	public QAndA(String a[], String b[]) {
		questionBank = a.clone();
		answerBank = b.clone();
	}
	
	Random rand = new Random();
	
	public void chooseQuestions() {
		
		int counter = 0;
		
		while (counter < 1) { //Choosing multiple choice
			int number = rand.nextInt(15);
			
			while (chosenQuestionDupeCheck.contains(number)) {
				number = rand.nextInt(15);
			}
			
			chosenQuestions[counter] = questionBank[number];
			chosenQuestionNums[counter] = number;
			chosenAnswerNums[counter] = number;
			counter++;
		}
		
		if (rand.nextInt() % 2 == 0) { //deciding on matching
			chosenQuestions[counter] = "Match the following questions with the given answers.";
			chosenAnswerNums[counter] = 40;
			counter++;
		} else { //if no matching, add 1 multiple choice
			int number = rand.nextInt(15);
			
			while (chosenQuestionDupeCheck.contains(number)) {
				number = rand.nextInt(15);
			}
			
			chosenQuestions[counter] = questionBank[number];
			chosenQuestionNums[counter] = number;
			chosenAnswerNums[counter] = number;
			counter++;
		}
		
		
		while (counter < 4) { //choosing fill-in-the-blank
			int number = 30 + rand.nextInt(10);
			
			while (chosenQuestionDupeCheck.contains(number)) {
				number = 30 + rand.nextInt(10);
			}
			
			chosenQuestions[counter] = questionBank[number];
			chosenQuestionNums[counter] = number;
			chosenAnswerNums[counter] = number;
			counter++;
		}
		
		while (counter < 5) { //choosing true/false
			int number = 15 + rand.nextInt(16);
			
			while (chosenQuestionDupeCheck.contains(number)) {
				number = 15 + rand.nextInt(16);
			}
			
			chosenQuestions[counter] = questionBank[number];
			chosenQuestionNums[counter] = number;
			chosenAnswerNums[counter] = number;
			counter++;
		}
	}
	
	public String returnMatchingQuestions() {
		String output = "";
		Set<String> dupeCheck = new HashSet<String>();
		
		int questionNumber = 97;
		
		for (int a = 0; a < 3; a++) {
			int number = 41 + rand.nextInt(10);
			String question = questionBank[number];
			
			while (dupeCheck.contains(question)) {
				number = 41 + rand.nextInt(10);
				question = questionBank[number];
			}
			
			output = output + (char)questionNumber + ". " + question + "\n\n";
			dupeCheck.add(question);
			chosenMatchingQuestionNums[a] = number;
			matchingAnswers[a] = answerBank[number];
			questionNumber++;
		}
		return output;
	}
	
	public String getMatchingAnswer(int a) {
		return matchingAnswers[a];
	}
	
	
	public void randomizeMatchingAnswerOrder() {
		
		for (int a = 0; a < 3; a++) {
			int number = 41 + rand.nextInt(10);
			
			while (randMatchingDupeCheck.contains(number)) {
				number = 41 + rand.nextInt(10);
			}
			randMatchingDupeCheck.add(number);
			randomizedMatchingIntegers[a] = number;
		}
	}
	
	/*public String getRandomMatchingAnswer(int a) {
		int actualInteger = randomizedMatchingIntegers[a];
		return answerBank[actualInteger];
	}*/
	
	public String getRandomMatchingAnswer() {
		int random = rand.nextInt(3);
		
		while (chosenMatchingAnswers.contains(random)) {
			random = rand.nextInt(3);
		}
		chosenMatchingAnswers.add(random);
		return answerBank[chosenMatchingQuestionNums[random]];
	}
	
	public String showQuestion(int a) {
		return chosenQuestions[a];
	}
	
	public void chooseAnswers() {		
		
		for (int a = 0; a < chosenAnswers.length; a++) {
			chosenAnswers[a] = answerBank[chosenAnswerNums[a]];
		}
	}
	
	public String showAnswer(int a) {
		return chosenAnswers[a];
	}
	
	
	public String[] genGivenAnswers(int a) {
		String output[] = new String[4];
		Set<String> dupeCheck = new HashSet<String>();
		
		int correctChoice = rand.nextInt(4);
		dupeCheck.add(chosenAnswers[a]);
		
		for (int b = 0; b < 4; b++) {
			String answer = answerBank[rand.nextInt(10)];
			
			while (dupeCheck.contains(answer)) {
				answer = answerBank[rand.nextInt(10)];
			}
			
			output[b] = answer;
			dupeCheck.add(answer);
		}
		
		output[correctChoice] = chosenAnswers[a];
		
		return output;
	}
	
	public void storeAnswer(String a, int b) {
			userAnswers[b] = a;
	}
	
	public void storeMatchingAnswer(String a, int b) {
		userMatchingAnswers[b] = a;
	}
	
	public String testCheckAnswer(int a) {
		return userAnswers[a];
	}
	
	public int checkAnswer() {
		int correct = 0;
		Arrays.fill(correctAnswerCheck, "false");
		
		for (int a = 0; a < 5; a++) { //CHECKS EVERYTHING BUT MATCHING
			if (userAnswers[a] == null) {
			} else if ((userAnswers[a] == chosenAnswers[a]) || (userAnswers[a].equals(chosenAnswers[a]))) {
				correct++;
				
				if (a == 0) {
					correctAnswerCheck[0] = "correct";
				} else {
					correctAnswerCheck[a + 3] = "correct";
				}
			}
		}
		
		for (int a = 0; a < 3; a++) { //CHECKS MATCHING
			if (userMatchingAnswers[a] == null) {
			} else if (userMatchingAnswers[a] == matchingAnswers[a]) {
				correct++;
				correctAnswerCheck[a + 1] = "correct";
			}
		}
		
		return correct;
	}
	
	public String printUserAnswers() {
		String output = "";
		
		for (int a = 1; a < 6; a++) {
			
			if (a == 2 && matchingAnswers[0] != null) {
				int c = 0;
				
				for (int b = 97; b < 100; b++) {
					output = output + a + (char)b + ". " + userMatchingAnswers[c] + "\t" + this.printCorrectCheck(c, true) + "\n";
					c++;
				}
			} else {
				output = output + a + ".  " + userAnswers[a - 1] + "\t\t" + this.printCorrectCheck(a - 1, false) + "\n";
			}
		}
		
		return output;
	}
	
	public String printCorrectAnswers() {
		String output = "";
		
		for (int a = 1; a < 6; a++) {
			
			if (a == 2 && matchingAnswers[0] != null) {
				int c = 0;
				
				for (int b = 97; b < 100; b++) {
					output = output + a + (char)b + ". " + matchingAnswers[c] + "\n";
					c++;
				}
			} else {
				output = output + a + ".  " + chosenAnswers[a - 1] + "\n";
			}
		}
		
		return output;
	}
	
	/*public String printCorrectCheck() {  *******SEPARATE FORM OF PRINTING CORRECTLY ANSWERED QUESTIONS
		String output = "";                *******NOT IN USE
		
		for (int a = 0; a < 7; a++) {
			if ( (a == 1 || a == 2 || a == 3) && matchingAnswers[0] != null) { //CHECKS IF MATCHING EXISTS, AND IF SO, HANDLES PRINTING 2A-C
				output = output + "2" + (char)(a + 96) + ". " + correctAnswerCheck[a] + "\n";
			} else if (a == 0) {
				output = output + (a + 1) + ". " + correctAnswerCheck[a] + "\n";
			} else if (matchingAnswers[0] != null) {
				output = output + (a - 1) + ". " + correctAnswerCheck[a - 1] + "\n";
			} else if (matchingAnswers[0] == null && a > 4) {
			} else {
				output = output + (a + 1) + ". " + correctAnswerCheck[a] + "\n";
			}
		}
		
		return output;
	}*/
	
	public String printCorrectCheck(int a, boolean match) {
		if (match == false) {
			if (userAnswers[a] == null) {
				return " - INCORRECT!";
			}
			if (userAnswers[a] == chosenAnswers[a] || userAnswers[a].equals(chosenAnswers[a])) {
				return " - CORRECT!";
			} else {
				return " - INCORRECT!";
			}
		} else {
			if (userMatchingAnswers[a] == matchingAnswers[a]) {
				return " - CORRECT!";
			} else { 
				return " - INCORRECT!";
			}
		}
	}
	
	
	
	private String questionBank[] = new String[50];
	private String chosenQuestions[] = new String[5];
	private int chosenQuestionNums[] = new int[5];
	
	private String chosenMatching[] = new String[3];
	
	private String answerBank[] = new String[50];
	private String chosenAnswers[] = new String[5];
	private int chosenAnswerNums[] = new int[5];
	
	private String matchingAnswers[] = new String[3];
	private String userAnswers[] = new String[5];
	private String userMatchingAnswers[] = new String[3];
	
	private String correctAnswerCheck[] = new String[8];
	
	private String selectedAnswer;
	
	private Set<Integer> randMatchingDupeCheck = new HashSet<Integer>();
	private Set<Integer> chosenMatchingAnswers = new HashSet<Integer>();
	private Set<Integer> chosenQuestionDupeCheck = new HashSet<Integer>();
	private int chosenMatchingQuestionNums[] = new int[3];
	private int randomizedMatchingIntegers[] = new int[3];
}
