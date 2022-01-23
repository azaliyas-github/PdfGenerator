package ru.itis.pdfgenerator.pdf.services;

import org.springframework.stereotype.*;
import ru.itis.pdfgenerator.pdf.utils.*;

import java.util.*;

@Service
public class PdfService {
	public byte[] generate(String pdfType, Map<String, String> form) {
		AcroFormFiller filler = createFiller(pdfType);

		throw new UnsupportedOperationException();
	}

	private static AcroFormFiller createFiller(String pdfType) {
		throw new UnsupportedOperationException();
	}
}
