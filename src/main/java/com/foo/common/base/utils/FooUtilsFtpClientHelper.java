package com.foo.common.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Ftp快速测试类，用于确认ftp服务是否可行
 * 
 * 服务器ftp配置可参考表：ITMS_FILE_SERVER
 * 
 * @author Steve
 * 
 */
public class FooUtilsFtpClientHelper {
	public static void main(String[] args) {
		FTPClient ftp = new FTPClient();
		String server = "133.37.111.34";
		// String server = "192.168.2.112";
		String savePath = "/var/ftp/";
		// String savePath = "home/ftpitms/";
		boolean error = false;
		try {
			int reply;
			ftp.connect(server);
			ftp.login("ftp", "itms%cs");
			// ftp.login("ftpitms", "ftpitms");
			System.out.println("Connected to " + server + ".");

			// After connection attempt, you should check the reply code to
			// verify
			// success.
			reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.err.println("FTP server refused connection.");
				System.exit(1);
			}
			ftp.enterLocalPassiveMode();
			// ftp.setFileType(FTP.BINARY_FILE_TYPE);
			boolean saveFileSuccessful = ftp.storeFile(savePath + "fuck.txt",
					new FileInputStream(new File(
							"c:\\/Users/Steve/Desktop/1.txt")));
			// transfer files here
			System.out.println("Save file result is " + saveFileSuccessful);
			ftp.logout();
		} catch (IOException e) {
			error = true;
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					// do nothing
				}
			}
			System.exit(error ? 1 : 0);
		}
	}
}
