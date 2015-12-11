package com.sample;

import org.drools.runtime.rule.FactHandle;

public class Odpowiedz {
	public static String text;
	public static int dym =10;
	Odpowiedz(){
		text = "odp";
	}
	void answer(){
		DroolsTest.ksession.insert(this);
	}
}
