package lab_1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import lab_1.digraph;
import lab_1.randomWalk;
import lab_1.queryBridgeWords;
import lab_1.generateNewText;
import lab_1.ShortestPath;
import lab_1.showDirectedGraph;

// C:/Users/zhou/Desktop/test.txt
// 12345
// B21
public class lab1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String txt_name;						// 文件名			
		String txt_test = "";					// 文本文档数据
		String []txt_word = new String[10000];	// 文本文档单词
		char txt_char;							// 文本文档字符

		System.out.println("Please imput your txt name:");
		Scanner sc = new Scanner(System.in);
		txt_name = sc.nextLine();
		System.out.println(txt_name);			// 获取文件名
		
		
		try
        {	 // 文本处理
             File inFile = new File(txt_name);
             FileReader reader = new FileReader(inFile);
             int character = reader.read();
             while(character != -1){
            	 //txt_test += (char)character;
            	 txt_char = (char)character;
            	 txt_char = Character.toLowerCase(txt_char);
            	 if(txt_char >= 'a' && txt_char <= 'z'){
            		 txt_test += txt_char; 
            	 }
            	 else{
            		 if( txt_test.charAt(txt_test.length()-1) != ' ')
            		 {
            			 txt_test += ' ';
            		 }
            	 }
            	 character = reader.read();
             }
             txt_word = txt_test.split(" ");
             System.out.println(txt_test);
             reader.close();
	    }catch(FileNotFoundException e) {
    	       System.out.println("FileStreamsTest: "+e);
	    }catch(IOException e) {
	       System.err.println("FileStreamsTest: "+e);
	    }
		
		digraph solutiondigraph = new digraph(txt_word);
		showDirectedGraph.showGraph(digraph.mVexs);
		catalog(); 						// 显示目录
		int choose = sc.nextInt();		// 功能选择
		String blank = sc.nextLine();
		if(choose == 9) System.out.println("程序结束");
		while(choose != 9){
			switch(choose){
				case 1:		// 展示有向图
					showDirectedGraph.show();
				//	solutiondigraph.print();
					break;
				case 2:		// 查询桥接词
					System.out.println("请输入要查询的桥接词：");
					String word1 = sc.nextLine();
					String word2 = sc.nextLine();					
					String word = queryBridgeWords.BridgeWords(word1, word2);
					System.out.println(word);
					break;
				case 3:		// 根据桥接词生成新文本
					System.out.println("请输入要添加桥接词的文本");
					String inputText = sc.nextLine();
					String new_text = generateNewText.newtext(inputText);
					System.out.println(new_text);
					break;
				case 4:		// 计算最短路径
					System.out.println("请输入要查询的单词：");
					System.out.print("star:");
					String w1 = sc.nextLine();
					System.out.print("stop:");
					String w2 = sc.nextLine();
					if(w2.equals("")){
						String w = ShortestPath.calcShortestPath(w1);
						 
						System.out.println(w);
					}else{
						String w = ShortestPath.calcShortestPath(w1,w2);
						
						System.out.println(w);
					}
					//System.out.println(w1+w2); 
					break;
				case 5:		// 随机遍历
					String randomwalk = randomWalk.randomw();
					System.out.println(randomwalk);
					break;
			}
			catalog();
			choose = sc.nextInt();
			blank = sc.nextLine();
			if(choose == 9) System.out.println("程序结束");
		}
		
	}
	// 生成目录
	private static void catalog(){
		System.out.println("请输入数字选择功能：");
		System.out.println("1：展示生成的有向图");
		System.out.println("2：查询两个单词之间的桥接词");
		System.out.println("3：根据桥接词补充文本");
		System.out.println("4：查询单词之间的最短路径");
		System.out.println("5：随机遍历");
		System.out.println("9：结束程序");
	}
	
	
}
