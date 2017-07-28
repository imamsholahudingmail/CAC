package Project;

/**
 *
 * @author Prashant
 */
import java.io.*;
import javax.swing.JOptionPane;

public class CopyFile {
    static void Copy(String source,String destination) {
        File sourceFile=new File(source);
        //extract the filename (without the full path, just filename) from inputFile and append it to "c:\\mylocation"
        File targetFile = new File(destination);

        try {
         BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile), 4096);
         BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile), 4096);
         int theChar;
         while ((theChar = bis.read()) != -1) {
              bos.write(theChar);
          }
        bos.close();
        bis.close();
        //System.out.println ("copy done!");

  }
  catch (Exception ex) {
   ex.printStackTrace();
  }
    }
}
