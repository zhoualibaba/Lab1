package lab_1;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.Runnable;
import java.lang.Thread;
import lab_1.digraph;
import lab_1.digraph.VNode;
import lab_1.digraph.ENode;

// B23
public class randomWalk {
	public static String randomw() {
		Walk walk = new Walk();
		// 两个线程来实现用户停止
		System.out.println("输入任意非'1'单字符结束随机遍历");
		walk.go.start();			// 随机遍历线程
		walk.stop.start();			// 用户停止线程
		// 判断线程是否结束
		try{
			walk.go.join();
			walk.stop.join();
		}catch(Exception e){
			System.out.println("线程未正常结束");
		}
		String result = Walk.randomresult;
		return result;
		}
}
class Walk implements Runnable {
	int sleeptime = 1100,giveflag = 0;
	int i,flag = 1,mVexslen,edgeslen,vexnum,edgenum,edgenumran;
	static String randomresult = new String();
	
	ENode vrandom;
	Thread go,stop;
	
	Walk() {
		go = new Thread(this);
		stop = new Thread(this);
	}

	VNode[] mVexs = digraph.mVexs;  // 有向图的邻接表
	public void run() {
		
		// 判断线程
		// 随机遍历线程
		int visit[][] = new int[1000][1000];
		if (Thread.currentThread() == go){
			// 实现随机遍历
			// 初始单词的随机遍历
			mVexslen = mVexs.length;
			vexnum = (int) (Math.random() * mVexslen); 			// 随机一个初始顶点
			System.out.print(mVexs[vexnum].data);
			randomresult += mVexs[vexnum].data;
			while(flag == 1){
			// 后续单词的随机遍历
				if(mVexs[vexnum].total == 0){   // 判断是否为结尾单词
					flag = 0;
				}else{
					edgeslen = mVexs[vexnum].total;
					edgenumran = (int) (Math.random() * edgeslen); // 随机一个边的个数
					vrandom = mVexs[vexnum].firstEdge;
					if(visit[vexnum][vrandom.ivex] == 1){
						giveflag = 2;
						System.out.print(" " + mVexs[vrandom.ivex].data);
						randomresult += (" " + mVexs[vrandom.ivex].data);
						flag = 0;
						continue;
					}
					for(i = 1;i < edgenumran;i++){
						vrandom = vrandom.nextEdge;
					}
					visit[vexnum][vrandom.ivex] = 1;
					System.out.print(" " + mVexs[vrandom.ivex].data);
					randomresult += (" " + mVexs[vrandom.ivex].data);
					vexnum = vrandom.ivex;
				}
				// 随机遍历线程休息1.1秒
				try {
					Thread.sleep(sleeptime);
				} catch (Exception e) {
					System.out.println("判断线程休息失败");
				}
			}
			// 文件写入
			if(flag == 0){
				FileWriter fw;
				try {
					System.out.println("\n 随机遍历结束输入任意字符返回菜单");
					fw = new FileWriter ("C:/Users/zhou/Desktop/out.txt",false);
					fw.write(randomresult);
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 用户结束线程
		if (Thread.currentThread() == stop) {
			char stopnum = '1';
			try {
				stopnum = (char)System.in.read();
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
			if(stopnum != '1'){
				System.out.println("结束随机遍历");
				flag = 0;
			}
		}
	}
}
