package com.rm.domain.common;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeEnumModelTypeHandler<E extends Enum<E>> implements TypeHandler<CodeEnumModel> {

    private Class <E> type;

    public CodeEnumModelTypeHandler(Class <E> type) {
        this.type = type;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, CodeEnumModel parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public CodeEnumModel getResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return getCodeEnum(code);
    }

    @Override
    public CodeEnumModel getResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return getCodeEnum(code);
    }

    @Override
    public CodeEnumModel getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return getCodeEnum(code);
    }

    private CodeEnumModel getCodeEnum(Object object) {
        if (object instanceof Integer || object instanceof String) {
            try {
                CodeEnumModel[] enumConstants = (CodeEnumModel[]) type.getEnumConstants();
                for (CodeEnumModel codeNum: enumConstants) {
                    if ((object instanceof Integer && codeNum.getCode() == (int)object)
                            || (object instanceof String && object.equals(codeNum.getName()))) {
                        return codeNum;
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