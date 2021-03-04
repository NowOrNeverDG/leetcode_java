import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Backtracking {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
    }

    //78 - 不要求长度的 返回所有可能的值
    //Input: nums = [1,2,3]
    //Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    private static List<List<Integer>> output55 = new ArrayList();
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length+1; i++) {
            backtracking(new ArrayList<Integer>(), i, nums);
        }
        return output55;
    }
    private void backtracking (ArrayList<Integer> curr, int length, int[] nums) {
        //结束条件
        if (curr.size() == length) {
            output55.add(curr);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            curr.add(nums[i]);//做选择
            backtracking(curr, i, nums);
            curr.remove(curr.size()-1);//撤销选择
        }
    }

    //51-N Queue
    //Input: n = 4
    //Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        //棋盘所有位置标记 可用
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(board,0, res);
        return res;
    }
    private void dfs (char[][] board, int colIndex, List<List<String>> res) {
        //结束条件
        if (colIndex == board.length) {
            res.add(construct(board));
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if ( isValid(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, res);
                board[i][colIndex] = '.';
            }
        }
    }
    private boolean isValid (char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'Q' && (row - i == col - j || row + col == i + j || row == i)) {
                    return  false;
                }
            }
        }
        return true;
    }
    //把棋盘数组转化为string输出
    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();

        for (int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}