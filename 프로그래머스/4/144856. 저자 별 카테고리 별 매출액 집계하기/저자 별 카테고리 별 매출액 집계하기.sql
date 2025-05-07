-- 코드를 입력하세요
SELECT b.AUTHOR_ID, a.AUTHOR_NAME, b.CATEGORY, SUM(s.SALES * b.PRICE) as TOTAL_SALES
FROM BOOK b
JOIN BOOK_SALES s on b.book_id = s.book_id
JOIN AUTHOR a on a.author_id = b.author_id
WHERE s.sales_date LIKE '2022-01%'
GROUP BY b.author_id, b.category
ORDER BY b.AUTHOR_ID, b.CATEGORY DESC

