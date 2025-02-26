package ru.otus.hw.ex00.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw.ex00.models.Repayment;

import java.util.Optional;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    Optional<Repayment> findFirstByStatus(String status);


}