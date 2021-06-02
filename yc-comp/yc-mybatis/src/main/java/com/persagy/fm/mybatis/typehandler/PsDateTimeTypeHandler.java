package com.persagy.fm.mybatis.typehandler;

import cn.hutool.core.util.StrUtil;
import com.persagy.yc.common.lang.PsDateTime;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mybatis日期时间全局处理
 * @author Charlie Yu
 * @version 1.0 2021-04-08
 */
@MappedJdbcTypes(JdbcType.CHAR)
@MappedTypes(PsDateTime.class)
public class PsDateTimeTypeHandler extends BaseTypeHandler<PsDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, PsDateTime psDateTime, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, psDateTime.toString());
    }

    @Override
    public PsDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String date = resultSet.getString(s);
        return StrUtil.isBlank(date)? null:PsDateTime.parsePsDateTime(date);
    }

    @Override
    public PsDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String date = resultSet.getString(i);
        return StrUtil.isBlank(date)? null:PsDateTime.parsePsDateTime(date);
    }

    @Override
    public PsDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String date = callableStatement.getString(i);
        return StrUtil.isBlank(date)? null:PsDateTime.parsePsDateTime(date);
    }
}
