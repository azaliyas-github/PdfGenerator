package ru.itis.pdfgenerator.pdf.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.pdfgenerator.pdf.services.*;

import java.util.*;

@RestController
@RequestMapping("api")
public class PdfController {
	@Autowired
	private PdfService pdfService;

	@PostMapping(
		value = "{name}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] generate(@PathVariable("name") String pdfType, @RequestBody Map<String, String> form) {
		return pdfService.generate(pdfType, form);
	}
}
