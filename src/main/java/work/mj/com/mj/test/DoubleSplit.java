package work.mj.com.mj.test;

import org.junit.Test;

/**
 * 二分查找法
 */
public class DoubleSplit {
    @Test
    public void demo01() {
        int[] arr = {1, 3, 4, 5, 6, 7, 8, 9};
        int target = 5;
        Boolean result = duan(arr, target);
        System.out.println(result);
    }

    private Boolean duan(int[] arr, int target) {
        if (target > arr[arr.length - 1] || target < arr[0])
            return false;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > arr[mid])
                left = mid + 1;
            else if (target < arr[mid])
                right = mid - 1;
            else
                return true;
        }
        return false;
    }
}
