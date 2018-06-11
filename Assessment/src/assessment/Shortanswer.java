package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

class ShortAnswer extends Question implements Serializable{
	String prompt;
	String response;
	String[] keywords;
	int charLimit;
	
	//Test Short Answer constructor
	public ShortAnswer(String enteredPrompt, String[] enteredChoices, String[] keywrds, String entInput) {
		super(enteredPrompt, enteredChoices);
		prompt = enteredPrompt;
		charLimit=150;
		keywords=keywrds;
	}
	
	void display(){
		Output.consoleString(prompt + "\nCharacter Limit: "+charLimit+"\n");
		if(!"-1".equals(keywords[0])){
			Output.consoleString("Keyword answers are");
			for(int i=0;i<keywords.length;i++){
				Output.consoleString(keywords[i]);
			}
			Output.consoleString("\n");
		}
	}
	
	void displayExam(){ //Display in exam without giving away the correct answer
		Output.consoleString(prompt + "\nCharacter Limit: "+charLimit+"\n");
	}
	
	public void edit(){
		String choice="";
		int numChoice=0;
		Scanner editsa = new Scanner(System.in);
		
		/* Modify the prompt */
		Output.consoleString("Do you wish to modify the prompt? 'yes' to edit, anything else for no");
		try{
			choice = editsa.nextLine();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid input");
		}
		if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
			Output.consoleString("Enter a new prompt:");
			prompt = editsa.nextLine();
		}
		else{}
		
		/* Modify the correct keywords */
		if(!"-1".equals(keywords[0])){
			Output.consoleString("Do you wish to modify the correct keywords?");
			try{
				choice = editsa.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
				Output.consoleString("Which choice do you want to modify?");
				int i=0;
				for(String option: keywords){
					Output.consoleString((i)+": "+option);//i+1
					i+=1;
				}
				try{
					choice = editsa.nextLine();
				}
				catch(InputMismatchException e){
					Output.consoleString("Invalid input");
				}
				numChoice=Integer.parseInt(choice);
				Output.consoleString("Enter new choice:");
				try{
					choice = editsa.nextLine();
				}
				catch(InputMismatchException e){
					Output.consoleString("Invalid input");
				}
				keywords[numChoice]=choice;
			}
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
	
	public boolean grade(String[] strings) {
		boolean isCorrect = false;
		for(int i=0;i<keywords.length;i++){
			if(strings[0].toLowerCase().contains(keywords[i].toLowerCase())){
				isCorrect=true;
				break;
			}
			else{
				isCorrect=false;
			}
		}
		return isCorrect;
	}
}
