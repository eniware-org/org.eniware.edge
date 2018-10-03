CREATE TABLE eniwareedge.ocpp_auth (
	created			TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP,
	idtag 			VARCHAR(20) NOT NULL,
	parent_idtag	VARCHAR(20),
	auth_status		VARCHAR(13) NOT NULL,
	expires			TIMESTAMP,
	PRIMARY KEY (idtag)
);

INSERT INTO eniwareedge.sn_settings (skey, svalue) 
VALUES ('eniwareedge.ocpp_auth.version', '1');
