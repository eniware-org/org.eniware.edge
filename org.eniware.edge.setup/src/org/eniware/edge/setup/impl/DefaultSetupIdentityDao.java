/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.setup.impl;

import static org.eniware.edge.SetupSettings.SETUP_TYPE_KEY;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.FileCopyUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eniware.edge.backup.BackupResource;
import org.eniware.edge.backup.BackupResourceInfo;
import org.eniware.edge.backup.BackupResourceProvider;
import org.eniware.edge.backup.BackupResourceProviderInfo;
import org.eniware.edge.backup.ResourceBackupResource;
import org.eniware.edge.backup.SimpleBackupResourceInfo;
import org.eniware.edge.backup.SimpleBackupResourceProviderInfo;
import org.eniware.edge.dao.SettingDao;
import org.eniware.util.OptionalService;

/**
 * Default implementation of {@link SetupIdentityDao} that saves data to a JSON
 * file.
 * 
 * @version 1.0
 * @since 1.18
 */
public class DefaultSetupIdentityDao implements SetupIdentityDao, BackupResourceProvider {

	/** The default value for the {@code dataFilePath} property. */
	public static final String DEFAULT_DATA_FILE_PATH = "conf/identity.json";

	private static final String BACKUP_RESOURCE_NAME_DATA_FILE = "identity.json";

	public static final String KEY_NODE_ID = "eniwareedge.id";
	public static final String KEY_ENIWARENETWORK_HOST_NAME = "eniwareedge.eniwarenet.host";
	public static final String KEY_ENIWARENETWORK_HOST_PORT = "eniwareedge.eniwarenet.port";
	public static final String KEY_ENIWARENETWORK_FORCE_TLS = "eniwareedge.eniwarenet.forceTLS";
	public static final String KEY_CONFIRMATION_CODE = "eniwareedge.eniwarenet.confirmation";
	public static final String KEY_KEY_STORE_PASSWORD = "eniwareedge.keystore.pw";

	private final ObjectMapper objectMapper;
	private final AtomicReference<SetupIdentityInfo> cachedInfo = new AtomicReference<SetupIdentityInfo>(
			null);
	private String dataFilePath = DEFAULT_DATA_FILE_PATH;
	private OptionalService<SettingDao> settingDao;
	private MessageSource messageSource;

	private final Logger log = LoggerFactory.getLogger(getClass());

	public DefaultSetupIdentityDao(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	@Override
	public SetupIdentityInfo getSetupIdentityInfo() {
		SetupIdentityInfo info = cachedInfo.get();
		if ( info == null ) {
			info = loadData();
			if ( info != null ) {
				cachedInfo.compareAndSet(null, info);
			}
		}
		return (info != null ? info : SetupIdentityInfo.UNKNOWN_IDENTITY);
	}

	@Override
	public void saveSetupIdentityInfo(SetupIdentityInfo info) {
		assert info != null;
		SetupIdentityInfo curr = cachedInfo.get();
		if ( !info.equals(curr) ) {
			if ( saveData(info) ) {
				cachedInfo.compareAndSet(curr, info);
			}
		}
	}

	private synchronized SetupIdentityInfo loadData() {
		SetupIdentityInfo result = null;
		File dataFile = new File(dataFilePath);
		if ( dataFile.canRead() ) {
			try {
				result = objectMapper.readValue(dataFile, SetupIdentityInfo.class);
			} catch ( IOException e ) {
				log.warn("Error reading identity data from {}: {}", dataFilePath, e.getMessage());
			}
		} else {
			// try load from DB
			result = loadLegacySettingsData();
			if ( result != null ) {
				saveData(result);
			}
		}
		return result;
	}

	private SetupIdentityInfo loadLegacySettingsData() {
		SettingDao dao = (settingDao != null ? settingDao.service() : null);
		if ( dao != null ) {
			String nodeId = dao.getSetting(KEY_NODE_ID, SETUP_TYPE_KEY);
			String confCode = dao.getSetting(KEY_CONFIRMATION_CODE, SETUP_TYPE_KEY);
			String hostName = dao.getSetting(KEY_ENIWARENETWORK_HOST_NAME, SETUP_TYPE_KEY);
			String hostPort = dao.getSetting(KEY_ENIWARENETWORK_HOST_PORT, SETUP_TYPE_KEY);
			String forceTls = dao.getSetting(KEY_ENIWARENETWORK_FORCE_TLS, SETUP_TYPE_KEY);
			String keyStorePw = dao.getSetting(KEY_KEY_STORE_PASSWORD, SETUP_TYPE_KEY);
			if ( nodeId != null && confCode != null && hostName != null && hostPort != null
					&& forceTls != null && keyStorePw != null ) {
				SetupIdentityInfo info = new SetupIdentityInfo(Long.valueOf(nodeId), confCode, hostName,
						Integer.valueOf(hostPort), Boolean.parseBoolean(forceTls), keyStorePw);
				return info;
			}
		}
		return null;
	}

	private synchronized boolean saveData(SetupIdentityInfo data) {
		File dataFile = new File(dataFilePath);
		File tmpFile = new File(dataFile.getParentFile(), "." + dataFile.getName());
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(tmpFile, data);
			dataFile.delete();
			return tmpFile.renameTo(dataFile);
		} catch ( IOException e ) {
			log.warn("Error writing identity data to {}: {}", dataFilePath, e.getMessage());
		} finally {
			if ( tmpFile.exists() ) {
				tmpFile.delete();
			}
		}
		return false;
	}

	@Override
	public String getKey() {
		return DefaultSetupIdentityDao.class.getName();
	}

	@Override
	public Iterable<BackupResource> getBackupResources() {
		File file = new File(dataFilePath);
		if ( !(file.isFile() && file.canRead()) ) {
			return Collections.emptyList();
		}
		List<BackupResource> result = new ArrayList<BackupResource>(1);
		result.add(new ResourceBackupResource(new FileSystemResource(file),
				BACKUP_RESOURCE_NAME_DATA_FILE, getKey()));
		return result;
	}

	@Override
	public boolean restoreBackupResource(BackupResource resource) {
		if ( resource != null
				&& BACKUP_RESOURCE_NAME_DATA_FILE.equalsIgnoreCase(resource.getBackupPath()) ) {
			final File destFile = new File(dataFilePath);
			final File destDir = destFile.getParentFile();
			if ( !destDir.isDirectory() ) {
				if ( !destDir.mkdirs() ) {
					log.warn("Error creating identity directory {}", destDir.getAbsolutePath());
					return false;
				}
			}
			synchronized ( this ) {
				File tmpFile = null;
				try {
					tmpFile = File.createTempFile(".identity-", ".json", destDir);
					FileCopyUtils.copy(resource.getInputStream(), new FileOutputStream(tmpFile));
					tmpFile.setLastModified(resource.getModificationDate());
					if ( destFile.exists() ) {
						destFile.delete();
					}
					return tmpFile.renameTo(destFile);
				} catch ( IOException e ) {
					log.error("IO error restoring identity resource {}: {}", destFile.getAbsolutePath(),
							e.getMessage());
					return false;
				} finally {
					if ( tmpFile != null && tmpFile.exists() ) {
						tmpFile.delete();
					}
				}
			}
		}
		return false;
	}

	@Override
	public BackupResourceProviderInfo providerInfo(Locale locale) {
		String name = "Node Identity Provider";
		String desc = "Backs up the EniwareEdge identity.";
		MessageSource ms = messageSource;
		if ( ms != null ) {
			name = ms.getMessage("title", null, name, locale);
			desc = ms.getMessage("desc", null, desc, locale);
		}
		return new SimpleBackupResourceProviderInfo(getKey(), name, desc);
	}

	@Override
	public BackupResourceInfo resourceInfo(BackupResource resource, Locale locale) {
		String desc = "Node identity information.";
		MessageSource ms = messageSource;
		if ( ms != null ) {
			desc = ms.getMessage("identity.desc", null, desc, locale);
		}
		return new SimpleBackupResourceInfo(resource.getProviderKey(), resource.getBackupPath(), desc);
	}

	/**
	 * The path to store the identity data in.
	 * 
	 * @param dataFilePath
	 *        the data file path; defaults to {@link #DEFAULT_DATA_FILE_PATH}
	 */
	public void setDataFilePath(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}

	/**
	 * Set an optional {@link SettingDao}.
	 * 
	 * <p>
	 * This is only used for backwards compatibility, to load legacy settings
	 * from {@link SettingDao} when the native identity data is not available.
	 * </p>
	 * 
	 * @param settingDao
	 *        the DAO to use
	 */
	public void setSettingDao(OptionalService<SettingDao> settingDao) {
		this.settingDao = settingDao;
	}

	/**
	 * Set a message source for backup localized messages.
	 * 
	 * @param messageSource
	 *        the message source
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
