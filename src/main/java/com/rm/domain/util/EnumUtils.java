package com.rm.domain.util;

import com.rm.common.core.exception.RmCommonException;
import com.rm.domain.common.CodeEnumModel;
import com.rm.domain.common.EnumModel;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

public class EnumUtils {
    public static <E extends CodeEnumModel> E getEnum(Object value, Class<E> enumClass) {
        if (value instanceof Integer) {
            return getEnumFromInt((Integer) value, enumClass);
        } else if (value instanceof String) {
            return getEnumFromName((String) value, enumClass);
        } else {
            throw new RmCommonException("WRONG ENUM VALUE: "+value+" IS MUST BE STRING OR INTEGER");
        }
    }

    public static <E extends CodeEnumModel> E  getEnumFromInt(int enumInt, Class<E> enumClass) {
        for (E enumObj : enumClass.getEnumConstants()) {
            if (enumObj.getCode() == enumInt) {
                return enumObj;
            }
        }
        return null;
    }

    public static <E extends CodeEnumModel> E getEnumFromName(String enumName, Class<E> enumClass) {
        for (E enumObj : enumClass.getEnumConstants()) {
            if (equalsIgnoreCase(enumObj.getName(), enumName)) {
                return enumObj;
            }
        }
        return null;
    }
    public static <E extends EnumModel> E getEnumModelFromName(String enumName, Class<E> enumClass) {
        for (E enumObj : enumClass.getEnumConstants()) {
            if (equalsIgnoreCase(enumObj.getKey(), enumName)) {
                return enumObj;
            }
        }
        return null;
    }
    public static <E extends CodeEnumModel> Map<String, Object> getEnumConstants(Class<E> enumClass) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        for (E enumObj : enumClass.getEnumConstants()) {
            resultMap.put(enumObj.toString(), enumObj.getName());
        }
        return resultMap;
    }
}
