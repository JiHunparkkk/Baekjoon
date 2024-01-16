#include<stdio.h>
#pragma warning(disable:4996)

int main() {

	long long s, n = 0, sum = 0;

	scanf("%lld", &s);
	
	while (1) {
		n++;
		sum += n;
		if (sum > s) {
			n--;
			break;
		}
	}

	printf("%lld", n);

	return 0;
}