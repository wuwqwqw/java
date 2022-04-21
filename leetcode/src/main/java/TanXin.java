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

//    给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
//    找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
//    返回容器可以储存的最大水量。
//    说明：你不能倾斜容器。
//    示例 1：
//    输入：[1,8,6,2,5,4,8,3,7]
//    输出：49
//    解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
//    链接：https://leetcode-cn.com/problems/container-with-most-water

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

    @Test
    public void testMaxArea(){
        maxArea(new int[]{1,3,2,5,25,24,5});
    }

//    给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
//    数组中的每个元素代表你在该位置可以跳跃的最大长度。
//    判断你是否能够到达最后一个下标。
//    示例1：
//    输入：nums = [2,3,1,1,4]
//    输出：true
//    解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
//    链接：https://leetcode-cn.com/problems/jump-game

    public boolean canJump(int[] nums) {
        for(int i=0;i<nums.length;){
            int max=0;
            int max_pos=0;
            if(nums[i]+i>=nums.length-1)return true;
            for(int j=1;j<=nums[i];j++){
                if(j+i+nums[j+i]>=max+max_pos){
                    max=nums[j+i];
                    max_pos=j+i;
                }
            }
            if(max==0)return false;
            i=max_pos;
        }
        return false;
    }
}
