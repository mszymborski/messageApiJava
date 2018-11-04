package pl.message.api.rest.user.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.message.api.rest.user.impl.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name = ?1")
    List<User> getALLByName(String name);

    @Query("select u from User u where u.email = ?1")
    User getByEmail(String email);
}
