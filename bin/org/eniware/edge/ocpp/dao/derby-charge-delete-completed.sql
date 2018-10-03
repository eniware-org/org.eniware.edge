DELETE FROM eniwareedge.ocpp_charge
WHERE ended IS NOT NULL
	AND ended < ?
