import java.util.Observable;
import java.util.Observer;

public class Modify implements Observer{
	int mObserve;
	@Override
	public void update(Observable o, Object arg) {
		if("modify".equals(arg)){
			mObserve++;
		}
	}
}