import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static double[][] adjMatrix;
	static boolean[] visited;
	static List<double[]> stars = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		adjMatrix = new double[N][N];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			stars.add(new double[] {x, y});
		}
		
		for (int i = 0; i < stars.size(); i++) {
			for (int j = i; j < stars.size(); j++) {
				if(i == j) continue;
				double distance =  Math.sqrt(Math.abs(stars.get(i)[0] - stars.get(j)[0])*Math.abs(stars.get(i)[0] - stars.get(j)[0]) + Math.abs(stars.get(i)[1] - stars.get(j)[1])*Math.abs(stars.get(i)[1] - stars.get(j)[1]));
				adjMatrix[i][j] = distance;
				adjMatrix[j][i] = distance;
			}
		}
		
		double[] minEdge = new double[N];
		Arrays.fill(minEdge, Double.MAX_VALUE);
		minEdge[0] = 0;
		double result = 0;
		int c;
		
		for (c = 0; c < N; c++) {
			double min = Double.MAX_VALUE;
			int minVertex = -1;
			
			for (int i = 0; i < N; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			if(minVertex == -1) break;
			result += min;
			visited[minVertex] = true;
			
			for (int i = 0; i < N; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && adjMatrix[minVertex][i] < minEdge[i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
			
		}
		
		System.out.printf("%.2f", result);
	}

}
