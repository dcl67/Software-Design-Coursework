import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Scanner;

public class FileHandler extends Observable {

	@SuppressWarnings("resource")
	public void run() throws IOException{
	int selection=0;
	String path;
	System.out.println("1. List files\n2. Quit");
	Scanner in = new Scanner(System.in);
	while(2!=selection){
		selection=in.nextInt();
		switch(selection){
		/*-------------------------------------------------------------------*/
			case 1:
				/* List directories */
				System.out.println();
				File dir = new File(".");
				String[] names = dir.list();
				
				for(String name : names){
				    if (new File(".").isDirectory()&&!name.startsWith(".")&&!name.startsWith("bin")&&!name.startsWith("src")){
				        System.out.println(name);
				    }
				}
				/* Prompt for dir selection in working directory */
				System.out.println("\nEnter a directory/path. Ex: dir or ./dir1");
				path = in.nextLine();
				path = in.nextLine();
				int sel=0;
				int choice=0;
				int fileCount=0;
				File[] files = new File(path+"/").listFiles();
				fileCount=showFiles(files, fileCount);
				while(4!=sel){
					System.out.println("\n1. Delete File\n"
							+ "2. Create File\n"
							+ "3. Modify File by duplicating its contents and appending them to the end.\n"
							+ "4. Return to Previous Menu");
					sel=in.nextInt();
					switch(sel){
					/*-------------------------------------------------------------------*/
						case 1:
							System.out.println("Delete which file?");
							choice=in.nextInt();
							files[choice-1].delete();
							setChanged();
							notifyObservers("delete");
							break;
					/*-------------------------------------------------------------------*/
						case 2:
							System.out.println("Create file");
							String fileName = "file"+Integer.toString(fileCount+1)+".txt";
							File f = new File(path+"/"+fileName);
							try{
								f.createNewFile();
							}
							catch(Exception e){
								System.out.println(e);
							}
							setChanged();
							notifyObservers("create");
							break;
					/*-------------------------------------------------------------------*/		
						case 3:
							String text = "";
							System.out.println("Modify which file?");
							choice=in.nextInt();
							try {
								Scanner input = new Scanner(files[choice-1]);
								while(input.hasNextLine()){
										text+=input.nextLine();
								}
							}
							catch (FileNotFoundException e) {
								System.out.println(e);
							}
							String file = files[choice-1].getName();
							BufferedWriter bw = new BufferedWriter(new FileWriter(path+"/"+file, true));
							bw.write(text);
							bw.close();
							System.out.println("Modified!");
							setChanged();
							notifyObservers("modify");
							break;
					/*-------------------------------------------------------------------*/
						case 4:
							sel=4;
							break;
					/*-------------------------------------------------------------------*/
					}
				}
				
				setChanged();
				notifyObservers("list");
				System.out.println("1. List files\n"
						+ "2. Quit");
				break;
		/*-------------------------------------------------------------------*/
			case 2: 
				selection=2;
				break;
		/*-------------------------------------------------------------------*/
		}
	}
	in.close();
}

	public static int showFiles(File[] files, int fileCount) {
		int i=0;
	    for (File file : files) {
	        if (file.getName().endsWith(".txt")) {
	        	i++;
	        	System.out.println(i+") file"+i+".txt");
	        }
	        else {/* Do nothing */}
	    }
	    fileCount=i;
	    return fileCount;
	}

	
}
