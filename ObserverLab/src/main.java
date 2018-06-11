import java.io.IOException;
import java.util.Observable;

public class main extends Observable{
	public static void main(String[] args) throws IOException{
		Listing list = new Listing();
		Create create = new Create();
		Delete del = new Delete();
		Modify modify = new Modify();
		FileHandler fh = new FileHandler();
		fh.addObserver(create);
		fh.addObserver(del);
		fh.addObserver(list);
		fh.addObserver(modify);
		fh.run();
		
		System.out.println("\nListings: "+list.lObserve+
						   "\nCreates: "+create.cObserve+
						   "\nDeletes: "+del.dObserve+
						   "\nModifications: "+modify.mObserve);
	}
}
