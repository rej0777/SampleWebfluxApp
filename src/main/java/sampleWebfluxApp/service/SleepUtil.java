package sampleWebfluxApp.service;

public class SleepUtil {

	public static void sleapSeconds(int seconds) {
		try {
			Thread.sleep(seconds *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
