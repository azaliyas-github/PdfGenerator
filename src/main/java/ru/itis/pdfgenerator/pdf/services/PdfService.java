package ru.itis.pdfgenerator.pdf.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.pdfgenerator.pdf.utils.*;

import java.nio.file.*;
import java.util.*;

@Service
public class PdfService {
	@Value("${pdf.pathToTemplates}")
	private String pathToTheFile;

	public byte[] generate(String pdfType, Map<String, String> form) {
		AcroFormFiller filler = createFiller(pdfType);
		return filler.fill(form);
	}

	private AcroFormFiller createFiller(String pdfType) {
		byte[] file = FileUtil.getBytes(Path.of(pathToTheFile, pdfType + ".pdf").toString());
		return new AcroFormFiller(file);
	}
}
