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

//    你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
//    若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。
//    编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
//    示例：
//    输入：
//            [
//            [0,2,1,0],
//            [0,1,0,1],
//            [1,1,0,1],
//            [0,1,0,1]
//            ]
//    输出： [1,2,4]
//    链接：https://leetcode-cn.com/problems/pond-sizes-lcci

    boolean[][] vis;
    LinkedList<Integer> list = new LinkedList<>();
    int count;

    public int[] pondSizes(int[][] land) {
        vis = new boolean[land.length][land[0].length];
        for (int i = 0;i<land.length;++i){
            for (int j = 0;j<land[0].length;++j){
                if (!vis[i][j]){
                    count = 0;
                    dfs(land,i,j);
                    if (count!=0)
                    list.add(count);
                }
            }
        }
        int[] ints = new int[list.size()];
        for (int i = 0;i<list.size();++i){
            ints[i] = list.get(i);
        }
        Arrays.sort(ints);
        return ints;
    }

    private void dfs(int[][] land, int i, int j) {
        if (i<0||j<0||i>= land.length||j>= land[0].length||land[i][j]!=0||vis[i][j]){
            return;
        }
        vis[i][j] = true;
        count++;
        dfs(land,i+1,j+1);
        dfs(land,i+1,j-1);
        dfs(land,i+1,j);
        dfs(land,i,j-1);
        dfs(land,i,j+1);
        dfs(land,i-1,j);
        dfs(land,i-1,j-1);
        dfs(land,i-1,j+1);
    }


//    现在你总共有 numCourses 门课需要选，记为0到numCourses - 1。给你一个数组prerequisites ，
//    其中 prerequisites[i] = [ai, bi]，表示在选修课程ai前必须先选修bi 。
//    例如，想要学习课程 0 ，你需要先完成课程1 ，我们用一个匹配来表示：[0,1] 。
//    返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
//    示例 1：
//    输入：numCourses = 2, prerequisites = [[1,0]]
//    输出：[0,1]
//    解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
//    链接：https://leetcode-cn.com/problems/course-schedule-ii

    List<List<Integer>> edges;
    int[] visited;
    int[] result;
    boolean valid = true;
    int index;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        result = new int[numCourses];
        index = numCourses - 1;
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        if (!valid) {
            return new int[0];
        }
        return result;
    }

    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            }
            else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
        result[index--] = u;
    }
}
