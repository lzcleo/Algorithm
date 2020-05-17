package homework.oj.eight;

import org.junit.Test;

import java.util.*;

/**
 * @author leolu
 * @create 2019-11-25-13:26
 **/
public class Main {
    /**
     * 按照数值个数排序 对给定数组中的元素按照元素出现的次数排序，出现次数多的排在前面
     * 如果出现次数相同，按照数值大小排序
     *
     * 输入
     * 第一行为用例个数，每一个用例第一行为数组长度，第二行为数组内容
     *
     * 输入
     * 1
     * 4
     * 2 3 2 5
     *
     * 输出
     * 2 2 3 5
     */

    public static void main8(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int t = 0; t < times; t++) {
            int n = scanner.nextInt();
            Map<Integer, Integer> count = new HashMap<>();
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int num = scanner.nextInt();
                count.merge(num, 1, (a, b) -> a + b);
            }
            for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                if (map.get(entry.getValue()) != null) {
                    map.get(entry.getValue()).add(entry.getKey());
                } else {
                    map.put(entry.getValue(), new ArrayList<>(Collections.singletonList(entry.getKey())));
                }
            }
            List<Integer> list = new ArrayList<>(count.values());
            Collections.sort(list);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = list.size() - 1; i >= 0; i--) {
                List<Integer> list2 = map.get(list.get(i));
                Collections.sort(list2);
                for (Integer integer : list2) {
                    for (int k = 0; k < i; k++) {
                        stringBuilder.append(integer).append(" ");
                    }
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            System.out.println(stringBuilder.toString());
        }
    }

    /**
     *
     * 拓扑排序解的个数 给定有向无环图多种所有边，计算图的拓扑排序解的个数
     * 第一行为用例个数，后面每一行表示一个图中的所有边，边的起点和终点用空格隔开
     * 边之间用逗号隔开
     * 输出 拓扑排序解的个数
     *
     * 输入
     * 1
     * a c,b c,c d,d e,d f,e g,f g
     *
     * 输出
     * 4
     */
    public static void main7(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        scanner.nextLine();
        for (int t = 0; t < times; t++) {
            String str = scanner.nextLine();
            String[] edges = str.split(",");
            Map<Character, Node> map = new HashMap<>();
            for (String edge : edges) {
                Character source = edge.charAt(0);
                Character target = edge.charAt(2);
                map.putIfAbsent(target, new Node(target, false));
                //可能每次都要false
                if (map.get(source) != null) {
                    map.get(source).add(map.get(target));
                } else {
                    map.put(source, new Node(source).add(map.get(target)));
                }
            }
            Integer count = 0;
            for (Node node : map.values()) {
                if (node.head) {
                    count += node.findTrace();
                }
            }
            System.out.println(count);
        }
    }

    static class Node {
        Character val;
        List<Node> next;
        boolean head;

        Node(Character val) {
            this.val = val;
            next = new ArrayList<>();
            head = true;
        }

        Node(Character val, boolean head) {
            this.val = val;
            next = new ArrayList<>();
            this.head = head;
        }

        Node add(Node next) {
            this.next.add(next);
            return this;
        }

        Integer findTrace() {
            if (next.size() == 0) {
                return 1;
            }
            int count = 0;
            for (Node node : next) {
                count += node.findTrace();
            }
            return count;
        }

        Integer findDeep(String trace) {
            if (next.size() == 0) {
                return 1;
            }
            int count = 1;
            for (Node node : next) {
                if (!trace.contains(String.valueOf(node.val))) {
                    count = Math.max(node.findDeep(trace + node.val) + 1, count);
                }

            }
            return count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(val, node.val);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }

    /**
     * 广度优先遍历图
     *
     * 输入
     * 1
     * 4 a
     * a b c d
     * a 0 1 1 0
     * b 1 0 1 0
     * c 1 1 0 1
     * d 0 0 1 0
     *
     * 输出
     * a b c d
     *
     * @param args
     */
    public static void main6(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int t = 0; t < times; t++) {
            int num = scanner.nextInt();
            char head = scanner.next().charAt(0);
            scanner.nextLine();
            String str = scanner.nextLine();
            char[] chars = new char[num];
            Node[] nodes = new Node[num];
            for (int i = 0; i < num; i++) {
                chars[i] = str.charAt(i * 2);
                nodes[i] = new Node(chars[i]);
            }
            for (int i = 0; i < num; i++) {
                str = scanner.nextLine().substring(2);
                String[] vals = str.split(" ");
                for (int j = 0; j < vals.length; j++) {
                    if (vals[j].equals("1")) {
                        nodes[i].add(nodes[j]);
                    }
                }
            }

            for (Node node : nodes) {
                if (node.val == head) {
                    StringBuilder stringBuilder = new StringBuilder(String.valueOf(head)).append(" ");
                    List<Node> deep = node.next;
                    List<Node> deepTemp;
                    while (deep.size() != 0) {
                        deepTemp = new ArrayList<>();
                        for (Node ne : deep) {
                            if (!stringBuilder.toString().contains(String.valueOf(ne.val))) {
                                stringBuilder.append(ne.val).append(" ");
                                deepTemp.addAll(ne.next);
                            }
                        }
                        deep = deepTemp;
                    }
                    System.out.println(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
                    break;
                }
            }
        }
    }


    /**
     * 深度优先遍历
     *
     * 输入
     * 1
     * 4 a
     * a b c d
     * a 0 1 1 0
     * b 1 0 1 0
     * c 1 1 0 1
     * d 0 0 1 0
     *
     * 输出（最大深度）
     * 4
     *
     * @param args
     */
    public static void main5(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int t = 0; t < times; t++) {
            int num = scanner.nextInt();
            char head = scanner.next().charAt(0);
            scanner.nextLine();
            String str = scanner.nextLine();
            char[] chars = new char[num];
            Node[] nodes = new Node[num];
            for (int i = 0; i < num; i++) {
                chars[i] = str.charAt(i * 2);
                nodes[i] = new Node(chars[i]);
            }
            for (int i = 0; i < num; i++) {
                str = scanner.nextLine().substring(2);
                String[] vals = str.split(" ");
                for (int j = 0; j < vals.length; j++) {
                    if (vals[j].equals("1")) {
                        nodes[i].add(nodes[j]);
                    }
                }
            }

            for (Node node : nodes) {
                if (node.val == head) {
                    System.out.println(node.findDeep(String.valueOf(node.val)));
                    break;
                }
            }
        }
    }

    private static void shellSort(int[] nums, int step) {
        for (int i = 0; i < step; i++) {
            List<Integer> list = new ArrayList<>();
            int j = i;
            while (j < nums.length) {
                list.add(nums[j]);
                j += step;
            }
            Collections.sort(list);
            j = i;
            int index = 0;
            while (j < nums.length) {
                nums[j] = list.get(index++);
                j += step;
            }
        }
    }



    /**
     * 实现希尔排序
     * 输入
     * 1
     * 49 38 65 97 76 13 27 49 55 4
     * 5 3
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        scanner.nextLine();
        for (int t = 0; t < times; t++) {
            String numStr = scanner.nextLine();
            String[] numsStr = numStr.split(" ");
            int[] nums = new int[numsStr.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(numsStr[i]);
            }
            String step = scanner.nextLine();
            for (String s : step.split(" ")) {
                shellSort(nums, Integer.parseInt(s));
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i : nums) {
                stringBuilder.append(" ").append(i);
            }
            stringBuilder.deleteCharAt(0);
            System.out.println(stringBuilder);
        }
    }

    /**
     * 棋盘覆盖问题
     * <p>棋盘覆盖问题：给定一个大小为2^n2^n个小方格的棋盘，其中有一个位置已经被填充，
     * 现在要用一个L型（22个小方格组成的大方格中去掉其中一个小方格）形状去覆盖剩下的小方格。
     * 求出覆盖方案，即哪些坐标下的小方格使用同一个L型格子覆盖。
     * 注意：坐标从0开始。左上方的第一个格子坐标为(0,0)，第一行第二个坐标为(0,1)
     * ，第二行第一个为(1,0)，以此类推。</p>
     *
     * 输入
     * 1
     * 1 1 1
     * 0 0
     *
     * 输出
     * 0 1,1 0
     * @param args
     */
    @Test
    public static void main4(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int t = 0; t < times; t++) {
            int n = scanner.nextInt();
            int num = new Double(Math.pow(2, n)).intValue();
            int[][] nums = new int[num][num];
            int sourceX = scanner.nextInt();
            int sourceY = scanner.nextInt();
            int targetX = scanner.nextInt();
            int targetY = scanner.nextInt();

            int baseX = 0;
            int baseY = 0;
            int tempN = num;
            while (tempN != 2) {
                int l1 = location(sourceX - baseX, sourceY - baseY, tempN);
                int l2 = location(targetX - baseX, targetY - baseY, tempN);
                boolean center = center(targetX - baseX, targetY - baseY, tempN);
                int half = tempN/2;
                if (l1 != l2 && center) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String[] result = new String[]{(baseX + half - 1) + " " + (baseY + half - 1), (baseX + half - 1)
                            + " " + (baseY + half), (baseX + half) + " " + (baseY + half - 1), (baseX + half) + " "
                            + (baseY + half)};
                    int except1 = l1;
                    int except2 = location(targetX - baseX, targetY - baseY, tempN);
                    for (int i = 0; i < result.length; i++) {
                        if (i != except1 && i != except2) {
                            stringBuilder.append(",").append(result[i]);
                        }
                    }
                    stringBuilder.deleteCharAt(0);
                    System.out.println(stringBuilder);
                    tempN = 0;
                    break;
                }
                tempN /= 2;
                switch (l2) {
                    case 0:
                        if (l1 != l2) {
                            sourceX = baseX + tempN - 1;
                            sourceY = baseY + tempN - 1;
                        }
                        break;
                    case 1:
                        baseY += tempN;
                        if (l1 != l2) {
                            sourceX = baseX + tempN - 1;
                            sourceY = baseY;
                        }
                        break;
                    case 2:
                        baseX += tempN;
                        if (l1 != l2) {
                            sourceX = baseX;
                            sourceY = baseY + tempN - 1;
                        }
                        break;
                    case 3:
                        baseX += tempN;
                        baseY += tempN;
                        if (l1 != l2) {
                            sourceX = baseX;
                            sourceY = baseY;
                        }
                        break;
                }
            }

            if (tempN == 2) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        if ((baseX + x == sourceX && baseY + y == sourceY) || baseX + x == targetX && baseY + y == targetY) {
                            continue;
                        }
                        stringBuilder.append(",").append(baseX + x).append(" ").append(baseY + y);
                    }
                }
                stringBuilder.deleteCharAt(0);
                System.out.println(stringBuilder);
            }
        }
    }

    private static int location(int x, int y, int length) {
        int half = length / 2;
        if (x < half) {
            return y < half ? 0 : 1;
        } else {
            return y < half ? 2 : 3;
        }
    }

    private static boolean center(int x, int y, int length) {
        if (length == 2) {
            return false;
        }
        int half = length / 2;
        return x <= half && x >= half - 1 && y <= half && y >= half - 1;
    }
}
