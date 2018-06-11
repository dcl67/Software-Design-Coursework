import java.util.Observable;
import java.util.Observer;

public class Listing implements Observer{
	int lObserve;
	@Override
	public void update(Observable o, Object arg) {
		if("list".equals(arg)){
			lObserve++;
		}
	}
}