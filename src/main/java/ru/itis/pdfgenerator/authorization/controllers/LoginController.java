package ru.itis.pdfgenerator.authorization.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.pdfgenerator.authorization.service.*;
import ru.itis.pdfgenerator.authorization.dto.*;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody PersonalIdDto personalIdDto) {
		return ResponseEntity.ok(loginService.login(personalIdDto));
	}
}

