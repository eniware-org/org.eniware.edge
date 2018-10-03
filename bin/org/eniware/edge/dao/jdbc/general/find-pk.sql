SELECT 
	created,
	source_id,
	jdata
FROM eniwareedge.sn_general_Edge_datum
WHERE created = ? AND source_id = ?
