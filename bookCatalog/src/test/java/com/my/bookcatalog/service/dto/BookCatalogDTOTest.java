package com.my.bookcatalog.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.my.bookcatalog.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BookCatalogDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookCatalogDTO.class);
        BookCatalogDTO bookCatalogDTO1 = new BookCatalogDTO();
        bookCatalogDTO1.setId(1L);
        BookCatalogDTO bookCatalogDTO2 = new BookCatalogDTO();
        assertThat(bookCatalogDTO1).isNotEqualTo(bookCatalogDTO2);
        bookCatalogDTO2.setId(bookCatalogDTO1.getId());
        assertThat(bookCatalogDTO1).isEqualTo(bookCatalogDTO2);
        bookCatalogDTO2.setId(2L);
        assertThat(bookCatalogDTO1).isNotEqualTo(bookCatalogDTO2);
        bookCatalogDTO1.setId(null);
        assertThat(bookCatalogDTO1).isNotEqualTo(bookCatalogDTO2);
    }
}
