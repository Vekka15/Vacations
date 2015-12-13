package main.java.com.sample;

        import java.awt.Color;
        import java.awt.Font;

        import javax.swing.*;


public class Odpowiedz {
    public String text;
    public static int dym =10;
    JRadioButton znacznik;
    JCheckBox check_znacznik;
    public Odpowiedz(String inf,int typ_odpowiedzi){
       if(typ_odpowiedzi==0) {
            Font myFont = new Font("Tahoma", Font.BOLD, 15);
            //wartość naszej odpowiedzi
            text = inf;
            znacznik = new JRadioButton(this.text);
            znacznik.setBackground(null);
            znacznik.setFont(myFont);
            znacznik.setForeground(Color.decode("#555555"));
        }
            if(typ_odpowiedzi==1) {
                Font myFont = new Font("Tahoma", Font.BOLD, 15);
                //wartość naszej odpowiedzi
                text = inf;
                check_znacznik = new JCheckBox(this.text);
                check_znacznik.setBackground(null);
                check_znacznik.setFont(myFont);
                check_znacznik.setForeground(Color.decode("#555555"));
            }

    }

    void answer(){
        DroolsTest.ksession.insert(this);
    }
}