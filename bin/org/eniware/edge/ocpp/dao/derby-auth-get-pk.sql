SELECT created, idtag, parent_idtag, auth_status, expires
FROM  eniwareedge.ocpp_auth
WHERE idtag = ?
