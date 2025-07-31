package com.zhyea.argo.data.type;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author robin
 */
@MappedTypes({String.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class NullIntHandler extends BaseTypeHandler<Integer> {


	private static final Integer ZERO = 0;


	@Override
	public void setParameter(PreparedStatement ps, int i, Integer parameter, JdbcType jdbcType) throws SQLException {
		if (null == parameter) {
			parameter = ZERO;
		}
		super.setParameter(ps, i, parameter, jdbcType);
	}


	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Integer parameter, JdbcType jdbcType) throws SQLException {
		ps.setLong(i, parameter);
	}


	@Override
	public Integer getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getInt(columnName);
	}


	@Override
	public Integer getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getInt(columnIndex);
	}


	@Override
	public Integer getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getInt(columnIndex);
	}

}
