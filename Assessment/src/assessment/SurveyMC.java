package assessment;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class SurveyMC extends SurveyQuestion implements Serializable{
	String prompt;
	String[] choices;
	int responses;
	
	/* Survey multiple choice */
	public SurveyMC(String enteredPrompt, String[] possChoices, int responseNum) {
		super(enteredPrompt, possChoices);
		prompt=enteredPrompt;
		choices = possChoices;
		responses=responseNum;
	}
	
	void displayExam(){ //Display in exam
		Output.consoleString(prompt + "\n");
		for(int i=0;i<choices.length;i++){
			Output.consoleString(Integer.toString(i+1)+": "+choices[i]);//toString(i+1)
		}
	}
	
	/* Edit survey multiple choice question */
	public void editSurvey(){
		Scanner in = new Scanner(System.in);
		String choice="";
		int numChoice=0;
		//Scanner editmc = new Scanner(System.in);
		
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
		if("Yes".equals(choice)||"yes".equals(choice)||"Y".equals(choice)||"y".equals(choice)){
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
			numChoice=Integer.parseInt(choice)-1;
			Output.consoleString("Enter new choice:");
			try{
				choice = in.nextLine();
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid input");
			}
			choices[numChoice]=choice;//numchoice-1
		}
		else{}
	}
	
	/* Take a survey */
	public String[] take(){
		Scanner in = new Scanner(System.in);
		String[] response=new String[responses];
		Output.consoleString("\nEnter your numeric response(s) for "+responses+" answer(s)");
		for(int i=0;i<responses;i++){
			response[i] = in.nextLine();
		}
		return response;
	}
}
