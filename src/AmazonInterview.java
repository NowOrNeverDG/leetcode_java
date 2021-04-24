public class AmazonInterview {
    //1041-Robot Bounded In Circle
    //The robot performs the instructions given in order, and repeats them forever.Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
    //Input: instructions = "GGLLGG"
    //Output: true
    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int head = 0;
        char[] arr = instructions.toCharArray();

        for (int time=0; time<4; time++) {
            for (int ins = 0; ins < arr.length;ins++) {
                if (arr[ins] == 'L') head = (head+3)%4;
                else if (arr[ins] == 'R') head = (head+1)%4;
                else {
                    if (head==0) x++;
                    else if (head==1) y--;
                    else if (head==2) x--;
                    else if (head==3) y++;
                }
            }
        }
        return x==0&y==0;
    }

    //200-Number of Islands
    //Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
    //Input: grid = [
    //  ["1","1","1","1","0"],
    //  ["1","1","0","1","0"],
    //  ["1","1","0","0","0"],
    //  ["0","0","0","0","0"]]
    //Output: 1
    public int numIslands(char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    helperDfs200(grid, i, j);
                }
            }
        }
        return count;
    }
    private void helperDfs200(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') { return; }
        grid[r][c] = '0';
        helperDfs200(grid,r-1,c);
        helperDfs200(grid,r+1,c);
        helperDfs200(grid,r,c-1);
        helperDfs200(grid,r,c+1);
    }

}
