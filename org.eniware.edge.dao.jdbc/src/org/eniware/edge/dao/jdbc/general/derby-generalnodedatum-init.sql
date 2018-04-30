CREATE TABLE eniwareedge.sn_general_node_datum (
	created			TIMESTAMP NOT NULL WITH DEFAULT CURRENT_TIMESTAMP,
	source_id 		VARCHAR(32) NOT NULL,
	uploaded		TIMESTAMP,
	jdata			VARCHAR(512) NOT NULL,
	PRIMARY KEY (created, source_id)
);

INSERT INTO eniwareedge.sn_settings (skey, svalue) 
VALUES ('eniwareedge.sn_general_node_datum.version', '1');
