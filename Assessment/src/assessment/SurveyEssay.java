package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class SurveyEssay extends SurveyQuestion implements Serializable{
	String prompt;
	String enteredInput;
	
	//Survey essay constructor
	public SurveyEssay(String enteredPrompt,String[] enteredChoices, String entInput){
		super(enteredPrompt,enteredChoices);
		prompt=enteredPrompt;
		enteredInput="";
	}
	
	void displayExam(){
		Output.consoleString(prompt+"\n");
	}
	
	public void editSurvey(){
		String choice="";
		Scanner in = new Scanner(System.in);
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
		Output.consoleString("\nEnter your response");
		response[0] = in.nextLine();
		return response;
	}
}
