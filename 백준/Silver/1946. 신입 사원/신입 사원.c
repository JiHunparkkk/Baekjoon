#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

int compare(const void* a, const void* b);
 typedef struct{
	int x;
	int y;
}score;

int main() {
	int t, n, max = 0, pass = 0;
	score sc[100000];

	scanf("%d", &t);

	while (t > 0) {
		scanf("%d", &n);
		for (int i = 0; i < n; i++) 
			scanf("%d %d", &sc[i].x, &sc[i].y);
		
		qsort(sc, n, sizeof(score), compare);
		
		pass = sc[0].y;
		for (int i = 0; i < n; i++) {
			if (sc[i].y > pass)
				continue;
			else {
				max++;
				pass = sc[i].y;
			}
		}

		printf("%d\n", max);

		max = 0;
		t--;
	}

	return 0;
}

int compare(const void* a, const void* b) {
	score num1 = *(score*)a;
	score num2 = *(score*)b;

	if (num1.x < num2.x) return -1;
	if (num1.x > num2.x) return 1;
	return 0;

}