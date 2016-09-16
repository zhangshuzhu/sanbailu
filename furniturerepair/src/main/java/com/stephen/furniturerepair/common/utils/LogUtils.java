package com.stephen.furniturerepair.common.utils;

import android.util.Log;

import com.stephen.furniturerepair.app.CONFIG;


/**
 * 日志类，用于统一关闭日志，或文件输出等
 *
 */
public class LogUtils {

	private static final String TAG = "--------";

	public static void I(String tag, String msg) {
		if (CONFIG.ISDEBUG) {
			Log.i(tag, msg);
		}
//		if(tag.equalsIgnoreCase("http")) {
//			I2File(msg);
//		}
	}

	public static void E(String msg) {
		if (CONFIG.ISDEBUG) {
			Log.e(LogUtils.class.getSimpleName(), TAG + msg);
		}
	}

	public static void E(String tag, String msg) {
		if (CONFIG.ISDEBUG) {
			Log.e(tag, msg);
		}
	}
	
	public static void setSystemOutToFile(){
//		File logFile = new File(PATH.getCacheDir()+"log.txt");
//		try {
//			
//			if (!logFile.exists()) {
//				logFile.createNewFile();
//			}
//			System.setOut(new PrintStream(new FileOutputStream(logFile , true)));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void I2File(String msg){
//		System.out.println(msg);
	}
	
	public static void printStackTrace(){
//		Appendable out = System.err;
//		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
//		try{
//			if(stack != null){
//				for(int i = 0;i < stack.length ;i++){
//					out.append("");
//					out.append("\tat ");
//					out.append(stack[i].toString());
//					out.append("\n");
//				}
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
	}
}
