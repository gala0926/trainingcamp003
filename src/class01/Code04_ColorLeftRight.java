/**
有红色和绿色两种正方形，现在选择任意一个正方形然后用两种颜色的任意一种进行染色，这个正方形的颜色将被覆盖。目标是在完成染色之后，每个红色R都比每个绿色G距离最左侧近。返回至少需要突然几个正方形

例如：s = RGRGR，我们涂染之后变成RRRGG满足要求，涂染个数为2，没有比这更好的涂染方案了

解法：在每个位置枚举分界线，求左边有几个R，右边有几个G

rall：当前位置到N位置一共还有几个R，从左往右遍历的时候，如果发现是R，那就rall--，如果是G，那么left++
**/
package class01;

public class Code04_ColorLeftRight {

	// RGRGR -> RRRGG
	public static int minPaint(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int rAll = 0;
		for (int i = 0; i < N; i++) {
			rAll += str[i] == 'R' ? 1 : 0;
		}
		int ans = rAll; // 如果数组所有的范围，都是右侧范围，都变成G
		int left = 0;
		for (int i = 0; i < N - 1; i++) { // 0..i 左侧 n-1..N-1
			left += str[i] == 'G' ? 1 : 0;
			rAll -= str[i] == 'R' ? 1 : 0;
			ans = Math.min(ans, left + rAll);
		}
		// 0...N-1 左全部 右无
		ans = Math.min(ans, left + (str[N - 1] == 'G' ? 1 : 0));
		return ans;
	}

	public static void main(String[] args) {
		String test = "GGGGGR";
		System.out.println(minPaint(test));
	}

}
