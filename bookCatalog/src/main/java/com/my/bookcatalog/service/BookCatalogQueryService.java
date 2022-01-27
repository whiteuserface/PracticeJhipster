package com.my.bookcatalog.service;

import com.my.bookcatalog.domain.*; // for static metamodels
import com.my.bookcatalog.domain.BookCatalog;
import com.my.bookcatalog.repository.BookCatalogRepository;
import com.my.bookcatalog.service.criteria.BookCatalogCriteria;
import com.my.bookcatalog.service.dto.BookCatalogDTO;
import com.my.bookcatalog.service.mapper.BookCatalogMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link BookCatalog} entities in the database.
 * The main input is a {@link BookCatalogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BookCatalogDTO} or a {@link Page} of {@link BookCatalogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BookCatalogQueryService extends QueryService<BookCatalog> {

    private final Logger log = LoggerFactory.getLogger(BookCatalogQueryService.class);

    private final BookCatalogRepository bookCatalogRepository;

    private final BookCatalogMapper bookCatalogMapper;

    public BookCatalogQueryService(BookCatalogRepository bookCatalogRepository, BookCatalogMapper bookCatalogMapper) {
        this.bookCatalogRepository = bookCatalogRepository;
        this.bookCatalogMapper = bookCatalogMapper;
    }

    /**
     * Return a {@link List} of {@link BookCatalogDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BookCatalogDTO> findByCriteria(BookCatalogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BookCatalog> specification = createSpecification(criteria);
        return bookCatalogMapper.toDto(bookCatalogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BookCatalogDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BookCatalogDTO> findByCriteria(BookCatalogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BookCatalog> specification = createSpecification(criteria);
        return bookCatalogRepository.findAll(specification, page).map(bookCatalogMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BookCatalogCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BookCatalog> specification = createSpecification(criteria);
        return bookCatalogRepository.count(specification);
    }

    /**
     * Function to convert {@link BookCatalogCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BookCatalog> createSpecification(BookCatalogCriteria criteria) {
        Specification<BookCatalog> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BookCatalog_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), BookCatalog_.title));
            }
            if (criteria.getAuthor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthor(), BookCatalog_.author));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), BookCatalog_.description));
            }
            if (criteria.getBookId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBookId(), BookCatalog_.bookId));
            }
            if (criteria.getRentCnt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRentCnt(), BookCatalog_.rentCnt));
            }
        }
        return specification;
    }
}
