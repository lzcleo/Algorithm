package leetcode.search.bfs;

import java.util.*;

/**
 * 有 N 个网络节点，标记为 1 到 N。
 *
 * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
 */
public class 网络延迟时间 {
    // Map<Integer, Integer> dist;
    // public int networkDelayTime(int[][] times, int N, int K) {
    //     Map<Integer, List<int[]>> graph = new HashMap();
    //     for (int[] edge: times) {
    //         if (!graph.containsKey(edge[0]))
    //             graph.put(edge[0], new ArrayList<int[]>());
    //         graph.get(edge[0]).add(new int[]{edge[2], edge[1]});
    //     }
    //     for (int node: graph.keySet()) {
    //         Collections.sort(graph.get(node), (a, b) -> a[0] - b[0]);
    //     }
    //     dist = new HashMap();
    //     for (int node = 1; node <= N; ++node)
    //         dist.put(node, Integer.MAX_VALUE);

    //     dfs(graph, K, 0);
    //     int ans = 0;
    //     for (int cand: dist.values()) {
    //         if (cand == Integer.MAX_VALUE) return -1;
    //         ans = Math.max(ans, cand);
    //     }
    //     return ans;
    // }

    // public void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
    //     if (elapsed >= dist.get(node)) return;
    //     dist.put(node, elapsed);
    //     if (graph.containsKey(node))
    //         for (int[] info: graph.get(node))
    //             dfs(graph, info[1], elapsed + info[0]);
    // }
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        // 初始化邻接表
        for (int[] t : times) {
            map.computeIfAbsent(t[0], k -> new ArrayList<>()).add(new int[]{t[1], t[2]});
        }

        // 初始化dis数组和vis数组
        int[] dis = new int[N + 1];
        Arrays.fill(dis, 0x3f3f3f3f);
        boolean[] vis = new boolean[N + 1];

        // 起点的dis为0，但是别忘记0也要搞一下，因为它是不参与的，我计算结果的时候直接用了stream，所以这个0也就要初始化下了
        dis[K] = 0;
        dis[0] = 0;

        // new一个小堆出来，按照dis升序排，一定要让它从小到大排，省去了松弛工作
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> dis[o1] - dis[o2]);
        // 把起点放进去
        queue.offer(K);

        while (!queue.isEmpty()) {
            // 当队列不空，拿出一个源出来
            Integer poll = queue.poll();
            if (vis[poll]) continue;
            // 把它标记为访问过
            vis[poll] = true;
            // 遍历它的邻居们，当然可能没邻居，这里用getOrDefault处理就很方便
            List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                // 如果这个邻居访问过了，继续
                if (vis[next]) continue;
                // 更新到这个邻居的最短距离，看看是不是当前poll出来的节点到它更近一点
                dis[next] = Math.min(dis[next], dis[poll] + arr[1]);
                queue.offer(next);
            }
        }
        // 拿到数组中的最大值比较下，返回结果
        int res = Arrays.stream(dis).max().getAsInt();
        return res == 0x3f3f3f3f ? -1 : res;
    }

    // dist[node] 记录的是信号最早到达 node 的时间。当我们访问 node 时，若经过了传递时间这个信号是最早到达该节点的，则我们广播这个信号
    // 为了加快速度，在访问每个节点时，若传递该信号的时间比已有信号到达的时间长，则我们退出该信号。

}
