DELETE FROM eniwareedge.ocpp_charge
WHERE ended IS NULL
	AND created < ?
