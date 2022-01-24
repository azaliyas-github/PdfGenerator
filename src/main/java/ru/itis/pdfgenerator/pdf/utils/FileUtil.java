package ru.itis.pdfgenerator.pdf.utils;

import java.io.*;
import java.nio.file.*;

public class FileUtil {
	public static byte[] getBytes(String fileName) {
		try {
			return Files.readAllBytes(Paths.get(fileName));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
