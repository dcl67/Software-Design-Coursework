package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

class Matching extends Question implements Serializable{
	String prompt;
	String[] choices;
	int[] responseArray;
	int[] correctArray;
	String[] matches;
	
	//Test matching
	public Matching(String enteredPrompt, String[] enteredChoices, int[] corrArray,int[] respArray, String[] enteredMatches) {
		super(enteredPrompt, enteredChoices);
		prompt=enteredPrompt;
		choices=enteredChoices;
		correctArray=corrArray;
		responseArray=respArray;
		matches=enteredMatches;
	}
	
	void display(){
		Output.consoleString(prompt);
		for(int i=0;i<choices.length;i++){
			//Output.consoleString(Integer.toString(i)+": "+choices[i]+"   "+Integer.toString(i)+" "+matches[i]);//toString(i+1)
			String outString=String.format("%d:%-25s%d", (i+1), choices[i], (i+1));
			Output.consoleString(outString);
		}
		if(correctArray[0]==-1){} // If survey do nothing.
		else if(correctArray[0]!=-1){
			Output.consoleString("The correct numerical order is: ");
			for(int i=0;i<correctArray.length;i++){ // Iterate and display correct order
				Output.consoleString(Integer.toString(i+1)+": "+correctArray[i]);//toString(i+1)
			}
		}
		Output.consoleString("\n");
	}
	
	void displayExam(){ // Display in an exam without giving away the order
		Output.consoleString(prompt);
		for(int i=0;i<choices.length;i++){
			String outString=String.format("%d: %-25s%d: %s", (i+1), choices[i], (i+1), matches[i]); //+1
			Output.consoleString(outString);
			//Output.consoleString(Integer.toString(i+1)+": "+choices[i]+"   "+Integer.toString(i+1)+" "+matches[i]);
		}
	}
	
	public void edit(){
		String choice="";
		int numChoice=0;
		Scanner editmtch = new Scanner(System.in);
		
		/* Modify the prompt */
		Output.consoleString("Do you wish to modify the prompt? 'yes' to edit, anything else for no");
		try{
			choice = editmtch.nextLine();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid input");
		}
		if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
			Output.consoleString("Enter a new prompt:");
			prompt = editmtch.nextLine();
		}
		else{}
		
		/* Modify the choices */
		Output.consoleString("Do you wish to modify the choices?");
		try{
			choice = editmtch.nextLine();
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
				choice = editmtch.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			numChoice=Integer.parseInt(choice)-1;
			Output.consoleString("Enter new choice:");
			try{
				choice = editmtch.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			choices[numChoice]=choice;//-1
			Output.consoleString("Modify another choice? 'yes' to modify, anything else to exit");
			choice=editmtch.nextLine();
		}
		
		/* Modify the matches */
		Output.consoleString("Do you wish to modify your matches? 'yes' to modify, anything else for no");
		try{
			choice = editmtch.nextLine();
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
			numChoice=(Integer.parseInt(editmtch.nextLine())-1);//-1
			Output.consoleString("What would you like this match option to be?");
			choice=editmtch.nextLine();
			matches[numChoice]=choice;
			Output.consoleString("Edit another match option? 'yes to modify, anything else for no");
			choice=editmtch.nextLine();
		}
		
		/* Modify the correct choice */
		if(correctArray[0]>=0){ // if not a survey, since survey question's correct answers  = -1
			Output.consoleString("Do you wish to modify the correct ranking order?");
			try{
				choice = editmtch.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			if(!"Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
				Output.consoleString("What is the modified correct order?");
				for(int j=0;j<correctArray.length;j++){
					try{
						Output.consoleString("What rank should choice "+(j+1)+" match with?");//+1
						correctArray[j]=editmtch.nextInt();
					}
					catch (InputMismatchException e){
						Output.consoleString("Invalid entry");
					}
				}
			}
		}
		else{}
	}
	
	/* Take a test */
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
	
	public boolean grade(String[] strings) {
		boolean isCorrect = false;
		for(int i=0;i<strings.length;i++){
			if(correctArray[i]==Integer.parseInt(strings[i])){
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