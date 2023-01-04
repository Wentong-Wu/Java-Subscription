package subscription;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface SubscriberRepository extends CrudRepository<Subscribers,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM customer WHERE email_address = :email")
    Optional<Subscribers> getSubscriberByEmail(@PathVariable String email);
}
