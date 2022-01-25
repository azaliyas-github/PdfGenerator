package ru.itis.pdfgenerator.redis.repo;

import org.springframework.data.keyvalue.repository.*;
import ru.itis.pdfgenerator.redis.model.*;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {
}
