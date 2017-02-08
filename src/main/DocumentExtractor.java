package main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import net.iharder.dnd.FileDrop;

public class DocumentExtractor {
	private static String text;
	private static Tika tika;
	public static void main(String[] args) throws IOException, SAXException, TikaException {
		// TODO Auto-generated method stub
		tika = new Tika();
		text = "";
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		JPanel  myPanel = new JPanel();
		myPanel.setSize(300, 300);
      		frame.add(myPanel);
		frame.setVisible(true);
		new  FileDrop( myPanel, new FileDrop.Listener()
		{   public void  filesDropped( java.io.File[] files )
	    		{   
				System.out.println("waiting");
			  	for(File f : files){
			  		try {
						text += parseToString(f);
						  writeToFile(text);
					} catch (IOException | SAXException | TikaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  	}
				System.out.println("done");
	      		}   // end filesDropped
	  	}); // end FileDrop.Listener
	}
	private static String parseToString(File file) throws IOException, SAXException, TikaException{
		return tika.parseToString(file);
		
	}
	private static void writeToFile(String text){
		try{
		    PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "/converted.txt", "UTF-8");
		    writer.print(text);
		    writer.close();
		} catch (IOException e) {
		}
	}

}
