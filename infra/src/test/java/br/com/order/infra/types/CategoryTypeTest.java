package br.com.order.infra.types;

import br.com.order.domain.core.domain.entities.Category;
import br.com.order.infra.types.CategoryType;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryTypeTest {

    private CategoryType categoryType;

    @Mock
    private ResultSet resultSet;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private SharedSessionContractImplementor session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryType = new CategoryType();
    }

    @Test
    void testGetSqlType() {
        assertEquals(Types.OTHER, categoryType.getSqlType());
    }

    @Test
    void testReturnedClass() {
        assertEquals(Category.class, categoryType.returnedClass());
    }

    @Test
    void testEquals() {
        Category category1 = Category.ACOMPANHAMENTO;
        Category category2 = Category.BEBIDA;
        assertFalse(categoryType.equals(category1, category2));
    }

    @Test
    void testHashCode() {
        Category category = Category.ACOMPANHAMENTO;
        assertEquals(category.hashCode(), categoryType.hashCode(category));
    }

    @Test
    void testNullSafeGetNull() throws SQLException {
        when(resultSet.getObject(1)).thenReturn(null);
        when(resultSet.wasNull()).thenReturn(true);

        Category category = categoryType.nullSafeGet(resultSet, 1, session, null);

        assertNull(category);
    }

    @Test
    void testNullSafeSetNotNull() throws SQLException {
        Category category = Category.ACOMPANHAMENTO;

        categoryType.nullSafeSet(preparedStatement, category, 1, session);

        verify(preparedStatement).setObject(1, category, Types.OTHER);
    }

    @Test
    void testNullSafeSetNull() throws SQLException {
        categoryType.nullSafeSet(preparedStatement, null, 1, session);

        verify(preparedStatement).setNull(1, Types.OTHER);
    }

    @Test
    void testDeepCopy() {
        Category category = Category.ACOMPANHAMENTO;
        Category copiedCategory = categoryType.deepCopy(category);

        assertNotNull(copiedCategory);
        assertEquals(category.getCode(), copiedCategory.getCode());
    }

    @Test
    void testIsMutable() {
        assertTrue(categoryType.isMutable());
    }

    @Test
    void testDisassemble() {
        Category category = Category.ACOMPANHAMENTO;
        Serializable disassembled = categoryType.disassemble(category);

        assertEquals(category, disassembled);
    }

    @Test
    void testAssemble() {
        Category category = Category.ACOMPANHAMENTO;
        Category assembled = categoryType.assemble(category, null);

        assertNotNull(assembled);
        assertEquals(category.getCode(), assembled.getCode());
    }

    @Test
    void testReplace() {
        Category category = Category.ACOMPANHAMENTO;
        Category replacedCategory = categoryType.replace(category, null, null);

        assertNotNull(replacedCategory);
        assertEquals(category.getCode(), replacedCategory.getCode());
    }
}
