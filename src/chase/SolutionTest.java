package chase;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 *
 */
public class SolutionTest {
    public static void main(String[] args) {
        class Person {
            String name;
            int age;

            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public String toString() {
                return name;
            }
        }

        // 构建一个 Person 集合
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));
        System.out.println(persons);
//        System.out.println(persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList()));
//        System.out.println(persons.stream().filter(p -> p.name.startsWith("P")).collect(Collectors.toList()));
        Map<Integer, List<Person>> collect = persons.stream().collect(Collectors.groupingBy(p -> p.age));
//        persons.stream().collect(Collectors.averagingInt())
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        if (stack == null && minStack == null) {
            stack.push(x);
            minStack.push(x);
        } else {
            if (x > minStack.peek()) {
                minStack.push(minStack.peek());
                stack.push(x);
            } else {
                stack.push(x);
                minStack.push(x);
            }
        }
    }

    public void pop() {
        minStack.pop();
        stack.pop();
    }

    public int top() {
        return Integer.valueOf(stack.peek());
    }

    public int getMin() {
        return stack.peek();
    }


}

