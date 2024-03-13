-- 코드를 작성해주세요
select A.ITEM_ID, A.ITEM_NAME, A.RARITY
from ITEM_INFO as A
join ITEM_TREE as B
on A.ITEM_ID = B.ITEM_ID
where B.PARENT_ITEM_ID in (select ITEM_ID from ITEM_INFO where RARITY = 'RARE')
order by ITEM_ID desc;