SELECT created, sessid_hi, sessid_lo, idtag, socketid, auth_status, xid, ended, posted
FROM  eniwareedge.ocpp_charge
WHERE ended IS NULL
ORDER BY created DESC
