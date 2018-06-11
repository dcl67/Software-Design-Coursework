package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class SurveyMatching extends SurveyQuestion implements Serializable {
	String prompt;
	String[] choices;
	int[] responseArray;
	String[] matches;
	
	//Survey Matching
	public SurveyMatching(String enteredPrompt, String[] enteredChoices,int[] respArray, String[] enteredMatches) {
		super(enteredPrompt, enteredChoices);
		prompt=enteredPrompt;
		choices=enteredChoices;
		responseArray=respArray;
		matches=enteredMatches;
	}
	
	void displayExam(){ // Display in an exam
		Output.consoleString(prompt);
		for(int i=0;i<choices.length;i++){
			String outString=String.format("%d: %-25s%d: %s", (i+1), choices[i], (i+1), matches[i]); //+1
			Output.consoleString(outString);
			//output.consoleString(Integer.toString(i+1)+": "+choices[i]+"   "+Integer.toString(i+1)+" "+matches[i]);
		}
	}
	
	/* Edit survey matching question */
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
				Output.consoleString((i+1)+": "+option);//consoleString(i+1)
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
			choices[numChoice-1]=choice;//-1
			Output.consoleString("Modify another choice? 'yes' to modify, anything else to exit");
			choice=in.nextLine();
		}
		
		/* Modify the matches */
		Output.consoleString("Do you wish to modify your matches? 'yes' to modify, anything else for no");
		try{
			choice = in.nextLine();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid input");
		}
		while("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
			int i=0;
			for(String m:matches){
				Output.consoleString((i+1)+": "+m);//+1
				i+=1;
			}
			Output.consoleString("Which match option would you like to modify?");
			numChoice=(Integer.parseInt(in.nextLine())-1);
			Output.consoleString("What would you like this match option to be?");
			choice=in.nextLine();
			matches[numChoice]=choice;
			Output.consoleString("Edit another match option? 'yes to modify, anything else for no");
			choice=in.nextLine();
		}
	}
	
	/* Take a survey */
	public String[] take(){
		Scanner in = new Scanner(System.in);
		String[] response=new String[choices.length];
		Output.consoleString("\nEnter your responses by corresponding numeric answer");
		for(int i=0;i<choices.length;i++){
			Output.consoleStrLine((i+1)+") "+choices[i]+": ");//StrLine(i+1)
			response[i] = in.nextLine();
		}
		return response;
	}
}
