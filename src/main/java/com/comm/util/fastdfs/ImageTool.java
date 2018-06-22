package com.comm.util.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.csource.fastdfs.FileInfo;

import com.comm.util.MapUtils;
import com.comm.util.file.FileUtil;

public class ImageTool {

	public static void main(String[] args) {

		String img_path = "/root/img_01.jpg";

		// FastDFSUtil.compressImage("/root/img_01.jpg", "/root/img_01_111.jpg",
		// "jpg");

		// ImageTool.portraitHandle(img_path);

		for (int i = 1; i < 2; i++) {

			if (i < 10) {
				String a = "0" + i;
				img_path = "/root/img_" + a + ".jpg";
			} else {
				img_path = "/root/img_" + i + ".jpg";
			}

			ImageTool.caseHandle(img_path);

			// ImageTool.portraitHandle(img_path);
		}

		// ImageTool.caseHandle(img_path);

		// ImageTool.caseHandle("/opt/zyb.jpg");

		// ImageTool.portraitHandle(img_path);

		// float sd = 1321.3f;

		// double d = (double) Math.round(sd * 100 / 100.0);

		/*
		 * System.out.println("as=" + ImageTool.foramtNumber(1000.0, 750.0, 2));
		 */

	}

	public static final String DYNAMIC_SCENE = "d";// 动态图片
	public static final String TERMINAL_SCENE = "t";// 终端/列表图片
	public static final String PORTRAIT_SCENE = "p";// 头像/认证图片

	public static final int D_REC_MAX = 400; // 动态图片的最大值
	public static final int D_REC_MIN = 200; // 动态图片的最小值

	// 步骤 1-质量压缩 （必须）
	// 步骤 2-剪裁 （可选的步骤）如果尺寸在范围内，则可以不做此步骤
	// 步骤 3-缩略图压缩 （必须）

	/**
	 * 
	 * 1-文库 2-话题 3-评论
	 * 
	 * @param srcPath
	 *            return Map { height=2336, width=4160, originalTime=2015:01:21
	 *            12:15:33,
	 *            filePath=/public/2018/06/02/wKgBMFUYnLqAPC71AAZ64WSrXlk978.jpg,
	 *            fileSize=424673 }
	 */

	@SuppressWarnings("resource")
	public static Map<String, Object> caseHandle(String srcPath) {

		Map<String, Object> returnMap = new HashMap<String, Object>();

		try {

			File srcFile = new File(srcPath);

			System.out.println("caseHandle   srcFile=" + srcFile.getName());

			String extName = srcFile.getName().substring(srcFile.getName().lastIndexOf(".") + 1);

			FileInputStream fis = new FileInputStream(srcFile);
			byte[] file_buff = null;
			if (fis != null) {
				int len = fis.available();
				file_buff = new byte[len];
				fis.read(file_buff);
			}

			FastDFSFile src_fastDfsfile = new FastDFSFile(null, file_buff, extName);

			// 原始文件上传
			String[] uploadResults = FastDFSUtil.upload(src_fastDfsfile, "520");

			System.out.println("caseHandle   uploadResults=" + uploadResults);

			String groupName = uploadResults[0];
			String remoteFileName = uploadResults[1];

			returnMap = FastDFSUtil.getImageInfo(srcPath);

			// 文件路径
			returnMap.put("filePath", groupName + "/" + remoteFileName);

			System.out.println("groupName=" + groupName);
			System.out.println("remoteFileName=" + remoteFileName);

			// 需要存储组名和文件名
			FileInfo fileInfo = FastDFSUtil.getFile(groupName, remoteFileName);

			System.out.println("fileInfo=" + MapUtils.beanToMap(fileInfo));

			// 文件路径
			returnMap.put("fileSize", fileInfo.getFileSize());

			// 原始文件压缩并上传

			System.out.println("上传的原始文件为：" + srcPath);

			String dest_compress_path = srcPath.replace(FastDFSUtil.DIAN_SEPARATOR,
					FastDFSUtil.UNDERLINE_SEPARATOR + FastDFSUtil.COMPRESS + FastDFSUtil.DIAN_SEPARATOR);

			System.out.println("本地75%压缩的文件为：" + dest_compress_path);

			FastDFSUtil.compressImage(srcPath, dest_compress_path, extName);

			FastDFSUtil.uploadRef(uploadResults, dest_compress_path,
					FastDFSUtil.UNDERLINE_SEPARATOR + FastDFSUtil.COMPRESS, extName);

			int originalWidth = 0;
			if (returnMap.containsKey("width")) {
				originalWidth = Integer.parseInt(returnMap.get("width").toString());
				System.out.println("originalWidth=" + originalWidth);
			}
			int originalHeight = 0;
			if (returnMap.containsKey("height")) {
				originalHeight = Integer.parseInt(returnMap.get("height").toString());
				System.out.println("originalHeight=" + originalHeight);
			}

			// 处理在终端、列表场景显示的图片
			handle_terminal_scene_normal(uploadResults, dest_compress_path, originalWidth, originalHeight, extName);

			// String destPath_900_600=dyna[0];
			// 处理在动态场景显示的图片
			System.out.println("---------dest_compress_path=" + dest_compress_path);
			String[] dyna = handle_special(uploadResults, dest_compress_path, originalWidth, originalHeight, D_REC_MIN,
					D_REC_MAX, extName, DYNAMIC_SCENE);

			returnMap.put("dynaWidth", dyna[1]);
			returnMap.put("dynaHeight", dyna[2]);

			FileUtil.deleteFile(srcPath);
			FileUtil.deleteFile(dest_compress_path);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("returnMap=" + returnMap);
		return returnMap;
	}

	@SuppressWarnings("resource")
	public static Map<String, Object> portraitHandle(String srcPath) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			File srcFile = new File(srcPath);
			System.out.println("caseHandle   srcFile=" + srcFile.getName());
			String extName = srcFile.getName().substring(srcFile.getName().lastIndexOf(".") + 1);
			FileInputStream fis = new FileInputStream(srcFile);
			byte[] file_buff = null;
			if (fis != null) {
				int len = fis.available();
				file_buff = new byte[len];
				fis.read(file_buff);
			}
			FastDFSFile src_fastDfsfile = new FastDFSFile(null, file_buff, extName);
			// 原始文件上传
			String[] uploadResults = FastDFSUtil.upload(src_fastDfsfile, "520");
			System.out.println("caseHandle   uploadResults=" + uploadResults);
			String groupName = uploadResults[0];
			String remoteFileName = uploadResults[1];
			returnMap = FastDFSUtil.getImageInfo(srcPath);
			// 文件路径
			returnMap.put("filePath", groupName + "/" + remoteFileName);
			System.out.println("groupName=" + groupName);
			System.out.println("remoteFileName=" + remoteFileName);

			// 需要存储组名和文件名
			FileInfo fileInfo = FastDFSUtil.getFile(groupName, remoteFileName);
			System.out.println("fileInfo=" + MapUtils.beanToMap(fileInfo));
			// 文件路径
			returnMap.put("fileSize", fileInfo.getFileSize());
			// 原始文件压缩并上传
			String dest_compress_path = srcPath.replace(FastDFSUtil.DIAN_SEPARATOR,
					FastDFSUtil.UNDERLINE_SEPARATOR + FastDFSUtil.COMPRESS + FastDFSUtil.DIAN_SEPARATOR);

			FastDFSUtil.compressImage(srcPath, dest_compress_path, extName);

			FastDFSUtil.uploadRef(uploadResults, dest_compress_path, "_c", extName);

			int originalWidth = 0;
			if (returnMap.containsKey("width")) {
				originalWidth = Integer.parseInt(returnMap.get("width").toString());
				System.out.println("originalWidth=" + originalWidth);
			}
			int originalHeight = 0;
			if (returnMap.containsKey("height")) {
				originalHeight = Integer.parseInt(returnMap.get("height").toString());
				System.out.println("originalHeight=" + originalHeight);
			}

			// 变更为压缩后的图片，准备对此图片进行剪裁、缩略图、水印等
			/*
			 * srcPath = srcPath.replace(FastDFSUtil.DIAN_SEPARATOR,
			 * FastDFSUtil.UNDERLINE_SEPARATOR + FastDFSUtil.COMPRESS +
			 * FastDFSUtil.DIAN_SEPARATOR);
			 */

			// 处理在终端、列表场景显示的图片

			String[] dyna = handle_portrait_scene_normal(dest_compress_path, uploadResults, originalWidth,
					originalHeight, extName);

			returnMap.put("dynaWidth", dyna[1]);
			returnMap.put("dynaHeight", dyna[1]);

			System.out.println("returnMap=" + returnMap);

			FileUtil.deleteFile(srcPath);
			FileUtil.deleteFile(dest_compress_path);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnMap;

	}

	/**
	 * 处理终端及列表图片（适用于终端页面、列表页面等）
	 * 
	 * 1-按900*600剪裁或缩放（不考虑600*900的可能性）， 然后等比缩放600*400，300*200，225*150
	 * 
	 * 2-按200*200剪裁或缩放， 然后等比缩放150*150，75*75
	 * 
	 * @param srcPath
	 * @param src_uploadResults
	 * @param originalWidth
	 * @param originalHeight
	 * @return
	 */
	private static String[] handle_terminal_scene_normal(String[] uploadResults, String srcPath, int originalWidth,
			int originalHeight, String extName) {

		// 第一张图片的处理（最大规格的900*600），其他规格从此文件生成
		// c_t_sp

		String[] dyna = null;

		handle_special(uploadResults, srcPath, originalWidth, originalHeight, 600, 900, extName, TERMINAL_SCENE);

		handle_special(uploadResults, srcPath, originalWidth, originalHeight, 400, 600, extName, TERMINAL_SCENE);

		handle_special(uploadResults, srcPath, originalWidth, originalHeight, 200, 300, extName, TERMINAL_SCENE);

		handle_special(uploadResults, srcPath, originalWidth, originalHeight, 150, 225, extName, TERMINAL_SCENE);

		handle_special(uploadResults, srcPath, originalWidth, originalHeight, 200, 200, extName, TERMINAL_SCENE);

		handle_special(uploadResults, srcPath, originalWidth, originalHeight, 150, 150, extName, TERMINAL_SCENE);

		handle_special(uploadResults, srcPath, originalWidth, originalHeight, 75, 75, extName, TERMINAL_SCENE);

		return dyna;

	}

	private static String[] handle_portrait_scene_normal(String srcPath, String[] uploadResults, int originalWidth,
			int originalHeight, String extName) {

		// 第一张图片的处理（最大规格的300*300），其他规格从此文件生成
		String[] dyna = null;

		try {
			dyna = handle_special(uploadResults, srcPath, originalWidth, originalHeight, 300, 300, extName,
					PORTRAIT_SCENE);

			handle_special(uploadResults, srcPath, originalWidth, originalHeight, 150, 150, extName, PORTRAIT_SCENE);

			handle_special(uploadResults, srcPath, originalWidth, originalHeight, 100, 100, extName, PORTRAIT_SCENE);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dyna;

	}

	private static String[] handle_special(String[] uploadResults, String srcPath, int originalWidth,
			int originalHeight, int limitLow, int limitHigh, String extName, String secneFlag) {

		String[] s = new String[3];
		try {

			String returnWidth = "";
			String returnHeight = "";

			Map<String, Object> a = null;

			if (secneFlag.equalsIgnoreCase(DYNAMIC_SCENE)) {
				a = getParamDynamic(originalWidth, originalHeight, limitLow, limitHigh);
			} else if (secneFlag.equalsIgnoreCase(TERMINAL_SCENE)) {
				a = getParamTerminal(originalWidth, originalHeight, limitHigh, limitLow);
			} else if (secneFlag.equalsIgnoreCase(PORTRAIT_SCENE)) {
				a = getParamSquare(originalWidth, originalHeight, limitLow);
			}

			System.out.println("=====Map a=" + a);

			Boolean isZoom = (Boolean) a.get("isZoom");
			Boolean isCrop = (Boolean) a.get("isCrop");

			int zoomWidth = (Integer) a.get("zoomWidth");
			int zoomHeight = (Integer) a.get("zoomHeight");

			int x_start = 0;
			int cropWidth = (Integer) a.get("cropWidth");
			int y_start = 0;
			int cropHeight = (Integer) a.get("cropHeight");

			System.out.println("originalWidth=" + originalWidth);
			System.out.println("originalHeight=" + originalHeight);

			System.out.println("cropWidth=" + cropWidth);
			System.out.println("cropHeight=" + cropHeight);

			System.out.println("isZoom=" + isZoom);
			System.out.println("isCrop=" + isCrop);

			String destPath = "";
			String destPath_temp = "";
			if (isZoom && isCrop) {

				System.out.println("zoom的srcPath本地文件为：" + srcPath);

				destPath_temp = srcPath.replace(FastDFSUtil.DIAN_SEPARATOR,
						"_temp" + FastDFSUtil.UNDERLINE_SEPARATOR + secneFlag + FastDFSUtil.DIAN_SEPARATOR);
				System.out.println("zoom的本地文件为：temp=" + destPath_temp);

				FastDFSUtil.zoomImage(zoomWidth, zoomHeight, srcPath, destPath_temp, zoomWidth, zoomHeight, extName);

				destPath = destPath_temp.replace("_temp", "");

				System.out.println("-----replace Temp件为： dest=" + destPath);

				destPath = returnS(secneFlag, destPath, limitHigh, limitLow);

				System.out.println("-----replace end 本地文件为： dest=" + destPath);

				FastDFSUtil.cropImage(destPath_temp, destPath, x_start, y_start, cropWidth, cropHeight, extName);

			} else if (isZoom && !isCrop) {

				destPath_temp = srcPath.replace(FastDFSUtil.DIAN_SEPARATOR,
						"_temp" + FastDFSUtil.UNDERLINE_SEPARATOR + secneFlag + FastDFSUtil.DIAN_SEPARATOR);

				System.out.println("case 2_zoom的本地文件1为：temp=" + destPath_temp);

				destPath = destPath_temp.replace("_temp", "");

				System.out.println("case 2__zoom的本地文件2为：destPath=" + destPath);

				destPath = returnS(secneFlag, destPath, limitHigh, limitLow);

				System.out.println("case 2__zoom的本地文件3为：destPath=" + destPath);

				FastDFSUtil.zoomImage(zoomWidth, zoomHeight, srcPath, destPath, zoomWidth, zoomHeight, extName);

			} else if (!isZoom && isCrop) {

				destPath_temp = srcPath.replace(FastDFSUtil.DIAN_SEPARATOR,
						"_temp" + FastDFSUtil.UNDERLINE_SEPARATOR + secneFlag + FastDFSUtil.DIAN_SEPARATOR);

				System.out.println("case 3__zoom的本地文件为：temp=" + destPath_temp);

				destPath = destPath_temp.replace("_temp", "");

				System.out.println("case 3__zoom的本地文件为：temp replace=" + destPath);

				destPath = returnS(secneFlag, destPath, limitHigh, limitLow);

				System.out.println("case 3__zoom的本地文件为：temp result=" + destPath);

				FastDFSUtil.cropImage(srcPath, destPath, x_start, y_start, cropWidth, cropHeight, extName);

			}

			System.out.println("单一文件在动态的文件为：" + destPath);

			// /root/IMG_3279_c_t_sp.JPG

			if (secneFlag.equalsIgnoreCase(DYNAMIC_SCENE)) {
				FastDFSUtil.uploadRef(uploadResults, destPath, "_c_" + secneFlag + "_sp", extName);

			} else if (secneFlag.equalsIgnoreCase(TERMINAL_SCENE)) {
				FastDFSUtil.uploadRef(uploadResults, destPath, "_c_" + secneFlag + "_" + limitHigh + "_" + limitLow,
						extName);

			} else if (secneFlag.equalsIgnoreCase(PORTRAIT_SCENE)) {
				FastDFSUtil.uploadRef(uploadResults, destPath, "_c_" + secneFlag + "_" + limitLow + "_" + limitLow,
						extName);

			}

			System.out.println("destPath=" + destPath);

			Map<String, Object> imageMap = FastDFSUtil.getImageInfo(destPath);

			returnWidth = imageMap.get("width").toString();
			returnHeight = imageMap.get("height").toString();

			s[0] = destPath;
			s[1] = returnWidth;
			s[2] = returnHeight;

			for (int i = 0; i < s.length; i++) {
				System.out.println(" <<<<<<<<<< s[" + i + "]=" + s[i]);
			}

			FileUtil.deleteFile(destPath);
			FileUtil.deleteFile(destPath_temp);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;

	}

	private static String returnS(String secneFlag, String destPath, int width, int height) {

		String returnString = "";

		System.out.println("=======returnString secneFlag=" + secneFlag);
		// System.out.println("=======returnString destPath="+destPath);

		try {
			if (secneFlag.equalsIgnoreCase(DYNAMIC_SCENE)) {
				returnString = destPath.replace("_c_d.", "_c_d_sp.");
			} else if (secneFlag.equalsIgnoreCase(TERMINAL_SCENE)) {
				returnString = destPath.replace("_c_t.", "_c_t_" + width + "_" + height + ".");
			} else if (secneFlag.equalsIgnoreCase(PORTRAIT_SCENE)) {
				returnString = destPath.replace("_c_p.", "_c_p_300_300.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("=======returnString=" + returnString);

		return returnString;
	}
	
	private static double foramtNumber(double d1, double d2, int len) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	private static Map<String, Object> getParamDynamic(int originalWidth, int originalHeight, int limitLow,
			int limitHigh) {

		Map<String, Object> returnMap = new HashMap<String, Object>();

		int case_scene = 0;

		boolean isZoom = Boolean.FALSE;
		boolean isCrop = Boolean.FALSE;

		int zoomWidth = 0;// 处理后的宽度
		int zoomHeight = 0;// 处理后的高度

		int cropWidth = 0;
		int cropHeight = 0;

		if (originalWidth >= limitHigh) {
			// 宽度>=max
			if (originalHeight >= limitHigh) {
				// 高度>=max
				case_scene = 1;
			} else if (originalHeight <= limitLow) {
				// 高度<=min
				case_scene = 2;
			} else {
				// 高度介于min-max
				case_scene = 3;
			}

		} else if (originalWidth < limitLow) {
			// 宽度<max
			if (originalHeight >= limitHigh) {
				// 高度>=max
				case_scene = 4;
			} else if (originalHeight <= limitLow) {
				// 高度<=min
				case_scene = 5;
			} else {
				// 高度介于min-max
				case_scene = 6;
			}
		} else {
			// 宽度介于min-max
			if (originalHeight >= limitHigh) {
				// 高度>=max
				case_scene = 7;
			} else if (originalHeight <= limitLow) {
				// 高度<=min
				case_scene = 8;
			} else {
				// 高度介于min-max
				case_scene = 9;
			}
		}

		switch (case_scene) {

		case 1:// 4000*1000

			if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) >= 2) { // 4000*1000
				zoomWidth = 0;
				zoomHeight = limitLow;

				cropWidth = limitHigh;
				cropHeight = limitLow;

				isZoom = Boolean.TRUE;
				isCrop = Boolean.TRUE;

			} else if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) > 1
					&& ImageTool.foramtNumber(originalWidth, originalHeight, 2) < 2) {
				zoomWidth = limitHigh;
				zoomHeight = 0;

				isZoom = Boolean.TRUE;
				isCrop = Boolean.FALSE;

			} else if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) == 1) {

				zoomWidth = 300;
				zoomHeight = 300;

				isZoom = Boolean.TRUE;
				isCrop = Boolean.FALSE;

			} else if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) > 0.5
					&& ImageTool.foramtNumber(originalWidth, originalHeight, 2) < 1) {

				zoomWidth = 0;
				zoomHeight = limitHigh;

				isZoom = Boolean.TRUE;
				isCrop = Boolean.FALSE;

			} else if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) <= 0.5) {

				zoomWidth = limitLow;
				zoomHeight = 0;

				cropWidth = limitLow;
				cropHeight = limitHigh;

				isZoom = Boolean.TRUE;
				isCrop = Boolean.TRUE;

			}

			break;
		case 2:// 4000*100 -> 400*100

			// 先缩放成为 400*100
			zoomWidth = 0; // 400
			zoomHeight = 0; // 100

			// 再剪裁成为 0，0—— 400，100
			cropWidth = limitHigh;
			cropHeight = limitLow;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;
		case 3:// 4000*300 - 400*300

			if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) >= 2) { // 4000*1000
				zoomWidth = 0;
				zoomHeight = limitLow;

				cropWidth = limitHigh;
				cropHeight = limitLow;

				isZoom = Boolean.TRUE;
				isCrop = Boolean.TRUE;

			} else if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) > 1
					&& ImageTool.foramtNumber(originalWidth, originalHeight, 2) < 2) {// 1000*4000
				zoomWidth = limitHigh;
				zoomHeight = 0;

				isZoom = Boolean.TRUE;
				isCrop = Boolean.FALSE;

			} else if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) < 1) {

				// not enter

				isZoom = Boolean.FALSE;
				isCrop = Boolean.FALSE;

			}

			break;
		case 4:// 100*4000
				// 先缩放成为 400*100
			zoomWidth = 0; // 400
			zoomHeight = 0; // 100

			// 再剪裁成为 0，0—— 400，100
			cropWidth = limitLow;
			cropHeight = limitHigh;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;
		case 5:// 10*10

			cropWidth = originalWidth;
			cropHeight = originalHeight;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;
		case 6:// 10*700
			cropWidth = originalWidth;
			cropHeight = originalHeight;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;
		case 7:// 700*4000

			if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) >= 1) { // 4000*1000
				// not enter

				isZoom = Boolean.FALSE;
				isCrop = Boolean.FALSE;

			} else if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) > 0.5
					&& ImageTool.foramtNumber(originalWidth, originalHeight, 2) < 1) {
				zoomWidth = 0;
				zoomHeight = limitHigh;

				isZoom = Boolean.TRUE;
				isCrop = Boolean.FALSE;

			} else if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) <= 0.5) {

				zoomWidth = limitLow;
				zoomHeight = 0;

				cropWidth = limitLow;
				cropHeight = limitHigh;

				isZoom = Boolean.TRUE;
				isCrop = Boolean.FALSE;

			}

			break;
		case 8:// 700*10

			cropWidth = originalWidth;
			cropHeight = originalHeight;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;
		case 9:// 700*700

			cropWidth = originalWidth;
			cropHeight = originalHeight;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;

		default:

		}

		returnMap.put("caseScene", case_scene);

		returnMap.put("isZoom", isZoom);
		returnMap.put("isCrop", isCrop);

		returnMap.put("zoomWidth", zoomWidth);
		returnMap.put("zoomHeight", zoomHeight);

		returnMap.put("cropWidth", cropWidth);
		returnMap.put("cropHeight", cropHeight);

		return returnMap;

	}

	private static Map<String, Object> getParamSquare(int originalWidth, int originalHeight, int limitLow) {

		Map<String, Object> returnMap = new HashMap<String, Object>();

		int case_scene = 0;

		boolean isZoom = Boolean.FALSE;
		boolean isCrop = Boolean.FALSE;

		int zoomWidth = 0;// 处理后的宽度
		int zoomHeight = 0;// 处理后的高度

		int cropWidth = 0;
		int cropHeight = 0;

		// 0: destWidth MAX_HIGH (400 200)

		if (originalWidth >= limitLow) {
			// 宽度>=max
			if (originalHeight >= limitLow) {
				// 高度>=max
				case_scene = 1;
			} else if (originalHeight < limitLow) {
				// 高度<=min
				case_scene = 2;
			}

		} else if (originalWidth < limitLow) {

			// 宽度<max
			if (originalHeight >= limitLow) {
				// 高度>=max
				case_scene = 3;
			} else if (originalHeight < limitLow) {
				// 高度<=min
				case_scene = 4;
			}
		}
		switch (case_scene) {

		case 1:// 4000*1000

			if (originalWidth >= originalHeight) {
				zoomWidth = limitLow;
				zoomHeight = 0;
			} else if (originalWidth < originalHeight) {
				zoomWidth = 0;
				zoomHeight = limitLow;
			}

			isZoom = Boolean.TRUE;
			isCrop = Boolean.FALSE;

			break;
		case 2:// 4000*100 -> 400*100

			if (originalWidth >= originalHeight) {
				zoomWidth = limitLow;
				zoomHeight = 0;
			} else if (originalWidth < originalHeight) {
				zoomWidth = 0;
				zoomHeight = limitLow;
			}

			isZoom = Boolean.TRUE;
			isCrop = Boolean.FALSE;

			break;
		case 3:// 4000*300 - 400*300

			if (originalWidth >= originalHeight) {
				zoomWidth = limitLow;
				zoomHeight = 0;
			} else if (originalWidth < originalHeight) {
				zoomWidth = 0;
				zoomHeight = limitLow;
			}

			isZoom = Boolean.TRUE;
			isCrop = Boolean.FALSE;

			break;
		case 4:// 100*4000

			cropWidth = originalWidth;
			cropHeight = originalHeight;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;

		default:

		}

		returnMap.put("isZoom", isZoom);
		returnMap.put("isCrop", isCrop);

		returnMap.put("zoomWidth", zoomWidth);
		returnMap.put("zoomHeight", zoomHeight);

		returnMap.put("cropWidth", cropWidth);
		returnMap.put("cropHeight", cropHeight);

		return returnMap;

	}

	private static Map<String, Object> getParamTerminal(int originalWidth, int originalHeight, int limitX, int limitY) {

		Map<String, Object> returnMap = new HashMap<String, Object>();

		int case_scene = 0;

		boolean isZoom = Boolean.FALSE;
		boolean isCrop = Boolean.FALSE;

		int zoomWidth = 0;// 处理后的宽度
		int zoomHeight = 0;// 处理后的高度

		int cropWidth = 0;
		int cropHeight = 0;

		// 0: destWidth MAX_HIGH (400 200)

		if (originalWidth >= limitX) {
			// 宽度>=max
			if (originalHeight >= limitY) {
				// 高度>=max
				case_scene = 1;
			} else if (originalHeight < limitY) {
				// 高度<=min
				case_scene = 2;
			}

		} else if (originalWidth < limitX) {

			// 宽度<max
			if (originalHeight >= limitY) {
				// 高度>=max
				case_scene = 3;
			} else if (originalHeight < limitY) {
				// 高度<=min
				case_scene = 4;
			}
		}
		switch (case_scene) {

		case 1:// 4000*1000

			System.out.println(">><<<<>>>>>>originalWidth=" + originalWidth);
			System.out.println(">><<<<>>>>>>originalHeight=" + originalHeight);
			System.out.println(">><<<<>>>>>>limitX=" + limitX);
			System.out.println(">><<<<>>>>>>limitY=" + limitY);

			System.out.println(">><<<<>>>>>>originalWidth/originalHeight="
					+ ImageTool.foramtNumber(originalWidth, originalHeight, 2));
			System.out.println(">><<<<>>>>>>limitX/limitY=" + ImageTool.foramtNumber(limitX, limitY, 2));

			if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) >= ImageTool.foramtNumber(limitX, limitY, 2)) {
				zoomWidth = 0;
				zoomHeight = limitY;
			} else if (ImageTool.foramtNumber(originalWidth, originalHeight, 2) < ImageTool.foramtNumber(limitX, limitY,
					2)) {
				zoomWidth = limitX;
				zoomHeight = 0;
			}

			cropWidth = limitX;
			cropHeight = limitY;

			isZoom = Boolean.TRUE;
			isCrop = Boolean.TRUE;

			break;
		case 2:// 4000*100 -> 400*100

			cropWidth = limitX;
			cropHeight = limitY;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;
		case 3:// 4000*300 - 400*300

			cropWidth = limitX;
			cropHeight = limitY;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;
		case 4:// 100*4000

			cropWidth = originalWidth;
			cropHeight = originalHeight;

			isZoom = Boolean.FALSE;
			isCrop = Boolean.TRUE;

			break;

		default:

		}

		returnMap.put("isZoom", isZoom);
		returnMap.put("isCrop", isCrop);

		returnMap.put("zoomWidth", zoomWidth);
		returnMap.put("zoomHeight", zoomHeight);

		returnMap.put("cropWidth", cropWidth);
		returnMap.put("cropHeight", cropHeight);

		return returnMap;

	}

}
