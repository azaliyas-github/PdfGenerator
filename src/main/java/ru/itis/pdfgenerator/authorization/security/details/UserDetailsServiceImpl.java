package ru.itis.pdfgenerator.authorization.security.details;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import ru.itis.pdfgenerator.authorization.models.User;
import ru.itis.pdfgenerator.authorization.repo.*;

/**
 * 13.03.2021
 * 03. Spring Security
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component("tokenUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	public UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
		DecodedJWT decodedToken = JWT.require(Algorithm.HMAC256("secret_key"))
			.build()
			.verify(token);
		Long userId = Long.parseLong(decodedToken.getSubject());
		User user = usersRepository.getById(userId);
		return new UserDetailsImpl(user);
	}
}

