package lab1;

import java.util.*;

public class MyShortestPath {
	private static final int MAX = 10000;

	private  List<VNode> adjustList = new ArrayList<VNode>();
	
	MyShortestPath(List<VNode> list)
	{
		adjustList = list;
	}
	
	public List<VNode> getAdjustList() {
		return adjustList;
	}

	public void setAdjustList(List<VNode> adjustList) {
		this.adjustList = adjustList;
	}
	
	public  String calcShortestPath(String word1, String word2){
		int vnodeNumber = adjustList.size();
		StringBuilder str = new StringBuilder();
		
		int[] D = new int[vnodeNumber];		//需要更新的最短路径
		boolean[] S = new boolean[vnodeNumber];	//源点方所包含的顶点的数目
		int[] P = new int[vnodeNumber];        //更新到它的当前最短路径的父节点
		int startPoint = locate(word1); //起点
		int endPoint = locate(word2);	//终点
		
		if(word2.equals("#")){
			if(startPoint == -1) return "所给单词有未出现在文本中的";
			StringBuilder str1 = new StringBuilder();
			for (int i = 0; i < adjustList.size(); i++) {
				str1.append(calcShortestPath(word1, adjustList.get(i).word));
				str1.append("\r\n");
			}
			return str1.toString();
		}
		if((startPoint == -1) || (endPoint == -1 && word2 != "#")){
			String completedString  = new StringBuilder().append("所给单词有未出现在文本中的").toString();
			return completedString;
		}
		for (int i = 0; i < adjustList.size(); i++) {
			S[i] = false;
			D[i] = firstCalculate(startPoint, i);
			P[i] = startPoint;
		}
		S[startPoint] = true;
		for (int i = 0; i < vnodeNumber -1; i++) {
			int w = minCost(D, S);
			if(w == -1){
				return "不可达";
			}
			S[w] = true;
			for (int j = 0; j < vnodeNumber; j++) {
				if(S[j] != true){
					int sum = D[w] + firstCalculate(w, j);
					if(sum < D[j]){
						D[j] = sum;
						P[j] = w;
					}
				}
			}
		}
		Stack<String> stk = new Stack<String>();
		while(endPoint != startPoint){
			stk.push(adjustList.get(endPoint).word);
			endPoint = P[endPoint];
		}
		stk.push(adjustList.get(endPoint).word);
		while(stk.isEmpty() != true){
			str.append(stk.pop());
			if(stk.isEmpty() != true){
				str.append("->");
			}
		}
		String completedString = str.toString();
		return completedString;
	}
	
	/**
	 * calcShortestPath(String, String)函数时调用下面三个函数
	 * firstCalculate函数,locate函数,minCost函数,backPoint函数
	 * @param word
	 * @return
	 */
	private  int minCost(int[] D, boolean[] S){
		int temp = MAX;
		int w = -1;        //可能没有
		for (int i = 0; i < D.length; i++) {
			if((S[i] == false)&&(D[i] < temp)){
				temp = D[i];
				w = i;
			}
		}
		return w;
	}
	
	private  int firstCalculate(int startPoint, int i){
		if(startPoint == i) return MAX;
		VNode vnode = adjustList.get(startPoint);
		if(vnode.next == null) return MAX;
		ENode enode = vnode.next;
		while(enode != null){
			if(enode.pos == i) return 1;
			enode = enode.next;
		}
		return MAX;
	}
	private  int locate(String word){
		for (int pos = 0; pos < adjustList.size(); pos++) {
			if(word.equals(adjustList.get(pos).word)){
				return pos;
			}
		}
		return -1;
	}
}
