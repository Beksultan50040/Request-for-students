package kg.megacom.requestforstudents.dao;

import kg.megacom.requestforstudents.models.Subscribers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepo extends JpaRepository<Subscribers, Long> {

    Subscribers findByNumber(String number);
}
