select a.id, 
    case 
    when a.r <= 0.25 THEN 'CRITICAL'
    when a.r <= 0.50 THEN 'HIGH'
    when a.r <= 0.75 THEN 'MEDIUM'
    else 'LOW'
    END as 'COLONY_NAME'
from 
(SELECT ID, PERCENT_RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) as r FROM ECOLI_DATA) as a
order by a.id;