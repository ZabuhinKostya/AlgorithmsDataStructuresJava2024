package LInkedList;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Создаем временный узел, который будет началом нового списка
        ListNode temporary = new ListNode(-1);
        ListNode current = temporary;

        // Пока оба списка не пусты
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Если один из списков пуст, присоединяем оставшиеся элементы другого списка
        if (list1 != null) {
            current.next = list1;
        } else if (list2 != null) {
            current.next = list2;
        }

        // Возвращаем объединенный список, начиная со следующего узла после фиктивного
        return temporary.next;
    }
}