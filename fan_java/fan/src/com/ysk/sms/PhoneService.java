package com.ysk.sms;

public class PhoneService {

	private String mobile_code;
	private boolean resultOk = false;

	public String getMobile_code() {
		return mobile_code;
	}

	public void setMobile_code(String mobile_code) {
		this.mobile_code = mobile_code;
	}

	public boolean isResultOk() {
		return resultOk;
	}

	public void setResultOk(boolean resultOk) {
		this.resultOk = resultOk;
	}

	public void send(String phone) {
		System.out.println("123!");
		PhoneApi phoneApi = new PhoneApi();
		phoneApi.send(phone);
	}
}
