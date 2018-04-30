SELECT created, measurand, reading, context, location, unit
FROM  eniwareedge.ocpp_meter_reading
WHERE sessid_hi = ? AND sessid_lo = ?
ORDER BY created, measurand
