/**
括号有效配对，返回一个括号字符串中，最长的括号有效子串的长度

解法：动态规划（题目中如果提到子串，知觉应该往 子串以..开头，或者子串以..结尾）

1. 一维数组，dp[i] 表示必须以i位置结尾的时候，子串的最大长度
2. 如果dp[i] = '（', 那么子串一定是不合法的
3. 如果dp[i] = '）'，检查dp[i-1]值的再前一个是否是'(',如果是，计算长度，如果不是，那么长度就是0

**/

package class01;

public class Code03_ParenthesesDeep {

	public static boolean isValid(char[] str) {
		if (str == null || str.length == 0) {
			return false;
		}
		int status = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] != ')' && str[i] != '(') {
				return false;
			}
			if (str[i] == ')' && --status < 0) {
				return false;
			}
			if (str[i] == '(') {
				status++;
			}
		}
		return status == 0;
	}

	public static int deep(String s) {
		char[] str = s.toCharArray();
		if (!isValid(str)) {
			return 0;
		}
		int count = 0;
		int max = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '(') {
				max = Math.max(max, ++count);
			} else {
				count--;
			}
		}
		return max;
	}

	public static int maxLength(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[] dp = new int[str.length];
		int pre = 0;
		int ans = 0;
		// dp[0] = 0;
		for (int i = 1; i < str.length; i++) {
			if (str[i] == ')') {
				pre = i - dp[i - 1] - 1; // 与str[i]配对的左括号的位置 pre
				if (pre >= 0 && str[pre] == '(') {
					dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
				}
			}
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

	public static void main(String[] args) {
		String test = "((()))";
		System.out.println(deep(test));

	}

}
