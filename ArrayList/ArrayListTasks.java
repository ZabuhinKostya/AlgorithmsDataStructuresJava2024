package ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListTasks {

    public static void main(String[] args) {
        // 1. Создание нового списка и добавление цветов
        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Orange");
        colors.add("Yellow");
        colors.add("Green");
        colors.add("Purple");
        System.out.println("Colors: " + colors);

        // 2. Итерация по элементам списка
        System.out.println("Iterating over colors:");
        for (String color : colors) {
            System.out.println(color);
        }

        // 3. Вставка элемента на первую позицию
        colors.add(0, "Blue");
        System.out.println("After inserting at first position: " + colors);

        // 4. Извлечение элемента по индексу
        String colorAtIndex = colors.get(2);
        System.out.println("Element at index 2: " + colorAtIndex);

        // 5. Обновление элемента списка
        colors.set(1, "Black");
        System.out.println("After updating second element: " + colors);

        // 6. Удаление третьего элемента
        colors.remove(2);
        System.out.println("After removing third element: " + colors);

        // 7. Поиск элемента
        String searchColor = "Red";
        if (colors.contains(searchColor)) {
            System.out.println("The color " + searchColor + " is found in the list.");
        } else {
            System.out.println("The color " + searchColor + " is not in the list.");
        }

        // 8. Сортировка списка
        Collections.sort(colors);
        System.out.println("After sorting: " + colors);

        // 9. Копирование списка в другой список
        List<String> colorsCopy = new ArrayList<>(colors);
        System.out.println("Copied list: " + colorsCopy);

        // 10. Реверс списка
        Collections.reverse(colors);
        System.out.println("After reversing: " + colors);

        // 11. Сравнение двух списков
        boolean isEqual = colors.equals(colorsCopy);
        System.out.println("Are the original and copied lists equal? " + isEqual);

        // 12. Очистка списка
        colors.clear();
        System.out.println("After clearing the list: " + colors);

        // 13. Проверка, пуст ли список
        boolean isEmpty = colors.isEmpty();
        System.out.println("Is the list empty? " + isEmpty);

        // 14. Увеличение размера списка
        colors.add("Pink");
        colors.add("Brown");
        System.out.println("After increasing size: " + colors);

        // 15. Подгонка емкости под текущий размер
        ((ArrayList<String>) colors).trimToSize();
        System.out.println("Trimmed list capacity.");
    }
}
