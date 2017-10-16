package lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public  class MyMain {
	
	public static void main(String[] args) throws IOException {
		List<String> lists = new ArrayList<String>();
		lists = MyRead.read();
		
		List<VNode> adjustList = new AdList(lists).getAdList();
		
		while(true)
		{
			System.out.println("\n请选择功能（输入序号）：");
			System.out.println("1.展示图");
			System.out.println("2.查询桥接词");
			System.out.println("3.生成新文本");
			System.out.println("4.查询最短路");
			System.out.println("5.随机游走");
			System.out.println("6.退出程序");
			Scanner input=new Scanner(System.in);
			System.out.println("请输入序号：");
			int func = input.nextInt();
			if(func == 1)
			{
				new DisplayGraph(adjustList);
			}
			else if(func == 2)
			{
				System.out.println("请输入两个单词");
				String word1 = input.next();
				String word2 = input.next();
				BridgeWords search = new BridgeWords(word1, word2, adjustList);
				search.Display();
			}
			else if(func == 3)
			{
				InsertBridgeWords insert = new InsertBridgeWords(adjustList);
				insert.DisplayResult();
			}
			else if(func == 4)
			{
				System.out.println("请输入两个单词");
				String word1 = input.next();
				String word2 = input.next();
				MyShortestPath M = new MyShortestPath(adjustList);
				String result1 = M.calcShortestPath(word1, word2);
				if(result1 != "所给单词有未出现在文本中的" && result1 != "不可达")
						new DisplayShortestPath(result1, adjustList);
				System.out.println(result1);
			}
			else if(func == 5)
			{
				System.out.println("输入quit手动结束随机游走.");
				MyRandomWalk W = new MyRandomWalk(adjustList);
				String result = W.randomWalk();
				System.out.println("下面路径将保存在ansForFunc6.txt中");
				System.out.println(result);
			}
			else if(func == 6)
			{
				input.close();
				break;
			}
			else
			{
				System.out.println("序号输入有误，请检查后重新输入");
			}
		}
		
		
		/*
		DisplayGraph G = new DisplayGraph(adjustList);
		
		
		
		BridgeWords search = new BridgeWords("to", "out", adjustList);
		search.Display();
		
		
		InsertBridgeWords insert = new InsertBridgeWords(adjustList);
		insert.DisplayResult();
		
		MyRandomWalk W = new MyRandomWalk(adjustList);
		String result = W.randomWalk();
		

		MyShortestPath M = new MyShortestPath(adjustList);
		String result1 = M.calcShortestPath("to", "out");
		DisplayShortestPath D = new DisplayShortestPath(result1, adjustList);
		
		
		//System.out.println(result);
		System.out.println(result1);
		
		*/
	}
}
