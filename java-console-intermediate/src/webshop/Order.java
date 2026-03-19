package webshop;

import java.time.LocalDate;
import java.util.List;

// puska: https://github.com/Nagraggini/blog/blob/main/Java_basic_knowledge.md

// 1. Enum a kategóriáknak és állapotoknak
enum OrderStatus {
    PENDING, SHIPPED, DELIVERED, CANCELLED
}

enum Category {
    ELECTRONICS, FASHION, HOME, GROCERY
}

// 2. Egy tétel a kosárban
record OrderItem(String productName, Category category, double price, int quantity) {
}

// 3. A fő Rendelés osztály
public class Order {
    private final String orderId;
    private final String customerName;
    private final LocalDate orderDate;
    private final OrderStatus status;
    private final List<OrderItem> items;
    private final String country;

    public Order(String orderId, String customerName, LocalDate orderDate,
            OrderStatus status, List<OrderItem> items, String country) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
        this.country = country;
    }

    // Getterek
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getCountry() {
        return country;
    }

    // Egy segédmetódus a teljes összeg számításához (Stream-en belül is hasznos)
    public double getTotalAmount() {
        // TODO later
        return 0;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", customerName=" + customerName + ", orderDate=" + orderDate + ", status="
                + status + ", items=" + items + ", country=" + country + "]";
    }

}