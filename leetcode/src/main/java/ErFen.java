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

//    在两个长度相等的排序数组中找到上中位数
//    给定两个递增数组arr1和arr2，已知两个数组的长度都为N，求两个数组中所有数的上中位数。
//    上中位数：假设递增序列长度为n，为第n/2个数
//    要求：时间复杂度 O(n)，空间复杂度 O(1)
//    进阶：时间复杂度为O(logN)，空间复杂度为O(1)
//    https://www.nowcoder.com/practice/6fbe70f3a51d44fa9395cfc49694404f?tpId=196&tqId=37141&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Fpage%3D2%26pageSize%3D50%26search%3D%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D196&difficulty=undefined&judgeStatus=undefined&tags=&title=

    public int findMedianinTwoSortedAray (int[] arr1, int[] arr2) {
        if (arr1[0]>arr2[0]){
            int[] tem = arr1;
            arr1 = arr2;
            arr2 = tem;
        }
        if (arr1[arr1.length-1]<arr2[0]){
            return arr1[arr1.length-1];
        }
        return 1;
    }
}
