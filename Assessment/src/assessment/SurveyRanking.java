package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class SurveyRanking extends SurveyQuestion implements Serializable{
	String prompt;
	String[] choices;
	int[] responseArray;
	
	//Survey ranking constructor
	public SurveyRanking(String enteredPrompt, String[] enteredChoices, int[] respArray) {
		super(enteredPrompt, enteredChoices);
		prompt=enteredPrompt;
		choices=enteredChoices;
		responseArray=respArray;
	}
	
	void displayExam(){ // Display in an exam
		Output.consoleString(prompt);
		for(int i=0;i<choices.length;i++){
			Output.consoleString(String.format("%d: %-25s", (i+1), choices[i]));
			//output.consoleString(Integer.toString(i+1)+": "+choices[i]+"   "+Integer.toString(i+1));
		}
	}
	
	public void editSurvey(){
		Scanner in = new Scanner(System.in);
		String choice="";
		int numChoice=0;
		
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
		
		/* Modify the choices */
		Output.consoleString("Do you wish to modify the choices?");
		try{
			choice = in.nextLine();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid input");
		}
		while("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
			Output.consoleString("Which choice do you want to modify?");
			int i=0;
			for(String option: choices){
				Output.consoleString((i+1)+": "+option);
				i++;
			}
			try{
				choice = in.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			numChoice=Integer.parseInt(choice);
			Output.consoleString("Enter new choice:");
			try{
				choice = in.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			choices[numChoice-1]=choice;
			Output.consoleString("Modify another choice? 'yes' to modify, anything else to exit");
			choice=in.nextLine();
		}
	}
	
	public String[] take(){
		Scanner in = new Scanner(System.in);
		String[] response=new String[choices.length];
		Output.consoleString("\nEnter your responses");
		for(int i=0;i<choices.length;i++){
			Output.consoleStrLine((i+1)+" "+choices[i]+": ");//i+1
			response[i] = in.nextLine();
		}
		return response;
	}
}
