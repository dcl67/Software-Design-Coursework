package assessment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
class Output implements Serializable{
	
	static void consoleString(String p){
		System.out.println(p);
	}
	
	static void consoleStrLine(String p){
		System.out.print(p);
	}

	static void consoleQuestion(Question q){
		System.out.println(q);
	}
	
	static void consoleQArrayList(List<Question> toPrint){
		for (Question q : toPrint){
			consoleQuestion(q);
		}
	}

	public static void consoleList(ArrayList<String> list) {
		System.out.println(list);
	}
}