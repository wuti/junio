package com.foo.example.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import com.foo.common.base.action.FooGenericAction;
import com.foo.common.base.utils.FooUtils;
import com.foo.example.model.QuickCopyModel;
import com.foo.example.service.FooService;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

public class QuickOperationAction extends FooGenericAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FooService fooService;

	/**
	 * 通过UI得到并解析需要部署的class的精确路径
	 * 
	 * @param projectName
	 * @param sourceFilePath
	 * @return
	 * @throws IOException
	 */
	private QuickCopyModel getExactRealPathOfClassFile(String projectName,
			String javaPathOfWindows) throws IOException {

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

	/**
	 * 快速部署class到jar中
	 * 
	 * @throws IOException
	 */
	public void quickDeployClass() throws IOException {

		String myConfigPattern = request.getParameter("myPattern");
		String sourceFilePath = request.getParameter("sourceFilePath");
		// String myConfigPattern = "itmsCore";
		// String sourceFilePath =
		// "D:\\zzNode\\itmsPlus\\ITMS_DEV\\src\\com\\zznode\\itms\\resource\\common\\ResCommon.java";

		QuickCopyModel copyModel = getExactRealPathOfClassFile(myConfigPattern,
				sourceFilePath);

		String realClassPath = copyModel.getClassPathOfWindows();

		String myPackagePattern = "";

		File realClassFile = new File(realClassPath);

		if (realClassFile == null || !realClassFile.isFile()) {
			logger.error("realClassFile named " + realClassFile
					+ " do not exist");
			FooUtils.printJsonMessage(response, "realClassFile named "
					+ realClassFile + " do not exist");
			return;
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
			logger.error("unrecognized class");
			FooUtils.printJsonMessage(response, "unrecognized class");
			return;
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
			logger.error("jarFile named " + jarFile + " do not exist");
			FooUtils.printJsonMessage(response, "jarFile named " + jarFile
					+ " do not exist");
			return;
		}
		Runtime.getRuntime().exec(
				"jar uf " + jarFilePath + " -C " + workingDirectory
						+ myDirForJarCommand);
		// 更新jar包中的指定文件,End

	}

	public void quickCopyClass() throws IOException {

		String myConfigPattern = request.getParameter("myPattern");

		ClassPathResource myPath = new ClassPathResource("target-"
				+ myConfigPattern + ".properties");
		Properties p = new Properties();
		p.load(myPath.getInputStream());

		// 由配置而来
		String classBaseDir = FilenameUtils.separatorsToSystem(p
				.getProperty("classBaseDir"));

		String javaBaseDir = FilenameUtils.separatorsToSystem(p
				.getProperty("javaBaseDir"));

		String sourceFilePath = request.getParameter("sourceFilePath");
		String targetFilePath = request.getParameter("targetFilePath");

		String realClassPath = classBaseDir
				+ sourceFilePath.replace(javaBaseDir, "").replaceAll(".java",
						".class");

		logger.info(realClassPath);

		File myOriginalFile = new File(realClassPath);

		if (myOriginalFile == null || !myOriginalFile.isFile()) {
			FooUtils.printJsonMessage(response, "File named " + myOriginalFile
					+ " do not exist");
			return;
		}

		Files.copy(myOriginalFile,
				new File(targetFilePath + myOriginalFile.getName()));

		logger.info("COPY CLASS:" + myOriginalFile + " TO " + targetFilePath
				+ myOriginalFile.getName());

		FooUtils.printJsonSuccessMsg(response);
	}
}
