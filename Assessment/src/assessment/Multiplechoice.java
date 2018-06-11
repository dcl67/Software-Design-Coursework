package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

class MultipleChoice extends Question implements Serializable{
	String prompt;
	String[] choices;
	int response;
	int[] correctChoice;
	
	/* Test multiple choice */
	public MultipleChoice(String enteredPrompt, String[] possChoices, int resp, int[] corrChoice) {
		super(enteredPrompt, possChoices);
		prompt=enteredPrompt;
		choices = possChoices;
		response=resp;
		correctChoice=corrChoice;
	}
	
	void display(){
		Output.consoleString(prompt + "\n");
		for(int i=0;i<choices.length;i++){
			Output.consoleString(Integer.toString(i+1)+": "+choices[i]);//toString(i+1)
		}
		if(correctChoice[0]==-1){}
		else if(correctChoice[0]!=-1){
			Output.consoleString("The correct answer is choice "+(correctChoice[0]+1)+"\n");//correctchoice[0]+1
		}
		else{}
	}
	
	void displayExam(){ //Display in exam without giving away the answer
		Output.consoleString(prompt + "\n");
		for(int i=0;i<choices.length;i++){
			Output.consoleString(Integer.toString(i+1)+": "+choices[i]);//toString(i+1)
		}
	}
	
	public void edit(){
		String choice="";
		int numChoice=0;
		int numModify=0;
		Scanner editmc = new Scanner(System.in);
		
		/* Modify the prompt */
		Output.consoleString("Do you wish to modify the prompt? 'yes' to edit, anything else for no");
		try{
			choice = editmc.nextLine();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid input");
		}
		if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
			Output.consoleString("Enter a new prompt:");
			prompt = editmc.nextLine();
		}
		else{}
		
		/* Modify the choices */
		Output.consoleString("Do you wish to modify the choices?");
		try{
			choice = editmc.nextLine();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid input");
		}
		if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
			Output.consoleString("Which choice do you want to modify?");
			int i=0;
			for(String option: choices){
				Output.consoleString((i+1)+": "+option);//consoleString(i+1)
				i++;
			}
			try{
				choice = editmc.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			numChoice=Integer.parseInt(choice)-1;
			Output.consoleString("Enter new choice:");
			try{
				choice = editmc.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			choices[numChoice]=choice;//numchoice-1
		}
		else{}
		
		/* Modify the correct choice */
		if(correctChoice[0]!=-1){ // 
			for(int c:correctChoice){
				Output.consoleString("Answer "+(c+1)+": "+Integer.toString(correctChoice[c]));//c+1
			}
			Output.consoleString("Do you wish to modify a correct answer?");
			try{
				choice = editmc.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			if(correctChoice.length>1){
				Output.consoleString("Which one");
				numModify=(Integer.parseInt(editmc.nextLine())-1);//nextLine()-1
			}
			else if(correctChoice.length==1){
				numModify=0;
			}
			if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
				Output.consoleString("What choice do you wish to make the new correct answer?");
				try{
					correctChoice[numModify] = (Integer.parseInt(editmc.nextLine())-1);//nextLine()-1
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
		String[] response=new String[correctChoice.length];
		Output.consoleString("\nEnter your numeric response(s) for "+correctChoice.length+" correct answer(s)");
		for(int i=0;i<correctChoice.length;i++){
			response[i] = in.nextLine();
		}
		return response;
	}

	
	public boolean grade(String[] strings) {
		boolean isCorrect = false;
		for(int i=0;i<strings.length;i++){
			if(correctChoice[i]==(Integer.parseInt(strings[i])-1)){//strings[i]-1
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