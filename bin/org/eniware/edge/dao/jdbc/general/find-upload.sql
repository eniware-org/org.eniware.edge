SELECT 
	created,
	source_id,
	jdata
FROM eniwareedge.sn_general_Edge_datum
WHERE uploaded IS NULL
ORDER BY created, source_id
