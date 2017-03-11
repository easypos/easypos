package com.ysk.login;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.f1jeeframework.http.Action;
import com.f1jeeframework.http.AppSession;
import com.f1jeeframework.http.RequestUtil;
import com.f1jeeframework.util.OASession;
import com.f1jframework.eform.CommonboService;

public class Register extends Action {
	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		PrintWriter out = arg1.getWriter();
		String responseResult = "ok";
		arg1.setContentType("text/html; charset=gbk");
		// StringBuffer strBuf = new StringBuffer();
		ApplicationContext cxt = AppSession.getApplicationContext();
		CommonboService boService = (CommonboService) cxt.getBean("boService");
		String id = arg0.getParameter("id");
		// String fid = arg0.getParameter("fid");
		String action = arg0.getParameter("action");
		// boService.init(fid, null, null);
		// if boService.getFormBean().getKeyType()
		if (oasession == null) {
			oasession = new OASession();
			oasession.setLoginID(0);
			oasession.setUserName("guest");
		}
		LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
		boService.setSession((OASession) oasession);
		HashMap<String, Object> paramValues = RequestUtil
				.getParamValuesByUtf_8(arg0);
		String user = (String) paramValues.get("user_name");
		int result = loginDaoImpl.load(user);
		if (result > 0) {
			responseResult = "errcode:1";
			out.print(responseResult);
			return;
		}
		boService.setParameterValues(paramValues);
		String pass = (String) arg0.getParameter("PASSWORD");
		boService.getParameterValues()
				.put("PASSWORD", MD5impl.GetMD5Code(pass));
		boService.init("beigebulu_user", null, null);
		Integer id_ = new Integer(boService.maxId() + 1);
		id = id_.toString();
		// ��Ա���ֳ�ʼ��
		boService.getParameterValues().put("score", "100");
		boService.getParameterValues().put("status", "1");
		boService.getParameterValues().put("status", "1");
		boService.add(id, "beigebulu_user");
		this.setForward(false);
		// ע��ɹ����Զ���¼
		OASession oasession = new OASession();
		oasession.setLoginIP(arg0.getRemoteHost());
		HttpSession session = arg0.getSession(false);
		oasession.setLoginID(id_);
		oasession.setUserName(user);
		session.setAttribute("oasession", oasession);		
		// �����ʼ�
		try {
			// Logger.getLogger(this.getClass().getName()).info("���ʼ�." + user);
			// send_(user);
			// Logger.getLogger(this.getClass().getName()).info("���ʼ�." + a);
		} catch (Exception ex) {
			// Logger.getLogger(this.getClass().getName()).info("���ʼ�." +
			// "err!");
			// out.print("error");
		}
		// Logger.getLogger(this.getClass().getName()).info("���ʼ�.1" + "err!");
		out.print(responseResult);
		// this.callURI(action + "?id=" + id, arg0, arg1);
	}

	private boolean send_(String to) {
		// Logger.getLogger(this.getClass().getName()).info("���ʼ�.1" + "true");
		String smtp = "smtp.sina.com";// smtp������
		// String from = "2739877110@qq.com";// �ʼ���ʾ����
		String from = "f1jee@sina.com";// �ʼ���ʾ����
		// String to = "51591263@qq.com";// �ռ��˵��ʼ���ַ����������ʵ��ַ
		String copyto = "";// �������ʼ���ַ
		String subject = "��E��";// �ʼ�����
		String content = "��E��";// �ʼ�����
		String username = "f1jee@sina.com";// ��������ʵ���˻���
		String password = "oa2000";// ����������
		MailImpl mail = new MailImpl(smtp);
		mail.setNeedAuth(true);
		return mail.sendAndCc(smtp, from, to, copyto, subject, content,
				username, password);

	}

	private boolean checkMail(String a) {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return a.matches(EMAIL_REGEX);
	}

	@Test
	public void test1() {
		System.out.println(checkMail("123@qq.com"));
		System.out.println(checkMail("1232#qq.com"));
	}
}
