package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class SurveySA extends SurveyQuestion implements Serializable{
	String prompt;
	String response;
	String[] keywords;
	int charLimit;
	
	/* Constructor */
	public SurveySA(String enteredPrompt, String[] enteredChoices) {
		super(enteredPrompt, enteredChoices);
		prompt = enteredPrompt;
		charLimit=150;
	}
	
	void displayExam(){ //Display in exam
		Output.consoleString(prompt + "\nCharacter Limit: "+charLimit+"\n");
	}
	
	/* Edit survey short answer */
	public void editSurvey(){
		Scanner in=new Scanner(System.in);
		String choice="";
		
		/* Modify the prompt */
		Output.consoleString("Do you wish to modify the prompt? 'yes' to edit, anything else for no");
		try{
			choice = in.nextLine();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid input");
		}
		if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
			Output.consoleString("Enter a new prompt:");
			prompt = in.nextLine();
		}
		else{}
	}
	
	
	public String[] take(){
		Scanner in = new Scanner(System.in);
		String[] response=new String[1];
		int responseLength=500;
		while(responseLength>charLimit){
			Output.consoleString("\nEnter your response. Your response must be under 150 characters");
			response[0] = in.nextLine();
			responseLength=response[0].length();
			if(responseLength>=charLimit){
				Output.consoleString("Your response is too long. Try again");
			}
			else{
				break;
			}
		}
		return response;
	}
}
