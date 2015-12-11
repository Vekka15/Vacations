package com.sample;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Pytanie {
	public String text; //PAMIETAC ZE MUSI BYC PUBLIC TO CO SPRAWDZAMY
	int odpowiedz=0;
	Integer answer_number;
	public static ArrayList<Odpowiedz> odpowiedzi= new ArrayList<Odpowiedz>();;
	JButton akceptuj_button = new JButton("Akceptuj");
	ButtonGroup radio_buttons = new ButtonGroup();
	public static JPanel panel_pytania;
	Pytanie(String inf,JPanel panel){
		this.text = inf;
		this.panel_pytania=panel;
	}
	
	//przerysowanie wszystkich elementów na panelu wraz z wartoœciami nowego pytania
	public void ask(String inf, String[] odp){
			this.text=inf;

			odpowiedzi.clear();
			for(int j=0;j<odp.length;j++){
				odpowiedzi.add(new Odpowiedz(odp[j]));
			}
		  panel_pytania.removeAll();
		  panel_pytania.setBackground(Color.decode("#ff6f69"));
		  panel_pytania.setVisible(true);
		  panel_pytania.setLayout(null);
		  panel_pytania.revalidate();
		  panel_pytania.repaint();
		  JLabel pytanie_label = new JLabel(this.text);
		  //RADIO BUTTONSY
		  int wysokosc = 300;
		  int szerokosc = 200;
		  for(int i=0;i<this.odpowiedzi.size();i++){
			  radio_buttons.add(this.odpowiedzi.get(i).znacznik); //¿eby tylko jeden mogl byc zaznaczony
			  this.odpowiedzi.get(i).znacznik.setBounds(szerokosc,wysokosc,150,50);
			  this.odpowiedzi.get(i).znacznik.setVisible(true);
			  panel_pytania.add(this.odpowiedzi.get(i).znacznik);
			  wysokosc = wysokosc+50;
		  }
		  this.odpowiedzi.get(0).znacznik.setSelected(true);
		  this.akceptuj_button.setBounds(200,500,150,50);
		  this.akceptuj_button.setVisible(true);
		  this.akceptuj_button.addActionListener(new ActionListener()
			{
				  public void actionPerformed(ActionEvent e)
				  {
					  ArrayList<Odpowiedz> odpowiedzi= Pytanie.this.odpowiedzi;
					  for(int i=0;i< odpowiedzi.size();i++){ 
						  if (odpowiedzi.get(i).znacznik.isSelected()==true){
							  System.out.println(odpowiedzi.get(i).text);
							  //dodanie 
							 // Odpowiedz od = new Odpowiedz("Klops");
							 // od.answer();
							  odpowiedzi.get(i).answer();
							  DroolsTest.ksession.fireAllRules();
						  }
					  }
				  }
			});
		  panel_pytania.add(this.akceptuj_button);
		  // TRESC PYTANIA
		  pytanie_label.setBounds(200,150,250,100);
		  pytanie_label.setVisible(true);
		  panel_pytania.add(pytanie_label);
		  panel_pytania.revalidate();
		  panel_pytania.repaint();
		}
	
	public void getMessage(){
		System.out.println("hello");
	}
	

	
	
}
