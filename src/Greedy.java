import java.util.Arrays;

public class Greedy {

    //455-Assign Cookies
    //Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
    //Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
    //Input: g = [1,2,3], s = [1,1]
    //Output: 1
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);//需要饼干数
        Arrays.sort(s);//饼干个数
        int gp = 0, sp = 0, count = 0;
        while (gp<g.length&&sp<s.length) {
            if (g[gp]<=s[sp]) {
                count++;
                gp++;
            }
            sp++;
        }
        return count;
    }

    //435 Non-overlapping Intervals
}
