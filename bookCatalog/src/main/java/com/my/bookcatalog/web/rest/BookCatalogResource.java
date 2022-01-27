package com.my.bookcatalog.web.rest;

import com.my.bookcatalog.repository.BookCatalogRepository;
import com.my.bookcatalog.service.BookCatalogQueryService;
import com.my.bookcatalog.service.BookCatalogService;
import com.my.bookcatalog.service.criteria.BookCatalogCriteria;
import com.my.bookcatalog.service.dto.BookCatalogDTO;
import com.my.bookcatalog.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.my.bookcatalog.domain.BookCatalog}.
 */
@RestController
@RequestMapping("/api")
public class BookCatalogResource {

    private final Logger log = LoggerFactory.getLogger(BookCatalogResource.class);

    private static final String ENTITY_NAME = "bookCatalogBookCatalog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookCatalogService bookCatalogService;

    private final BookCatalogRepository bookCatalogRepository;

    private final BookCatalogQueryService bookCatalogQueryService;

    public BookCatalogResource(
        BookCatalogService bookCatalogService,
        BookCatalogRepository bookCatalogRepository,
        BookCatalogQueryService bookCatalogQueryService
    ) {
        this.bookCatalogService = bookCatalogService;
        this.bookCatalogRepository = bookCatalogRepository;
        this.bookCatalogQueryService = bookCatalogQueryService;
    }

    /**
     * {@code POST  /book-catalogs} : Create a new bookCatalog.
     *
     * @param bookCatalogDTO the bookCatalogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bookCatalogDTO, or with status {@code 400 (Bad Request)} if the bookCatalog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/book-catalogs")
    public ResponseEntity<BookCatalogDTO> createBookCatalog(@RequestBody BookCatalogDTO bookCatalogDTO) throws URISyntaxException {
        log.debug("REST request to save BookCatalog : {}", bookCatalogDTO);
        if (bookCatalogDTO.getId() != null) {
            throw new BadRequestAlertException("A new bookCatalog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookCatalogDTO result = bookCatalogService.save(bookCatalogDTO);
        return ResponseEntity
            .created(new URI("/api/book-catalogs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /book-catalogs/:id} : Updates an existing bookCatalog.
     *
     * @param id the id of the bookCatalogDTO to save.
     * @param bookCatalogDTO the bookCatalogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookCatalogDTO,
     * or with status {@code 400 (Bad Request)} if the bookCatalogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bookCatalogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/book-catalogs/{id}")
    public ResponseEntity<BookCatalogDTO> updateBookCatalog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BookCatalogDTO bookCatalogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BookCatalog : {}, {}", id, bookCatalogDTO);
        if (bookCatalogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bookCatalogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookCatalogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BookCatalogDTO result = bookCatalogService.save(bookCatalogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bookCatalogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /book-catalogs/:id} : Partial updates given fields of an existing bookCatalog, field will ignore if it is null
     *
     * @param id the id of the bookCatalogDTO to save.
     * @param bookCatalogDTO the bookCatalogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bookCatalogDTO,
     * or with status {@code 400 (Bad Request)} if the bookCatalogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bookCatalogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bookCatalogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/book-catalogs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BookCatalogDTO> partialUpdateBookCatalog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BookCatalogDTO bookCatalogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BookCatalog partially : {}, {}", id, bookCatalogDTO);
        if (bookCatalogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bookCatalogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bookCatalogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BookCatalogDTO> result = bookCatalogService.partialUpdate(bookCatalogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bookCatalogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /book-catalogs} : get all the bookCatalogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bookCatalogs in body.
     */
    @GetMapping("/book-catalogs")
    public ResponseEntity<List<BookCatalogDTO>> getAllBookCatalogs(
        BookCatalogCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get BookCatalogs by criteria: {}", criteria);
        Page<BookCatalogDTO> page = bookCatalogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /book-catalogs/count} : count all the bookCatalogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/book-catalogs/count")
    public ResponseEntity<Long> countBookCatalogs(BookCatalogCriteria criteria) {
        log.debug("REST request to count BookCatalogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(bookCatalogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /book-catalogs/:id} : get the "id" bookCatalog.
     *
     * @param id the id of the bookCatalogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bookCatalogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/book-catalogs/{id}")
    public ResponseEntity<BookCatalogDTO> getBookCatalog(@PathVariable Long id) {
        log.debug("REST request to get BookCatalog : {}", id);
        Optional<BookCatalogDTO> bookCatalogDTO = bookCatalogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bookCatalogDTO);
    }

    /**
     * {@code DELETE  /book-catalogs/:id} : delete the "id" bookCatalog.
     *
     * @param id the id of the bookCatalogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/book-catalogs/{id}")
    public ResponseEntity<Void> deleteBookCatalog(@PathVariable Long id) {
        log.debug("REST request to delete BookCatalog : {}", id);
        bookCatalogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
