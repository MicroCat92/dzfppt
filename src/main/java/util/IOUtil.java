package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOUtil {
	
	/**
	 * д������->redirectInfo.txt
	 */
	public static void redirectInfo(String msg) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File("redirectInfo.txt")));
			bw.write(msg);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void redirectInfo(String msg, String fileName) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(fileName)));
			bw.write(msg);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * �����Ķ�ȡΪ�ַ���
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String msgMachine(String fileName) throws Exception {
		BufferedReader br = null;
		String target = "";
		try {
			br = new BufferedReader(new FileReader(new File(fileName)));
			String msg = null;
			
			while((msg = br.readLine()) != null) {
				target += msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				br.close();
			}
		}
		return target;
	}

}
