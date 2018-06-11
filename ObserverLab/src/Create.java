import java.util.Observable;
import java.util.Observer;

public class Create implements Observer{
	int cObserve;
	@Override
	public void update(Observable o, Object arg) {
		if("create".equals(arg)){
			cObserve++;
		}
	}
}