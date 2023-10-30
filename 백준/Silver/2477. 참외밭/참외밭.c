#pragma warning(disable:4996)
#include<stdio.h>

int main() {

	int k,p,max=0,area=1,current,index;
	int point[6];
	long long result;
	scanf("%d", &k);


	scanf("%d %d", &p, &point[0]);
	current = point[0];

	for (int i = 1; i < 6; i++) {
		scanf("%d %d", &p, &point[i]);
		area = current * point[i];
		current = point[i];

		if (max < area) {
			max = area;
			index = i - 1;
		}
	}

	if (max < current * point[0]) {
		max = current * point[0];
		index = 5;
	}

	area = point[(index + 3) % 6] * point[(index + 4) % 6];
	result = (long long)k * (max - area);
	printf("%lld",result);

	return 0;
}