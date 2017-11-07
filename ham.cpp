#include<iostream>


using namespace std;
int main(){
	int data[10];
	int datare[10],c,c1,c2,c3;
	cout<<"Enter 4 bits\n";
	cin>>data[3]>>data[5]>>data[6]>>data[7];
	data[1]=data[3]^data[5]^data[7];
	data[2]=data[3]^data[6]^data[7];
	data[4]=data[5]^data[6]^data[7];

	cout<<"Encoded: ";
	for(int i=1;i<=7;i++)
		cout<<data[i];
	cout<<"\n\nEnter received data bits one by one\n";
	for(int i=1;i<=7;i++)
		cin>>datare[i];
	c1=datare[1]^datare[3]^datare[5]^datare[7];
	c2=datare[2]^datare[3]^datare[6]^datare[7];
	c3=datare[4]^datare[5]^datare[6]^datare[7];
	c=4*c3 + 2*c2 +c1;
	if(c==0)
		cout<<"no error";
	else{
		cout<<"\nError on position "<<c;

		cout<<"\nData sent : ";
		for(int i=1;i<=7;i++)
			cout<<data[i];

		cout<<"\nData received : ";
		for(int i=1;i<=7;i++)
			cout<<datare[i];

		cout<<"\nCorrect message is\n";

		if(datare[c]==0)
			datare[c]=1;
		else
			datare[c]=0;
		for (int i=1;i<=7;i++) {
			cout<<datare[i];
		}
	}
}