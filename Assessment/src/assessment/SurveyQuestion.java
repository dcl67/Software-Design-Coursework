package assessment;

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class SurveyQuestion implements Serializable{
	protected String prompt;
	protected String[] choices;
	int response;
	String writtenResponse;
	
	public SurveyQuestion(String enteredPrompt,String[] possChoices) {
		prompt=enteredPrompt;
		choices=possChoices;
	}

	void display(){
	}
	
	void displayExam(){
	}
	
	public void editSurvey(){
	}

	public String[] take(){
		Scanner in = new Scanner(System.in);
		String[] response = new String[choices.length];
		return response;
	}
}
