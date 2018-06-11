package assessment;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import assessment.Test;
import assessment.Survey;

@SuppressWarnings("serial")
class Assessment implements Serializable{
	
	static void topMenu(){ 

		Scanner in = new Scanner(System.in);
		Output.consoleString("1) Survey\n2) Test\n3) Quit");
		int input = in.nextInt();
		while(input!=3){
			try{
				if(input==1){
					Survey.menu();
				}
				else if(input==2){
					Test.menu();
				}
				else if(input==3){
					Output.consoleString("Bye!");
					input=3;
				}
				else{
					Output.consoleString("Invalid entry, double-check your input and try again.");
				}
			}
			catch (InputMismatchException e){
				Output.consoleString("Invalid entry, double-check your input and try again.");
			}
		Output.consoleString("1) Survey\n2) Test\n3) Quit");
		in.hasNextInt();
		input = in.nextInt();
		}
		in.close();
	}
}