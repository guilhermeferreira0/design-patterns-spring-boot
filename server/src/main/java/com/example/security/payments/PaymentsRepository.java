package com.example.security.payments;

import com.example.security.payments.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentsRepository extends JpaRepository<Order, UUID> {
    List<Order> findByCreatorId(UUID userId);
    void deleteByCreatorId(UUID userId);
}
