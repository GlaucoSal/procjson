package com.learning.procjson.consumer.repository;

import com.learning.procjson.shared.model.InfoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoDetailRepository extends JpaRepository<InfoDetail, Long> {
    // Você pode adicionar consultas personalizadas aqui, se necessário.
}
