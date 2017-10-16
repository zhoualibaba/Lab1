package lab1;
import java.util.*;
import java.io.*;

/*InsertBridgeWords(List<VNode> list): constructor, text input required
 *GetResult(): get result string
 *DisplayResult(): output the result
 */
public class InsertBridgeWords 
{	
	List<String> S = new ArrayList<String>();
	String result = new String();
	//List<VNode> list = new ArrayList<VNode>();
	
	InsertBridgeWords(List<VNode> list)
	{
		
		System.out.println("请输入文本(以#结尾)：");
		Scanner sc = new Scanner(System.in);
		String str = new String();
		while(sc.hasNext())
		{
			str = sc.next();
			if(str.contains("#"))
			{
				str = str.substring(0,str.indexOf('#'));
				S.add(str.toString());
				break;
			}
			S.add(str.toString());
		}
		result = "";
		result += (S.get(0) + " ");
		for(int i = 0; i < S.size() - 1; i++)
		{
			BridgeWords search = new BridgeWords(S.get(i), S.get(i+1), list);
			if((search.result).size() == 0) 
			{
				result += (S.get(i+1) + " ");
			}
			else 
			{
				//System.out.print(text.get(i) + " ");
				result += "( ";
				for(String s : search.result)
				{
					result += (s + " ");
				}
				result +=(")" + " ");
				result += (S.get(i+1) + " ");
			}
		}
	}
	public String GetResult()
	{
		return result.toString();
	}
	public void DisplayResult()
	{
		System.out.println("插入桥接词后得文本为：");
		System.out.print(result);
	}
}
