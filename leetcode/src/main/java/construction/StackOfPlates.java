package construction;

//    堆盘子。设想有一堆盘子，堆太高可能会倒下来。
//    因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。
//    请实现数据结构SetOfStacks，模拟这种行为。
//    SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。
//    此外，SetOfStacks.push()和SetOfStacks.pop()
//    应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。
//    进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
//    当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt应返回 -1.
//    示例1:
//    输入：
//            ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
//            [[1], [1], [2], [1], [], []]
//    输出：
//            [null, null, null, 2, 1, -1]
//    链接：https://leetcode-cn.com/problems/stack-of-plates-lcci

import java.util.*;

public class StackOfPlates {

    private final List<Stack<Integer>> stackList;
    private final Integer cap;

    public StackOfPlates(int cap) {
        this.stackList = new ArrayList<>();
        this.cap = cap;
    }

    // push的时候直接在最后push，不用遍历
    public void push(int val) {
        if (stackList.size()==0){
            stackList.add(new Stack<>());
        }
        if (stackList.get(stackList.size()-1).size()<cap){
            stackList.get(stackList.size()-1).push(val);
        }else {
            Stack<Integer> integers = new Stack<>();
            integers.push(val);
            stackList.add(integers);
        }
    }

    public int pop() {
        if (stackList.size()>0&&stackList.get(0).size()>0){
            Integer pop = stackList.get(stackList.size() - 1).pop();
            if (stackList.get(stackList.size() - 1).size()==0){
                stackList.remove(stackList.size()-1);
            }
            return pop;
        }else {
            return -1;
        }

    }

    public int popAt(int index) {
        if (stackList.size()>index||index<0){
            if (stackList.get(index).size()==0){
                return -1;
            }else {
                Integer pop = stackList.get(index).pop();
                if (stackList.get(index).size()==0){
                    stackList.remove(index);
                }
                return pop;
            }
        }else {
            return -1;
        }
    }
}