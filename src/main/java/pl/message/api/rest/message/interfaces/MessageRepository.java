package pl.message.api.rest.message.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.message.api.rest.message.impl.Message;
import pl.message.api.rest.user.impl.User;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query("select m from Message m where m.title = ?1")
    List<Message> getByTitle(String title);

    @Query("select m from Message m where m.sender = ?1")
    List<Message> getBySender(User sender);

}
