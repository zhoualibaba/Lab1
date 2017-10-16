package lab1;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.awt.Image;
import javax.imageio.*;
import java.awt.*;  
import javax.swing.*;  

//import Graphviz.GraphViz;

public class prago
{
   public static void main(String[] args) throws IOException
   {
      prago p = new prago();
      
      p.start();
//      p.start2();
   }

   
   class Ipanel extends JPanel{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		   Image i = new ImageIcon("C:/Users/joker/Desktop/a.gif").getImage();
		   g.drawImage(i, 10, 20, this);
	   }
   }
   
   
   
/**
    * Construct a DOT graph in memory, convert it
    * to image and store the image in the file system.
 * @throws IOException 
    */
   private void start() throws IOException
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.addln("A -> B;");
		gv.addln("A -> C;");
		gv.addln(gv.end_graph());
		System.out.println(gv.getDotSource());

		gv.increaseDpi();   // 106 dpi

		String type = "gif";
		//      String type = "dot";
		//      String type = "fig";    // open with xfig
		//      String type = "pdf";
		//      String type = "ps";
		//      String type = "svg";    // open with inkscape
		//      String type = "png";
		//      String type = "plain";
		
		String repesentationType= "dot";
		//		String repesentationType= "neato";
		//		String repesentationType= "fdp";
		//		String repesentationType= "sfdp";
		// 		String repesentationType= "twopi";
		// 		String repesentationType= "circo";
		
		//File out = new File("/tmp/out"+gv.getImageDpi()+"."+ type);   // Linux
		File out = new File("C:/Users/joker/Desktop/a." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );

		Image array = ImageIO.read(out);
		ImageIO.createImageOutputStream(array);
	}

	/**
	 * Read the DOT source from a file,
	 * convert to image and store the image in the file system.
	 */
	private void start2()
	{
		//String dir = "/home/jabba/Dropbox/git.projects/laszlo.own/graphviz-java-api";     // Linux
		//String input = dir + "/sample/simple.dot";
		String input = "c:/eclipse.ws/graphviz-java-api/sample/simple.dot";    // Windows

		GraphViz gv = new GraphViz();
		gv.readSource(input);
		System.out.println(gv.getDotSource());

		String type = "gif";
		//    String type = "dot";
		//    String type = "fig";    // open with xfig
		//    String type = "pdf";
		//    String type = "ps";
		//    String type = "svg";    // open with inkscape
		//    String type = "png";
		//      String type = "plain";
		
		
		String repesentationType= "dot";
		//		String repesentationType= "neato";
		//		String repesentationType= "fdp";
		//		String repesentationType= "sfdp";
		// 		String repesentationType= "twopi";
		//		String repesentationType= "circo";
		
		//File out = new File("/tmp/simple." + type);   // Linux
		File out = new File("C:/Users/joker/Desktop/a." + type);   // Windows
		gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, repesentationType), out );
	}
	
	
}