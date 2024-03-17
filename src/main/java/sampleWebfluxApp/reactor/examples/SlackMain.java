package sampleWebfluxApp.reactor.examples;

import sampleWebfluxApp.reactor.Util;

public class SlackMain {

	
	public static void mymain(String[] args) {
		
		SlackRoom slackRoom = new SlackRoom("reactor");
		
		SlackMember sam = new SlackMember("sam");
		SlackMember jake = new SlackMember("mike");
		SlackMember mee = new SlackMember("mee");
		
		slackRoom.joinRoom(sam);	
		slackRoom.joinRoom(jake);
	
		
		sam.says("hhi am sam ");
		Util.sleepSeconds(4);
		
		jake.says("hi am jake");
		sam.says("hii ame sam !!");
		Util.sleepSeconds(4);
		
		slackRoom.joinRoom(mee);
		mee.says("halloo im MEEE");
		
		
		
	}
	
}
