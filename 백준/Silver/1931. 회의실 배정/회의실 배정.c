#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

typedef struct {
	int x;
	int y;
}time;

int compare(const void* a, const void* b);

int main() {

	int n, j = 1, max = 0, last = 0;
	time* arr;

	scanf("%d", &n);

	arr = (time*)malloc(sizeof(time) * n);

	for (int i = 0; i < n; i++) 
		scanf("%d %d", &arr[i].x, &arr[i].y);
	
	qsort(arr, n, sizeof(time), compare);

	for (int i = 0; i < n; i++) {

		if (arr[i].x >= last) {
			max++;
			last = arr[i].y;
		}

	}

	printf("%d", max);

	free(arr);

	return 0;
}

int compare(const void* a, const void* b) {
	time s = *(time*)a;
	time e = *(time*)b;

	if (s.y < e.y) return -1;
	if (s.y == e.y) {
		if (s.x <= e.x) return -1;
		else return 1;
	}
	else return 1;
}

