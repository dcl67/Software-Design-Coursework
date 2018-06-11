package assessment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;


import assessment.MultipleChoice;
import assessment.TrueFalse;
import assessment.Essay;
import assessment.ShortAnswer;
import assessment.Matching;
import assessment.Ranking;
import assessment.Output;

class Test implements Serializable{
	String testName;
	List<Question> questionsList = new ArrayList<Question>();
	public Test(String name){
		testName=name;
	}
	
	static void menu(){
		Scanner testsc = new Scanner(System.in);
		String testName="";
		String toLoad="";
		int input=0;
		List<Question> questionsList = new ArrayList<Question>();
		List<String[]> responses = new ArrayList<String[]>();
		Output.consoleString("\nOptions for test:\n"
				+ "1. Create a new test\n"
				+ "2. Display a test\n"
				+ "3. Load a test\n"
				+ "4. Save a test\n"
				+ "5. Modify an existing test\n"
				+ "6. Take a test\n"
				+ "7. Tabulate a test\n"
				+ "8. Grade a test\n"
				+ "9. Back one menu\n"
				+ "Enter your choice:");
		try{
			input = Integer.parseInt(testsc.nextLine());
		}
		catch(NumberFormatException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		catch(InputMismatchException e){ 
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		try{
			while(input!=9){
				if(input==1){ // create test with questions
					questionsList.clear();
					Output.consoleString("Create test\nEnter a name for your test\nNOTE: if your input doesn't work the first time, try to enter it a second time");
					testsc.nextLine();
					testName=testsc.nextLine();
					Test testObj = new Test(testName);
					testObj.newQuestion(questionsList);
				}
				else if(input==2){ // display test questions
					Output.consoleString("Display test");
					for(Question q:questionsList){
						q.display();
					}
				}
				else if(input==3){ // Load a test
					Output.consoleString("Which test would you like to load?\n"
							+ "Enter the full filename with extension: "
							+ "\nNOTE: if your input doesn't work the first time, try to enter it a second time");
					File dir = new File(".");
					File[] filesList = dir.listFiles();
					for(File file : filesList){
					    if(file.isFile()){
					    	if(file.toString().endsWith(".test"))
					        Output.consoleString(file.getName());
					    }
					}
					testsc.nextLine();
					testName = testsc.nextLine();
					questionsList = Test.load(testName);
				}
				else if(input==4){ // Save test with questions in memory to a .test file
					Output.consoleString("Save test");
					Test.save(testName,questionsList);
				}
				else if(input==5){ // Modify a test
					Output.consoleString("Which test do you wish to modify?\n"
							+ "Enter the full filename with extension: ");
					File dir = new File(".");
					File[] filesList = dir.listFiles();
					for(File file : filesList){
					    if(file.isFile()){
					    	if(file.toString().endsWith(".test"))
					        Output.consoleString(file.getName());
					    }
					}
					questionsList=Test.editTest();
				}
				else if(input==6){ // Take a test
					Output.consoleString("Take a test");
					String toSave=testName.split("\\.")[0];
					responses=Test.takeTest(toSave,questionsList);
				}
				else if(input==7){ // Tabulate test
					Output.consoleString("Which test would you like to tabulate?\n"
							+ "Enter the full filename with extension: "
							+ "\nNOTE: if your input doesn't work the first time, try to enter it a second time");
					File dir = new File(".");
					File[] filesList = dir.listFiles();
					for(File file : filesList){
					    if(file.isFile()){
					    	if(file.toString().endsWith(".test"))
					        Output.consoleString(file.getName());
					    }
					}
					testsc.nextLine();
					testName = testsc.nextLine();
					Test.tabulate(testName);
				}
				else if(input==8){ // Grade attempt
					Output.consoleString("Grade an attempt");
					Test.grade(questionsList, responses);
				}
				else if(input==9){ // Back one menu
					input=9;
				}
				else{ // Invalid input
					Output.consoleString("Invalid entry, double check your input and try again");
				}
				Output.consoleString("\nOptions for test:\n"
						+ "1. Create a new test\n"
						+ "2. Display a test\n"
						+ "3. Load a test\n"
						+ "4. Save a test\n"
						+ "5. Modify an existing test\n"
						+ "6. Take a test\n"
						+ "7. Tabulate a test\n"
						+ "8. Grade a test\n"
						+ "9. Back one menu\n"
						+ "Enter your choice:");
				try{
					input = testsc.nextInt();
				}
				catch(InputMismatchException e){
					Output.consoleString("Invalid entry, double check your input and try again");
				}
			} // end while
		} // end try
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
	} // end menu
	
	
	private static void tabulate(String testName) {
		HashMap<String, Integer> tabDict=new HashMap<String, Integer>(); 
		List<Question> loadedTest = Test.load(testName);
		List<String[]> responses=new ArrayList<String[]>();
		List<File> files=new ArrayList<File>();
		String prefix=testName.substring(0, testName.lastIndexOf("."));
		File dir = new File(".");
		File[] toTab = dir.listFiles();
		for(File file:toTab){ // Get all corresponding response files 
			if(file.getName().startsWith(prefix)){
				if(file.getName().endsWith("testresp")){
					files.add(file); //Add corresponding response files to ArrayList
				}
			}
		}
		int numFiles=0;
		for(File f:files){
			responses.addAll(Test.loadTab(f));
			numFiles++;
		}
		
		try{
			for(int i=0;i<loadedTest.size();i++){//iterate through all questions in a test
				Output.consoleString("\nQuestion:");
				loadedTest.get(i).displayExam();
				Output.consoleString("\nReplies:");
				int y=0;
				for(int j=0;j<responses.size();j++){ //Iterate through responses
					ArrayList<String> list=new ArrayList<String>();
					String resp = responses.get(j)[0]; //error, 0 is just to show all questions
					/*if(loadedTest.get(i) instanceof Matching || loadedTest.get(i) instanceof Ranking){
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
	    	Output.consoleString("Error loading saved test");
	    }
	    catch (ClassNotFoundException c) {
	        c.printStackTrace();
	    	Output.consoleString("File not found. Going back to menu.");
	    }
		catch(NullPointerException e){
			Output.consoleString("Error: null pointer. Returning to main menu.");
		}
		return loadedResponses;
	}

	public static List<String[]> takeTest(String testName, List<Question> qList) {
		Scanner testResponse = new Scanner(System.in);
		ArrayList<String[]> responses = new ArrayList<String[]>();
		for(Question q:qList){
			q.displayExam();
			responses.add(q.take());
		}
		
		Test.saveResponses(testName, responses); //Save test responses after recording them all
		return responses;
	}


	private static void grade(List<Question> qList, List<String[]> responses) {
		try{
			float grade=0;
			int numCorrect=0;
			int i=0;
			boolean isCorrect;
			int total=qList.size();
			for(Question q:qList){
				//if(!(q instanceof Essay)){
					isCorrect=q.grade(responses.get(i));
					if(isCorrect){
						Output.consoleString("Correct!");
						numCorrect++;
					}
					else{}
					i++;
			}
			for(Question q:qList){
				if(q instanceof Essay){ //Find essays, subtract from total
					total=total-1;
				}
				else{} //Not an essay, do nothing
			}
		numCorrect*=10;
		total*=10;
		Output.consoleString("Attempt grade: "+Integer.toString(numCorrect)+"/"+Integer.toString(total));
		try{
		}
		catch(ArithmeticException a){
			Output.consoleString("");
		}
		catch(NullPointerException e){
			Output.consoleString("Error: null pointer. Returning to main menu.");
		}
		}
		catch(IndexOutOfBoundsException e){
			Output.consoleString("Error grading, going back to the previous menu");
		}
	}

	/* Save test responses */
	private static void saveResponses(String testName ,List<String[]> responses) {
		int num=0;
		String extension = ".testresp";
		while (true) {//If file exists for attempt, increment
			File file = new File(testName + num + ".testresp");
			if (file.exists()) {
				num++;
			} else {
				break;
			}
		}
	    try {
	    	FileOutputStream fileOut = new FileOutputStream(testName+num+extension);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(responses);
	        out.close();
	        fileOut.close();
	        Output.consoleString("Test saved to "+testName+num+".testresp");
	     } 
	    catch (IOException i) {
	    	Output.consoleString("Error saving test");
	    }
		catch(NullPointerException e){
			Output.consoleString("Error: null pointer. Returning to main menu.");
		}
	}


	private static List<Question> load(String toLoad) { 
		List<Question> loadedTest = null; 
	    try {
	        FileInputStream fileIn = new FileInputStream(toLoad);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        loadedTest = (List<Question>) in.readObject();
	        in.close();
	        fileIn.close();
	    }
	    catch (IOException i) {
	    	Output.consoleString("Error loading saved test");
	    }
		catch(NullPointerException e){
			Output.consoleString("Error: null pointer. Returning to main menu.");
		}
	    catch (ClassNotFoundException c) {
	        c.printStackTrace();
	    	Output.consoleString("File not found. Going back to menu.");
	    }
		return loadedTest;
	}
	
	
	private static void save(String testName, List<Question> questionsList) {
		String extension = ".test"; // all tests are .test extension
	    try {
	    	FileOutputStream fileOut = new FileOutputStream(testName+extension);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(questionsList);
	        Output.consoleString("Test saved to "+testName+extension);
	        out.close();
	        fileOut.close();
	     } 
	    catch (IOException i) {
	    	Output.consoleString("Error saving test");
	    	//i.printStackTrace();
	    	//System.out.print(i);
	    }
		catch(NullPointerException e){
			Output.consoleString("Error: null pointer. Returning to main menu.");
		}
	}
	
	
	public static List<Question> editTest(){
		/* Load test first */
		Scanner editTest = new Scanner(System.in);
		int qNum=0;
		List<Question> loadedTest = null;
		Output.consoleString("Which test would you like to load?\nNOTE: if your input doesn't work the first time, try to enter it again");
		editTest.nextLine();
		String toLoad = editTest.nextLine();
		loadedTest = Test.load(toLoad);		
		/* Display all numbered questions next*/
		int i = 0;
		for(Question l:loadedTest){
			Output.consoleStrLine("\n"+Integer.toString(i+1)+") ");//toString(i+1)
			l.display();
			i++;
		}
		Output.consoleString("What question do you wish to modify?");
		try{
			qNum=editTest.nextInt()-1;
		}
		catch (InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input.");
		}
		catch(NullPointerException e){
			Output.consoleString("Error: null pointer. Returning to main menu.");
		}
		Question toModify = loadedTest.get(qNum); //qnum-1
		toModify.edit();
		loadedTest.set((qNum), toModify); //-1
		
		return loadedTest;
	}
	

	private void newQuestion(List<Question> questionsList) {
		Scanner testquest = new Scanner(System.in);
		int testQInput=0;
		int correctAnswer;
		Output.consoleString("1) Add a new T/F question\n"
			+ "2) Add a new multiple choice question\n"
			+ "3) Add a new short answer question\n"
			+ "4) Add a new essay question\n"
			+ "5) Add a new ranking question\n"
			+ "6) Add a new matching question\n"
			+ "7) Back one menu\n");
		try{
			testQInput=testquest.nextInt();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		catch(NullPointerException e){
			Output.consoleString("Error: null pointer. Returning to main menu.");
		}
		try{
			while(testQInput!=7){ // while loop to keep creating questions or exit with choice 7
				if(testQInput==1){//True/False
					Question newTFQ = newTF();
					questionsList.add(newTFQ);
					}
				else if(testQInput==2){//Multiple choice
					Question newMCQ = newMC();
					questionsList.add(newMCQ);
				}
				else if(testQInput==3){//Short Answer
					Question newShortAnswer = newSA();
					questionsList.add(newShortAnswer);
				}
				else if(testQInput==4){//Essay
					Question newEssay = newEss();
					questionsList.add(newEssay);
				}
				else if(testQInput==5){//Ranking
					Question newRanking = newRank();
					questionsList.add(newRanking);
				}
				else if(testQInput==6){//Matching
					Question newMatching = newMatch();
					questionsList.add(newMatching);
				}
				else if(testQInput==7){
					testQInput=7;
				}
				else{
					Output.consoleString("Invalid entry, double-check your input and try again");
					testQInput = testquest.nextInt();
				} // end if
				Output.consoleString("1) Add a new T/F question\n"
						+ "2) Add a new multiple choice question\n"
						+ "3) Add a new short answer question\n"
						+ "4) Add a new essay question\n"
						+ "5) Add a new ranking question\n"
						+ "6) Add a new matching question\n"
						+ "7) Back one menu");
				try{
					testQInput=testquest.nextInt();
				}
				catch(InputMismatchException e){
					Output.consoleString("Invalid entry, double check your input and try again");
				}
				catch(NullPointerException e){
					Output.consoleString("Error: null pointer. Returning to main menu.");
				}
			} // end while
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		catch(NullPointerException e){
			Output.consoleString("Error: null pointer. Returning to main menu.");
		}
	}
	
	
	public Question newTF(){
		int[] correctAnswer = new int[1];
		Scanner testquest = new Scanner(System.in);
		String[] choices = new String[2];
		choices[0]="True"; choices[1]="False";
		Output.consoleString("Enter the prompt for your True/False question: ");
		String prompt = testquest.nextLine();
		Output.consoleString("\nEnter which is correct, 1 for true, 2 for false: ");
		try{
			correctAnswer[0] = testquest.nextInt()-1; //-1
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		int r = 0;
		Question tfQuestion = new TrueFalse(prompt,choices, r, correctAnswer); 
		return tfQuestion;
	}
	
	
	public Question newMC(){
		int numChoices=0;
		int numCorrect=0;
		int resp=0; //null signifier
		Scanner testquest = new Scanner(System.in);
		Output.consoleString("Enter the prompt for your multiple choice question: ");
		String prompt = testquest.nextLine();
		Output.consoleString("Enter the number of choices for your multiple choice question:\n");
		try{
			numChoices = testquest.nextInt();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		String[] choices= new String[numChoices];
		testquest.nextLine();
		for(int i=0;i<numChoices;i++){
			Output.consoleString("Enter choice #"+(i+1)+".");//i+1
			choices[i]=testquest.nextLine();
		}
		Output.consoleString("How many correct answers are there?");
		try{
			numCorrect=testquest.nextInt();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		int[] correctAnswer=new int[numCorrect];
		for(int i=0;i<numCorrect;i++){
		Output.consoleString("Which is the correct answer "+(i+1)+"?");//+1
		testquest.nextLine();
			try{
				correctAnswer[i]=(Integer.parseInt(testquest.nextLine())-1);//-1
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid entry, double check your input and try again");
			}
		}
		Question mcQuestion = new MultipleChoice(prompt,choices, resp, correctAnswer);
		return mcQuestion;
	}
	
	
	public Question newEss(){
		Scanner testquest = new Scanner(System.in);
		Output.consoleString("Enter the prompt for your essay question: ");
		String prompt = testquest.nextLine();
		String[] resp = new String[1];
		String entInput="";
		Question newEssay = new Essay(prompt, resp, entInput);
		return newEssay;
	}
	
	
	public Question newSA(){
		int numKeywords = 1;
		Scanner testquest = new Scanner(System.in);
		Output.consoleString("Enter the prompt for your short answer question");
		String prompt = testquest.nextLine();
		String[] resp = new String[1];
		String entInput="";
		Output.consoleString("How many answer keywords would you like to add? ");
		try{
			numKeywords = testquest.nextInt();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		String[] keywords= new String[numKeywords];
		Output.consoleString("What are these keywords?");
		testquest.nextLine();
		for(int i=0;i<keywords.length;i++){
			Output.consoleString(Integer.toString(i+1)+": ");//+1
			keywords[i]=testquest.nextLine();
		}
		Question sAnswer = new ShortAnswer(prompt, resp, keywords, entInput);
		return sAnswer;
	}
	
	
	public Question newMatch(){
		int numChoices=0;
		Scanner testquest = new Scanner(System.in);
		Output.consoleString("Enter the prompt for your matching question");
		String prompt = testquest.nextLine();
		Output.consoleString("Enter the number of choices you want your matching question to have");
		try{
			numChoices = testquest.nextInt();
		}
		catch(InputMismatchException e){
			Output.consoleString("Invalid entry, double check your input and try again");
		}
		Output.consoleString("What options would you like on the left side?: ");
		testquest.nextLine();
		String[] choices = new String[numChoices];
		for(int i=0;i<numChoices;i++){
			Output.consoleString(Integer.toString(i+1)+": ");//+1
			choices[i]=testquest.nextLine();
		}
		String[] matchingChoices = new String[numChoices];
		Output.consoleString("What options are you matching them with on the right side?");
		for(int i=0;i<numChoices;i++){
			Output.consoleString(Integer.toString(i+1)+": ");//+1
			matchingChoices[i]=testquest.nextLine();
		}
		int[] correctOrder = new int[numChoices];
		Output.consoleString("What is the correct numerical matching order of right-hand options to match your respective left-hand options?");
		for(int i=0;i<numChoices;i++){
			Output.consoleString(Integer.toString(i+1)+" "+choices[i]+": ");//toString+1
			correctOrder[i]=testquest.nextInt();
		}
		int[] respArray = new int[numChoices];
		Question match = new Matching(prompt, choices, correctOrder, respArray, matchingChoices);
		return match;
	}
	
	
	public Question newRank(){
		Scanner testquest = new Scanner(System.in);
		Output.consoleString("Enter the prompt for your ranking question");
		String prompt = testquest.nextLine();
		Output.consoleString("Enter the number of choices you want your ranking question to have");
		int numChoices = testquest.nextInt();
		Output.consoleString("What are your options for your ranking question?");
		String[] choices = new String[numChoices];
		testquest.nextLine();
		for(int i=0;i<numChoices;i++){
			Output.consoleString(Integer.toString(i+1)+": ");//toString(i+1)
			choices[i]=testquest.nextLine();
		}
		String[] correctOrder = new String[numChoices];
		Output.consoleString("What is the correct numerical ranking order of right-hand options to match your left-hand options?");
		for(int i=0;i<numChoices;i++){
			Output.consoleString(Integer.toString(i+1)+": ");//toString(i+1)
			try{
				correctOrder[i]=Integer.toString(testquest.nextInt()-1);
			}
			catch(InputMismatchException e){
				Output.consoleString("Invalid entry, double check your input and try again");
			}
		}
		int[] respArray = new int[numChoices];
		Question rank = new Ranking(prompt, choices, correctOrder, respArray);
		return rank;
	}
	
	public List<Question> getQuestions(){
		return questionsList;
	}
}