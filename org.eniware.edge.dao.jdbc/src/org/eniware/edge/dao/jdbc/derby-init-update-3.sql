ALTER TABLE eniwareedge.sn_settings
ALTER COLUMN skey SET DATA TYPE VARCHAR(255);

UPDATE eniwareedge.sn_settings SET svalue = '3'
WHERE skey = 'eniwareedge.sn_settings.version';
