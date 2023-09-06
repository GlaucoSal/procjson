package com.learning.procjson.consumer.repository;

import com.learning.procjson.shared.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Você pode adicionar consultas personalizadas aqui, se necessário.
}
