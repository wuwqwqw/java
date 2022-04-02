import org.junit.Test;

import java.util.*;

public class GraphAlgorithms {

//    设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。
//    机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
//    设计一种算法，寻找机器人从左上角移动到右下角的路径。
//    网格中的障碍物和空位置分别用 1 和 0 来表示。
//    返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
//    示例1:
//    输入:
//            [
//            [0,0,0],
//            [0,1,0],
//            [0,0,0]
//            ]
//    输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
//    解释:
//    输入中标粗的位置即为输出表示的路径，即0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
//    链接：https://leetcode-cn.com/problems/robot-in-a-grid-lcci

    // 这道题不加访问数组会超时
    List<List<Integer>> ansList = new ArrayList<List<Integer>>();
    boolean returnFlag = false;

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        boolean[][] vis = new boolean[obstacleGrid.length][obstacleGrid[0].length];
        dfs(obstacleGrid, 0, 0, vis);
        return ansList;
    }

    public void dfs(int[][] obstacleGrid, int col, int row, boolean[][] vis) {
        if (col >= obstacleGrid.length || row >= obstacleGrid[0].length || obstacleGrid[col][row] == 1 || returnFlag || vis[col][row]) {
            return;
        }
        if ((col == obstacleGrid.length - 1 && row == obstacleGrid[0].length - 1)) {
            returnFlag = true;
            ansList.add(Arrays.asList(col, row));
            return;
        }
        ansList.add(Arrays.asList(col, row));
        vis[col][row] = true;
        dfs(obstacleGrid, col + 1, row, vis);
        dfs(obstacleGrid, col, row + 1, vis);
        if (!returnFlag)
            ansList.remove(ansList.size() - 1);
    }

    @Test
    public void testDfs() {
        int[][] ints = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        List<List<Integer>> lists = pathWithObstacles(ints);
    }

//    节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
//    示例1:
//    输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
//    输出：true
//    链接：https://leetcode-cn.com/problems/route-between-nodes-lcci

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            if (!map.containsKey(graph[i][0])) {
                map.put(graph[i][0], new ArrayList<>());
            }
            map.get(graph[i][0]).add(graph[i][1]);
        }
        boolean vis[] = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            List<Integer> integers = map.get(poll);
            if (integers.isEmpty()) {
                continue;
            }
            for (Integer i : integers) {
                if (!vis[i]) {
                    if (target == i) {
                        return true;
                    } else {
                        vis[i] = true;
                        queue.offer(i);
                    }
                }
            }
        }
        return false;
    }


}
