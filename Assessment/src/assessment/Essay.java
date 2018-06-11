package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("serial")
class Essay extends ShortAnswer implements Serializable{
	String prompt;
	String enteredInput;
	
	//constructor
	public Essay(String enteredPrompt,String[] enteredChoices, String entInput){
		super(enteredPrompt,enteredChoices, enteredChoices, entInput);
		prompt=enteredPrompt;
		enteredInput="";
	}
	
	void display(){
		Output.consoleString(prompt+"\n");
	}
	
	void displayExam(){
		Output.consoleString(prompt+"\n");
	}
	
	public void edit(){
		Scanner in = new Scanner(System.in);
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
		Output.consoleString("\nEnter your response");
		response[0] = in.nextLine();
		return response;
	}
	
	public boolean grade(String[] strings) {
		boolean isCorrect=true;
		return isCorrect;
	}
}