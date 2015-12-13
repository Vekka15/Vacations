package main.java.com.sample;


import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

import static java.awt.Color.*;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static StatefulKnowledgeSession ksession;
    public static KnowledgeRuntimeLogger logger;
    public static KnowledgeBase kbase;
    public static Pytanie pytanie ;
    public static JPanel panel = new JPanel(); //panel powitalny
    public static JFrame frame = new JFrame("Vacations");


    public static final void main(String[] args) throws IOException {
//        try {
//            // load up the knowledge base
//            KnowledgeBase kbase = readKnowledgeBase();
//            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
//            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
//            ksession.insert(new Pomiar(100,150));
//            ksession.fireAllRules();
//            logger.close();
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }

        JLabel napis = new JLabel("<html><center> VACATION</center><center> FINDER <center></html>");
        Font myFont = new Font("Tahoma", Font.BOLD, 40);
        Font buttonFont = new Font("Tahoma", Font.BOLD, 25);
        napis.setFont(myFont);
        napis.setHorizontalAlignment(SwingConstants.CENTER);
        napis.setBounds(175,325,250,100);
        napis.setForeground(decode("#555555"));
        napis.setVisible(true);

        //obrazek logo
        BufferedImage logoPicture = ImageIO.read(new File("logo.png"));
        JLabel logoLabel = new JLabel(new ImageIcon(logoPicture));
        logoLabel.setBounds(0,0,logoPicture.getWidth(),logoPicture.getHeight());
        logoLabel.setVisible(true);

        // ustawienia okna
        frame.setSize(600,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //ustawienia panelu powitalnego
        panel.setBackground(decode("#84C3BB"));
        panel.setLayout(null);
        frame.add(panel);

        //ustawienia panelu startowego
        JButton start = new JButton("START");
        start.setBounds(210,500,180,70);
        start.setBorder(BorderFactory.createLineBorder(decode("#555555"),4));
        start.setFont(buttonFont);
        start.setForeground(decode("#555555"));
        start.setContentAreaFilled(false);
        start.setVisible(true);
        panel.add(start);
        panel.add(logoLabel);
        panel.add(napis);

        //ustawienia poczatkowe panelu z pytaniami
        start.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //REGUŁY
                try {
                    // load up the knowledge base
                    kbase = readKnowledgeBase();
                    ksession = kbase.newStatefulKnowledgeSession();
                    logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
                    ksession.fireAllRules();
                    //   logger.close();
                } catch (Throwable t) {
                    t.printStackTrace();
                }

            }
        });
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





}