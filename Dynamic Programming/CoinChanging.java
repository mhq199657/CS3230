import java.util.Arrays;
class CoinChanging{
	int[] denomination;
	int totalAmount;
	int[][] dpTable;
	boolean[][] used;
	int minCoins;
	CoinChanging(int[] denomination, int totalAmount){
		this.denomination = denomination; //1 based
		this.totalAmount = totalAmount;
		int numOfDenomination = denomination.length-1;
		this.used = new boolean[numOfDenomination+1][totalAmount+1];
		dpTable = new int[numOfDenomination+1][totalAmount+1];
		for(int j = 0; j<=totalAmount; j++){
			dpTable[numOfDenomination][j] = j;
		}
		for(int i = numOfDenomination; i>=2; i--){
			for(int j = 0; j<=totalAmount; j++) {
				if(j>=denomination[i-1]){
					if(1+dpTable[i-1][j-denomination[i-1]]<dpTable[i][j]){
						dpTable[i-1][j]=1+dpTable[i-1][j-denomination[i-1]];
						used[i-1][j]=true;
					}else{
						dpTable[i-1][j]=dpTable[i][j];
						used[i-1][j]=false;
					}
				}else{
					dpTable[i-1][j]=dpTable[i][j];
					used[i-1][j]=false;
				}
			}
		}
		minCoins = dpTable[1][totalAmount];
	}
	public int minCoins(){
		return minCoins;
	}
	public int[] coinsUsed(){
		int[] u = new int[denomination.length];
		int remainingValue = totalAmount;
		int i = 1;
		while(remainingValue>0){
			if(used[i][remainingValue]){
				u[i] = u[i]+1;
				remainingValue = remainingValue - denomination[i];
			}else{
				i++;
			}
		}
		return u;
	}
	public static void main(String[] args) {
		int[] coins = new int[]{-1, 50, 25, 20,15, 5, 3, 1};
		CoinChanging c1 = new CoinChanging(coins, 38);
		System.out.println(c1.minCoins());
		System.out.println(Arrays.toString(c1.coinsUsed()));
	}
}