/**
给定一个有序数组arr，从左到右依次表示x轴上从左往右点的位置，给定一个正整数k，返回如果有一根长度为k的绳子，最多能覆盖住几个点，绳子的边缘点碰倒x轴上的点也算盖住

1. 贪心：必然使绳子的一端盖住x轴上的点
解法1：使用二分法，根据绳子结尾处倒推出绳子的起点，然后使用二分法算出大于起点最左的位置
解法2：窗口滑动
**/

package class01;

import java.util.Arrays;

public class Code01_CordCoverMaxPoint {

	public static int maxPoint1(int[] arr, int L) {
		int res = 1;
		for (int i = 0; i < arr.length; i++) {
			int nearest = nearestIndex(arr, i, arr[i] - L);
			res = Math.max(res, i - nearest + 1);
		}
		return res;
	}

	public static int nearestIndex(int[] arr, int R, int value) {
		int L = 0;
		int index = R;
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid] >= value) {
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return index;
	}

	public static int maxPoint2(int[] arr, int L) {
		int left = 0;
		int right = 0;
		int N = arr.length;
		int max = 0;
		while (left < N) {
			while (right < N && arr[right] - arr[left] <= L) {
				right++;
			}
			max = Math.max(max, right - (left++));
		}
		return max;
	}

	// for test
	public static int test(int[] arr, int L) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int pre = i - 1;
			while (pre >= 0 && arr[i] - arr[pre] <= L) {
				pre--;
			}
			max = Math.max(max, i - pre);
		}
		return max;
	}

	// for test
	public static int[] generateArray(int len, int max) {
		int[] ans = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * max);
		}
		Arrays.sort(ans);
		return ans;
	}

	public static void main(String[] args) {
		int len = 100;
		int max = 1000;
		int testTime = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int L = (int) (Math.random() * max);
			int[] arr = generateArray(len, max);
			int ans1 = maxPoint1(arr, L);
			int ans2 = maxPoint2(arr, L);
			int ans3 = test(arr, L);
			if (ans1 != ans2 || ans2 != ans3) {
				System.out.println("oops!");
				break;
			}
		}

	}

}
