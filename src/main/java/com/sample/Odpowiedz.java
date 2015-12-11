package com.sample;

import javax.swing.JRadioButton;


public class Odpowiedz {
	public String text;
	public static int dym =10;
	JRadioButton znacznik;
	Odpowiedz(String inf){
		text=inf;
		znacznik = new JRadioButton(this.text);
	}
	void answer(){
		DroolsTest.ksession.insert(this);
	}
}
