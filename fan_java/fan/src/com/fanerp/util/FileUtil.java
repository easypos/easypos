package com.fanerp.util;

import java.io.*;

public class FileUtil {
	/**
	 * 
	 * @param fromFile
	 * @param toFile
	 */
	public static void copy(String fromFile, String toFile) throws Exception {
		try {
			File inFile = new File(fromFile);
			File outFile = new File(toFile);
			FileInputStream in1 = new FileInputStream(inFile);
			FileOutputStream out1 = new FileOutputStream(outFile);

			byte[] bytes = new byte[1024];
			int c;
			while ((c = in1.read(bytes)) != -1) {
				out1.write(bytes, 0, c);

			}
			in1.close();
			out1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param sourceDir
	 * @param targetDir
	 * @throws Exception
	 */
	public static void copyDirectiory(String source, String target)
			throws Exception {
		(new File(target)).mkdirs();
		File file1 = new File(source);
		if (file1.isFile()) {
			copy(source, target + "/" + file1.getName());
		} else {
			// target=target+"/"+file1.getName();
			File[] file = (file1).listFiles();
			if (file == null)
				return;
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					FileInputStream input = new FileInputStream(file[i]);
					File outFile = new File(target + "/" + file[i].getName());
					// 如果目标文件不存在,则拷贝

					System.out.println("copy..." + target + "/"
							+ file[i].getName());
					FileOutputStream output = new FileOutputStream(outFile);
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();

					input.close();
				}
				if (file[i].isDirectory()) {

					copyDirectiory(source + "/" + file[i].getName(), target
							+ "/" + file[i].getName());
				}
			}
		}

	}

	/**
	 * @param sourceDir
	 * @param targetDir
	 * @throws Exception
	 */
	public static void listDirectiory(String source, String target)
			throws Exception {
		(new File(target)).mkdirs();
		File file1 = new File(source);
		if (file1.isFile()) {
			copy(source, target + "/" + file1.getName());
		} else {
			// target=target+"/"+file1.getName();
			File[] file = (file1).listFiles();
			if (file == null)
				return;
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {					
					
					FileInputStream input = new FileInputStream(file[i]);
					File outFile = new File(target + "/" + file[i].getName());
					// 如果目标文件不存在,则拷贝

					System.out.println(file[i].getName());
					FileOutputStream output = new FileOutputStream(outFile);
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();

					input.close();
				}
				if (file[i].isDirectory()) {					
					System.out.println("==="+file[i].getName());
					listDirectiory(source + "/" + file[i].getName(), target
							+ "/" + file[i].getName());
				}
			}
		}

	}
	
	
	/**
	 * @param sourceDir
	 * @param targetDir
	 * @throws Exception
	 */
	public static void copyDirectiory_(String source, String target, String a,
			String ext) throws Exception {
		(new File(target)).mkdirs();
		File file1 = new File(source);
		if (file1.isFile()) {
			copy(source, target + "/" + a + file1.getName() + ext);
		} else {
			// target=target+"/"+file1.getName();
			File[] file = (file1).listFiles();
			if (file == null)
				return;
			for (int i = 0; i < file.length; i++) {
				if (file[i].isFile()) {
					FileInputStream input = new FileInputStream(file[i]);
					File outFile = new File(target + "/" + a
							+ file[i].getName() + ext);
					System.out.println("如果目标文件不存在,则拷贝");

					System.out.println("copy..." + target + "/"
							+ file[i].getName() + ".png");
					FileOutputStream output = new FileOutputStream(outFile);
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();

					input.close();
				}
				if (file[i].isDirectory()) {

					copyDirectiory_(source + "/" + file[i].getName(), target,
							a, ext);
				}
			}
		}

	}

	/**
	 * 
	 * @param delpath
	 * @throws Exception
	 */
	public static void deletefile(String delpath) throws Exception {
		File file = new File(delpath);
		if (!file.isDirectory()) {
			file.delete();
			return;
		} else if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File delfile = new File(delpath + "/" + filelist[i]);
				if (delfile.isDirectory())
					deletefile(delpath + "/" + filelist[i]);
				else
					delfile.delete();
			}
			file.delete();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("hihihihi");
			deletefile("d:\\deltest");
			File file = new File("E:/test/superwebcn/SeeHotel.jsp");
			System.out.println(file.getPath());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
