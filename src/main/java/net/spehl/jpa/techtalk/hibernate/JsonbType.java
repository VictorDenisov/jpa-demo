/*-
 * ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
 * Autonomic Proprietary 1.0
 * ——————————————————————————————————————————————————————————————————————————————
 * Copyright (C) 2017 Autonomic Incorporated - All rights reserved
 * ——————————————————————————————————————————————————————————————————————————————
 * Proprietary and confidential.
 * 
 * NOTICE:  All information contained herein is, and remains the property of
 * Autonomic, LLC and its suppliers, if any.  The intellectual and technical
 * concepts contained herein are proprietary to Autonomic, LLC and its suppliers
 * and may be covered by U.S. and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Autonomic, LLC.
 * 
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * ______________________________________________________________________________
 */

package net.spehl.jpa.techtalk.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.SerializationException;
import org.hibernate.usertype.UserType;

/**
 * Maps Postgres jsonb type to java String type.
 */
public class JsonbType implements UserType {

    /**
     * @return Map to java.sql.Types
     */
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.LONGVARCHAR};
    }

    /**
     * @return Java Class which is the result of mapping from JSONB
     */
    @Override
    public Class<String> returnedClass() {
        return String.class;
    }

    /**
     * Create a new instance of the mapped class from JDBC resultset
     */
    @Override
    public Object nullSafeGet(final ResultSet resultSet,
                              final String[] names,
                              final SessionImplementor session,
                              final Object owner) throws HibernateException, SQLException {
        return resultSet.getString(names[0]);
    }

    /**
     * Write an instance of the mapped class to a Prepared Statement
     */
    @Override
    public void nullSafeSet(final PreparedStatement ps,
                            final Object value,
                            final int idx,
                            final SessionImplementor session) throws HibernateException, SQLException {
        if (value instanceof String) {
            ps.setObject(idx, value, Types.OTHER);
        } else {
            ps.setNull(idx, Types.OTHER);
        }
    }

    /**
     * @return true - a json type is mutable
     */
    public boolean isMutable() {
        return true;
    }

    /**
     * Convert object to cacheable representation.
     * @param object
     * @return cacheable representation
     * @throws HibernateException
     */
    public Serializable disassemble(Object object) throws HibernateException {
        Object copy = deepCopy(object);

        if (copy instanceof Serializable) {
            return (Serializable) copy;
        }

        throw new SerializationException(String.format(
                "Cannot serialize '%s', %s is not Serializable.",
                object,
                object.getClass()), null);
    }


    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    /**
     * Called during merging from a detached entity.
     */
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    @Override
    public Object deepCopy(final Object object) throws HibernateException {
        if (object instanceof String) {
            return ((String) object); // strings are immutable so a noop deep copy should be fine.
        }
        return null;
    }

    public boolean equals(Object object1, Object object2) throws HibernateException {
        return Objects.equals(object1, object2);
    }

    public int hashCode(Object object) throws HibernateException {
        return Objects.hashCode(object);
    }
}
