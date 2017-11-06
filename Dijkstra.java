import java.util.*;


public class Dijkstra{
	public static void main(String args[]){
		int n,min=999,nextnode=0;
		System.out.println("Enter number of nodes");
		Scanner src = new Scanner(System.in);
		n = src.nextInt();
		System.out.println(n);

		int distance[] = new int[n];
		int pre[] = new int[n];
		int cost[][] = new int[n][n];
		int visited[] = new int[n];

		for(int i=0;i<n;i++){
			visited[i]=0;
			distance[i]=0;
			pre[i]=0;
			for(int j=0;j<n;j++){
				cost[i][j]=src.nextInt();
				if(cost[i][j]==0)
					cost[i][j]=999;
			}
		}
		for (int i=0;i<n ;i++ ) {
			for (int j=0;j<n ;j++ ) {
				System.out.print(cost[i][j]+"\t");
				
			}
			System.out.println();
		}
		distance = cost[0];
		distance[0]=0;
		visited[0]=1;

		for(int count=0;count<n;count++){
			min=999;
			for(int i=0;i<n;i++){
				if(min>distance[i] && visited[i]!=1){
					min = distance[i];
					nextnode = i;

				}
			}

			visited[nextnode]=1;

			for(int i=0;i<n;i++){
				if(min+cost[nextnode][i]<distance[i] && visited[i]!=1){
					distance[i]=min+cost[nextnode][i];
					pre[i] = nextnode;
				}
			}

		}
		for(int i=0;i<n;i++){
			System.out.print("|"+distance[i]);
		}
		System.out.println("|");
		for(int i=1;i<n;i++){
			int j=i;
			System.out.print("Path ="+i);
			do
			{
				j = pre[j];
				System.out.print("<---"+j);
			}while(j!=0);
			System.out.println();
		}

	}
}