package com.softech.ls360.database.dialect;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StandardBasicTypes;

public class SQLServerNativeDialect extends SQLServerDialect {
	
	public SQLServerNativeDialect() {
        super();
        registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
    }

}
