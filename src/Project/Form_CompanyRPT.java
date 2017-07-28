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
import java.net.URL;

public class Form_CompanyRPT  extends JInternalFrame
{
    JPanel panel_Content = new JPanel();
    static JButton btn_CandPrintPrev;
    static JEditorPane ep = new JEditorPane();

    //MDIDesktopManager desktop;
    public Form_CompanyRPT()
    {

        setSize(1000,600);
        setTitle("Report Preview");
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setResizable(true);

        
        final PrintPreview pp = new PrintPreview();


        ep.setEditable(false);
        ep.setContentType("text/html");

        String str="";
        str+="<html><head></head><body><table border=0 color='gray' cellpadding=5 cellspacing=0>";

        str+="<tr><th><font color='#0000FF'><u>Code</u></font></th>" +
                "<th><font color='#0000FF'><u>Name</u></font></th>" +
                "<th><font color='#0000FF'><u>Address</u></font></th>" +
                "<th><font color='#0000FF'><u>HR Manager</u></font></th>" +
                "<th><font color='#0000FF'><u>Email Id</u></font></th>" +
                "<th><font color='#0000FF'><u>Contact No.</u></font></th></tr>";
        for(int i=0;i<Form_CompanyTbl.dataRow;i++)
         {
            str+="<tr>";
            for(int j=0;j<Form_CompanyTbl.dataCol;j++)
            {
                str+="<td>";
                str+=Form_CompanyTbl.mydata[i][j]+"<b>";
                str+="</td>";
            }
            str+="</tr>";
         }
        str+="</table></body>";

        ep.setText(str);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel_Content);
        panel_Content.setLayout(null);
        panel_Content.setBounds(0, 0, getBounds().width, getBounds().height);
        btn_CandPrintPrev=new JButton(new ImageIcon("icons/print_preview.png"));
        btn_CandPrintPrev.setToolTipText("Print Preview");
        btn_CandPrintPrev.setBounds(10, 30, 40, 40);
        panel_Content.add(btn_CandPrintPrev);
        btn_CandPrintPrev.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pp.showPreview(ep);
            }
        });

        //ep.add(new JScrollPane(ep));
        int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
        int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        JScrollPane jsp = new JScrollPane(ep,v,h);
        jsp.setBounds(10, 100, getBounds().width-5, getBounds().height-150);
        panel_Content.add(jsp);
    }
}