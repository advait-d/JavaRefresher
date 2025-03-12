package springboot.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import springboot.model.Order.OrderStatus;

/**
 * DTO for Order entity - used to transfer data between layers without exposing
 * entity details and to customize the JSON response.
 */
public class OrderDTO {
    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private Long userId;
    private String username;

    // Empty constructor for serialization/deserialization
    public OrderDTO() {
    }

    // Constructor to easily convert from Entity to DTO
    public OrderDTO(Long id, BigDecimal totalAmount, OrderStatus status,
                    LocalDateTime orderDate, Long userId, String username) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.userId = userId;
        this.username = username;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}