public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
    public int[] productIndices(int[] values, int target) {
        for(int i = 0;i < values.length-1;i++){
            if(values[i] == 0) continue;
            for(int j = i+1;j < values.length;j++){
                if(values[i]*values[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int[] lineArray = new int[rows*cols];

        if (values == null || rows == 0 || cols == 0) {
            return lineArray;
        }

        int left = 0,right = cols - 1,top = 0, bottom = rows- 1,index = 0;


        while(left <= right && top <= bottom){
            //Going Right
            for(int i = left;i <= right;i++) lineArray[index++] = values[top][i];
            top++;

            //Going Bottom
            for(int i = top;i <= bottom;i++) lineArray[index++] = values[i][right];
            right--;

            //Going Left
            if(top <= bottom){
                for(int i = right;i >= left;i--)  lineArray[index++] = values[bottom][i];
                bottom--;
            }
            

            //Going Up
            if(left <= right){
                for(int i = bottom;i >= top;i--)  lineArray[index++] = values[i][left];
                left++;
            }
            
        }

        return lineArray;
    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */
    public int[][] intPartitions(int n) {
        // Calculate the total number of partitions
        int partitionCount = countPartitions(n, n);
        int[][] result = new int[partitionCount][];
        int[] currentPartition = new int[n];
        int[] resultIndex = {0};
    
        generatePartitions(n, n, currentPartition, 0, result, resultIndex);
        return result;
    }
    
    private void generatePartitions(int n, int max, int[] currentPartition, int index, int[][] result, int[] resultIndex) {
        if (n == 0) {
            // Add the current partition to the result
            result[resultIndex[0]] = new int[index];
            for (int i = 0; i < index; i++) {
                result[resultIndex[0]][i] = currentPartition[i];
            }
            resultIndex[0]++;
            return;
        }
    
        for (int i = Math.min(max, n); i >= 1; i--) {
            currentPartition[index] = i;
            generatePartitions(n - i, i, currentPartition, index + 1, result, resultIndex);
        }
    }
    
    private int countPartitions(int n, int max) {
        if (n == 0) return 1;
        int count = 0;
        for (int i = Math.min(max, n); i >= 1; i--) {
            count += countPartitions(n - i, i);
        }
        return count;
    }

    public static void main(String[] args) {
        // you can test your code here
        Exercises x = new Exercises();
        int[][] nums = {
            {1, 2, 3, 4, 5, 6},
            {7, 8, 9, 10, 11, 12},
            {13, 14, 15, 16, 17, 18},
            {19, 20, 21, 22, 23, 24},
            {25, 26, 27, 28, 29, 30}
    };
    int rows = 5;
    int cols = 6;
        int[] m = x.spiralTraversal(nums, rows, cols);
        for(int i = 0;i < 30;i++){
            System.out.println(m[i]);
        }

    }
}