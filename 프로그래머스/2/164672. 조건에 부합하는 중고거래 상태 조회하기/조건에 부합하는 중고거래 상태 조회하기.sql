-- 코드를 입력하세요
SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, 
    case when status = 'SALE' then '판매중'
         when status = 'RESERVED' then '예약중'
         when status = 'DONE' then '거래완료'
    end as 'STATUS'
FROM USED_GOODS_BOARD
WHERE CREATED_DATE = '2022-10-05'
ORDER BY BOARD_ID DESC;