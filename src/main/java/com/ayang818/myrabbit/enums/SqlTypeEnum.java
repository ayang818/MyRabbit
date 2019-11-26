package com.ayang818.myrabbit.enums;

public enum SqlTypeEnum {
    SELECT("select"),
    INSERT("insert"),
    UPDATE("update"),
    DELETE("delete");

    private String sqlType;

    SqlTypeEnum(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getSqlType() {
        return sqlType;
    }
}
