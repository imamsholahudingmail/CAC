/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

/**
 *
 * @author prashant
 */
import javax.swing.*;
import java.awt.*;


public class Form_About extends JDialog{
    JPanel contentPanel=new JPanel();
    JLabel content=new JLabel();
    ImageIcon pic = new ImageIcon("icons/Company.jpg");
    JLabel picContent=new JLabel(pic);
    public Form_About()
    {
        getContentPane().add(contentPanel);
        //JOptionPane myJoptionPane = new JOptionPane();
        contentPanel.setLayout(null);

        
        setSize(400,300);
        setVisible(true);
        this.setAlwaysOnTop(true);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (screen.width / 2) - (400 / 2); // Center horizontally.
        int Y = (screen.height / 2) - (300 / 2); // Center vertically.

        this.setBounds(X,Y , 400 ,300);
        setResizable(false);
        setTitle("Inspirasi Program Sistem Informasi Dengan Java");

        
        picContent.setBounds(0,0,400,150);
        contentPanel.add(picContent);

        String str="<html>" +
                "<font color='#1111FF'><center><b>SIS:</b>" +
                " Sistem Informasi Sekolah berbasis java netbean</center></font>";
        content.setFont(new Font("Serif", Font.PLAIN,12));
        content.setText(str);
        content.setOpaque(true);
        content.setBackground(new Color(187, 208, 235));
        content.setBounds(0,130,400,150);
        contentPanel.add(content);
    }
}
