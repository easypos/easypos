package com.fanerp.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.upload.UploadListener;
import com.f1jeeframework.upload.dao.PhotoDao;
import com.f1jeeframework.upload.view.UploadUtils;
import com.f1jeeframework.workflow.impl.BpmIDGenerator;
import com.f1jframework.eform.CommonboService;

public class AjaxUploadDish extends com.f1jeeframework.http.Action {
	static String separator = "";
	static String newline = "\n";
	// private String uploadPath = "C:\\upload\\";
	// 用于存放上传文件的目录
	private String tempPath = "C:\\upload\\tmp\\";

	// 用于存放临时文件的目录
	public void afterExcute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			Exception {
		response.setContentType("text/html;charset=GBK");
		StringBuffer strBuf = new StringBuffer();
		String id = request.getParameter("id");
		String path = "dish";
		String companycode = request.getParameter("companycode");
		// 判断文件是否存在，否则建
		//String rootPath = request.getRealPath("/");
		String rootPath = AppSession.getAbsoluteUploadPath();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		factory.setSizeThreshold(10240000);
		//
		// 设置临时文件存储位置
		factory.setRepository(new File(tempPath));
		ServletFileUpload upload_ = new ServletFileUpload(factory);
		upload_.setFileSizeMax(114194304);
		upload_.setProgressListener(new UploadListener(request));
		// DiskFileUpload fu = new DiskFileUpload();
		// 设置最大文件尺寸，这里是4MB
		// fu.setSizeMax(20 * 1024 * 1024);
		// 设置缓冲区大小，这里是4kb
		// fu.setSizeThreshold(4096);
		// 设置临时目录：
		// fu.setRepositoryPath(tempPath);
		// 得到所有的文件：
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		try {
			// List fileItems = fu.parseRequest(request);
			// Iterator i = fileItems.iterator();
			List fileItems = upload_.parseRequest(request);
			Iterator i = fileItems.iterator();
			// 依次处理每一个文件：
			PhotoDao photoDao = new PhotoDao();
			photoDao.init();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				// 获得文件名，这个文件名包括路径：
				String fileName = fi.getName();
				if ((fileName != null) && !fileName.equals("")) {
					String tmp = fileName.toLowerCase();
					System.out.println("upload file," + tmp);
					if (tmp.endsWith("jsp") || tmp.endsWith("war")
							|| tmp.endsWith("jar") || tmp.endsWith("exe")
							|| tmp.endsWith("bat")) {
						// throw new
						// Exception("sorry,upload file type is not permit!");
						strBuf.append("err:不允许的文件类型");
					}
					// 在这里可以记录用户和文件信息
					// ...
					// 这里有可能写入一个空的文件,
					// fi.write(new File(filePath + getFileName(fileName)));
					String fileId = BpmIDGenerator.getID();

					String fileName_ = fileName.substring(0,
							fileName.indexOf(".")).trim();
					List ids = boService.getDao().queryForList(
							"Select  UNIVERSALID  as id from fandish where name='"
									+ fileName.substring(0,
											fileName.indexOf(".")) + "'"
									+ " and companycode='" + companycode + "'"
									+ " and status='1' ");

					System.out
							.println("Select  UNIVERSALID  as id from fandish where name='"
									+ fileName.substring(0,
											fileName.indexOf("."))
									+ "'"
									+ " and companycode='"
									+ companycode
									+ "' "
									+ " and status='1' ");
					if (ids.size() > 0) {
						Map e = (Map) ids.get(0);
						Integer id_ = (Integer) e.get("id");
						id = id_.toString();
						System.out.println(fileName + "===" + id);
						// 查找id
						String filePath = UploadUtils.getPath(rootPath, path,
								id);
						System.out.println(filePath);
						File newFile = new File(filePath);
						if (!newFile.exists()) {
							// newFile.mkdir();
							newFile.mkdirs();
							// StringUtils.printLog(rootPath);
						}
						fi.write(new File(filePath + fileId));
						System.out.println("-----------------" + rootPath
								+ fileId);
						// 在这里可以记录用户和文件信息
						// 这里有可能写入一个空的文件,
						// 写入数据库
						boService
								.update("update T_Uploadphoto set filetype='1' where dataid='"
										+ id + "'");
						photoDao.setDataid(id);
						photoDao.setId(fileId);
						photoDao.setTablename(path);
						photoDao.setFilename(fileName);
						photoDao.setFiletype("0");
						photoDao.insertIntoDb();
					}
				}
			}
			List list_ = photoDao.list_(id, path);
			photoDao.close();
			UploadUtils util = new UploadUtils();
			util.setPhotos(list_);
			strBuf.append(util.getList(rootPath, path, id));
		} catch (Exception ex) {
			ex.printStackTrace();
			strBuf.append("err:文件大小超过4MB");
		}

		// System.out.println(strBuf.toString());
		response.getWriter().print(strBuf.toString());

	}

	private String getFileName(String s) {
		int i = s.lastIndexOf("\\");
		if (i < 0 || i >= s.length() - 1) {
			i = s.lastIndexOf("/");
			if (i < 0 || i >= s.length() - 1)
				return s;
		}
		return s.substring(i + 1);
	}

	@Test
	public void a() {
		String fileName = "aaa.png";
		System.out.println(fileName.substring(0, fileName.indexOf(".")));
	}
}