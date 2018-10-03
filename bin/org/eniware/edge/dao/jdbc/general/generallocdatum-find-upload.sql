SELECT 
	created,
	loc_id,
	source_id,
	jdata
FROM eniwareedge.sn_general_loc_datum
WHERE uploaded IS NULL
ORDER BY created, loc_id, source_id
