-- 코드를 작성해주세요
SELECT COUNT(id) as COUNT
FROM ECOLI_DATA
WHERE genotype & 2 = 0
and (genotype & 1 > 0 or genotype & 4 > 0)