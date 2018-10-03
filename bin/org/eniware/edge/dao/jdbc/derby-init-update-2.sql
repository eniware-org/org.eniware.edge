ALTER TABLE eniwareedge.sn_settings
ADD COLUMN tkey VARCHAR(255) WITH DEFAULT '';

UPDATE eniwareedge.sn_settings SET tkey = '';

ALTER TABLE eniwareedge.sn_settings
ALTER COLUMN tkey NOT NULL;

ALTER TABLE eniwareedge.sn_settings
DROP PRIMARY KEY;

ALTER TABLE eniwareedge.sn_settings
ADD PRIMARY KEY (skey, tkey);

UPDATE eniwareedge.sn_settings SET svalue = '2'
WHERE skey = 'eniwareedge.sn_settings.version';
