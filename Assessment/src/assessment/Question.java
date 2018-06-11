package assessment;
import java.io.Serializable;
import java.util.Scanner;


@SuppressWarnings("serial")
class Question implements Serializable{
	/* Questions */
	protected String prompt;
	protected String[] choices;
	int response;
	String writtenResponse;
	int correctChoice;
	
	public Question(String enteredPrompt,String[] possChoices) {
		prompt=enteredPrompt;
		choices=possChoices;
	}
		
	public boolean isCorrect(int index){
		boolean isCorrect;
		if(response==index){
			isCorrect=true;
		}
		else if(response!=index){
			isCorrect=false;
		}
		else{
			isCorrect=false;
		}
		return isCorrect;
	}
	
	public int getAnswer(){
		return correctChoice;
	}

	void display(){
	}
	
	void displayExam(){
	}
	
	public void edit(){
	}

	public String[] take(){
		String[] response = new String[choices.length];
		return response;
	}

	public boolean grade(String[] strings) {
		boolean isCorrect = false;
		return isCorrect;
	}
}
