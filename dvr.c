#include<stdio.h>
struct a
{
	int dist[10];
	int from[10];
}vrt[10];

void main(){
	int n,cost[10][10],i,j,count,k;
	printf("Enter number of nodes");
	scanf("%d",&n);
	for (i=0;i<n;i++){
		for(j=0;j<n;j++){
			scanf("%d",&cost[i][j]);
			// if(cost[i][j]==0)
			// 	cost[i][j]=999;
		
			vrt[i].dist[j]=cost[i][j];
			vrt[i].from[j]=j;
		}
	}





	
		// count=0;
		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				for (k = 0; k < n; k++)
				{
					if(vrt[i].dist[j] > vrt[i].dist[k] + vrt[k].dist[j]){

						vrt[i].dist[j] = vrt[i].dist[k] + cost[k][j];
						vrt[i].from[j] = k;
						// count++;
					}
				}
			}
		}

	



	for(i=0;i<n;i++){
		printf("for node %d\n",i+1 );
		for(j=0;j<n;j++){

			printf("%d via %d - %d\n",j+1,vrt[i].from[j]+1,vrt[i].dist[j]);
		}
		printf("\n");
	}

}