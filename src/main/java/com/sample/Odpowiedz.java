package com.sample;

import javax.swing.JRadioButton;

import org.drools.runtime.rule.FactHandle;

public class Odpowiedz {
	public static String text;
	public static int dym =10;
	JRadioButton znacznik;
	Odpowiedz(String inf){
		this.text = inf;
		znacznik = new JRadioButton(this.text);
	}
	void answer(){
		DroolsTest.ksession.insert(this);
	}
}
