package com.malaone.interview;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Auther: xulifei
 * @Date: 2019-04-22 18:10
 * @Description:
 */
public class Interview {

    public static void main(String[] args) {
//        testSort1();
//        testGetZeroReplaceInt();
//        System.out.println(romanToInt("III"));
//        System.out.println(threeSum(new int[]{-1, -1, -4, 0, 1, 2}));
//        System.out.println(reverse(-10));
//        System.out.println(isPalindrome(121));
//        System.out.println(totalNQueens(1));
//        int[][] matrix = {{1}};
//        System.out.println(spiralOrder(matrix));
//        threeSumClosest(new int[] {-1,2,1,-4},1);
        System.out.println(letterCombinations("23"));
    }




    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits != null && digits.length() > 0) {
            letterRecursion("", 0, digits, result);
        }
        return result;
    }

    private static void letterRecursion(String curStr, int numIndex, String digits, List<String> result) {
        if (numIndex >= digits.length()) {
            result.add(curStr);
        } else {
            String numMapStr = numMapStr(digits.charAt(numIndex));
            for (int i = 0; i < numMapStr.length(); i++) {
                letterRecursion(curStr + numMapStr.charAt(i), numIndex + 1, digits, result);
            }
        }
    }

    private static String numMapStr(char no) {
        String str;
        switch (no) {
            case '2':
                str = "abc";
                break;
            case '3':
                str = "def";
                break;
            case '4':
                str = "ghi";
                break;
            case '5':
                str = "jkl";
                break;
            case '6':
                str = "mno";
                break;
            case '7':
                str = "pqrs";
                break;
            case '8':
                str = "tuv";
                break;
            case '9':
                str = "wxyz";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }


    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            throw new IllegalArgumentException("array length must bigger than 2");
        }

        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        int gap = Math.abs(sum - target);

        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1;

            while (l < r) {
                int tmp = nums[i] + nums[l] + nums[r];
                if (Math.abs(tmp - target) < gap) {
                    gap = Math.abs(tmp - target);
                    sum = tmp;
                }
                if (tmp < target) {
                    l++;
                } else if (tmp > target) {
                    r--;
                } else {
                    return sum;
                }
            }
        }
        return sum;
    }

    private int num = 1;

    public int[][] generateMatrix(int n) {
        if (n < 0) {
            return null;
        }

        int[][] matrix = new int[n][n];
        round(0, matrix, n);
        return matrix;
    }

    private void round(int roundNum, int[][] matrix, int n) {
        if (matrix == null || matrix.length == 0 || num > n * n
                || roundNum >= matrix.length - roundNum || roundNum >= matrix[0].length - roundNum) {
            return;
        }

        int i = roundNum, j = roundNum;

        while (num <= n * n && j < matrix[0].length - roundNum) {
            matrix[i][j++] = num++;
        }
        i++;
        j--;

        while (num <= n * n && i < matrix.length - roundNum) {
            matrix[i++][j] = num++;
        }
        i--;
        j--;

        while (num <= n * n && j >= roundNum) {
            matrix[i][j--] = num++;
        }
        i--;
        j++;

        while (num <= n * n && i > roundNum) {
            matrix[i--][j] = num++;
        }
        round(roundNum + 1, matrix, n);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        round(0, matrix, list);

        return list;
    }

    private static void round(int roundNum, int[][] matrix, List<Integer> list) {
        if (matrix == null || matrix.length == 0
            || roundNum >= matrix.length - roundNum || roundNum >= matrix[0].length - roundNum) {
            return;
        }

        int i = roundNum, j = roundNum;
        if (roundNum == matrix.length - roundNum - 1) {//只有一行
            while (j < matrix[0].length - roundNum) {
                list.add(matrix[i][j++]);
            }
            return;
        }
        if (roundNum == matrix[0].length - roundNum - 1) {//只有一列
            while (i < matrix.length - roundNum) {
                list.add(matrix[i++][j]);
            }
            return;
        }

        while (j < matrix[0].length - roundNum) {
            list.add(matrix[i][j++]);
        }
        i++;
        j--;

        while (i < matrix.length - roundNum) {
            list.add(matrix[i++][j]);
        }
        i--;
        j--;

        while (j >= roundNum) {
            list.add(matrix[i][j--]);
        }
        i--;
        j++;

        while (i > roundNum) {
            list.add(matrix[i--][j]);
        }

        round(roundNum + 1, matrix, list);
    }


    public static int[][] lcsLength(Object[] x,Object[] y){
        int m=x.length;
        int n=y.length;
        int[][] c = new int[m+1][n+1];
        int i,j;
        for(i=1;i<=m;i++){
            c[i][0]=0;
        }
        for(j=0;j<=n;j++){
            c[0][j]=0;
        }
        for(i=1;i<=m;i++){
            for(j=1;j<=n;j++){
                if(x[i-1].equals(y[j-1])){
                    c[i][j]=c[i-1][j-1]+1;
                }else if(c[i-1][j]>=c[i][j-1]){
                    c[i][j]=c[i-1][j];
                }else{
                    c[i][j]=c[i][j-1];
                }
            }
        }
        return c;
    }

    private static void printLcs(int[][] c,Object[] x,Object[] y,int i,int j){
        if(i==0||j==0){
            return ;
        }
        if(x[i-1].equals(y[j-1])){
            printLcs(c,x,y,i-1,j-1);
            System.out.print(x[i-1]+" ");
        }else if(c[i-1][j]>=c[i][j-1]){
            printLcs(c, x, y, i-1, j);
        }else{
            printLcs(c, x, y, i, j-1);
        }
    }

    private static int total = 0;

    public static int totalNQueens(int n) {
        int[] rows = new int[n];
        perRowQueen(0, rows, n);
        return total;
    }

    private static void perRowQueen(int row, int[] rows, int n) {
        if (row >= n) {
            total++;
            return;
        }

        for (int i = 0; i < n; i++) {
            rows[row] = i;
            if (checkPerRow(row, rows)) {
                perRowQueen(row + 1, rows, n);
            }
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        int[] rows = new int[n];
        perRowQueen(0, rows, n, list);
        return list;
    }

    private static void perRowQueen(int row, int[] rows, int n, List<List<String>> list) {
        if (row >= n) {
            List<String> result = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (rows[i] == j) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                result.add(sb.toString());
            }
            list.add(result);
            return;
        }

        for (int i = 0; i < n; i++) {
            rows[row] = i;
            if (checkPerRow(row, rows)) {
                perRowQueen(row + 1, rows, n, list);
            }
        }
    }

    private static boolean checkPerRow(int row, int[] rows) {
        for (int i = 0; i < row; i++) {
            if (rows[row] == rows[i] || Math.abs(row - i) == Math.abs(rows[row] - rows[i])) {
                return false;
            }
        }

        return true;
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] newNums = new int[nums1.length + nums2.length];

        int i = 0, j = 0, n = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                newNums[n++] = nums1[i++];
            } else {
                newNums[n++] = nums2[j++];
            }
        }

        while (i < nums1.length ) {
            newNums[n++] = nums1[i++];
        }

        while (j < nums2.length ) {
            newNums[n++] = nums2[j++];
        }

        return ((double) newNums[newNums.length / 2] + newNums[(newNums.length - 1) / 2]) / 2;
    }


    public static int maxArea(int[] height) {
        if (height == null) {
            return 0;
        }

        int l = 0, r = height.length - 1;
        int max = 0;

        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

    public static boolean isPalindrome(int x) {
        StringBuilder sb = new StringBuilder();
        sb.append(x).reverse();
        return sb.toString().equals(String.valueOf(x));
    }

    public static int reverse(int x) {
        if(x==0) return 0;
        boolean flag = x<0;

        StringBuilder sb = new StringBuilder();
        sb.append(x);

        if(flag) {
            sb.deleteCharAt(0);
        }
        sb.reverse();

        int zeros=0;
        for(int i=0;i<sb.length();i++) {
            if(sb.charAt(i)=='0') {
                zeros++;
            } else{
                break;
            }
        }

        String str = sb.substring(zeros);
        if(flag) str = "-" + str;
        long result = Long.valueOf(str);

        if(result<=Integer.MAX_VALUE&&result>=Integer.MIN_VALUE)
            return (int)result;


        return 0;
    }


    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        StringBuilder result = new StringBuilder();

        int row = 0;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            rows.get(row).append(s.charAt(i));

            if (row == 0 || row == numRows - 1) {
                flag = !flag;
            }

            row += flag ? 1 : -1;
        }

        for (StringBuilder sb : rows) {
            result.append(sb);
        }


        return result.toString();

    }


    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();

        for(int i=0; i<nums.length-2; i++) {
            int l = i+1;
            int r = nums.length-1;
            int sum = 0-nums[i];

            while(l<r) {
                if(nums[l] + nums[r] == sum) {
                    list.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while(l<r && nums[l]==nums[l+1]){
                        l++;
                    }
                    while(l<r && nums[r]==nums[r-1]) {
                        r--;
                    }
                    l++;
                    r--;
                } else if(nums[l] + nums[r] < sum) {
                    while(l<r && nums[l]==nums[l+1]){
                        l++;
                    }
                    l++;
                } else{
                    while(l<r && nums[r]==nums[r-1]) {
                        r--;
                    }
                    r--;
                }
            }

        }
        return list;
    }

    public static int romanToInt(String s) {
        Map<String,Integer> map = new HashMap<>();
        map.put("M",1000);
        map.put("CM",900);
        map.put("D",500);
        map.put("CD",400);
        map.put("C",100);
        map.put("XC",90);
        map.put("L",50);
        map.put("XL",40);
        map.put("X",10);
        map.put("IX",9);
        map.put("V",5);
        map.put("IV",4);
        map.put("I",1);

        int len = s.length();
        int value=0;
        int i = 0;

        while(i<len) {
            if(i<len-1) {
                String key = s.substring(i,i+2);
                if(map.containsKey(key)) {
                    value+=map.get(key);
                    i+=2;
                    continue;
                }
            }
            value+=map.get(s.substring(i,i+1));
            i++;
        }
        return value;
    }



    public static void testGetZeroReplaceInt() {
        int a[] = {5, 1, 2, 6, 0, 4, 3, 8};
        System.out.println("zero replace int: " + getZeroReplaceInt(a));

    }


    public static int getZeroReplaceInt(int[] a) {
        int len = a.length;
        int sum = 0;

        for (int i = 0; i < len; i++) {
            sum += a[i];
        }
        return (1 + len) * len / 2 - sum;
    }



    public static void testSort1() {
        int a[] = {1, 0, 1, 1, 0, 0, 0, 1};
        System.out.println("before sort:" + Arrays.toString(a));
        sort1(a);
        System.out.println("after sort:" + Arrays.toString(a));

    }

    /**
     * 通联数据，要求用双指针
     * 可以遍历两遍，将和累加，后面直接插入0
     *
     * @param a 数组中只有0和1
     */
    public static void sort1(int[] a) {
        int l=0;
        int r = a.length - 1;

        while (l < r) {

            if (a[l] == 1) {
                while(l < r) {
                    if (a[r] == 0) {
                        int tmp = a[l];
                        a[l] = a[r];
                        a[r] = tmp;
                        break;
                    }
                    r--;
                }

            }
            l++;
        }

    }
}
