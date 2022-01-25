package ru.itis.pdfgenerator.authorization.repo;

import org.springframework.data.jpa.repository.*;
import ru.itis.pdfgenerator.authorization.models.*;

import java.util.*;

public interface UsersRepository extends JpaRepository<User, Long> {
	Optional<User> findByPersonalId(String personalId);
}

