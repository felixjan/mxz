package com.comm.util.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String res = downloadFromUrl("http://192.168.1.49/public1/M00/00/1C/wKgBL1UcuWmALIZ5AAA3iNdKmC8904.jpg","/opt/");
		System.out.println(res);
	}

	public static boolean deleteFile(String filePath) {
		boolean returnBool = Boolean.FALSE;
		File file = new File(filePath);
		// 删除原始文件
		if (file != null && file.isFile()) {
			returnBool = file.delete();
		}
		return returnBool;

	}

	public static String downloadFromUrl(String url, String dir) {
		try {
			URL httpurl = new URL(url);
			String fileName = getFileNameFromUrl(url);
			System.out.println(fileName);
			File f = new File(dir + fileName);
			FileUtils.copyURLToFile(httpurl, f);
		} catch (Exception e) {
			e.printStackTrace();
			return "Fault!";
		}
		return "Successful!";
	}

	public static String getFileNameFromUrl(String url) {
		String name = new Long(System.currentTimeMillis()).toString() + ".X";
		int index = url.lastIndexOf("/");
		if (index > 0) {
			name = url.substring(index + 1);
			if (name.trim().length() > 0) {
				return name;
			}
		}
		return name;
	}

	/**
	 * 文件上传到后台服务器
	 * 
	 * @return
	 */

	public static void uploadFileToServer(File srcFile, File destFile) {
		// 基于myFile创建一个文件输入流
		InputStream is = null;
		// 创建一个输出流
		OutputStream os = null;
		try {
			is = new FileInputStream(srcFile);
			os = new FileOutputStream(destFile);
			// 设置缓存
			byte[] buffer = new byte[1024];
			int length = 0;
			// 读取myFile文件输出到toFile文件中
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭输入流
				is.close();
				// 关闭输出流
				os.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("上传用户"+username);
		// System.out.println("上传文件名"+myFileFileName);
		// System.out.println("上传文件类型"+myFileContentType);

	}

	public static void getSize(String fileName) {
		try {
			ImageIcon icon = new ImageIcon(fileName);
			icon.getIconHeight();
			icon.getIconWidth();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static byte[] getFileInput(String fileName) {
		// 声明流对象
		FileInputStream fis = null;
		byte[] data = new byte[1024 * 1024 * 1024]; // 数据存储的数组
		try {
			// 创建流对象
			fis = new FileInputStream(fileName);
			// 读取数据，并将读取到的数据存储到数组中
			int i = 0; // 当前下标
			// 读取流中的第一个字节数据
			int n = fis.read();
			// 依次读取后续的数据
			while (n != -1) { // 未到达流的末尾
				// 将有效数据存储到数组中
				data[i] = (byte) n;
				// 下标增加
				i++;
				// 读取下一个字节的数据
				n = fis.read();
			}
			// 解析数据
			//String s = new String(data, 0, i);
			// 输出字符串
			// System.out.println(s);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流，释放资源
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;

	}

	public static List<String> showAllFiles(File dir) {
		List<String> a = new ArrayList<String>();
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			System.out.println(fs[i].getAbsolutePath());
			a.add(fs[i].getAbsolutePath());
			if (fs[i].isDirectory()) {
				try {
					a.addAll(showAllFiles(fs[i]));
				} catch (Exception e) {
				}
			}
		}
		return a;
	}

	@SuppressWarnings("resource")
	public static byte[] readFileImage(File file) {
		byte[] bytes = null;
		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					new FileInputStream(file));
			int len = bufferedInputStream.available();
			bytes = new byte[len];
			int r = bufferedInputStream.read(bytes);

			if (len != r) {
				bytes = null;
				throw new IOException("读取文件不正确");
			}
			bufferedInputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

}
