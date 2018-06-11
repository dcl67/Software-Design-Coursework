package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import assessment.Matching;

class Ranking extends Question implements Serializable{
	String prompt;
	String[] choices;
	int[] responseArray;
	String[] correctArray;
	
	//Test ranking constructor
	public Ranking(String enteredPrompt, String[] enteredChoices, String[] corrChoices, int[] respArray) {
		super(enteredPrompt, enteredChoices);
		prompt=enteredPrompt;
		choices=enteredChoices;
		responseArray=respArray;
		correctArray=corrChoices;
	}
	
	void display(){
		Output.consoleString(prompt);
		for(int i=0;i<choices.length;i++){
			Output.consoleString(Integer.toString(i+1)+": "+choices[i]+"   "+Integer.toString(i+1));
		}
		if(correctArray[0]=="-1"){} // If survey do nothing
		else if(correctArray[0]!="-1"){
			Output.consoleString("The correct numerical ranking order is: \n");
			for(int i=0;i<correctArray.length;i++){ // Iterate and display correct order
				Output.consoleString(String.format("%d: %-25s%d", (i+1), choices[i], (i+1)));
			}
		}
		Output.consoleString("\n");
	}
	
	void displayExam(){ // Display in an exam, without giving away the answer
		Output.consoleString(prompt);
		for(int i=0;i<choices.length;i++){
			Output.consoleString(String.format("%d: %-25s", (i+1), choices[i]));
			//output.consoleString(Integer.toString(i+1)+": "+choices[i]+"   "+Integer.toString(i+1));
		}
	}
	
	public void edit(){
		String choice="";
		int numChoice=0;
		Scanner editrank = new Scanner(System.in);
		
		/* Modify the prompt */
		Output.consoleString("Do you wish to modify the prompt? 'yes' to edit, anything else for no");
		try{
			choice = editrank.nextLine();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid input");
		}
		if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
			Output.consoleString("Enter a new prompt:");
			prompt = editrank.nextLine();
		}
		else{}
		
		/* Modify the choices */
		Output.consoleString("Do you wish to modify the choices?");
		try{
			choice = editrank.nextLine();
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
				choice = editrank.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			numChoice=Integer.parseInt(choice)-1;
			Output.consoleString("Enter new choice:");
			try{
				choice = editrank.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			choices[numChoice]=choice;
			Output.consoleString("Modify another choice? 'yes' to modify, anything else to exit");
			choice=editrank.nextLine();
		}
		
		/* Modify the correct choice */
		if(!"0".equals(correctArray[0])){ // if not a survey, since survey question's correct answers  = -1
			Output.consoleString("Do you wish to modify the correct ranking order?");
			try{
				choice = editrank.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			while(!"Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
				Output.consoleString("What is the modified correct order?");
				for(int j=0;j<correctArray.length;j++){
					try{
						Output.consoleString("What rank should choice "+(j+1)+" match with?");
						correctArray[j]=editrank.nextLine();
					}
					catch (InputMismatchException e){
						Output.consoleString("Invalid entry");
					}
				}
			}
		}
		else{}
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
	
	public boolean grade(String[] strings) {
		boolean isCorrect = false;
		for(int i=0;i<strings.length;i++){
			if(strings[i].equals(correctArray[i])){
				isCorrect=true;
			}
			else{
				isCorrect=false;
				break;
			}
		}
		return isCorrect;
	}
}