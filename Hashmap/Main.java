package Hashmap;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        // Пример добавления заказов
        List<String> dishes1 = List.of("Pizza", "Pasta");
        Order order1 = new Order("001", "Alice", dishes1);
        restaurant.addOrder(order1);

        List<String> dishes2 = List.of("Burger", "Fries");
        Order order2 = new Order("002", "Bob", dishes2);
        restaurant.addOrder(order2);

        // Поиск заказа по номеру
        System.out.println("Finding order 001:");
        System.out.println(restaurant.findOrder("001"));

        // Удаление заказа
        System.out.println("Removing order 001:");
        restaurant.removeOrder("001");

        // Вывод всех оставшихся заказов
        System.out.println("Displaying all orders:");
        restaurant.displayAllOrders();

        // Удаление всех заказов
        System.out.println("Removing all orders:");
        restaurant.removeAllOrders();

        // Вывод всех заказов после удаления
        System.out.println("Displaying all orders after removing all:");
        restaurant.displayAllOrders();
    }
}

