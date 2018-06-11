import java.util.Observable;
import java.util.Observer;

public class Delete implements Observer{
	int dObserve;
	@Override
	
	public void update(Observable o, Object arg) {
		if("delete".equals(arg)){
			dObserve++;
		}
	}
}