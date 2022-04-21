package construction;

import java.util.Stack;

public class SortedStack {

//    栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。
//    最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
//    该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
//    示例1:
//    输入：
//            ["SortedStack", "push", "push", "peek", "pop", "peek"]
//            [[], [1], [2], [], [], []]
//    输出：
//            [null,null,null,1,null,2]
//    链接：https://leetcode-cn.com/problems/sort-of-stacks-lcci

    private final Stack<Integer> stack1;
    private final Stack<Integer> stack2;

    public SortedStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int val) {

    }

    public void pop() {
        if (stack1.isEmpty()&&stack2.isEmpty()){
            return;
        }
        if (stack1.isEmpty()){
            stack2.pop();
            return;
        }
        if (stack2.isEmpty()){
            stack1.pop();
            return;
        }
        Integer integer = stack1.peek() <= stack2.peek() ? stack1.pop() : stack2.pop();
    }

    public int peek() {
        if (stack1.isEmpty()&&stack2.isEmpty()){
            return -1;
        }
        if (stack1.isEmpty()){
            return stack2.pop();
        }
        if (stack2.isEmpty()){
            return stack1.pop();
        }
        return stack1.peek() <= stack2.peek() ? stack1.pop() : stack2.pop();
    }

    public boolean isEmpty() {
        return stack1.isEmpty()&&stack2.isEmpty();
    }

    public static void main(String[] args) {
        SortedStack sortedStack = new SortedStack();
        sortedStack.push(1);
        sortedStack.push(2);
        sortedStack.peek();
        sortedStack.pop();
        sortedStack.peek();

    }
}
