package com.sample;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JRadioButton;


public class Odpowiedz {
	public String text;
	public static int dym =10;
	JRadioButton znacznik;
	Odpowiedz(String inf){
		Font myFont = new Font("Tahoma", Font.BOLD, 15);
		text=inf;
		znacznik = new JRadioButton(this.text);
		znacznik.setBackground(null);
		znacznik.setFont(myFont);
		znacznik.setForeground(Color.decode("#555555"));
	}
	
	void answer(){
		DroolsTest.ksession.insert(this);
	}
}
