		class Radio{
			int channel;
			String amfm;
			int volume;
			boolean on;
			
			public Radio(int ch, String af, int v, boolean o){
			channel=ch;
			amfm=af;
			volume=v;
			on=o;
			}
			/*public void displayRadio(){
				System.out.println(amfm + " " + channel + "at volume "+ volume);
			}*/
			
			public void set(int ch){
				channel=ch;
			}
			
			public void volume(int v){
				volume=v;
			}
			
			public void on(){
				on=true;
			}
			
			public void off(){
				on=false;
			}
			public void dispayRadio() {
				System.out.println(channel + " " + amfm + " at volume "+ volume);
			}
			public String displayChannel(){
				System.out.println(channel + " " + amfm);
				return(channel + " " + amfm);
			}
		}
		
class AlarmClock{
	
	/* Alarm Class*/
		int hour;
		int minute;
		int second;
		String meridien;
		int alarmHour;
		int alarmMinute;
		String alarmMeridien;
		Boolean active;
		Boolean radioAlarm;
		Radio alarmRadio;
		
		public AlarmClock(int h, int m, int s, String mer, int alarmh, int alarmm, String aMer) {
			/* Clock */
			hour=h;
			minute=m;
			second=s;
			meridien=mer;
			/* Alarm */
			alarmHour=alarmh;
			alarmMinute=alarmm;
			alarmMeridien=aMer;
			active=true;
			radioAlarm=false;
			alarmRadio = new Radio(1200,"FM",45,true);
		}
	
			public void set(int hr, int min, String meridien){
				alarmHour=hr;
				alarmMinute=min;
				alarmMeridien=meridien;
			}
			
			public void AlarmOn(){
				active=true;
			}
			
			public void AlarmOff(){
				System.out.println("Alarm is off");
				active=false;
			}
			
			public void alarmRadioOn(){
				radioAlarm=true;
			}
			
			public void alarmRadioOff(){
				radioAlarm=false;
			}
			
			public void ring(){
				if(radioAlarm){
					alarmRadio = new Radio(1200,"FM",45,true);
					System.out.println("Radio is playing." + alarmRadio.displayChannel());
				}
				else if(!radioAlarm){
					System.out.println("Buzz buzz buzz");
				}
				else{
					System.out.println("Buzz buzz buzz");
				}
			}
					
			public void setMeridien(String meridien){
				if(meridien.equals("AM")||meridien.equals("Am")||meridien.equals("am")||meridien.equals("PM")||meridien.equals("Pm")||meridien.equals("pm")){
					alarmMeridien=meridien;
				}
			}
			
			public void useRadioAlarm(Boolean set){ //True/False
				radioAlarm=set;
			}
			
			public void snooze(){
				alarmMinute+=9;
				if(alarmMinute>=60){
					alarmMinute%=60;
					alarmHour+=1;
					if(alarmHour>=12){
						alarmHour+=1;
					}
				}
			}
			
			public void tick(){
				second=second+1;
				if(second>=60){
					second%=60;
					minute+=1;
					if(minute>=60){
						minute%=60;
						hour+=1;
						if(hour>=12){
							hour=1;
						}
					}
				}
			}
			
			public String showTime(){
				return(hour+":"+minute+" " +meridien);
				
			}
			
			public void checkAlarm(){
				if( (hour==alarmHour) && (minute==alarmMinute) ){
					if(radioAlarm){
						System.out.println("Radio alarm is playing" );
					}
					else{
						System.out.println("Buzz buzz buzz");
					}
				}
			}
		}
		
		
public class lab1 {

	
	public static void main(String[] args){
		int i=0;
		AlarmClock myClock = new AlarmClock(8, 0, 0, "AM", 8, 5, "AM");
		Radio myRadio = new Radio(1200, "AM", 50, true);
		for (i = 0; i <= myClock.alarmMinute; i++)
		  {
		    System.out.println("Time: " + myClock.showTime());
		    for (int seconds = 0; seconds < 60; seconds++)
		    {
		     myClock.checkAlarm();
		     myClock.tick();
		    }
		  }
		//myClock.checkAlarm();
		myClock.snooze();
		myClock.alarmRadioOn();
		for (i = 0; i < 9; i++)
		{
		  System.out.println("Time: " + myClock.showTime());
		  for (int seconds = 0; seconds < 60; seconds++)
		  {
			//System.out.println("Time: " + myClock.showTime());
		    myClock.checkAlarm();
		    myClock.tick();
		  }
		}
		myRadio.dispayRadio();
		myClock.AlarmOff();
		System.out.println("Complete");
	}
	
}
