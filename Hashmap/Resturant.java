package Hashmap;

import java.util.HashMap;
import java.util.Map;

class Restaurant {
    private Map<String, Order> orders;

    public Restaurant() {
        orders = new HashMap<>();
    }

    // Метод для добавления заказа
    public void addOrder(Order order) {
        orders.put(order.getOrderNumber(), order);
        System.out.println("Order added: " + order);
    }

    // Метод для удаления заказа по номеру
    public void removeOrder(String orderNumber) {
        Order removedOrder = orders.remove(orderNumber);
        if (removedOrder != null) {
            System.out.println("Order removed: " + removedOrder);
        } else {
            System.out.println("Order with number " + orderNumber + " not found.");
        }
    }

    // Метод для поиска заказа по номеру
    public Order findOrder(String orderNumber) {
        return orders.get(orderNumber);
    }

    // Метод для вывода всех заказов
    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("All Orders:");
            for (Order order : orders.values()) {
                System.out.println(order);
            }
        }
    }
    // Метод для удаления всех заказов
    public void removeAllOrders() {
        orders.clear();
        System.out.println("All orders have been removed.");
    }
}
