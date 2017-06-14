package com.tim.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class CommonUtils {

	/**
	 * Designed for creating files or directory
	 * 
	 * @param Filepath
	 *            it is path of a file or path of directory
	 * @param createType
	 *            DIR or FILE
	 * @throws IOException
	 */
	public void createFile(String Filepath, String createType) throws IOException {
		File file = new File(Filepath);
		if (!file.exists()) {
			if (createType.equalsIgnoreCase("DIR")) {
				file.mkdir();
			} else if (createType.equalsIgnoreCase("FILE")) {
				file.createNewFile();
			}

		}
	}

	/**
	 * check file or path exists
	 * 
	 * @param Filepath
	 * @return
	 * @throws IOException
	 */
	public boolean checkPath(String Filepath) throws IOException {
		File file = new File(Filepath);
		if (!file.exists()) {
			return false;
		}
		return true;

	}

	/**
	 * It is to write data into File
	 * 
	 * @param TarData
	 * @throws IOException
	 */
	public void writeDatatoFile(File objFile, String TarData) throws IOException {
		FileOutputStream txtFileContentOut = new FileOutputStream(objFile);
		byte[] contentInBytes = (TarData).getBytes();
		txtFileContentOut.write(contentInBytes);
		txtFileContentOut.flush();
		txtFileContentOut.close();
	}

	/**
	 * encode a String
	 * 
	 * @param decodedString
	 * @return encoded String
	 */
	public String encodeBase64(String decodedString) {
		byte[] encodedBytes = Base64.encodeBase64(decodedString.getBytes());

		return new String(encodedBytes);

	}

	/**
	 * decode a String
	 * 
	 * @param encodedString
	 * @return decoded String
	 */
	public String decodeBase64(String encodedString) {
		byte[] decodedBytes = Base64.decodeBase64(encodedString.getBytes());

		return new String(decodedBytes);

	}
}
