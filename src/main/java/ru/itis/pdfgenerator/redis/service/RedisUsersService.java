package ru.itis.pdfgenerator.redis.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.pdfgenerator.authorization.models.*;
import ru.itis.pdfgenerator.authorization.repo.*;
import ru.itis.pdfgenerator.authorization.service.*;
import ru.itis.pdfgenerator.redis.model.*;
import ru.itis.pdfgenerator.redis.repo.*;

import java.util.*;

@Service
public class RedisUsersService {
	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private RedisUsersRepository redisUsersRepository;

	@Autowired
	private JwtBlackListService blackListService;

	public void addTokenToUser(User user, String token) {
		String redisId = user.getRedisId();

		RedisUser redisUser;
		if (redisId != null) {
			redisUser = redisUsersRepository.findById(redisId)
				.orElseThrow(IllegalArgumentException::new);

			if (redisUser.getTokens() == null) {
				redisUser.setTokens(new ArrayList<>());
			}
			redisUser.getTokens().add(token);
		} else {
			redisUser = RedisUser.builder()
				.userId(user.getId())
				.tokens(Collections.singletonList(token))
				.build();
		}
		redisUsersRepository.save(redisUser);
		user.setRedisId(redisUser.getId());
		userRepository.save(user);
	}

	public void addAllTokenToBlackList(User user) {
		if (user.getRedisId() != null) {
			RedisUser redisUser = redisUsersRepository.findById(user.getRedisId())
				.orElseThrow(IllegalArgumentException::new);

			List<String> tokens = redisUser.getTokens();
			for (String token : tokens) {
				blackListService.add(token);
			}
			redisUser.getTokens().clear();
			redisUsersRepository.save(redisUser);
		}
	}
}
