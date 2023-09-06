package com.learning.procjson.consumer.repository;

import com.learning.procjson.shared.model.InfoHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoHeadRepository extends JpaRepository<InfoHead, Long> {
    // Você pode adicionar consultas personalizadas aqui, se necessário.
}
