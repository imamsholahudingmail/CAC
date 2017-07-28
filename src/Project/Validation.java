/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Project;

import javax.swing.JOptionPane;

/**
 *
 * @author prashant-lappy
 */
public class Validation {
    boolean validName(String name)
    {
        if(name.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter name.");
            return false;
        }
        else if(!(name.matches("[a-zA-Z]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter character only in name.");
            return false;
        }
        else
        {
            return true;
        }

    }
    boolean validCellNo(String cellNo)
    {
        if( cellNo.length()>0 && !(cellNo.matches("[0-9]*")))
        {

           JOptionPane.showMessageDialog(null, "Please enter number only in Cell Number.");
           return false;

        }
        else
        {
            return true;
        }
    }
    boolean validHomePhone(String homePhone)
    {
        if(homePhone.length()>0 && !(homePhone.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in Home Phone.");
            return false;
        }
        else
        {
            return true;
        }


    }

     boolean validPassPort(String passPort)
    {


            if(!(passPort.matches("[0-9A-Za-z]*")))
            {
                 JOptionPane.showMessageDialog(null, "Please enter alphanumeric only in PassPort.");
                return false;
            }
            else
            {
                return true;
            }


    }
      boolean validHigherEdu(String higherEdu)
    {

       if(!(higherEdu.matches("[a-zA-Z.]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter character only in Higher Education.");
            return false;
        }
        else
        {
            return true;
        }

    }
    boolean validUniversity(String university)
    {

       /*if( university.length()<=0)
        {
            JOptionPane.showMessageDialog(null, "Please enter character only in University.");
            return false;
        }
        else*/
        {
            return true;
        }

    }
     boolean validDivClass(String divClass)
    {

       /*if( divClass.length()<=0)
        {
            JOptionPane.showMessageDialog(null, "Please enter character only in Div/Class.");
            return false;
        }
        else*/
        {
            return true;
        }

    }
     boolean validPercentage(String percentage)
    {
        if(percentage.length()>2)
        {
            if(!(percentage.matches("[0-9]*")))
            {
                 JOptionPane.showMessageDialog(null, "Please enter number only in Percentage.");
                return false;
            }
            else
            {
                return true;
            }
        }
        else
            return true;
    }

    boolean validExperience(String experience)
    {

            if(!(experience.matches("[0-9]*")))
            {
                 JOptionPane.showMessageDialog(null, "Please enter number only in Experience.");
                return false;
            }
            else
            {
                return true;
            }


    }
     boolean validExpectedSal(String expectedSal)
    {

            if(!(expectedSal.matches("[0-9]*")) && expectedSal.length()>0)
            {
                 JOptionPane.showMessageDialog(null, "Please enter number only in Expected Salary.");
                return false;
            }
            else
            {
                return true;
            }

     }



       boolean validEmailId(String emailId)
    {

        if((emailId.indexOf("@")<0 || emailId.indexOf(".")<0) && emailId.length()>0)
        {
            JOptionPane.showMessageDialog(null, "Please enter valid email id.");
            return false;
        }
       else
       {
           return true;
       }
       /*if((!(emailId.matches("[a-zA-Z]*[0-9]*[_]*[a-zA-Z]*[0-9]@[a-zA-Z]*.[a-zA-Z]*.[a-zA-Z]*")) ||
               !(emailId.matches("[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*.[a-zA-Z]*")))
               && emailId.length()>0)
        {
            JOptionPane.showMessageDialog(null, "Please enter valid email id.");
            return false;
        }
       else
       {
           return true;
       }*/
    }
    boolean validCname(String cname)
    {
        if(cname.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter name.");
            return false;
        }
        else
        {
            return true;
        }


    }

     boolean validCcode(String ccode)
    {
        if(ccode.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter code.");
            return false;
        }
        else if(!(ccode.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in code.");
            return false;
        }
        else
        {
            return true;
        }


    }

     boolean validContactNo1(String contactno1)
    {

       if( contactno1.length()>0 && !(contactno1.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in contact1.");
            return false;
        }
        else
        {
            return true;
        }


    }

      boolean validContactNo2(String contactno2)
    {

       if( contactno2.length()>0 && !(contactno2.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in contact2.");
            return false;
        }
        else
        {
            return true;
        }


    }

       boolean validWebSite(String website)
    {

       if(website.indexOf(".")<0 && website.length()>0)
        {
            JOptionPane.showMessageDialog(null, "Please enter valid web site.");
            return false;
        }
       else
       {
           return true;
       }
    }

        boolean validSize(String size)
    {

       if( size.length()>0 && !(size.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in size of organization.");
            return false;
        }
        else
        {
            return true;
        }


    }
         boolean validHrMngr(String hrmngr)
    {

       if(!(hrmngr.matches("[a-zA-Z]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter character only in Hr Manager.");
            return false;
        }
        else
        {
            return true;
        }

    }
        boolean validEmail(String email)
    {

       if((!(email.matches("[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*")) ||
               !(email.matches("[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*.[a-zA-Z]*")))
               && email.length()>0)
        {
            JOptionPane.showMessageDialog(null, "Please enter valid email id.");
            return false;
        }
            else
        {
           return true;
        }
    }
         boolean validContactNo(String contactno)
    {

       if( contactno.length()>0 && !(contactno.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in contact No.");
            return false;
        }
        else
        {
            return true;
        }
    }
        boolean validFax(String fax)
    {

       if(fax.length()>0 && !(fax.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in Fax.");
            return false;
        }
        else
        {
            return true;
        }
    }

         boolean validJcode(String jcode)
    {
        if(jcode.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter code.");
            return false;
        }
        else if(!(jcode.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in code.");
            return false;
        }
        else
        {
            return true;
        }


    }
         boolean validJname(String jname)
    {
        if(jname.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter name.");
            return false;
        }
        else
        {
            return true;
        }


    }


 //boolean validJshift(String jshift)
   // {
     //  if(jshift.isEmpty())
       // {
         //   JOptionPane.showMessageDialog(null, "Please enter character only in shifts.");
           // return false;
        //}
        //else
       // {
         //   return true;
        //}

    //}

 boolean validJannsal(String jannsal)
    {

       if(jannsal.length()>0 && !(jannsal.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in Annual Salary.");
            return false;
        }
        else
        {
            return true;
        }
    }

     boolean validVacancy(String vacancy)
    {

       if(vacancy.length()>0 && !(vacancy.matches("[0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter number only in Vacancy.");
            return false;
        }
        else
        {
            return true;
        }
    }

 boolean validLogname(String username)
    {
        if(username.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter name.");
            return false;
        }
        else if(!(username.matches("[a-zA-Z]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter character only in name.");
            return false;
        }
        else
        {
            return true;
        }

    }






    boolean validSearch(String search)
    {
        if(search.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter name/code.");
            return false;
        }
        else if(!(search.matches("[a-zA-Z0-9]*")))
        {
            JOptionPane.showMessageDialog(null, "Please enter alphanumeric only in search.");
            return false;
        }
        else
        {
            return true;
        }

    }
      boolean validUserName(String username)
    {
         if( username.length()<=0)
        {
            JOptionPane.showMessageDialog(null, "Please enter name.");
            return false;
        }

            return true;

    }
    boolean validPassWord(String txt1, String txt2)
    {
        if( !(txt1.equals(txt2)))
        {
            JOptionPane.showMessageDialog(null, "Password does not match.");
            return false;
        }
             return true;
    }
}