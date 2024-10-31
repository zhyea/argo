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
public class NullBigIntHandler extends BaseTypeHandler<Long> {


    private static final Long ZERO = 0L;


    @Override
    public void setParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        if (null == parameter) {
            parameter = ZERO;
        }
        super.setParameter(ps, i, parameter, jdbcType);
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter);
    }


    @Override
    public Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getLong(columnName);
    }


    @Override
    public Long getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getLong(columnIndex);
    }


    @Override
    public Long getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getLong(columnIndex);
    }

}
