package ru.itis.pdfgenerator.authorization.service;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import ru.itis.pdfgenerator.authorization.dto.*;
import ru.itis.pdfgenerator.authorization.models.User;
import ru.itis.pdfgenerator.authorization.repo.*;
import ru.itis.pdfgenerator.redis.service.*;

import java.util.*;
import java.util.function.*;

@Service
public class LoginService {
	@Autowired
	private RedisUsersService redisUsersService;

	@Autowired
	private UsersRepository usersRepository;

	public TokenDto login(PersonalIdDto personalIdDto) {
		User user = null;
		try {
			user = usersRepository.findByPersonalId(personalIdDto.getPersonalId())
				.orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
		} catch (Throwable e) {
			throw new UsernameNotFoundException("Invalid username or password");
		}

		String tokenValue = JWT.create()
			.withSubject(user.getId().toString())
			.withIssuedAt(new Date(System.currentTimeMillis()))
			.sign(Algorithm.HMAC256("secret_key"));
		return TokenDto.builder()
			.token(tokenValue)
			.build();
	}
}

