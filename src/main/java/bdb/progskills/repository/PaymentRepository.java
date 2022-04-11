package bdb.progskills.repository;

import bdb.progskills.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {


    List<Payment> findByClient_Id(Long id);
}
