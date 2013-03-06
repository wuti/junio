package com.foo.example.action;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import com.foo.common.base.action.FooGenericAction;
import com.foo.common.base.utils.FooUtils;
import com.foo.example.service.FooService;
import com.google.common.io.Files;

public class QuickOperationAction extends FooGenericAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FooService fooService;

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

		Files.copy(myOriginalFile,
				new File(targetFilePath + myOriginalFile.getName()));


		logger.info("COPY CLASS:" + myOriginalFile + " TO " + targetFilePath
				+ myOriginalFile.getName());

		FooUtils.printJsonSuccessMsg(response);
	}
}
