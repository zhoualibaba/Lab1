package lab1;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DisplayShortestPath {
	String Path = new String();
	List<VNode> list = new ArrayList<VNode>();
	DisplayShortestPath(String Path, List<VNode> list)
	{
		this.Path = Path;
		this.list = list;
		String str = "";
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		for(VNode V : list)
		{
			ENode node = V.next;
			while(node != null)
			{
				str += (V.word).toString();
				str += " -> ";
				str += ((list.get(node.pos)).word).toString();
				if( EdgeCheck(V.word, list.get(node.pos).word) )
					str += "[label = " + node.weight + ", color = red]";
				else
					str += "[color = blue]";
				gv.addln(str.toString());
				node = node.next;
				str = "";
			}
		}
		gv.addln(gv.end_graph());
		
		//System.out.println(gv.getDotSource());
		
	    String type = "jpg";
	    String representationType="dot";
	    File out = new File("shortPath." + type);   // out.gif in this example
	    gv.writeGraphToFile( gv.getGraph(gv.getDotSource(), type, representationType), out );
	    new ScrollPane();
	}
	public boolean EdgeCheck(String word1, String word2)
	{
		String []A = Path.split("->");
		for(int i = 0; i < A.length - 1; i++)
		{
			if(A[i].equals(word1) && A[i+1].equals(word2))
				return true;
		}
		return false;
	}
}

class ScrollPane extends JFrame implements MouseListener {
    ImagePane jp;
    JScrollPane jsp;
    public ScrollPane() {
            super("JScrollPane Demo");
            jp = new ImagePane();
            jp.setPreferredSize(new Dimension(600, 400));
            jsp = new JScrollPane(jp);
            jp.addMouseListener(this);
            getContentPane().add(jsp);
            setSize(640, 480);
            //setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
    }
    public void mouseClicked(MouseEvent e) {
            //System.out.println("mouseClicked");
            jp.enlarge();
            jp.setPreferredSize(jp.getPreferredSize());
            jsp.validate();
    }
    public void mousePressed(MouseEvent e) {
            // System.out.println("mousePressed");
    }

    public void mouseReleased(MouseEvent e) {
            // System.out.println("mouseReleased");
    }
    public void mouseEntered(MouseEvent e) {
            // System.out.println("mouseEntered");
    }
    public void mouseExited(MouseEvent e) {
            // System.out.println("mouseExited");
    }
    public static void main(String[] args) {
            new ScrollPaneDemo();
    }
}
class ImagePane extends JPanel {
    private Image image;
    private Dimension theSize = new Dimension(1800, 2400);
    public void setImage(Image image) {
            this.image = image;
            this.repaint();
    }
    public Image getImage() {
            return this.image;
    }
    public void paintComponent(Graphics g) {
            ImageIcon img = new ImageIcon("E:\\eclipse workplace\\Lab1GAI\\shortPath.jpg");
            g.drawImage(img.getImage(), 0, 0, theSize.width, theSize.height, null);
    }
    public void enlarge() {
            theSize.width = (theSize.width * 101) / 100;
            theSize.height = (theSize.height * 101) / 100;
            setSize(theSize);
    }        
    public Dimension getPreferredSize() {
            return this.theSize;
    }
}


