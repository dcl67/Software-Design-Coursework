package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class SurveyTF extends SurveyQuestion implements Serializable{
	String prompt;
	String[] choices;
	
	//Survey TrueFalse constructor
	public SurveyTF(String enteredPrompt,String[] enteredChoices){
		super(enteredPrompt, enteredChoices);
		prompt=enteredPrompt;
		choices=enteredChoices;
	}
	
	void displayExam(){ // Display in exam
		Output.consoleString(prompt + "\n");
		for(int i=0;i<choices.length;i++){
			Output.consoleString(Integer.toString(i+1)+": "+choices[i]);//toString(i+1)
		}
	}
	
	/* Edit survey TrueFalse*/
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
		Output.consoleString("Enter your response");
		int resp = Integer.parseInt(in.nextLine())-1;//nextLine-1
		response[0]=Integer.toString(resp);
		return response;
	}
}