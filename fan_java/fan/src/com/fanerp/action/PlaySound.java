package com.fanerp.action;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import com.f1jeeframework.http.Action;

public class PlaySound extends Action {
	public static void main(String args[]) {
		a();
	}

	public static void a() {
		try {
			FileInputStream fileau = new FileInputStream(
					"D:/webapps/blog/sound/call.wav");
			AudioStream as = new AudioStream(fileau);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	@Override
	public void afterExcute(HttpServletRequest arg0, HttpServletResponse arg1)
			throws Exception {
		// TODO Auto-generated method stub
		// ªÒµ√Ã®∫≈
		String tabNu = arg0.getParameter("tabNu");
		// ∫ÙΩ–
		a();
	}
}
