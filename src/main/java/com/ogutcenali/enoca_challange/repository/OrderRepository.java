package com.ogutcenali.enoca_challange.repository;

import com.ogutcenali.enoca_challange.model.Customer;
import com.ogutcenali.enoca_challange.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.createDate> :date")
    List<Order> findAllCreateAfter(@Param("date")LocalDate date);

    @Query("SELECT o.id FROM Order o WHERE o.customer = :customer")
    List<Long> findIdsByCustomer(@Param("customer") Customer customer);

    List<Order> findByCustomer_Id(Long id);


}
