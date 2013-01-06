package com.foo.common.base.utils;

import java.io.File;
import java.nio.charset.Charset;

import org.terracotta.agent.repkg.de.schlichtherle.io.FileInputStream;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

public class FooUtilsCharSetHelper {

	public static void transformFileCharSet(File myDirectoryOnly)
			throws Exception {

		if (myDirectoryOnly.isDirectory()) {
			for (File myFile : myDirectoryOnly.listFiles()) {
				if (myFile.isDirectory()) {
					transformFileCharSet(myFile);
				} else {
					// Execute real transform here,Start

					if (!myFile.getName().endsWith(".java")) {
						continue;
					}

					byte myByte[] = ByteStreams
							.toByteArray(new FileInputStream(myFile));

					System.out.println(myFile.getAbsolutePath() + ""
							+ myByte[0] + " " + myByte[1] + " " + myByte[2]);

					Files.write(new String(myByte, "gbk"), myFile,
							Charset.forName("UTF-8"));

					System.out.println(myFile.getAbsolutePath() + ""
							+ myByte[0] + " " + myByte[1] + " " + myByte[2]);
					// Execute real transform here,End
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {

		String myDirectory = "d:\\temp";

		transformFileCharSet(new File(myDirectory));

	}
}
