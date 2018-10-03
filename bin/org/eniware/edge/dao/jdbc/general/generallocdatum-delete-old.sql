DELETE FROM eniwareedge.sn_general_loc_datum
WHERE uploaded IS NOT NULL AND uploaded < ?
