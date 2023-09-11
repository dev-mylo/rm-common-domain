package com.rm.domain.common;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumModelTypeHandler<E extends Enum<E>> implements TypeHandler<EnumModel> {

    private Class <E> type;

    public EnumModelTypeHandler(Class <E> type) {
        this.type = type;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, EnumModel parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public EnumModel getResult(ResultSet rs, String columnName) throws SQLException {
        return getCodeEnum(rs.getObject(columnName));
    }

    @Override
    public EnumModel getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getCodeEnum(rs.getObject(columnIndex));
    }

    @Override
    public EnumModel getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getCodeEnum(cs.getObject(columnIndex));
    }

    public E getCodeEnumByString(String code) {
        EnumModel enumModel = getCodeEnum(code);
        return (E)enumModel;
    }
    private EnumModel getCodeEnum(Object object) {
        if (object instanceof Integer || object instanceof String) {
            try {
                EnumModel[] enumConstants = (EnumModel[]) type.getEnumConstants();
                for (EnumModel enumModel : enumConstants) {
                    if (object instanceof String && object.equals(enumModel.getValue())) {
                        return enumModel;
                    }
                }
                return null;
            } catch (Exception e) {
                throw new TypeException("can't make enum '" + type + "'", e);
            }
        } else if(object == null) {
            return null;
        } else {
            throw new TypeException("Can't parse '"+object+"' to enum '"+type.getName()+"'!");
        }
    }
}