SELECT fh.FLAVOR
FROM FIRST_HALF fh
JOIN JULY j ON fh.FLAVOR = j.FLAVOR
GROUP BY FLAVOR
ORDER BY SUM(fh.TOTAL_ORDER) + SUM(j.TOTAL_ORDER) DESC
LIMIT 3;