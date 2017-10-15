package lab_1;

import lab_1.digraph;
import lab_1.digraph.ENode;
import lab_1.digraph.VNode;

// B22
public class queryBridgeWords {
	// 桥接词查询
	static VNode mVexs[] = digraph.mVexs;
	
	public static String getBridgeWords(String word1,String word2){
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		
		int p1 = digraph.getPosition(word1);
		int p2 = digraph.getPosition(word2);
		
		String bridge = "";
		if(p1 >= 0 && p2 >= 0){
			ENode node1 = mVexs[p1].firstEdge;
			ENode node2;
			while(node1 != null){
				int p = node1.ivex;
				node2 = mVexs[p].firstEdge;
				while(node2 != null){
					int p3 = node2.ivex;
					if( p3 == p2){
						bridge = bridge + mVexs[p].data + " ";
						break;
					}
					node2 = node2.nextEdge;
				}
				node1 = node1.nextEdge;
			}			
		}
		return bridge;
	}
	
	
	public static String BridgeWords(String word1,String word2){
		
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		
		int p1 = digraph.getPosition(word1);
		int p2 = digraph.getPosition(word2);
		
		String bridge = getBridgeWords(word1,word2);
		if(p1 >= 0 && p2 >= 0){
			
			String bridgelist[] = getbridgeword(bridge);
			if(bridge == ""){
				bridge = "No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!";
			}else if(bridgelist.length == 1){
				bridge = "The bridge words from \"" + word1 + "\" to \"" + word2 + "\" is: " + bridgelist[0];
			}else{
				bridge = "The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are: ";
				for(int i = 0;i < bridgelist.length;i++){
					bridge += bridgelist[i] + " ";
				}
			}
		}else if(p1 < 0 && p2 < 0){
			bridge = "No \"" + word1 + "\" and \"" + word2 + "\" in the graph!";
		}else if(p1 < 0){
			bridge = "No \"" + word1 + "\" in the graph!";
		}else if(p2 < 0){
			bridge = "No \"" + word2 + "\" in the graph!";
		}
		
		return bridge;
	}
	// 切割生成桥接词字符串数组
	public static String[] getbridgeword(String bridgeword){
		
		String bridges[] = new String[1000];
		bridges = bridgeword.split(" ");
		return bridges;
	}

}
