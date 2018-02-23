import java.util.Arrays;
class LongestCommonSubsequence{
	private static final int UP = 1;
	private static final int DIAGONAL = 2;
	private static final int LEFT = 3;
	private static final int INIT = 0;
	char[] stringA;
	char[] stringB;
	int[][] dpTable;
	int[][] direction;
	int maxLength;
	String lcs;
	LongestCommonSubsequence(char[] stringA, char[] stringB) {
		this.stringA = stringA;
		this.stringB = stringB;
		int sizeA = stringA.length;
		int sizeB = stringB.length;
		lcs = "";
		dpTable = new int[sizeA+1][sizeB+1];
		direction = new int[sizeA+1][sizeB+1];
		for(int i = 0; i<= sizeA; i++){
			dpTable[i][0]=0;
		}
		for(int j = 0; j<=sizeB; j++){
			dpTable[0][j]=0;
		}
		for(int i = 1; i<=sizeA; i++){
			for(int j = 1; j<=sizeB; j++){
				if(stringA[i-1]==stringB[j-1]){
					dpTable[i][j]=1+dpTable[i-1][j-1];
					direction[i][j]=DIAGONAL;
				}else if(dpTable[i-1][j]>=dpTable[i][j-1]){
					dpTable[i][j]=dpTable[i-1][j];
					direction[i][j]=UP;
				}else{
					dpTable[i][j]=dpTable[i][j-1];
					direction[i][j]=LEFT;
				}
			}
		}
		maxLength = dpTable[sizeA][sizeB];
		for(int i = 0; i<dpTable.length; i++)
			System.out.println(Arrays.toString(dpTable[i]));
		for(int i = 0; i<dpTable.length; i++)
			System.out.println(Arrays.toString(direction[i]));
		StringBuilder sb = new StringBuilder();
		sb.append(getLCS(stringA, sizeA, sizeB, direction));
		lcs = sb.toString();
	} 
	private String getLCS(char[] stringA, int x, int y, int[][] direction){
			//System.out.println(x+","+y+":"diagona);
		if(x==0||y==0){
			return "";
		}else if(direction[x][y]==DIAGONAL){
			return getLCS(stringA, x-1, y-1, direction)+stringA[x-1];
		}else if(direction[x][y]==UP){
			return getLCS(stringA, x-1, y, direction);
		}else{
			return getLCS(stringA, x, y-1, direction);
		}
	}
	public int maxLength(){
		return maxLength;
	}
	public String lcs(){
		return lcs;
	}

	public static void main(String[] args){
		char[] sequenceA = "CAGTCAGCTATCATGCTA".toCharArray();
		char[] sequenceB = "ACTGTCAGACATCA".toCharArray();
		LongestCommonSubsequence lcs1 = new LongestCommonSubsequence(sequenceA, sequenceB);
		System.out.println(lcs1.maxLength());
		System.out.println(lcs1.lcs());

	}
}