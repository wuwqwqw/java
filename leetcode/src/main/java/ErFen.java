public class ErFen {

//    一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
//    示例 1:
//    输入: [0,1,3]
//    输出: 2
//    链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof

    public int missingNumber(int[] nums) {
        if(nums[0]!=0)return 0;
        int left = 0,right = nums.length-1;
        if(right==0){
            if(nums[0]!=0)return 0;
            else return 1;
        }
        if(right==1){
            if(nums[0]!=0)return 0;
            else{
                if(nums[1]==1)
                    return 2;
                else return 1;
            }
        }
        while(left<right){
            if(nums[(left+right)/2]==(left+right)/2){
                left=(left+right)/2;
            }else{
                right=(left+right)/2;
            }
            if(right-left<=3){
                for(;left<right;left++){
                    if(left==nums[left]&&left+1!=nums[left+1]){
                        return left+1;
                    }
                }
            }
        }
        return nums[nums.length-1]+1;
    }
}
