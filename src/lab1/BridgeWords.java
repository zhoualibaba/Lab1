package lab1;
import java.util.*;

/*To find the word linking word1 and word2
 * Construct function:  BridgeWords(String word1, String word2, List<VNode>List);
 * Display index result : Display();
 */
public class BridgeWords 
{
	String word1;
	String word2;
	String word3;
	ArrayList<String> result = new ArrayList<String>();
	public BridgeWords(String word1, String word2, List<VNode> List)
	{
		int pre = Index(word1, List), next = Index(word2, List);
		if(pre == -1 || next == -1)  
		{
			word3 = "No word1 or word2 in the graph!";
		}
		else
		{
			//System.out.println(pre + " " + next);
			VNode node = List.get(pre);
			ENode edge = node.next;
			while(edge != null)
			{
				ENode SearchEdge = (List.get(edge.pos)).next;
				while(SearchEdge != null)
				{
					if(SearchEdge.pos == next)
						result.add(List.get(edge.pos).word);
					SearchEdge = SearchEdge.next;
				}
				edge = edge.next;
			}
			if(result.size() == 0) word3 = "No bridge words from word1 to word2!";
			else word3 = "The bridge words from word1 to word2 are: ";
		}
	}
	public int Index(String word, List<VNode> List)
	{
		for(int i = 0; i < List.size(); i++)
		{
			if((List.get(i).word).equals(word))
				return i;
		}
		return -1;
	}
	public void Display()
	{
		System.out.println(word3);
		for(String s : result)
		{
			System.out.print(s);
			System.out.print(" ");
		}
		System.out.print("\n");
	}
}
