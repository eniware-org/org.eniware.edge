/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */
package org.eniware.edge.settings.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eniware.edge.settings.GroupSettingSpecifier;
import org.eniware.edge.settings.SettingSpecifier;

/**
 * Helper utilities for settings.
 * 
 * @version 1.0
 */
public final class SettingsUtil {

	private SettingsUtil() {
		// Do not construct me.
	}

	/**
	 * API to map a list element into a set of {@link SettingSpecifier} objects.
	 * 
	 * @param <T>
	 *        The collection type.
	 */
	public interface KeyedListCallback<T> {

		/**
		 * Map a single list element value into one or more
		 * {@link SettingSpecifier} objects.
		 * 
		 * @param value
		 *        The list element value.
		 * @param index
		 *        The list element index.
		 * @param key
		 *        An indexed key prefix to use for the grouped settings.
		 * @return The settings.
		 */
		public Collection<SettingSpecifier> mapListSettingKey(T value, int index, String key);

	}

	/**
	 * Get a dynamic list {@link GroupSettingSpecifier}.
	 * 
	 * @param collection
	 *        The collection to turn into settings.
	 * @param mapper
	 *        A helper to map individual elements into settings.
	 * @return The resulting {@link GroupSettingSpecifier}.
	 */
	public static <T> BasicGroupSettingSpecifier dynamicListSettingSpecifier(String key,
			Collection<T> collection, KeyedListCallback<T> mapper) {
		List<SettingSpecifier> listStringGroupSettings;
		if ( collection == null ) {
			listStringGroupSettings = Collections.emptyList();
		} else {
			final int len = collection.size();
			listStringGroupSettings = new ArrayList<SettingSpecifier>(len);
			int i = 0;
			for ( T value : collection ) {
				Collection<SettingSpecifier> res = mapper.mapListSettingKey(value, i, key + "[" + i
						+ "]");
				i++;
				if ( res != null ) {
					listStringGroupSettings.addAll(res);
				}
			}
		}
		return new BasicGroupSettingSpecifier(key, listStringGroupSettings, true);
	}
}
