package ru.itis.pdfgenerator.authorization.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.pdfgenerator.authorization.repo.*;

@Service
public class JwtBlackListService {

	@Autowired
	public BlackListRepository blackListRepository;

	public void add(String token) {
		blackListRepository.save(token);
	}

	public boolean exists(String token) {
		return blackListRepository.exists(token);
	}
}
