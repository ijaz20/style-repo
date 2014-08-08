package com.style.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * String util to help file conventions
 * 
 * @author mathi
 */
public final class FileUtil {

	public FileUtil() {
	}

	/**
	 * check the string is empty
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static String saveFile(File file, String filePath, String fileName)
			throws IOException {
		// The following seems to happen when running jetty:run
		if (filePath == null) {
			filePath = new File("src/main/webapp/resources").getAbsolutePath();
		}

		// write the file to the file specified
		File dirPath = new File(filePath);

		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		// retrieve the file data
		InputStream stream = new FileInputStream(file);

		// write the file to the file specified
		OutputStream bos = new FileOutputStream(filePath + fileName);
		int bytesRead;
		byte[] buffer = new byte[8192];

		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}

		bos.close();
		stream.close();

		return filePath + fileName;
	}

}