import java.lang.reflect.Array;

public class StockSales {
    public static void main(String[] args) {
        int[] ans = {1,2,3,4,5};
        System.out.println("Answer is : " + maxProfitIII2(ans));
        System.out.println("Answer is : " + maxProfitIII(ans));

    }
    //121 Best Time to Buy and Sell Stock (只能做一次交易)
    //Input: prices = [7,1,5,3,6,4]
    //Output: 5
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    //122 Best Time to Buy and Sell Stock II (可以做无限次交易)
    //Input: prices = [7,1,5,3,6,4]
    //Output: 7
    public int maxProfitII(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_i_0-prices[i]);
        }
        return dp_i_0;
    }

    //123 Best Time to Buy and Sell Stock III
    //Input: prices = [3,3,5,0,0,3,1,4]
    //Output: 6
    public static int maxProfitIII(int[] prices) {
        int n = prices.length;
        int k = 2;
        int[][][] dp = new int[n][k+1][2];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0][0] = 0;
                dp[i][1][0] = Integer.MIN_VALUE;
                dp[i][1][1] = -prices[i];
                dp[i][2][0] = Integer.MIN_VALUE;
                dp[i][2][1] = -prices[i];
                continue;
            }
                dp[i][2][0] = Math.max(dp[i-1][2][0], dp[i-1][2][1] + prices[i]);
                dp[i][2][1] = Math.max(dp[i-1][2][1], dp[i-1][1][0] -prices[i]);
                dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
                dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][1][0] - prices[i]);
        }
        return dp[n-1][2][0];
    }
    public static int maxProfitIII2(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;

        for (int price: prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, - price);
        }
        return dp_i20;
    }

    //714.Best Time to Buy and Sell Stock
    //Input: prices = [1,3,2,8,4,9], fee = 2
    //Output: 8
    public static int maxProfitWithFee(int[] prices, int fee) {
        int n = prices.length;
        int dp_i0 = 0, dp_i1 = Integer.MIN_VALUE;

        for (int i:prices) {
            dp_i0 = Math.max(dp_i0, dp_i1 + i);
            dp_i1 = Math.max(dp_i1, dp_i0 - i - fee);
        }
        return dp_i0;
    }

    //309. Best Time to Buy and Sell Stock with Cooldown
    //Input: prices = [1,2,3,0,2]
    //Output: 3
    public int maxProfitWithCooldown(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = - prices[i];
                continue;
            }
            if (i == 1) {
                dp[i][1] = Math.max(dp[i-1][1], - prices[i]);
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }
        return dp[n-1][0];
    }
    public int maxProfitWithCooldown2(int[] prices) {
        int n = prices.length;
        int dpi0 = 0, dpi1 = 0, dppre0 = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dpi0 = 0;
                dpi1 = -prices[i];
                continue;
            } else if (i == 1) {
                dpi1 = Math.max(dpi1, - prices[i]);
                dpi0 = Math.max(dpi0, dpi1 + prices[i]);
                continue;
            }
            int dptemp = dpi0;
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, dppre0 - prices[i]);
            dppre0 = dptemp;
        }
        return dpi0;
    }

    //188. Best Time to Buy and Sell Stock IV
    //Input: k = 2, prices = [3,2,6,5,0,3]
    //Output: 7
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][k+1][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if (i == 0) {
                    switch (j){
                        case 0: dp[i][j][0] = 0; dp[i][j][1] = -prices[i]; break;
                        default: dp[i][j][0] = Integer.MIN_VALUE; dp[i][j][1] = 0;
                    }
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i][j][1] - prices[i]);
            }
        }
        return dp[n-1][k][0];
    }

}


