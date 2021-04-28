
/**
 * This class represents 2 recursive methods: 1. Count - Checks how many times one string appears in another string.
 *  2.prince - Returns the shortest path from the given cell to the cell that contains the number -1 ("evil")
 *
 * @author Rina Gatz
 * @version 24/05/2020
 */
public class Ex14
{
    /**
     * A recursive static method, which returns the number of times that the given pattern string appears within the given str string as a sub-series
     * (if there is one, not necessarily continuous) 
     *
     * @param  pattern the given string to check within the longer string
     * @param  str  the given long  string  to check in him
     * @return  count the number of times that the given pattern string appears within the given str string
     */
    public static int count (String str, String pattern){
        if(pattern.length()>str.length()) // check if the str string is long enough to contain the pattern string
            return 0;
        return  count(str,pattern ,0 , 0); // call the private recursive method
    }
    // A private recursive method that returns the number of times that the given pattern string appears within the given str string as a sub-series
    private static int count (String str, String pattern,  int s, int p){
        if (p==pattern.length())
            return 1;
        if(str.length()==s)
            return  0;
        int equal=0;
        if(str.charAt(s)==pattern.charAt(p))
            equal=count(str,pattern,s+1,p+1);
        return count(str,pattern,s+1,p)+equal;
    }
    
    /**
     * A recursive static method, which returns the lowest number of steps from the given cell- "The Prince", to the cell that contains the number -1
     * - "The Evil". If there is no path with the allow jumps of -2 to 1, return -1. 
     *
     * @param  i is the given row index of the array to check from. is the start cell of the "prince"
     * @param  j is the given column index of the array to check from. is the start cell of the "prince"
     * @return  the shortest path from the given cell to the cell that contains the number -1, if there is path. otherwise return -1.
     */
    public static int prince(int[][] drm, int i, int j){
        int result=prince2(drm, i, j); //call the private recursive method
        if (result>=Integer.MAX_VALUE||result<0) //there is no path with the allow jumps of -2 to 1
            return -1; 
        return result; //return the shortest path
    }
    // A private recursive method that returns the lowest number of steps from the given cell, to the cell that contains the number -1
    private static int prince2(int[][] drm, int i, int j){
        if (drm[i][j]==-1) //is the destination
            return 1;
        int temp=drm[i][j]; //save the cell temporarily
        drm[i][j]=Integer.MAX_VALUE; //change the cell to avoid repetition
        // go to the next cell if it is valid. otherwise - return invalid value
        int p1=(isJampy(drm,i+1, j,temp))?prince2(drm, i+1,j):Integer.MAX_VALUE;
        int p2=(isJampy(drm,i-1, j,temp))?prince2(drm, i-1,j):Integer.MAX_VALUE;
        int p3=(isJampy(drm,i, j+1,temp))?prince2(drm, i,j+1):Integer.MAX_VALUE;
        int  p4=(isJampy(drm,i, j-1,temp))?prince2(drm, i,j-1):Integer.MAX_VALUE;
        drm[i][j]=temp; //change the cell to its original value
        if(Math.min(Math.min(p1,p2),Math.min(p3,p4))==Integer.MAX_VALUE||Math.min(Math.min(p1,p2),Math.min(p3,p4))<=0)// there is no path
            return  Integer.MAX_VALUE; //return invalid value
        return 1+Math.min(Math.min(p1,p2),Math.min(p3,p4)); //return the shortest path
    }
    //return true if the next cell is valid - is in the array, and if the difference between cells is -2 to 1, (jump is allowed). otherwise false return
    private static boolean isJampy(int[][] drm, int fi, int fj,int curr)
    {
        if(fi!=drm.length&&fj!=drm[0].length&&fi>=0&&fj>=0&&(drm[fi][fj]==-1||curr-drm[fi][fj]<3 &&curr-drm[fi][fj]>-2))
            return true;
        return false;
    }
} //end of class Ex14

