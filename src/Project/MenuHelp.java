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
import java.io.File;
import javax.swing.*;
//import java.beans.PropertyVetoException;


class MenuHelp extends JMenu{

    private JMenuItem mnu_Help=new JMenuItem("Help");
    private JMenuItem mnu_About= new JMenuItem("About");


    //private MDIDesktopPane desktop;
    public MenuHelp(MDIDesktopPane desktop)
    {
        this.setText("Help");
       this.setMnemonic('H');
       add(mnu_Help);
       mnu_Help.setMnemonic('h');
       mnu_Help.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_F1,ActionEvent.CTRL_MASK
					)
				);
       mnu_Help.setIcon(new ImageIcon("icons/small_help.png"));
       mnu_Help.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
            Runtime rt = Runtime.getRuntime();
             try {
                 File fl=new File("Help/index.html");
                 //System.getRuntime().exec("rundll32 url.dll,FileProtocolHandler ");
                Process pc = rt.exec("rundll32 url.dll,FileProtocolHandler "+ fl);
                //pc.waitFor();
                 
              } catch (Exception ex) {
                ex.printStackTrace();
              }

          }
        });
        add(mnu_About);
        mnu_About.setMnemonic('a');
        mnu_About.setAccelerator(
				KeyStroke.getKeyStroke(
				KeyEvent.VK_A,ActionEvent.CTRL_MASK
					)
				);
        mnu_About.setIcon(new ImageIcon("icons/small_about.png"));
        mnu_About.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            new Form_About();
          }
        });
    }
}
