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
	// ���ڴ���ϴ��ļ���Ŀ¼
	private String tempPath = "C:\\upload\\tmp\\";

	// ���ڴ����ʱ�ļ���Ŀ¼
	public void afterExcute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			Exception {
		response.setContentType("text/html;charset=GBK");
		StringBuffer strBuf = new StringBuffer();
		String id = request.getParameter("id");
		String path = "dish";
		String companycode = request.getParameter("companycode");
		// �ж��ļ��Ƿ���ڣ�����
		//String rootPath = request.getRealPath("/");
		String rootPath = AppSession.getAbsoluteUploadPath();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ڴ滺������������д����ʱ�ļ�
		factory.setSizeThreshold(10240000);
		//
		// ������ʱ�ļ��洢λ��
		factory.setRepository(new File(tempPath));
		ServletFileUpload upload_ = new ServletFileUpload(factory);
		upload_.setFileSizeMax(114194304);
		upload_.setProgressListener(new UploadListener(request));
		// DiskFileUpload fu = new DiskFileUpload();
		// ��������ļ��ߴ磬������4MB
		// fu.setSizeMax(20 * 1024 * 1024);
		// ���û�������С��������4kb
		// fu.setSizeThreshold(4096);
		// ������ʱĿ¼��
		// fu.setRepositoryPath(tempPath);
		// �õ����е��ļ���
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		try {
			// List fileItems = fu.parseRequest(request);
			// Iterator i = fileItems.iterator();
			List fileItems = upload_.parseRequest(request);
			Iterator i = fileItems.iterator();
			// ���δ���ÿһ���ļ���
			PhotoDao photoDao = new PhotoDao();
			photoDao.init();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				// ����ļ���������ļ�������·����
				String fileName = fi.getName();
				if ((fileName != null) && !fileName.equals("")) {
					String tmp = fileName.toLowerCase();
					System.out.println("upload file," + tmp);
					if (tmp.endsWith("jsp") || tmp.endsWith("war")
							|| tmp.endsWith("jar") || tmp.endsWith("exe")
							|| tmp.endsWith("bat")) {
						// throw new
						// Exception("sorry,upload file type is not permit!");
						strBuf.append("err:��������ļ�����");
					}
					// ��������Լ�¼�û����ļ���Ϣ
					// ...
					// �����п���д��һ���յ��ļ�,
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
						// ����id
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
						// ��������Լ�¼�û����ļ���Ϣ
						// �����п���д��һ���յ��ļ�,
						// д�����ݿ�
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
			strBuf.append("err:�ļ���С����4MB");
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