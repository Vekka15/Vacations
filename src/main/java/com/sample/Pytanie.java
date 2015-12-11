package com.sample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Pytanie {
	String text;
	Integer answer_number;
	ArrayList<Odpowiedz> odpowiedzi= new ArrayList<Odpowiedz>();;
	JButton akceptuj_button = new JButton("Akceptuj");
	Pytanie(String inf){
		this.text = inf;
		
	}
	
	

	
	void ask(){
		
	}	
	
	
}
