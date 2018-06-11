package assessment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.*;
import java.util.Map.Entry;

@SuppressWarnings("serial")
class Survey implements Serializable{
	String surveyName;
	public Survey(String name){
		surveyName=name;
	}

	static void menu(){
		Scanner in = new Scanner(System.in);
		String surveyName="";
		int input=0;
		List<SurveyQuestion> questionsList = new ArrayList<SurveyQuestion>();
		Output.consoleString("\nOptions for survey:\n"
				+ "1. Create a new survey\n"
				+ "2. Display a survey\n"
				+ "3. Load a survey\n"
				+ "4. Save a survey\n"
				+ "5. Modify an existing survey\n"
				+ "6. Take a survey\n"
				+ "7. Tabulate a survey\n"
				+ "8. Back one menu\n"
				+ "Enter your choice:");
		try{
			input = Integer.parseInt(in.nextLine());
		}
		catch(NumberFormatException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		catch(InputMismatchException e){ 
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		try{
			while(input!=8){
				if(input==1){ // Create a survey
					Output.consoleString("Create survey\nEnter a name for your survey"
							+"\nNOTE: if your input doesn't work the first time, try to enter it a second time");
					in.nextLine();
					surveyName=in.nextLine();
					Survey surveyObj = new Survey(surveyName);
					surveyObj.newQuestion(questionsList);
				}
				else if(input==2){ // Display a survey
					Output.consoleString("Display survey");
					for(SurveyQuestion q:questionsList){
						q.displayExam();
					}
				}
				else if(input==3){ // Load a survey
					Output.consoleString("Which survey would you like to load?\n"
							+ "Enter the full filename with extension: "
							+ "\nNOTE: if your input doesn't work the first time, try to enter it a second time");
					File dir = new File(".");
					File[] filesList = dir.listFiles();
					for(File file : filesList){
					    if(file.isFile()){
					    	if(file.toString().endsWith(".survey"))
					        Output.consoleString(file.getName());
					    }
					}
					in.nextLine();
					surveyName = in.nextLine();
					questionsList = Survey.load(surveyName);
				}
				else if(input==4){ //Save a survey
					Output.consoleString("Save survey");
					Survey.save(surveyName,questionsList);
				}
				else if(input==5){ //Modify a survey
					Output.consoleString("Which survey do you wish to modify?\n"
							+ "Enter the full filename with extension: ");
					File dir = new File(".");
					File[] filesList = dir.listFiles();
					for(File file : filesList){
					    if(file.isFile()){
					    	if(file.toString().endsWith(".survey"))
					        Output.consoleString(file.getName());
					    }
					}
					questionsList = Survey.editSurvey();
				}
				else if(input==6){ //Take a survey
					Output.consoleString("Take a survey");
					String toSave=surveyName.split("\\.")[0];
					Survey.takeSurvey(toSave,questionsList);
				}
				else if(input==7){ // Tabulate a survey
					Output.consoleString("Which survey would you like to tabulate?\n"
							+ "Enter the full filename with extension: ");
					File dir = new File(".");
					File[] filesList = dir.listFiles();
					for(File file : filesList){
					    if(file.isFile()){
					    	if(file.toString().endsWith(".survey"))
					        Output.consoleString(file.getName());
					    }
					}
					//in.nextLine();
					surveyName = in.nextLine();
					Survey.tabulate(surveyName);
				}
				else if(input==8){ // Back one menu
					input=8;
				}
				else{ // Invalid input
					Output.consoleString("Invalid entry, double check your input and try again");
					input = in.nextInt();
				}
				Output.consoleString("\nOptions for survey:\n"
						+ "1. Create a new survey\n"
						+ "2. Display a survey\n"
						+ "3. Load a survey\n"
						+ "4. Save a survey\n"
						+ "5. Modify an existing survey\n"
						+ "6. Take a survey\n"
						+ "7. Tabulate a survey\n"
						+ "8. Back one menu\n"
						+ "Enter your choice:");
				try{
					input = in.nextInt();
				}
				catch(InputMismatchException e){
					Output.consoleString("Invalid entry, double check your input and try again");
				}
			} // end menu while
		} // end menu try
		catch (InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
	} // end menu
	
	
	private static void tabulate(String surveyName) {
		HashMap<String, Integer> tabDict=new HashMap<String, Integer>(); 
		List<SurveyQuestion> loadedSurvey = Survey.load(surveyName);
		List<String[]> responses=new ArrayList<String[]>();
		List<File> files=new ArrayList<File>();
		String prefix=surveyName.substring(0, surveyName.lastIndexOf("."));
		File dir = new File(".");
		File[] toTab = dir.listFiles();
		for(File file:toTab){ // Get all corresponding response files 
			if(file.getName().startsWith(prefix)){
				if(file.getName().endsWith("surveyresp")){
					files.add(file); //Add corresponding response files to ArrayList
				}
			}
		}
		for(File f:files){
			responses.addAll(Survey.loadTab(f));
		}
		try{
			for(int i=0;i<loadedSurvey.size();i++){//iterate through all questions in a survey
				Output.consoleString("\nQuestion:");
				loadedSurvey.get(i).displayExam();
				Output.consoleString("\nReplies:");
				for(int j=0;j<responses.size();j++){ //Iterate through responses
					ArrayList<String> list=new ArrayList<String>();
					String resp = responses.get(j)[0]; //error, 0 is just to show all questions
					/*if(loadedSurvey.get(i) instanceof Matching || loadedSurvey.get(i) instanceof Ranking){
						String[] r=responses.get(j);
						output.consoleStrLine("[");
						for(int x=0;x<r.length;x++){
							output.consoleStrLine(r[x]+",");
							r+=r[x];
						}
						output.consoleStrLine(" ]");
						list.add(e)
					}
					else{*/
						list.add(resp);
						Output.consoleList(list);
					//}
					for (int k=0;k<list.size();k++) {//Tabulate the responses
						if(tabDict.containsKey(list.get(k).toLowerCase())){
							int n=tabDict.get(list.get(k).toLowerCase());
							tabDict.put(list.get(k).toLowerCase(),n+1);
						}
						else{
							tabDict.put(list.get(k).toLowerCase(),1);
						}
					}
				}
				//}
				Output.consoleString("\nTabulation:");
				for(Entry<String,Integer> formatTab:tabDict.entrySet()){
					String k=formatTab.getKey();
					int num=formatTab.getValue();
					Output.consoleString(k+") "+Integer.toString(num));
				}
				tabDict.clear();
			}
		}
		catch(IndexOutOfBoundsException e){
			Output.consoleString("Error iterating array. Going back to menu");
		}
		catch(NullPointerException e){
			Output.consoleString("Error, null value found. Returning to main menu.");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<String[]> loadTab(File toLoad){
		List<String[]> loadedResponses = new ArrayList<String[]>();
	    try {
	        FileInputStream fileIn = new FileInputStream(toLoad);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        loadedResponses = (List<String[]>) in.readObject();
	        in.close();
	        fileIn.close();
	    }
	    catch (IOException i) {
	    	Output.consoleString("Error loading saved survey");
	    }
	    catch (ClassNotFoundException c) {
	        c.printStackTrace();
	    	Output.consoleString("File not found. Going back to menu.");
	    }
		return loadedResponses;
	}
	
	
	public static void takeSurvey(String surveyName, List<SurveyQuestion> qList){
		Scanner in = new Scanner(System.in);
		List<String[]> responses = new ArrayList<String[]>();
		for(SurveyQuestion q:qList){
			q.displayExam();
			responses.add(q.take());
		}
		Survey.saveResponses(surveyName, responses); //Save survey responses after recording them all
	}
	
	
	/* Save survey responses */
	private static void saveResponses(String surveyName, List<String[]> responses) {
		String extension = ".surveyresp";
		int num=0;
		while (true) {//If file exists for attempt, increment
			File file = new File(surveyName + num + ".surveyresp");
			if (file.exists()) {
				num++;
			} else {
				break;
			}
		}
	    try {
	    	FileOutputStream fileOut = new FileOutputStream(surveyName+num+extension);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(responses);
	        out.close();
	        fileOut.close();
	        Output.consoleString("Survey saved to "+surveyName+num+".surveyresp");
	     } 
	    catch (IOException i) {
	    	Output.consoleString("Error saving survey");
	    }
	}

	
	@SuppressWarnings("unchecked")
	public static List<SurveyQuestion> load(String toLoad) {
		List<SurveyQuestion> loadedSurvey = null;
	    try {
	        FileInputStream fileIn = new FileInputStream(toLoad);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        loadedSurvey = (List<SurveyQuestion>) in.readObject();
	        in.close();
	        fileIn.close();
	    }
	    catch (IOException i) {
	    	System.out.println(i);
	    	Output.consoleString("Error loading saved survey");
	    }
	    catch (ClassNotFoundException c) {
	        c.printStackTrace();
	    	Output.consoleString("File not found. Going back to menu.");
	    }
		return loadedSurvey;
	}
	
	
	public static void save(String surveyName, List<SurveyQuestion> questionsList) {
		String extension = ".survey"; // all surveys are .survey extension
	    try {
	    	FileOutputStream fileOut = new FileOutputStream(surveyName+extension);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(questionsList);
	        Output.consoleString("Survey saved to "+surveyName+extension);
	        out.close();
	        fileOut.close();
	     } 
	    catch (IOException i) {
	    	Output.consoleString("Error saving survey");
	    	System.out.print(i);
	    }
		catch(NullPointerException e){
			Output.consoleString("Error: null pointer. Returning to main menu.");
		}
	}
	
	
	public static List<SurveyQuestion> editSurvey(){
		Scanner in = new Scanner(System.in);
		/* Load survey first */
		SurveyQuestion toModify =null;
		int qNum=0;
		List<SurveyQuestion> loadedSurvey = null;
		Output.consoleString("Which survey would you like to load?\nNOTE: if your input doesn't work the first time, try to enter it a second time");
		in.nextLine();
		String toLoad = in.nextLine();
		loadedSurvey = Survey.load(toLoad);
		/* Display all numbered questions next*/
		int i = 0;
		for(SurveyQuestion l:loadedSurvey){
			Output.consoleStrLine("\n"+Integer.toString(i+1)+") ");
			l.displayExam();
			i++;
		}
		Output.consoleString("What question do you wish to modify?");
		try{
			qNum=in.nextInt()-1;
		}
		catch (InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input.");
		}
		try{
			toModify = loadedSurvey.get(qNum); //qnum-1
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Unable to load question");
		}
		toModify.editSurvey();
		loadedSurvey.set((qNum), toModify); //qnum-1
		
		return loadedSurvey;
	}
	

	public void newQuestion(List<SurveyQuestion> questionsList) {
		Scanner in = new Scanner(System.in);
		int survQInput=0;
		Output.consoleString("1) Add a new T/F question\n"
			+ "2) Add a new multiple choice question\n"
			+ "3) Add a new short answer question\n"
			+ "4) Add a new essay question\n"
			+ "5) Add a new ranking question\n"
			+ "6) Add a new matching question\n"
			+ "7) Back one menu");
		try{
			survQInput=in.nextInt();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		try{
			while(survQInput!=7){
				if(survQInput==1){//True/False
					SurveyQuestion newTFQ = newTF();
					questionsList.add(newTFQ);
					}
				else if(survQInput==2){//Multiple choice
					SurveyQuestion newMCQ = newMC();
					questionsList.add(newMCQ);
				}
				else if(survQInput==3){//Short Answer
					SurveyQuestion newShortAnswer = newSA();
					questionsList.add(newShortAnswer);
				}
				else if(survQInput==4){//Essay
					SurveyQuestion newEssay = newEss();
					questionsList.add(newEssay);
				}
				else if(survQInput==5){//Ranking
					SurveyQuestion newRanking = newRank();
					questionsList.add(newRanking);
				}
				else if(survQInput==6){//Matching
					SurveyQuestion newMatching = newMatch();
					questionsList.add(newMatching);
				}
				else if(survQInput==7){
					survQInput=7;
				}
				else{
					Output.consoleString("Invalid entry, double-check your input and try again");
				} // end else
				Output.consoleString("1) Add a new T/F question\n"
						+ "2) Add a new multiple choice question\n"
						+ "3) Add a new short answer question\n"
						+ "4) Add a new essay question\n"
						+ "5) Add a new ranking question\n"
						+ "6) Add a new matching question\n"
						+ "7) Back one menu");
				try{
					survQInput=in.nextInt();
				}
				catch(InputMismatchException e){
					Output.consoleString("Invalid entry, double check your input and try again");
				}
			}// end while
		}
		catch (InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
	}
	
	
	public static SurveyQuestion newTF(){
		Scanner in = new Scanner(System.in);
		String[] choices = new String[2];
		choices[0]="True"; choices[1]="False";
		Output.consoleString("Enter the prompt for your True/False question: ");
		String prompt = in.nextLine();
		SurveyQuestion tfQuestion = new SurveyTF(prompt,choices); 
		return tfQuestion;
	}
	
	
	public static SurveyQuestion newMC(){
		Scanner in = new Scanner(System.in);
		int numChoices=0;
		int responses=0;
		Output.consoleString("Enter the prompt for your multiple choice question: ");
		String prompt = in.nextLine();
		Output.consoleString("Enter the number of choices for your multiple choice question:");
		try{
			numChoices = in.nextInt();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		in.nextLine();
		String[] choices= new String[numChoices];
		for(int i=0;i<numChoices;i++){
			Output.consoleString("Enter choice #"+(i+1)+".");
			choices[i]=in.nextLine();
		}
		Output.consoleString("How many responses do you want to allow?");
		responses=Integer.parseInt(in.nextLine());
		SurveyQuestion mcQuestion = new SurveyMC(prompt,choices, responses); 
		return mcQuestion;
	}
	
	
	public static SurveyQuestion newEss(){
		Scanner in = new Scanner(System.in);
		Output.consoleString("Enter the prompt for your essay question");
		String prompt = in.nextLine();
		String[] c = new String[1];
		String entInput="";
		SurveyQuestion newEssay = new SurveyEssay(prompt, c, entInput);
		return newEssay;
	}
	
	
	public static SurveyQuestion newSA(){
		Scanner in = new Scanner(System.in);
		Output.consoleString("Enter the prompt for your short answer question");
		String prompt = in.nextLine();
		String[] c = new String[1];
		SurveyQuestion sAnswer = new SurveySA(prompt, c);
		return sAnswer;
	}
	
	
	public static SurveyQuestion newMatch(){
		Scanner in = new Scanner(System.in);
		int numChoices=0;
		Output.consoleString("Enter the prompt for your matching question");
		String prompt = in.nextLine();
		Output.consoleString("Enter the number of choices you want your matching question to have");
		try{
			numChoices = in.nextInt();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		Output.consoleString("What options would you like on the left side?: ");
		in.nextLine();
		String[] choices = new String[numChoices];
		for(int i=0;i<numChoices;i++){
			Output.consoleString(Integer.toString(i+1)+": ");//toString(i+1)
			choices[i]=in.nextLine();
		}
		String[] matchingChoices = new String[numChoices];
		Output.consoleString("What options are you matching them with on the right side?");
		for(int i=0;i<numChoices;i++){
			Output.consoleString(Integer.toString(i+1)+": ");//toString(i+1)
			matchingChoices[i]=in.nextLine();
		}
		int[] respArray = new int[numChoices];
		SurveyQuestion match = new SurveyMatching(prompt, choices, respArray, matchingChoices);
		return match;
	}
	
	
	public static SurveyQuestion newRank(){
		Scanner in = new Scanner(System.in);
		int numChoices=0;
		Output.consoleString("Enter the prompt for your ranking question");
		String prompt = in.nextLine();
		Output.consoleString("Enter the number of choices you want your ranking question to have");
		try{
			numChoices = in.nextInt();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		Output.consoleString("What are your options for your ranking question?");
		in.nextLine();
		String[] choices = new String[numChoices];
		for(int i=0;i<numChoices;i++){
			Output.consoleString("" + Integer.toString(i+1)+": ");//toString(i+1)
			choices[i]=in.nextLine();
		}
		int[] respArray = new int[numChoices];
		SurveyQuestion rank = new SurveyRanking(prompt, choices, respArray);
		return rank;
	}
}