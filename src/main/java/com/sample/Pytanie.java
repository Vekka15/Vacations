package main.java.com.sample;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Pytanie {

    public String text;
    public static ArrayList<Odpowiedz> odpowiedzi= new ArrayList<Odpowiedz>();;
    JButton akceptuj_button = new JButton("Akceptuj");
    ButtonGroup radio_buttons = new ButtonGroup();
    public Pytanie(){
        Font buttonFont = new Font("Tahoma", Font.BOLD, 15);
        akceptuj_button.setBackground(null);
        akceptuj_button.setBorder(BorderFactory.createLineBorder(Color.decode("#555555"),4));
        akceptuj_button.setFont(buttonFont);
        akceptuj_button.setForeground(Color.decode("#555555"));
        akceptuj_button.setContentAreaFilled(false);
        akceptuj_button.setVisible(true);
        //TO JEST TAK JAKBY NASZE PIERWSZE PYTANIE
        this.ask("Gdzie chcesz jechać?",new String[]{"Europa","Świat","Polska"});
    }

    //przerysowanie wszystkich elementów na panelu wraz z wartościami nowego pytania
    public void ask(String inf, String[] odp){
        this.text=inf;
        odpowiedzi.clear();
        for(int j=0;j<odp.length;j++){
            odpowiedzi.add(new Odpowiedz(odp[j]));
        }
        // ODSWIEZENIE PANELU
        DroolsTest.panel.removeAll();
        DroolsTest.panel.revalidate();
        DroolsTest.panel.repaint();
        try {
            final BufferedImage logo = ImageIO.read(new File("logo.png"));
            JLabel logoLabel = new JLabel(new ImageIcon(logo));
            logoLabel.setBounds(0,0,logo.getWidth(),logo.getHeight());
            logoLabel.setVisible(true);
            DroolsTest.panel.add(logoLabel);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // TRESC PYTANIA (Label)
        Font font = new Font("Tahoma", Font.BOLD, 15);
        JLabel pytanie_label = new JLabel(this.text);
        pytanie_label.setBounds(150,300,300,100);
        pytanie_label.setHorizontalAlignment(SwingConstants.CENTER);
        pytanie_label.setForeground(Color.decode("#555555"));
        pytanie_label.setVisible(true);
        pytanie_label.setFont(font);
        DroolsTest.panel.add(pytanie_label);
        //RADIO BUTTONSY
        int wysokosc = 400;
        int szerokosc = 240;
        for(int i=0;i<this.odpowiedzi.size();i++){
            radio_buttons.add(this.odpowiedzi.get(i).znacznik); //żeby tylko jeden mogl byc zaznaczony
            this.odpowiedzi.get(i).znacznik.setBounds(szerokosc,wysokosc,150,25);
            this.odpowiedzi.get(i).znacznik.setVisible(true);
            DroolsTest.panel.add(this.odpowiedzi.get(i).znacznik);
            wysokosc = wysokosc+25;
        }
        this.odpowiedzi.get(0).znacznik.setSelected(true);
        this.akceptuj_button.setBounds(200,500,150,50);
        this.akceptuj_button.setVisible(true);
        this.akceptuj_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // jedziemy po wszystkich możliwych radiobuttonsach i te które są zaznaczone
                //w przypadku checkboxow mozna zrobic inna grube przyciskow dla pytania i przy .ask zaznaczac 1 w przypadku radio 0 dla check
                ArrayList<Odpowiedz> odpowiedzi= Pytanie.this.odpowiedzi;
                for(int i=0;i< odpowiedzi.size();i++){
                    if (odpowiedzi.get(i).znacznik.isSelected()==true){
                        System.out.println(odpowiedzi.get(i).text);
                        odpowiedzi.get(i).answer();
                        DroolsTest.ksession.fireAllRules();
                    }
                }
            }
        });
        DroolsTest.panel.add(this.akceptuj_button);
        DroolsTest.panel.revalidate();
        DroolsTest.panel.repaint();
    }



}