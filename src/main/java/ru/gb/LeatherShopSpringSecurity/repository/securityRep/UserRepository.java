package ru.gb.LeatherShopSpringSecurity.repository.securityRep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.LeatherShopSpringSecurity.model.securityModel.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
