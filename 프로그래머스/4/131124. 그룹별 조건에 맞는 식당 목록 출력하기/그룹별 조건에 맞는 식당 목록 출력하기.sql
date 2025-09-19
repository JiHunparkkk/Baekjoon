SELECT m.MEMBER_NAME, r.REVIEW_TEXT, DATE_FORMAT(r.REVIEW_DATE, '%Y-%m-%d') as 'REVIEW_DATE'
FROM REST_REVIEW r
JOIN MEMBER_PROFILE m ON r.member_id = m.member_id
WHERE r.MEMBER_ID = (select member_id from REST_REVIEW
                  group by member_id
                  order by count(member_id) desc
                  limit 1)
ORDER BY r.REVIEW_DATE ASC, r.REVIEW_TEXT ASC;