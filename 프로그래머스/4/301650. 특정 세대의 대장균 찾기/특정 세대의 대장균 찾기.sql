select id from ECOLI_DATA 
where parent_id in(select id from ECOLI_DATA where parent_id in(select id from ECOLI_DATA where parent_id is null))
        