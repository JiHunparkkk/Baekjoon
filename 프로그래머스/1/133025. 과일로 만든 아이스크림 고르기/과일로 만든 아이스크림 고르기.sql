-- 코드를 입력하세요
SELECT A.FLAVOR
from FIRST_HALF as A
join ICECREAM_INFO  as B
on A.FLAVOR = B.FLAVOR
where A.TOTAL_ORDER > 3000 and B.INGREDIENT_TYPE = 'fruit_based'
order by A.TOTAL_ORDER desc;