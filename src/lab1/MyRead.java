package lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MyRead {
	private static StringBuilder str = new StringBuilder();
	private static List<String> Arr = new ArrayList<String>();
	
	public  static List<String> read(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入文件名：");
		String FileName = scanner.next();
		File file = new File(FileName);
		//scanner.close();
		try{
			FileInputStream out = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(out);
			
			int ch = 0;
			while(true)
			{
				ch = isr.read();
				if(ch >= 'A' && ch <= 'Z')
				{
					ch += 32;
					str.append((char)ch);
				}
				else if(ch >= 'a' && ch <= 'z')
				{
					str.append((char)ch);
				}
				else if((ch == ' ' || ch == '\n') && str != null && str.length() != 0)
				{
					Arr.add(str.toString());
					str.delete(0, str.length());
				}	
				else if(ch == -1)
				{
					if(str != null && str.length() != 0) {Arr.add(str.toString()); str.delete(0, str.length());}
					break;
				}	
			}
			isr.close();
			
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		finally{}
		return Arr;
	}
}
