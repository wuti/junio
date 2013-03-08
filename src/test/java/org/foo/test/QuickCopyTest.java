package org.foo.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import net.sf.ehcache.util.ClassLoaderUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.foo.example.model.QuickCopyModel;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

public class QuickCopyTest {

	/**
	 * 通过UI得到并解析需要部署的class的精确路径
	 * 
	 * @param projectName
	 * @param sourceFilePath
	 * @return
	 * @throws IOException
	 */
	public static QuickCopyModel getExactRealPathOfClassFile(
			String projectName, String javaPathOfWindows) throws IOException {

		QuickCopyModel copyModel = new QuickCopyModel();

		ClassPathResource myPath = new ClassPathResource("target-"
				+ projectName + ".properties");
		Properties p = new Properties();
		p.load(myPath.getInputStream());

		copyModel.setProjectName(projectName);

		copyModel.setClassBaseDirOfWindows(FilenameUtils.separatorsToSystem(p
				.getProperty("classBaseDir")));

		copyModel.setJavaBaseDirOfWindows(FilenameUtils.separatorsToSystem(p
				.getProperty("javaBaseDir")));

		copyModel.setJavaPathOfWindows(javaPathOfWindows);

		return copyModel;
	}

	public static void main(String[] args) throws IOException {

		String myConfigPattern = "itmsCore";
		String sourceFilePath = "D:\\zzNode\\itmsPlus\\ITMS_DEV\\src\\com\\zznode\\itms\\acs\\manager\\CommandExecutingFaultException.java";

		if (FileUtils.getFile(sourceFilePath) == null) {
			throw new FileNotFoundException("jarFile named " + sourceFilePath
					+ " do not exist");
		}

		QuickCopyModel copyModel = getExactRealPathOfClassFile(myConfigPattern,
				sourceFilePath);

		String realClassPath = copyModel.getClassPathOfWindows();

		String myPackagePattern = "";

		File realClassFile = new File(realClassPath);

		if (realClassFile == null || !realClassFile.isFile()) {
			throw new FileNotFoundException("File named " + realClassFile
					+ " do not exist");
		}

		// 通过枚举类型判定所属包,Start
		if (realClassPath.indexOf("com\\zznode\\itms\\resource\\") != -1) {
			myPackagePattern = "inventory.jar";
		} else if (realClassPath.indexOf("com\\zznode\\itms\\oam\\") != -1) {
			myPackagePattern = "oamserver.jar";
		} else if (realClassPath.indexOf("com\\zznode\\itms\\pon\\") != -1) {
			myPackagePattern = "ponmonitorserver.jar";
		} else if (realClassPath.indexOf("com\\zznode\\itms\\nbi\\") != -1) {
			myPackagePattern = "nbiserver.jar";
		} else if (realClassPath.indexOf("com\\zznode\\itms\\acs\\manager\\") != -1) {
			myPackagePattern = "acsserver.jar";
		} else if (realClassPath.indexOf("com\\zznode\\itms\\monitor\\") != -1) {
			myPackagePattern = "systemmonitor.jar";
		} else if (realClassPath.indexOf("com\\zznode\\itms\\fm\\") != -1) {
			myPackagePattern = "fmprocessorserver.jar";
		} else if (realClassPath.indexOf("com\\zznode\\itms\\safe\\") != -1) {
			myPackagePattern = "safeProcessorServer.jar";
		} else if (realClassPath.indexOf("com\\zznode\\itms\\snmp\\") != -1) {
			myPackagePattern = "snmpManager.jar";
		} else {
			throw new UnsupportedOperationException("unrecognized class");
		}
		// 通过枚举类型判定所属包,End

		String workingDirectory = "d:\\zznode\\tmp\\";

		// 依据类信息形成规范的目录结构,同时清理掉以前的目录,Start
		String tmpCopyPath = FilenameUtils.normalize(workingDirectory
				+ copyModel.getClassPatternOfWindows());
		String myDirForJarCommand = Splitter.on("\\").omitEmptyStrings()
				.split(copyModel.getClassPatternOfWindows()).iterator().next();
		FileUtils.deleteDirectory(FileUtils.getFile(workingDirectory
				+ myDirForJarCommand));
		File myFile = new File(tmpCopyPath);
		Files.createParentDirs(myFile);
		Files.copy(realClassFile, myFile);
		// 依据类信息形成规范的目录结构,同时清理掉以前的目录,End

		// 更新jar包中的指定文件,Start
		String jarFilePath = workingDirectory + myPackagePattern;
		File jarFile = new File(jarFilePath);
		if (jarFile == null || !jarFile.isFile()) {
			throw new FileNotFoundException("jarFile named " + jarFile
					+ " do not exist");
		}
		Runtime.getRuntime().exec(
				"jar uf " + jarFilePath + " -C " + workingDirectory
						+ myDirForJarCommand);
		// 更新jar包中的指定文件,End

	}

	@Test
	public void test() {

	}

	/**
	 * copy目录为如下的java类的class到桌面
	 */
	public void quickCopyTest() throws IOException {

		String myFilePath = "c:\\Users\\Steve\\Desktop\\";
		Process p = Runtime.getRuntime().exec(
				"jar uf " + myFilePath + "antlr-2.7.7.jar -C " + myFilePath
						+ " antlr");

		BufferedOutputStream bu = new BufferedOutputStream(p.getOutputStream());

		ByteStreams.copy(p.getInputStream(), System.out);

		String targetFilePath = "d:\\zzNode\\DEV_BRANCH_SICHUAN\\UI_DEV\\dist\\acsserver.jar\\com\\zznode";
		File myOriginalFile = new File(targetFilePath);

		// Linux version:jar uf /home/itms/ITMS_HOME/antlr-2.7.7.jar -C
		// /home/itms/ITMS_HOME/ 1.txt

	}

	public void getFile() {
		FTPClient ftp = new FTPClient();
		String server = "133.37.111.34";
		// String server = "192.168.2.112";
		String savePath = "/home/itms/ITMS_HOME/lib/";
		// String savePath = "home/ftpitms/";
		boolean error = false;
		try {
			int reply;
			ftp.connect(server);
			ftp.login("itms", "itmscs");
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

			// backup the original jar file with dateTime extension,Start

			ftp.setFileType(FTP.BINARY_FILE_TYPE);

			String myBackupDirectory = "/home/itms/ITMS_HOME/backup/";
			String myWorkingDirectory = "/home/itms/ITMS_HOME/";

			System.out.println(FilenameUtils.separatorsToUnix(FilenameUtils
					.concat(myWorkingDirectory, myBackupDirectory)));

			if (ftp.changeWorkingDirectory(myWorkingDirectory)) {
				ftp.makeDirectory(myBackupDirectory);
			}
			ftp.storeFile(myBackupDirectory + "test1.txt", new FileInputStream(
					new File("c:\\Users\\Steve\\Desktop\\1.txt")));
			// backup the original jar file with dateTime extension,End

			// OutputStream myStream = Files.newOutputStreamSupplier(
			// new File("c:\\Users\\Steve\\Desktop\\radius.sql"))
			// .getOutput();
			//
			// ftp.retrieveFile(savePath + "radius.sql", myStream);

			// myStream.close();

			ftp.logout();

			System.out.println("write..");

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

	public void quickMySqlTest() throws IOException {
		ClassPathResource myPath = new ClassPathResource(
				"target-mysql.properties");
		Properties p = new Properties();
		p.load(myPath.getInputStream());

		Configuration configuration = new Configuration().setProperties(p);

		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();

		SessionFactory myFactory = configuration
				.buildSessionFactory(serviceRegistry);

		Session mySession = myFactory.openSession();

		mySession.beginTransaction();

		Query myQuery = mySession.createSQLQuery(" select value from tmp ");

		List myList = myQuery.list();

		String mypath = myList.get(0).toString();

		mySession.getTransaction().commit();

		System.out.println(FilenameUtils.getFullPath(mypath));
	}

}
