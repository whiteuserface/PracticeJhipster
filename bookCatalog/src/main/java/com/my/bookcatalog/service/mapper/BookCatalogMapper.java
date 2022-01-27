package com.my.bookcatalog.service.mapper;

import com.my.bookcatalog.domain.BookCatalog;
import com.my.bookcatalog.service.dto.BookCatalogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BookCatalog} and its DTO {@link BookCatalogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookCatalogMapper extends EntityMapper<BookCatalogDTO, BookCatalog> {}
