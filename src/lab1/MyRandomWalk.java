package lab1;

import java.util.*;
import java.io.*;

public class MyRandomWalk{
	private  List<VNode> adjustList = new ArrayList<VNode>();
	
	MyRandomWalk(List<VNode> adjustList)
	{
		this.adjustList =  adjustList; 
	}
	public void setAdjustList(List<VNode> adjustList) {
		this.adjustList = adjustList;
	}
	public  String  randomWalk(){
		StopControl ctrl = new StopControl(adjustList);
		ctrl.go.start();
		ctrl.stop.start();
		try {
			ctrl.go.join();
			ctrl.stop.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String answerString = ctrl.getCompletedString();
		return answerString;
	}
}


class StopControl implements Runnable{
	boolean flagTop = true;
	private int sleepTime = 1000;
	private  List<VNode> adjustList = new ArrayList<VNode>();
	private String completedString = new String();
	public String getCompletedString() {
		return completedString;
	}
	Thread go, stop;
	
	public StopControl(List<VNode> adjustList) {
		go = new Thread(this);
		stop = new Thread(this);
		this.adjustList = adjustList;
	}
	public void run() {
		if(Thread.currentThread() == go){
			StringBuilder str = new StringBuilder();
			boolean[][] flag = new boolean[adjustList.size()][adjustList.size()];    //判断是否已经遍历的标志位
			for (int i = 0; i < adjustList.size(); i++) {
				for (int j = 0; j < adjustList.size(); j++) {
					flag[i][j] = false;
				}
			}
			int firstRandomPos = new Random().nextInt(adjustList.size());
			str.append(adjustList.get(firstRandomPos).word);
			
			int connectionNum = calculate(firstRandomPos);
			while((connectionNum != 0) && (flagTop == true)){
				int connectionRandom = new Random().nextInt(connectionNum) + 1;
				int nextPos = find(firstRandomPos, connectionRandom);
				if(flag[firstRandomPos][nextPos] == true){
					str.append(" ");
					str.append(adjustList.get(nextPos).word);
					//System.out.println("有重复的边了,打印遍历的顶点");
					flagTop = false;
					break;
				}
				flag[firstRandomPos][nextPos] = true;
				str.append(" ");
				str.append(adjustList.get(nextPos).word);
				connectionNum = calculate(nextPos);
				firstRandomPos = nextPos;
				try {
					Thread.sleep(sleepTime);
					System.out.println(str.toString());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			/*if(connectionNum == 0)
				System.out.println("没有下一条出边了,打印遍历的节点");*/
			completedString = str.toString();
			File file = new File("ansForFunc6.txt");
			try {
				file.createNewFile();      //创建文件
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//向文件写入内容(输出流)
			byte bt[] = completedString.getBytes();
			try {
				FileOutputStream in = new FileOutputStream(file);
				try {
					in.write(bt);
					in.flush();
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		if(Thread.currentThread() == stop){
			String strStoping = "quit";
			boolean flagin = true;
			Scanner scanner = new Scanner(System.in);
			while(flagin){
				if(strStoping.equals(scanner.next())) {
					System.out.println("手动结束随机游走");
					//scanner.close();
					flagTop = false;
					flagin = false;
				}else{
					System.out.println("不正确的输入,继续输入?");
				}
			}
		}
	}
	
	private  int find(int firstRandomPos, int connectionRandom){
		ENode e = adjustList.get(firstRandomPos).next;
		connectionRandom--;
		while(connectionRandom > 0){
			e = e.next;
			connectionRandom--;
		}
		return e.pos;
	}
	
	private  int calculate(int firstRandomPos){
		int cnt = 0;
		if(adjustList.get(firstRandomPos).next == null){
			return cnt;
		}else{
			ENode e = adjustList.get(firstRandomPos).next;
			++cnt;
			while (e.next != null) {
				++cnt;
				e = e.next;
			}
			return cnt;
		}
	}
	
}

