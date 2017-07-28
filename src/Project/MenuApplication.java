/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

/**
 *
 * @author prashant-lappy
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
//import java.beans.PropertyVetoException;


class MenuApplication extends JMenu{

    private JMenuItem mnu_Calculator=new JMenuItem("Calculator");
    private JMenuItem mnu_Notepad= new JMenuItem("Notepad");

   
    //private MDIDesktopPane desktop;
    public MenuApplication()
    {
        this.setText("Application");
       this.setMnemonic('A');
       add(mnu_Calculator);
       mnu_Calculator.setMnemonic('u');
       mnu_Calculator.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_U,ActionEvent.CTRL_MASK
					)
				);
       mnu_Calculator.setIcon(new ImageIcon("icons/small_calculator.png"));
       mnu_Calculator.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
            try {
                Runtime.getRuntime().exec("C:/WINDOWS/system32/calc.exe");
              } catch (Exception ex) {
                ex.printStackTrace();
              }



          }
        });
        add(mnu_Notepad);
        mnu_Notepad.setMnemonic('p');
        mnu_Notepad.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_P,ActionEvent.CTRL_MASK
					)
				);
        mnu_Notepad.setIcon(new ImageIcon("icons/small_notepad.png"));
        mnu_Notepad.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            try {
                Runtime.getRuntime().exec("C:/WINDOWS/system32/notepad.exe");
              } catch (Exception ex) {
                ex.printStackTrace();
              }
          }
        });
    }
}
