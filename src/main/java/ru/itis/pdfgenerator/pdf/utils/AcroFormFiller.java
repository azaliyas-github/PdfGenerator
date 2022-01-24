package ru.itis.pdfgenerator.pdf.utils;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.interactive.form.*;

import java.io.*;
import java.util.*;

public class AcroFormFiller {
	private final PDDocument templatePdf;

	public AcroFormFiller(byte[] templatePdf) {
		try {
			this.templatePdf = PDDocument.load(templatePdf);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public byte[] fill(Map<String, String> form) {
		try {
			PDAcroForm documentForm = templatePdf.getDocumentCatalog().getAcroForm();
			for (Map.Entry<String, String> field : form.entrySet()) {
				PDField documentField = documentForm.getField(field.getKey());
				documentField.setValue(field.getValue());
				documentField.setReadOnly(true);
			}

			var result = saveToBytes(templatePdf);
			templatePdf.close();
			return result;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static byte[] saveToBytes(PDDocument document) throws IOException {
		try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream()) {
			document.save(byteStream);
			document.close();
			return byteStream.toByteArray();
		}
	}
}
