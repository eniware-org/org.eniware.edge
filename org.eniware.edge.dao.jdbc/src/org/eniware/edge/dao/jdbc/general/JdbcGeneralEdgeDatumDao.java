/* ==================================================================
 *  Eniware Open sorce:Nikolai Manchev
 *  Apache License 2.0
 * ==================================================================
 */

package org.eniware.edge.dao.jdbc.general;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.eniware.domain.GeneralDatumSamples;
import org.eniware.domain.GeneralEdgeDatumSamples;
import org.eniware.edge.domain.GeneralEdgeDatum;

import org.eniware.edge.dao.jdbc.AbstractJdbcDatumDao;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JDBC-based implementation of {@link org.eniware.edge.dao.DatumDao} for
 * {@link GeneralEdgeDatum} domain objects.
 * 
 * @version 1.1
 */
public class JdbcGeneralEdgeDatumDao extends AbstractJdbcDatumDao<GeneralEdgeDatum> {

	/** The default tables version. */
	public static final int DEFAULT_TABLES_VERSION = 1;

	/** The table name for {@link PowerDatum} data. */
	public static final String TABLE_GENERAL_Edge_DATUM = "sn_general_Edge_datum";

	/** The default classpath Resource for the {@code initSqlResource}. */
	public static final String DEFAULT_INIT_SQL = "derby-generalEdgedatum-init.sql";

	/** The default value for the {@code sqlGetTablesVersion} property. */
	public static final String DEFAULT_SQL_GET_TABLES_VERSION = "SELECT svalue FROM eniwareedge.sn_settings WHERE skey = "
			+ "'eniwareedge.sn_general_Edge_datum.version'";

	private ObjectMapper objectMapper;

	/**
	 * Default constructor.
	 */
	public JdbcGeneralEdgeDatumDao() {
		super();
		setSqlResourcePrefix("derby-generalEdgedatum");
		setTableName(TABLE_GENERAL_Edge_DATUM);
		setTablesVersion(DEFAULT_TABLES_VERSION);
		setSqlGetTablesVersion(DEFAULT_SQL_GET_TABLES_VERSION);
		setInitSqlResource(new ClassPathResource(DEFAULT_INIT_SQL, getClass()));
	}

	@Override
	public Class<? extends GeneralEdgeDatum> getDatumType() {
		return GeneralEdgeDatum.class;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = DuplicateKeyException.class)
	public void storeDatum(GeneralEdgeDatum datum) {
		try {
			storeDomainObject(datum);
		} catch ( DuplicateKeyException e ) {
			List<GeneralEdgeDatum> existing = findDatum(SQL_RESOURCE_FIND_FOR_PRIMARY_KEY,
					preparedStatementSetterForPrimaryKey(datum.getCreated(), datum.getSourceId()),
					rowMapper());
			if ( existing.size() > 0 ) {
				// only update if the samples have changed
				GeneralDatumSamples existingSamples = existing.get(0).getSamples();
				GeneralDatumSamples newSamples = datum.getSamples();
				if ( !newSamples.equals(existingSamples) ) {
					updateDomainObject(datum, getSqlResource(SQL_RESOURCE_UPDATE_DATA));
				}
			}
		}
	}

	@Override
	protected void setUpdateStatementValues(GeneralEdgeDatum datum, PreparedStatement ps)
			throws SQLException {
		int col = 1;
		ps.setString(col++, jsonForSamples(datum));
		ps.setTimestamp(col++, new Timestamp(datum.getCreated().getTime()));
		ps.setString(col++, datum.getSourceId());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void setDatumUploaded(GeneralEdgeDatum datum, Date date, String destination, String trackingId) {
		updateDatumUpload(datum, date == null ? System.currentTimeMillis() : date.getTime());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int deleteUploadedDataOlderThan(int hours) {
		return deleteUploadedDataOlderThanHours(hours);
	}

	private RowMapper<GeneralEdgeDatum> rowMapper() {
		return new RowMapper<GeneralEdgeDatum>() {

			@Override
			public GeneralEdgeDatum mapRow(ResultSet rs, int rowNum) throws SQLException {
				if ( log.isTraceEnabled() ) {
					log.trace("Handling result row " + rowNum);
				}
				GeneralEdgeDatum datum = new GeneralEdgeDatum();
				int col = 0;
				datum.setCreated(rs.getTimestamp(++col));
				datum.setSourceId(rs.getString(++col));

				String jdata = rs.getString(++col);
				if ( jdata != null ) {
					GeneralEdgeDatumSamples s;
					try {
						s = objectMapper.readValue(jdata, GeneralEdgeDatumSamples.class);
						datum.setSamples(s);
					} catch ( IOException e ) {
						log.error("Error deserializing JSON into GeneralEdgeDatumSamples: {}",
								e.getMessage());
					}
				}
				return datum;
			}
		};
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<GeneralEdgeDatum> getDatumNotUploaded(String destination) {
		return findDatumNotUploaded(rowMapper());
	}

	private String jsonForSamples(GeneralEdgeDatum datum) {
		String json;
		try {
			json = objectMapper.writeValueAsString(datum.getSamples());
		} catch ( IOException e ) {
			log.error("Error serializing GeneralDatumSamples into JSON: {}", e.getMessage());
			json = "{}";
		}
		return json;
	}

	@Override
	protected void setStoreStatementValues(GeneralEdgeDatum datum, PreparedStatement ps)
			throws SQLException {
		int col = 0;
		ps.setTimestamp(++col,
				new java.sql.Timestamp(datum.getCreated() == null ? System.currentTimeMillis() : datum
						.getCreated().getTime()));
		ps.setString(++col, datum.getSourceId() == null ? "" : datum.getSourceId());

		String json = jsonForSamples(datum);
		ps.setString(++col, json);
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

}
