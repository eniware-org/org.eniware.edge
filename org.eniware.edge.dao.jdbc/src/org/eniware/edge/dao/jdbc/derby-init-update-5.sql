ALTER TABLE eniwareedge.sn_settings
ADD COLUMN flags INTEGER WITH DEFAULT 0;

UPDATE eniwareedge.sn_settings SET flags = 0;

UPDATE eniwareedge.sn_settings SET flags = 1, tkey = ''
WHERE tkey = '_modification_date_ignore';

ALTER TABLE eniwareedge.sn_settings
ALTER COLUMN flags NOT NULL;

UPDATE eniwareedge.sn_settings SET svalue = '5'
WHERE skey = 'eniwareedge.sn_settings.version';
