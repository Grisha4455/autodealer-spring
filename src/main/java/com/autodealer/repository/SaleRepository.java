package com.autodealer.repository;

import com.autodealer.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    
    List<Sale> findByCustomerId(Long customerId);
    
    List<Sale> findByEmployeeId(Long employeeId);
    
    List<Sale> findBySaleDateBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT s FROM Sale s ORDER BY s.saleDate DESC")
    List<Sale> findAllOrderByDateDesc();
    
    // Аналитика
    @Query("SELECT s.carModel.id, s.carModel.name, COUNT(s) as salesCount " +
           "FROM Sale s GROUP BY s.carModel.id, s.carModel.name ORDER BY salesCount DESC")
    List<Object[]> findPopularModels();
    
    @Query("SELECT s.employee.id, s.employee.fullName, COUNT(s) as salesCount, SUM(s.finalPrice) as totalRevenue " +
           "FROM Sale s GROUP BY s.employee.id, s.employee.fullName ORDER BY salesCount DESC")
    List<Object[]> findSalesByEmployee();
}

