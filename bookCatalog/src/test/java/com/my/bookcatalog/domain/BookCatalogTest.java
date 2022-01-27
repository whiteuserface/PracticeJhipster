package com.my.bookcatalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.my.bookcatalog.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BookCatalogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookCatalog.class);
        BookCatalog bookCatalog1 = new BookCatalog();
        bookCatalog1.setId(1L);
        BookCatalog bookCatalog2 = new BookCatalog();
        bookCatalog2.setId(bookCatalog1.getId());
        assertThat(bookCatalog1).isEqualTo(bookCatalog2);
        bookCatalog2.setId(2L);
        assertThat(bookCatalog1).isNotEqualTo(bookCatalog2);
        bookCatalog1.setId(null);
        assertThat(bookCatalog1).isNotEqualTo(bookCatalog2);
    }
}
