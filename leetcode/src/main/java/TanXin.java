import org.junit.Test;

public class TanXin {

//    在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
//    例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。
//    现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
//    示例:
//    输入: [5, 3, 1, 2, 3]
//    输出: [5, 1, 3, 2, 3]
//    示例：[7,6,6,9]
//    链接：https://leetcode-cn.com/problems/peaks-and-valleys-lcci

    @Test
    public void testWiggleSort(){
        wiggleSort(new int[]{3,5,2,1,6,4});
    }

    public void wiggleSort(int[] nums) {
        if (nums.length<=2){
            return;
        }
        boolean flag = nums[1] > nums[0];
        for (int i = 1;i<nums.length-1;++i){
            if (!flag){
                if ((nums[i-1]>=nums[i]&&nums[i+1]>=nums[i])){
                }else {
                    swap(nums, i);
                }
                flag = true;
            }else {
                if (nums[i-1]<=nums[i]&&nums[i+1]<=nums[i]){
                }else {
                    swap(nums, i);
                }
                flag = false;
            }
        }
    }

    private void swap(int[] nums, int i) {
        int tem = nums[i];
        nums[i] = nums[i +1];
        nums[i +1] = tem;
    }

//    给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
//    注意：n-m尽量最小，也就是说，找出符合条件的最短序列。
//    函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
//    输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
//    输出： [3,9]
//    链接：https://leetcode-cn.com/problems/sub-sort-lcci

    public int[] subSort(int[] array) {

        return null;
    }
}
