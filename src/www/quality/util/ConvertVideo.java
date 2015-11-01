package www.quality.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.struts2.ServletActionContext;

public class ConvertVideo {

	/**
	 * 功能函数
	 * 
	 * @param inputFile
	 *            待处理视频，需带路径
	 * @param outputFile
	 *            处理后视频，需带路径
	 * @param aviPath
	 *            中间产物AVI，需要路径
	 * @return
	 * @throws IOException
	 */
	public static boolean convert(String inputFile) throws IOException {
		if (!checkfile(inputFile)) {
			System.out.println(inputFile + " is not file");
			return false;
		}
		// 去掉文件的后缀名
		String file_path = inputFile.substring(0, inputFile.lastIndexOf("."));
		// 转化后文件路径
		String output_path = file_path + ".flv";
		// 图片路径
		String img_path = file_path + ".jpg";
		String aviPath = file_path + ".avi";
		// 判断类型
		if (process(inputFile, output_path, aviPath)) {
			return true;
		}
		return false;
	}

	// 检查文件是否存在
	private static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}

	/**
	 * 转换过程 ：先检查文件类型，在决定调用 processFlv还是processAVI
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @return
	 * @throws IOException
	 */
	public static boolean process(String inputFile, String outputFile,
			String aviPath) throws IOException {
		int type = checkContentType(inputFile);
		boolean status = false;
		if (type == 0) {
			status = processFlv(inputFile, outputFile);// 直接将文件转为flv文件
		} else if (type == 1) {
			if (!processAVI(inputFile, aviPath)) {
				System.out.println("AVI转化失败！");
				return false;// avi文件没有得到
			} else {
				status = processFlv(aviPath, outputFile);// 将avi转为flv
				if (status) {
					File file = new File(aviPath);
					if (file.exists())
						file.delete();
				}
			}
		}
		return status;
	}

	/**
	 * 格式验证
	 * 
	 * @param resourcePath
	 *            文件路径
	 * @return
	 */
	private static int checkContentType(String resourcePath) {
		String type = resourcePath.substring(resourcePath.lastIndexOf(".") + 1,
				resourcePath.length()).toLowerCase();
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
		if (type.equals("avi")) {
			return 0;
		} else if (type.equals("mpg")) {
			return 0;
		} else if (type.equals("wmv")) {
			return 0;
		} else if (type.equals("3gp")) {
			return 0;
		} else if (type.equals("mov")) {
			return 0;
		} else if (type.equals("mp4")) {
			return 0;
		} else if (type.equals("asf")) {
			return 0;
		} else if (type.equals("asx")) {
			return 0;
		} else if (type.equals("flv")) {
			return 0;
		} else if (type.equals("mpeg")) {
			return 0;
		} else if (type.equals("mpe")) {
			return 0;
		}
		// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
		// 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
		else if (type.equals("wmv9")) {
			return 1;
		} else if (type.equals("rm")) {
			return 1;
		} else if (type.equals("rmvb")) {
			return 1;
		}
		return 9;
	}

	/**
	 * 把视频文件转化为FLV格式
	 * 
	 * @param input_path
	 *            文件路径
	 * @param output_path
	 *            输出路径
	 * @return
	 */
	public static boolean processFlv(String input_path, String output_path) {
		// 效验文件是否存在，自行取舍
		File file = new File(output_path);
		if (file.exists())
			file.delete();
		List<String> commend = new java.util.ArrayList<String>();
		// ffmpeg地址，不需要带.exe后缀
		commend.add(ServletActionContext.getServletContext().getRealPath("")+"/upload/tools/ffmpeg");
		commend.add("-i");
		commend.add(input_path);
		commend.add("-ar");// 视频流采样率：（大多数情况下使用44100和48000，分别对用PAL和NTSC制式，根据需要选择）
		commend.add("22050");
		commend.add("-qscale");// 视频量化指标
		commend.add("8");
		commend.add("-r");// 视频流帧数（一般来书PAL制式同常用25，ntsc制式通常用29）
		commend.add("15");
		// commend.add("-s");// 视频解析度：（分辨率）可以自己定义所需要的大小： 改变视频流的解析式很耗cpu的
		// commend.add("600x500");
		commend.add(output_path);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.redirectErrorStream(true);
			Process process = builder.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[1024];
			while (in.read(re) != -1) {
			}
			in.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 截取图片
	 * 
	 * @param input_path
	 *            文件路径
	 * @param img_path
	 *            输出图片路径
	 * @return
	 */
	public static boolean processImg(String input_path, String img_path,
			 String width, String height) {
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(ServletActionContext.getServletContext().getRealPath("")+"/upload/tools/ffmpeg");
		commend.add("-i");// 输入文件
		commend.add(input_path);
		commend.add("-y");// 覆盖输出文件
		commend.add("-f"); // 表示输出文件格式
		commend.add("image2");
		commend.add("-ss");// 表示相对于文件开始处的时间偏移值, 即从5秒出开始截图
		commend.add("1");
		commend.add("-s"); // 输出的分辨率
		commend.add(width+"*"+height);// 图片大小
		commend.add(img_path);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 转化为AVI
	 * 
	 * @param input_path
	 * @param output_path
	 * @return
	 */
	private static boolean processAVI(String input_path, String output_path) {
		File file = new File(output_path);
		if (file.exists())
			file.delete();
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(ServletActionContext.getServletContext().getRealPath("")+"/upload/tools/mencoder.exe");
		commend.add(input_path);
		commend.add("-oac"); // 声音编码方式
		commend.add("mp3lame");
		commend.add("-lameopts");
		commend.add("preset=64");
		commend.add("-ovc");
		commend.add("xvid");
		commend.add("-xvidencopts");
		commend.add("bitrate=600");
		commend.add("-of");
		commend.add("avi");
		commend.add("-o");
		commend.add(output_path);
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process p = builder.start();
			/**
			 * 清空Mencoder进程 的输出流和错误流 因为有些本机平台仅针对标准输入和输出流提供有限的缓冲区大小，
			 * 如果读写子进程的输出流或输入流迅速出现失败，则可能导致子进程阻塞，甚至产生死锁。
			 */
			final InputStream is1 = p.getInputStream();
			final InputStream is2 = p.getErrorStream();
			new Thread() {
				public void run() {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is1));
					try {
						while (br.readLine() != null) {

						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			new Thread() {
				public void run() {
					BufferedReader br2 = new BufferedReader(
							new InputStreamReader(is2));
					try {
						while (br2.readLine() != null) {
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			// 等Mencoder进程转换结束，再调用ffmepg进程
			p.waitFor();
			System.out.println("who cares");
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		convert("D:\\video\\222.mp4");
		// convert("D:\\video\\1.rmvb");
		// convert("D:\\video\\2.rmvb");
		/*
		 * int [] size=getWidthHeight("D:\\video\\kusoheji.rmvb");
		 * System.out.print("宽："+size[0]+"x"+"*****高："+size[1]+"x");
		 */
	}
}