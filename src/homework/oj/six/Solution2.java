package homework.oj.six;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author leolu
 * @create 2019-11-05-17:56
 *
 * 判断一个单向链表是否为回文结构
 *
 * 输入
 * 4
 * 3 1 2 1
 * 4 1 2 2 1
 * 3 3 5 3
 * 6 a b c d c a
 *
 * 输出
 * true
 * true
 * true
 * false
 *
 **/

public class Solution2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        for (int i = 0; i < count; i++) {
            sc.nextLine();
            int nodeLength = sc.nextInt();
        }
    }

    static class Node{
        Object value;
        Node next;
        public Node(Object val){
            this.value = val;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            int n = scanner.nextInt();
            Main.Node head = new Main.Node();
            Main.Node temp = head;
            for (int j = 0; j < n; j++) {
                Main.Node node = new Main.Node();
                node.o = scanner.next();
                temp.next = node;
                temp = node;
            }

            Main.Node first = head;
            Main.Node second = head;
            while (second.next != null) {
                first = first.next;
                second = second.next;
                if (second.next != null) {
                    second = second.next;
                }
            }
            Stack<Object> stack = new Stack<>();
            while (first.next != null) {
                first = first.next;
                stack.push(first.o);
            }
            boolean result = true;
            while (!stack.empty()) {
                if (!head.next.o.equals(stack.pop())) {
                    result = false;
                    break;
                }
                head = head.next;
            }
            System.out.println(result);
        }

    }
    static class Node{
        Object o;
        Main.Node next;
    }
}