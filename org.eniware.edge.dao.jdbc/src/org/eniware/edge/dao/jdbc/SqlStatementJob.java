/* ===================================================================
 *  Eniware Open Source:Nikolai Manchev
 *  Apache License 2.0 * ===================================================================
 */

package org.eniware.edge.dao.jdbc;

import org.eniware.edge.job.AbstractJob;

import org.quartz.JobExecutionContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Job to execute a SQL update statement of some kind.
 * 
 * <p>This can be used to execute periodic SQL administration tasks, such as the 
 * "SHUTDOWN COMPACT" command in hsqldb.</p>
 * 
 * @version $Revision: 398 $ $Date: 2009-10-26 14:39:27 +1300 (Mon, 26 Oct 2009) $
 */
public class SqlStatementJob extends AbstractJob {
	
	private JdbcTemplate jdbcTemplate;
	private String sql;

	@Override
	protected void executeInternal(JobExecutionContext jobContext)
			throws Exception {
		if ( log.isDebugEnabled() ) {
			log.debug("Executing SQL: " +sql);
		}
		jdbcTemplate.execute(sql);
		if ( log.isInfoEnabled() ) {
			log.info("Executed SQL: " +sql);
		}
	}
	
	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}
	
	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

}
