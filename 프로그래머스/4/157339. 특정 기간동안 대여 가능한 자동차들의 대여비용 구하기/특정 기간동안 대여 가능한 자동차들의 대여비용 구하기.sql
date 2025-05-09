SELECT cc.CAR_ID as CAR_ID, cc.CAR_TYPE as CAR_TYPE, ROUND(cc.DAILY_FEE * 30 * (100 - dp.DISCOUNT_RATE) / 100) AS FEE
FROM CAR_RENTAL_COMPANY_CAR cc
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS dp on dp.CAR_TYPE = cc.CAR_TYPE
WHERE cc.CAR_TYPE in('세단', 'SUV') 
    AND cc.CAR_ID NOT IN(
        SELECT CAR_ID
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
        WHERE END_DATE >= '2022-11-01' AND START_DATE <= '2022-12-01'
    )
AND dp.DURATION_TYPE LIKE '30%'
GROUP BY cc.CAR_ID
HAVING FEE BETWEEN 500000 AND 1999999
ORDER BY FEE DESC, CAR_TYPE, CAR_ID DESC;

