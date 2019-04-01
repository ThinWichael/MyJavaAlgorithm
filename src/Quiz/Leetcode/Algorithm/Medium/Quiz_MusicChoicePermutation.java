package Quiz.Leetcode.Algorithm.Medium;
//

//���]���Ѧ��@�i�ۤ�  �̭��� n ���q  n �b 1 ~ 60000 ����  �C���q���� s ��  s �b 1~ 500 ���� 
//�ڭ̤��ѷQ��X�⭺�q  ���ץ��n�i�H�Q 60 �㰣  �ݤ@�U�@�@���X�سo�زզX

//�|��  ���o�ǭ�
//[30,20,150,100,40]
//�ڭ̥i�H��X
//[30, 150]
//[20, 100]
//[20, 40]
//�o�T�زզX  ���i�H�Q 60 �㰣
//�G���׬� 3
//
//�S�|��
//[60,60,60]
//���׬�3  �]����⦨�ﳣ��Q 60 �㰣

public class Quiz_MusicChoicePermutation {

	public Quiz_MusicChoicePermutation() {
		// TODO Auto-generated constructor stub
	}

	int sec = 60;
	int m = 2; // find two songs

	// case 1 ,60's multiples
	// case 2 , not 60's multiples, 1 ~ 59 + 60 * K. Go pairing ! 1 and 59 , 2 and 58 ...
	// O (N + N/60 + 30(N/60) ) = O (N), space O (N)
	public static int findPermutationOfSongs(int[] songs) {

		int[] dp = new int[501];
		for (int song : songs)
			dp[song]++;

		int res = 0;

		// case 1 ,60's multiples
		int i = 60;
		int total = 0;
		while (i <= 500) {
			total += dp[i];
			i += 60;
		}
		res = total * (total - 1) / (1 * 2); // C N ��2
		
		// case 2 , not 60's multiples, 1 ~ 59 + 60 * K
		for (int j = 1; j <= 29; j++) {
			int pair1 = 0; // e.g 1,2,3,4 ...,29
			int pair2 = 0; // e.g 59,58,...,31
			int loops = 1;
			
			while (j + 60*(loops-1) <= 500) {
				
				pair1 += dp[j + 60*(loops -1)]; // 1 , 61 , 121 , 181 ...
		
				if (60 * loops - j <= 500)
					pair2 += dp[60 * loops - j]; // 59 , 119, 179 ....
				
				loops++;
			}

			res += pair1 * pair2;
		}
		
		i = 30; // 30 case 
		int pair30 = 0;
		while(i <= 500) {
			pair30 += dp[i];
			i = i + 60;
		}
		
		res += pair30 * (pair30-1) / (1*2); //C n �� 2
		
		return res;
	}

	public static int findPermutationOfSongs2(int[] songs) { 
		int n = songs.length;
		int[] dp = new int[61];
		for (int song : songs) {
			if(song%60 == 0) dp[60]++;
			else dp[song%60]++;
		}
		
		int res = 0;
		// case 1 ,60's multiples
		res += dp[60] * (dp[60]-1) / (1 * 2);
		
		// case 2 , not 60's multiples, 1 ~ 59 + 60 * K
		for(int i = 1; i <= 29; i++) {
		res += dp[i] * dp[60 - i];
		}
		
		res += dp[30] * (dp[30]-1) / (1 * 2);
		
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] test1 = {30,20,150,100,40};
		int [] test2 = {60,60,60};
		int [] test3 = {60,60,60,180,1,59};
		System.out.println(findPermutationOfSongs2(test1));
		System.out.println(findPermutationOfSongs2(test2));
		System.out.println(findPermutationOfSongs2(test3));
//		3
//		3
//		7
	}

}
