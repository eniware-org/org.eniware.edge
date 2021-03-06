CREATE TABLE eniwareedge.sn_general_loc_datum (
	created			TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP,
	loc_id			BIGINT NOT NULL,
	source_id 		VARCHAR(32) NOT NULL,
	uploaded		TIMESTAMP,
	jdata			VARCHAR(512) NOT NULL,
	PRIMARY KEY (created, source_id)
);

INSERT INTO eniwareedge.sn_settings (skey, svalue) 
VALUES ('eniwareedge.sn_general_loc_datum.version', '1');
