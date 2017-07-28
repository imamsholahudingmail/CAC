/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

/**
 *
 * @author Prashant
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.net.URL;

public class Form_Report  extends JInternalFrame
{
    JPanel panel_Content = new JPanel();

    public Form_Report()
    {
        setSize(700,500);
        setTitle("Print Preview");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);

        
        final PrintPreview pp = new PrintPreview();

        final JEditorPane ep = new JEditorPane();
        ep.setEditable(false);

        String str="";

        for(int i=0;i<Form_CandidateTbl.dataRow;i++)
         {
            for(int j=0;j<Form_CandidateTbl.dataCol;j++)
            {
             str+=Form_CandidateTbl.mydata[i][j]+"\t";
            }
            str+="\n";
         }

        ep.setText(str);
        getContentPane().add(panel_Content);
        panel_Content.setLayout(null);
        ep.setBounds(10, 100, 600, 100);
        panel_Content.add(ep);
        
    }

    private JMenuItem createMenuItem(Action a, int mnemonic, KeyStroke accel) {
        JMenuItem item = new JMenuItem(a);
        item.setMnemonic(mnemonic);
        item.setAccelerator(accel);
        return item;
    }
}