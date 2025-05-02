SELECT concat('/home/grep/src/', b.board_id, "/", f.file_id, f.file_name, f.file_ext) as FILE_PATH
FROM USED_GOODS_BOARD b
JOIN USED_GOODS_FILE f on b.board_id = f.board_id
WHERE b.board_id = (
        SELECT BOARD_ID
        FROM USED_GOODS_BOARD
        ORDER BY VIEWS DESC
        LIMIT 1
    )
ORDER BY f.FILE_ID DESC;