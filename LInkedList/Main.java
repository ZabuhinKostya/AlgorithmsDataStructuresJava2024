package LInkedList;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode result1 = solution.mergeTwoLists(list1, list2);
        System.out.print("Example 1 Output: ");
        printList(result1);

       
        ListNode list3 = null;
        ListNode list4 = null;
        ListNode result2 = solution.mergeTwoLists(list3, list4);
        System.out.print("Example 2 Output: ");
        printList(result2);

        
        ListNode list5 = null;
        ListNode list6 = new ListNode(0);
        ListNode result3 = solution.mergeTwoLists(list5, list6);
        System.out.print("Example 3 Output: ");
        printList(result3);
    }

    // Method for displaying a LinkedList
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
    
}
