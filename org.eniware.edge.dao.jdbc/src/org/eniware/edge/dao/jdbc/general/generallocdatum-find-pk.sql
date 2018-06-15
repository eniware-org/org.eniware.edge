SELECT 
	created,
	loc_id,
	source_id,
	jdata
FROM eniwareedge.sn_general_loc_datum
WHERE created = ? AND source_id = ?
