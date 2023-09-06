package com.learning.procjson.producer.repository;

import com.learning.procjson.shared.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerMessageRepository extends JpaRepository<Message, Long> {
    // Você pode adicionar consultas personalizadas aqui, se necessário.
}
