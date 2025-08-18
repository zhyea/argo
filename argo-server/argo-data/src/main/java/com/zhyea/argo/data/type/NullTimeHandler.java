package com.zhyea.argo.data.type;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.chobit.commons.utils.LocalDateKit;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * @author robin
 */
@MappedTypes({Date.class})
@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class NullTimeHandler extends BaseTypeHandler<LocalDateTime> {


	@Override
	public void setParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
		if (null == parameter) {
			parameter = LocalDateKit.fromEpochMilli(0L);
		}
		super.setParameter(ps, i, parameter, jdbcType);
	}


	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
		ps.setTimestamp(i, Timestamp.valueOf(parameter));
	}


	@Override
	public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp value = rs.getTimestamp(columnName);
		return (null == value || 0 == value.getTime() ? null : value.toLocalDateTime());
	}


	@Override
	public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp value = rs.getTimestamp(columnIndex);
		return (null == value || 0 == value.getTime() ? null : value.toLocalDateTime());
	}


	@Override
	public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp value = cs.getTimestamp(columnIndex);
		return (null == value || 0 == value.getTime() ? null : value.toLocalDateTime());
	}
}
