package com.ogutcenali.enoca_challange.repository;

import com.ogutcenali.enoca_challange.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:name%")
    List<Customer> findByNameContaining(@Param("name") String name);

    @Query("SELECT c FROM Customer c WHERE c NOT IN (SELECT o.customer FROM Order o)")
    List<Customer> findCustmersWithoutOrders();
}
