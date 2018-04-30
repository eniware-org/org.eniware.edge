ALTER TABLE eniwareedge.sn_settings
ADD COLUMN modified TIMESTAMP WITH DEFAULT CURRENT_TIMESTAMP;

UPDATE eniwareedge.sn_settings SET modified = CURRENT_TIMESTAMP;

ALTER TABLE eniwareedge.sn_settings
ALTER COLUMN modified NOT NULL;

UPDATE eniwareedge.sn_settings SET svalue = '4'
WHERE skey = 'eniwareedge.sn_settings.version';
