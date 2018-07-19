/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.backup;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eniware.edge.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract support class for {@link BackupService} implementations.
 * 
 * @version 1.1
 * @since 1.54
 */
public abstract class BackupServiceSupport implements BackupService {

	/** A date and time format to use with backup keys. */
	public static final String BACKUP_KEY_DATE_FORMAT = "yyyyMMdd'T'HHmmss";

	/**
	 * A pattern to match {@literal Edge-N-backup-D-Q} where {@literal N} is a
	 * Edge ID and {@literal D} is a date formatted using
	 * {@link #BACKUP_KEY_DATE_FORMAT} and {@literal Q} is an optional
	 * qualifier.
	 * 
	 * <p>
	 * Note that the qualifier and the leading dash is optional, so its
	 * {@link Matcher} group is {@literal 4} (not 3).
	 * </p>
	 */
	public static final Pattern Edge_AND_DATE_BACKUP_KEY_PATTERN = Pattern
			.compile("Edge-(\\d+)-backup-(\\d{8}T\\d{6})(-(\\w+))?");

	protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.setSerializationInclusion(JsonInclude.Include.NON_NULL);

	private static final String MARKED_BACKUP_PROP_KEY = "key";
	private static final String MARKED_BACKUP_PROP_PROPS = "props";

	/** A class-level logger. */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Get a directory to use for local backup data.
	 * 
	 * @return a directory to use for local backup data
	 */
	protected File defaultBackuprDir() {
		String path = System.getProperty(Constants.SYSTEM_PROP_Edge_HOME, null);
		if ( path == null ) {
			path = System.getProperty("java.io.tmpdir");
		} else {
			if ( !path.endsWith("/") ) {
				path += "/";
			}
			path += "var/backups";
		}
		return new File(path);
	}

	/**
	 * Get a file to use for "marked backup" metadata.
	 * 
	 * @return the file for marked backup metadata
	 */
	protected File markedBackupForRestoreFile() {
		return new File(defaultBackuprDir(), getKey() + ".RESTORE_ON_BOOT");
	}

	/**
	 * Shortcut for {@link #backupDateFromProps(Date, Map, Pattern, String)}
	 * using default values.
	 * 
	 * <p>
	 * The {@link #Edge_AND_DATE_BACKUP_KEY_PATTERN} pattern and
	 * {@link #BACKUP_KEY_DATE_FORMAT} format are used.
	 * </p>
	 * 
	 * @param date
	 *        if not {@literal null} then return this value
	 * @param props
	 *        a mapping of properties, which may include a
	 *        {@link BackupManager#BACKUP_KEY} value
	 * @return the date, never {@code null}
	 * @see #backupDateFromProps(Date, Map, Pattern, String)
	 */
	protected Date backupDateFromProps(Date date, Map<String, String> props) {
		return backupDateFromProps(date, props, Edge_AND_DATE_BACKUP_KEY_PATTERN,
				BACKUP_KEY_DATE_FORMAT);
	}

	/**
	 * Parse a date from a backup key.
	 * 
	 * <p>
	 * If no date can be extracted from the given arguments, the current system
	 * time will be returned.
	 * </p>
	 * 
	 * @param date
	 *        if not {@literal null} then return this value
	 * @param props
	 *        a mapping of properties, which may include a
	 *        {@link BackupManager#BACKUP_KEY} value
	 * @param EdgeIdAndDatePattern
	 *        a regular expression to match a Edge ID and date from a backup key
	 * @param dateFormat
	 *        the format of the date extracted from the
	 *        {@code EdgeIdAndDatePattern}
	 * @return the date, never {@code null}
	 */
	protected Date backupDateFromProps(Date date, Map<String, String> props,
			Pattern EdgeIdAndDatePattern, String dateFormat) {
		if ( date != null ) {
			return date;
		}
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String backupKey = (props == null ? null : props.get(BackupManager.BACKUP_KEY));
		if ( backupKey != null ) {
			Matcher m = EdgeIdAndDatePattern.matcher(backupKey);
			if ( m.find() ) {
				try {
					return sdf.parse(m.group(2));
				} catch ( ParseException e ) {
					log.warn("Unable to parse backup date from key [{}]", backupKey);
				}
			}
		}
		return new Date();
	}

	/**
	 * Shortcut for {@link #backupEdgeIdFromProps(Long, Map, Pattern)} using
	 * default values.
	 * 
	 * <p>
	 * The {@link #Edge_AND_DATE_BACKUP_KEY_PATTERN} pattern is used.
	 * </p>
	 * 
	 * @param EdgeId
	 *        if not {@literal null} then return this value
	 * @param props
	 *        a mapping of properties, which may include a
	 *        {@link BackupManager#BACKUP_KEY} value
	 * @return the Edge ID, or {@literal 0} if not available
	 * @see #backupEdgeIdFromProps(Long, Map, Pattern)
	 */
	protected Long backupEdgeIdFromProps(Long EdgeId, Map<String, String> props) {
		return backupEdgeIdFromProps(EdgeId, props, Edge_AND_DATE_BACKUP_KEY_PATTERN);
	}

	/**
	 * Parse a Edge ID from a backup key.
	 * 
	 * @param EdgeId
	 *        if not {@literal null} then return this value
	 * @param props
	 *        a mapping of properties, which may include a
	 *        {@link BackupManager#BACKUP_KEY} value
	 * @param EdgeIdAndDatePattern
	 *        a regular expression to match a Edge ID and date from a backup key
	 * @return the Edge ID, or {@literal 0} if not available
	 */
	protected Long backupEdgeIdFromProps(Long EdgeId, Map<String, String> props,
			Pattern EdgeIdAndDatePattern) {
		if ( EdgeId != null ) {
			return EdgeId;
		}
		Long result = 0L;
		String backupKey = (props == null ? null : props.get(BackupManager.BACKUP_KEY));
		if ( backupKey != null ) {
			Matcher m = EdgeIdAndDatePattern.matcher(backupKey);
			if ( m.find() ) {

				try {
					result = Long.valueOf(m.group(1));
				} catch ( NumberFormatException e ) {
					log.warn("Unable to parse Edge ID from key [{}]", backupKey);
				}
			}
		}
		return result;
	}

	/**
	 * Extract backup identity information from a backup key.
	 * 
	 * <p>
	 * This method calls
	 * {@link #identityFromBackupKey(Pattern, String, String)}, passing
	 * {@link #Edge_AND_DATE_BACKUP_KEY_PATTERN} and
	 * {@link #BACKUP_KEY_DATE_FORMAT} for arguments.
	 * </p>
	 * 
	 * @param key
	 *        the key to extract the details from
	 * @return the extracted details, or {@literal null} if none found
	 * @since 1.1
	 */
	public static final BackupIdentity identityFromBackupKey(String key) {
		return identityFromBackupKey(Edge_AND_DATE_BACKUP_KEY_PATTERN, BACKUP_KEY_DATE_FORMAT, key);
	}

	/**
	 * Extract backup identity information from a backup key.
	 * 
	 * @param EdgeIdAndDatePattern
	 *        a pattern that contains groups for a Edge ID, date, and an
	 *        optional qualifier
	 * @param dateFormat
	 *        the date format to parse the date with
	 * @param key
	 *        the key to extract the details from
	 * @return the extracted details, or {@literal null} if none found
	 * @since 1.1
	 */
	public static final BackupIdentity identityFromBackupKey(Pattern EdgeIdAndDatePattern,
			String dateFormat, String key) {
		BackupIdentity result = null;
		if ( key != null ) {
			Matcher m = EdgeIdAndDatePattern.matcher(key);
			if ( m.find() ) {
				final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				try {
					Long EdgeId = Long.valueOf(m.group(1));
					Date date = sdf.parse(m.group(2));
					String qualifier = null;
					if ( m.groupCount() > 2 ) {
						qualifier = m.group(m.groupCount());
					}
					result = new SimpleBackupIdentity(key, date, EdgeId, qualifier);
				} catch ( NumberFormatException e ) {
					// ignore
				} catch ( ParseException e ) {
					// ignore
				}
			}
		}
		return result;
	}

	@Override
	public synchronized boolean markBackupForRestore(Backup backup, Map<String, String> props) {
		File markFile = markedBackupForRestoreFile();
		if ( backup == null ) {
			if ( markFile.exists() ) {
				log.info("Clearing marked backup.");
				return markFile.delete();
			}
			return true;
		} else if ( markFile.exists() ) {
			log.warn("Marked backup exists, will not mark again");
			return false;
		} else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put(MARKED_BACKUP_PROP_KEY, backup.getKey());
			if ( props != null && !props.isEmpty() ) {
				data.put(MARKED_BACKUP_PROP_PROPS, props);
			}
			try {
				OBJECT_MAPPER.writeValue(markFile, data);
				return true;
			} catch ( IOException e ) {
				log.warn("Failed to create restore mark file {}", markFile, e);
			}
			return false;
		}
	}

	@Override
	public synchronized Backup markedBackupForRestore(Map<String, String> props) {
		File markFile = markedBackupForRestoreFile();
		if ( markFile.exists() ) {
			try {
				@SuppressWarnings("unchecked")
				Map<String, Object> data = OBJECT_MAPPER.readValue(markFile, Map.class);
				if ( data == null || !data.containsKey(MARKED_BACKUP_PROP_KEY) ) {
					return null;
				}
				String key = (String) data.get(MARKED_BACKUP_PROP_KEY);
				if ( props != null && data.get(MARKED_BACKUP_PROP_PROPS) instanceof Map ) {
					@SuppressWarnings("unchecked")
					Map<String, String> dataProps = (Map<String, String>) data
							.get(MARKED_BACKUP_PROP_PROPS);
					props.putAll(dataProps);
				}
				return backupForKey(key);
			} catch ( IOException e ) {
				log.warn("Failed to read restore mark file {}", markFile, e);
			}
		}
		return null;
	}

}
