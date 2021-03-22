package com.demo.sort.util;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 10:33 on 2021/3/22
 * @version V0.1
 * @classNmae BinarySearch
 */
public class BinarySearch {


    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] array1 = {3,3,3,4,5,6,9,9,9,10,11,25,25};
        System.out.println(binarySearch.findLastEqualOrLess(array1,8));
    }


    public int find(int[] array,int target){
        if(array.length ==0){
            return -1;
        }
        if(array.length>1){
            SortUtil.merge_sort(array,0,array.length-1);
        }
        SortUtil.printArray(array);
        int mid = 0;
        int low =0;
        int high = array.length-1;
        while (low <= high){
            mid = (low + high)>>1;
            if(array[mid]<target){
                low = mid + 1;
            }else if(array[mid]>target){
                high = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找第一个值等于给定值的元素
     */


    public int findFirstEqual(int[] array,int target){

        SortUtil.printArray(array);
        int mid ;
        int low =0;
        int high = array.length-1;
        while (low <= high){
            mid = (low + high)>>1;
            if(array[mid]<target){
                low = mid + 1;
            }else if(array[mid]>target){
                high = mid - 1;
            }else{
               if(mid!=0 && array[mid-1] ==target){
                    high = mid - 1;
               }else {
                   return mid;
               }

            }
        }
        return -1;
    }

    //查找最后一个值等于给定值的元素
    public int findLastEqual(int[] array,int target){

        SortUtil.printArray(array);
        int mid ;
        int low =0;
        int high = array.length-1;
        while (low <= high){
            mid = (low + high)>>1;
            if(array[mid]<target){
                low = mid + 1;
            }else if(array[mid]>target){
                high = mid - 1;
            }else{
                if(mid!=array.length -1 && array[mid+1] ==target){
                    low = mid + 1;
                }else {
                    return mid;
                }

            }
        }
        return -1;
    }

    //查找第一个大于等于给定值的元素

    public int findFirstEqualOrGreater(int[] array,int target){

        SortUtil.printArray(array);
        int mid ;
        int low =0;
        int high = array.length-1;
        while (low <= high){
            mid = (low + high)>>1;
            if(array[mid]<target){
                low = mid + 1;
            }else {
                if(mid!=0 && array[mid-1]>=target){
                    high = mid - 1;
                }else {
                    return mid;
                }
            }
        }
        return -1;
    }

    //查找最后一个小于等于给定值的元素
    public int findLastEqualOrLess(int[] array,int target){

        SortUtil.printArray(array);
        int mid ;
        int low =0;
        int high = array.length-1;
        while (low <= high){
            mid = (low + high)>>1;
            if(array[mid]<=target){
                if(mid!=array.length -1 && array[mid+1]<=target){
                    low = mid + 1;
                }else {
                    return mid;
                }
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
