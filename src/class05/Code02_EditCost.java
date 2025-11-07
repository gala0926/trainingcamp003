/**
给定两个字符串str1 和 str2，再给定三个整数ic, dc 和rc，分别代表插入，删除和替换一个字符的代价，返回将str1编辑成str2的最小代价
比如：
str1 = abc， str2 = adc，ic=5， dc=3， rc=2， 把b替换成d是代价最小的，所以返回2
str1 = abc， str2 = adc，ic=5， dc=3， rc=100， 先删除b，然后插入d是代价最小的，所以返回8

解法：一个样本作行，一个样本作列的对应模型 (str1 行，str2 列）
dp[i][j]: str1前i个 与 str2前j个的最小编辑代价
- 0行表示，str1一个字符串也没有的时候，如何编辑成str2

可能性：
1. 保留i-1的字符串
	- 使得 str1[i-1] == str2[j-1]， 
		- str1[i-1] == str2[j-1], dp[i][j] = dp[i-1][j-1] + 0
	- str1[i-1] != str2[j-1],

可能性：
- 如果 str1[i] = str2[j], dp[i][j] = dp[i-1][j-1] + 0
- str1[i].length = str2[j].length, str1之前str1[i-1]编辑成str2[j-1]所需代价 + 一个替换的代价，dp[i][j] = dp[i-1][j-1] + rc 
- str1[i].length > str2[j].length，dp[i][j] = dp[i-1][j] + dc 
- str1[i].length < str2[j].length，dp[i][j] = dp[i][j-1] + ic 
**/
package class05;

public class Code02_EditCost {

	public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length + 1;
		int M = str2.length + 1;
		int[][] dp = new int[N][M];
		// dp[0][0]  = 0
		for (int i = 1; i < N; i++) {
			dp[i][0] = dc * i;
		}
		for (int j = 1; j < M; j++) {
			dp[0][j] = ic * j;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (str1[i - 1] == str2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = dp[i - 1][j - 1] + rc;
				}
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
			}
		}
		return dp[N - 1][M - 1];
	}

	public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
		char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
		if (chs1.length < chs2.length) {
			int tmp = ic;
			ic = dc;
			dc = tmp;
		}
		int[] dp = new int[shorts.length + 1];
		for (int i = 1; i <= shorts.length; i++) {
			dp[i] = ic * i;
		}
		for (int i = 1; i <= longs.length; i++) {
			int pre = dp[0];
			dp[0] = dc * i;
			for (int j = 1; j <= shorts.length; j++) {
				int tmp = dp[j];
				if (longs[i - 1] == shorts[j - 1]) {
					dp[j] = pre;
				} else {
					dp[j] = pre + rc;
				}
				dp[j] = Math.min(dp[j], dp[j - 1] + ic);
				dp[j] = Math.min(dp[j], tmp + dc);
				pre = tmp;
			}
		}
		return dp[shorts.length];
	}

	public static void main(String[] args) {
		String str1 = "ab12cd3";
		String str2 = "abcdf";
		System.out.println(minCost1(str1, str2, 5, 3, 2));
		System.out.println(minCost2(str1, str2, 5, 3, 2));

		str1 = "abcdf";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 3, 2, 4));
		System.out.println(minCost2(str1, str2, 3, 2, 4));

		str1 = "";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 1, 7, 5));
		System.out.println(minCost2(str1, str2, 1, 7, 5));

		str1 = "abcdf";
		str2 = "";
		System.out.println(minCost1(str1, str2, 2, 9, 8));
		System.out.println(minCost2(str1, str2, 2, 9, 8));

	}

}
