package sampleWebfluxApp.reactor.flux;

import java.util.List;

import sampleWebfluxApp.reactor.Util;

public class FlucVsList {
	
	void myMain(){
		List<String>names=Util.nameGeneratorList(5);
		System.out.println(names);
		
		Util.nameGeneratorFlux(5).subscribe(Util.onNext());
	}

}
