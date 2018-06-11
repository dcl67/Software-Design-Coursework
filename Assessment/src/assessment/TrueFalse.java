package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import assessment.Question;

public class TrueFalse extends MultipleChoice implements Serializable{
	String prompt;
	String[] choices;
	int response;
	int[] correctChoice;
	
	//Test TrueFalse constructor
	public TrueFalse(String enteredPrompt,String[] enteredChoices,int resp, int[] corrChoice){
		super(enteredPrompt, enteredChoices, resp, corrChoice);
		prompt=enteredPrompt;
		choices=enteredChoices;
		correctChoice=corrChoice;
	}
	
	void display(){
		Output.consoleString(prompt + "\n");
		for(int i=0;i<choices.length;i++){
			Output.consoleString(Integer.toString(i+1)+": "+choices[i]);//toString(i+1)
		}
		if(correctChoice[0]!=-1){
			Output.consoleString("The correct answer is choice "+(correctChoice[0]));//correctChoice+1
		}
		else if(correctChoice[0]==-1){}
		else{}
		Output.consoleString("\n");
	}
	
	void displayExam(){ // Display in exam without giving away answer
		Output.consoleString(prompt + "\n");
		for(int i=0;i<choices.length;i++){
			Output.consoleString(Integer.toString(i+1)+": "+choices[i]);//toString(i+1)
		}
	}
	
	public void edit(){
		String choice="";
		int numChoice=0;
		Scanner edittf = new Scanner(System.in);
		
		/* Modify the prompt */
		Output.consoleString("Do you wish to modify the prompt? 'yes' to edit, anything else for no");
		try{
			choice = edittf.nextLine();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid input");
		}
		if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
			Output.consoleString("Enter a new prompt:");
			prompt = edittf.nextLine();
		}
		else{}
				
		/* Modify the correct choice */
		if(correctChoice[0]!=-1){
			Output.consoleString("Do you wish to modify the correct answer?");
			try{
				choice = edittf.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
				Output.consoleString("What choice do you wish to make the new correct answer?\n1 for True, 2 for false.");
				try{
					correctChoice[0] = (Integer.parseInt(edittf.nextLine())-1);//nextLine-1
				}
				catch(InputMismatchException e){
					Output.consoleString("Invalid input");
				}
			}
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
	
	public boolean grade(String[] strings) {
		boolean isCorrect = false;
		for(int i=0;i<strings.length;i++){
			if(correctChoice[0]==Integer.parseInt(strings[i])){
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