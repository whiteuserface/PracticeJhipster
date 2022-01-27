package com.my.bookcatalog.repository;

import com.my.bookcatalog.domain.BookCatalog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BookCatalog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookCatalogRepository extends JpaRepository<BookCatalog, Long>, JpaSpecificationExecutor<BookCatalog> {}
