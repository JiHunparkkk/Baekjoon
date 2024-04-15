#include<stdio.h>
#include<stdlib.h>
#pragma warning(disable:4996)

int compare_pos(const void* a, const void* b) {
	return *(int*)b - *(int*)a;
}
int compare_neg(const void* a, const void* b) {
	return *(int*)a - *(int*)b;
}

int main() {

	int n,tmp,multi=1;
	int pos[50] = { 0 }, neg[50] = { 0 };
	long long sum = 0;

	scanf("%d", &n);

	int j=0, k = 0;
	for (int i = 0; i < n; i++) {
		scanf("%d", &tmp);
		if (tmp > 0) {
			pos[j] = tmp;
			j++;
		}
		else {
			neg[k] = tmp;
			k++;
		}
	}

	qsort(pos, j, sizeof(int), compare_pos);
	qsort(neg, k, sizeof(int), compare_neg);

	for (int i = 0; i < j; i++) {
		if (pos[i] != 1) {
			if (multi != 1) {
				multi *= pos[i];
				sum += multi;
				multi = 1;
			}
			else {
				multi *= pos[i];
				if (i == j - 1)
					sum += multi;
			}
		}
		else {
			if (multi != 1) sum += pos[i] + multi;
			else sum += pos[i];
			multi = 1;
		}
	}

	multi = 1;
	for (int i = 0; i < k; i++) {

		if (multi != 1) {
			multi *= neg[i];
			sum += multi;
			multi = 1;
		}
		else {
			multi *= neg[i];
			if (i == k - 1)
				sum += multi;
		}
	}

	printf("%lld", sum);

	return 0;
}