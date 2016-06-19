package org.fancy.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class IOUtil {

	private static volatile IOUtil instance;
	
	private IOUtil() {}
	
	public static IOUtil getInstance() {
		IOUtil ins = instance;
		if (null == ins) {
			synchronized (IOUtil.class) {
				if (null == ins) {
					ins = new IOUtil();
					instance = ins;
				}
			}
		}
		return ins;
	}
	
	/**
	 * Read a input stream to string and close it after read
	 * 
	 * @param input
	 *            the input stream to read
	 * @return string of the input stream 
	 * @throws IOException 
	 */
	public String readStream(InputStream input) throws IOException {
		String content = IOUtils.toString(input);
		IOUtils.closeQuietly(input);
		return content;
	}
	
	/**
	 * Read a file from giving file path to string
	 * 
	 * @param path
	 *            the file path to read
	 * @return string of the file
	 * @throws IOException 
	 */
	public String readFile(String path) throws IOException {
		InputStream inStream = new FileInputStream(path);
		String content = IOUtils.toString(inStream);
		IOUtils.closeQuietly(inStream);
		return content;
	}

}
