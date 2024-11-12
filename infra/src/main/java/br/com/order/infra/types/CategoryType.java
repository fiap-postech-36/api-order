package br.com.order.infra.types;

import br.com.order.domain.core.domain.entities.Category;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class CategoryType implements UserType<Category> {

    public static final CategoryType INSTANCE = new CategoryType();

    @Override
    public int getSqlType() {
        return Types.OTHER;
    }

    @Override
    public Class<Category> returnedClass() {
        return Category.class;
    }

    @Override
    public boolean equals(Category x, Category y) {
        return x.equals(y);
    }

    @Override
    public int hashCode(Category x) {
        return Objects.hashCode(x);
    }

    @Override
    public Category nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session,
                              Object owner) throws SQLException {
        String columnValue = (String) rs.getObject(position);
        if (rs.wasNull()) {
            columnValue = null;
        }

        return Category.fromCode(columnValue);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Category value, int index,
                            SharedSessionContractImplementor session) throws SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        }
        else {
            st.setObject(index, value, Types.OTHER);
        }
    }

    @Override
    public Category deepCopy(Category value) {
        return value == null ? null : Category.fromCode(value.getCode());
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Category value) {
        return deepCopy(value);
    }

    @Override
    public Category assemble(Serializable cached, Object owner) {
        return deepCopy((Category) cached);
    }

    @Override
    public Category replace(Category detached, Category managed, Object owner) {
        return deepCopy(detached);
    }
}
