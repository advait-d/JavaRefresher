package springboot.repository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springboot.model.Order;
import springboot.model.Order.OrderStatus;

/**
 * Repository interface for Order entity.
 * Demonstrates paging and sorting capabilities along with custom queries.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find orders by user id - derived query method
    List<Order> findByUserId(Long userId);

    // Find orders by status - derived query method
    List<Order> findByStatus(OrderStatus status);

    // Find orders by user id and status - derived query method with multiple conditions
    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status);

    // Find orders with amount greater than specified value - derived query method with comparison
    List<Order> findByTotalAmountGreaterThan(BigDecimal amount);

    // Find orders between dates - derived query method with date range
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Paginated and sorted results - same method but with Pageable parameter
    Page<Order> findByUserId(Long userId, Pageable pageable);

    // Custom query to find orders with amount in range
    @Query("SELECT o FROM Order o WHERE o.totalAmount BETWEEN :minAmount AND :maxAmount")
    List<Order> findOrdersInAmountRange(@Param("minAmount") BigDecimal minAmount,
                                        @Param("maxAmount") BigDecimal maxAmount);

    // Custom query to calculate total sales by status
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.status = :status")
    BigDecimal calculateTotalSalesByStatus(@Param("status") OrderStatus status);

    // Native SQL query example to find orders with specific details
    @Query(value = "SELECT o.* FROM orders o JOIN users u ON o.user_id = u.id " +
            "WHERE u.email = :email AND o.status = :status",
            nativeQuery = true)
    List<Order> findOrdersByUserEmailAndStatus(@Param("email") String email,
                                               @Param("status") String status);
}