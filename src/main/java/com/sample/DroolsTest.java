package com.sample;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import javax.swing.BorderFactory;



/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {
	public static StatefulKnowledgeSession ksession;

    public static final void main(String[] args) throws IOException {
    	
    	//PYTANIA
    	final ButtonGroup radio_buttons = new ButtonGroup();
    	final Pytanie pytanie1 = new Pytanie("Wakacje gdzie bardziej ciê interesuj¹?");
    	Odpowiedz odp1 = new Odpowiedz("Polska");   	
    	Odpowiedz odp2 = new Odpowiedz("Europa");
    	Odpowiedz odp3 = new Odpowiedz("Œwiat");
    	pytanie1.odpowiedzi.add(odp1); //dodajemy do listy ¿eby potem po niej iterowac i nie trzeba wiedziec ile potrzeba radiobuttonow
    	pytanie1.odpowiedzi.add(odp2);
    	pytanie1.odpowiedzi.add(odp3);
    	 	
    	
    	//INTERFACE
    	
    	final JFrame frame = new JFrame("Vacations"); //okno
    	final JPanel panel = new JPanel(); //panel powitalny
    	final JPanel panel_pytania = new JPanel(); //panel z pytaniami
    	
    	//napis
    	
    	JLabel napis = new JLabel("<html><center> VACATION</center><center> FINDER <center></html>");
    	Font myFont = new Font("Tahoma", Font.BOLD, 40);
    	Font buttonFont = new Font("Tahoma", Font.BOLD, 25);
    	napis.setFont(myFont);
    	napis.setHorizontalAlignment(SwingConstants.CENTER);
    	napis.setBounds(175,325,250,100);
    	napis.setForeground(Color.decode("#555555"));
    	napis.setVisible(true);
    	
    	//obrazek logo
    	BufferedImage logoPicture = ImageIO.read(new File("/Users/Zuzanna/workspace/Vacations/src/resources/logo.png"));
    	JLabel logoLabel = new JLabel(new ImageIcon(logoPicture));
    	logoLabel.setBounds(0,0,logoPicture.getWidth(),logoPicture.getHeight());
    	logoLabel.setVisible(true);
    	
    	// ustawienia okna
    	frame.setSize(600,700);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	
    	//ustawienia panelu powitalnego
    	panel.setBackground(Color.decode("#84C3BB"));
    	panel.setLayout(null);
    	frame.add(panel);
    	
    	JButton start = new JButton("START");
    	start.addActionListener(new ActionListener()
    	{
    		  public void actionPerformed(ActionEvent e)
    		  {
    			  frame.remove(panel);
    			  frame.add(panel_pytania);
    			  frame.revalidate();
    			  frame.repaint();
    			  panel_pytania.setBackground(Color.decode("#ff6f69"));
    			  panel_pytania.setVisible(true);
    			  JLabel pytanie_label = new JLabel(pytanie1.text);
    			  //RADIO BUTTONSY
    			  int wysokosc = 300;
    			  int szerokosc = 200;
    			  for(int i=0;i<pytanie1.odpowiedzi.size();i++){
    				  System.out.println(pytanie1.odpowiedzi.get(i).text);
					  System.out.println(i);
    				  radio_buttons.add(pytanie1.odpowiedzi.get(i).znacznik); //¿eby tylko jeden mogl byc zaznaczony
    				  pytanie1.odpowiedzi.get(i).znacznik.setBounds(szerokosc,wysokosc,150,50);
    				  pytanie1.odpowiedzi.get(i).znacznik.setVisible(true);
    				  panel_pytania.add(pytanie1.odpowiedzi.get(i).znacznik);
    				  wysokosc = wysokosc+50;
    			  }
    			  pytanie1.odpowiedzi.get(0).znacznik.setSelected(true);
    			  pytanie1.akceptuj_button.setBounds(200,500,150,50);
    			  pytanie1.akceptuj_button.setVisible(true);
    			  pytanie1.akceptuj_button.addActionListener(new ActionListener()
    				{
    					  public void actionPerformed(ActionEvent e)
    					  {
    						  for(int i=0;i< pytanie1.odpowiedzi.size();i++){
    							  
    							  if (pytanie1.odpowiedzi.get(i).znacznik.isSelected()==true){
    								  System.out.println(pytanie1.odpowiedzi.get(i).text);
    							  }
    						  }
    					  }
    				});
    			  panel_pytania.add(pytanie1.akceptuj_button);
    			  // TRESC PYTANIA
    			  pytanie_label.setBounds(200,150,250,100);
    			  pytanie_label.setVisible(true);
    			  panel_pytania.add(pytanie_label);
    		  }
    	});
    	start.setBounds(210,500,180,70);
    	start.setBorder(BorderFactory.createLineBorder(Color.decode("#555555"),4));
    	start.setFont(buttonFont);
    	start.setForeground(Color.decode("#555555"));
    	start.setContentAreaFilled(false);
    	start.setVisible(true);
    	panel.add(start);
    	panel.add(logoLabel);
    	panel.add(napis);
    	
    	//REGU£Y
        try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();
            ksession = kbase.newStatefulKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            // go !
            Odpowiedz odp = new Odpowiedz("odp");
            //ksession.insert(message);
            odp.answer();
            ksession.fireAllRules();
            logger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("Sample.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}
