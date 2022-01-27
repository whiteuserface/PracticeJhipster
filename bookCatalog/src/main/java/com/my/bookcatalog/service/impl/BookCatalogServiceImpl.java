package com.my.bookcatalog.service.impl;

import com.my.bookcatalog.domain.BookCatalog;
import com.my.bookcatalog.repository.BookCatalogRepository;
import com.my.bookcatalog.service.BookCatalogService;
import com.my.bookcatalog.service.dto.BookCatalogDTO;
import com.my.bookcatalog.service.mapper.BookCatalogMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BookCatalog}.
 */
@Service
@Transactional
public class BookCatalogServiceImpl implements BookCatalogService {

    private final Logger log = LoggerFactory.getLogger(BookCatalogServiceImpl.class);

    private final BookCatalogRepository bookCatalogRepository;

    private final BookCatalogMapper bookCatalogMapper;

    public BookCatalogServiceImpl(BookCatalogRepository bookCatalogRepository, BookCatalogMapper bookCatalogMapper) {
        this.bookCatalogRepository = bookCatalogRepository;
        this.bookCatalogMapper = bookCatalogMapper;
    }

    @Override
    public BookCatalogDTO save(BookCatalogDTO bookCatalogDTO) {
        log.debug("Request to save BookCatalog : {}", bookCatalogDTO);
        BookCatalog bookCatalog = bookCatalogMapper.toEntity(bookCatalogDTO);
        bookCatalog = bookCatalogRepository.save(bookCatalog);
        return bookCatalogMapper.toDto(bookCatalog);
    }

    @Override
    public Optional<BookCatalogDTO> partialUpdate(BookCatalogDTO bookCatalogDTO) {
        log.debug("Request to partially update BookCatalog : {}", bookCatalogDTO);

        return bookCatalogRepository
            .findById(bookCatalogDTO.getId())
            .map(existingBookCatalog -> {
                bookCatalogMapper.partialUpdate(existingBookCatalog, bookCatalogDTO);

                return existingBookCatalog;
            })
            .map(bookCatalogRepository::save)
            .map(bookCatalogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookCatalogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BookCatalogs");
        return bookCatalogRepository.findAll(pageable).map(bookCatalogMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookCatalogDTO> findOne(Long id) {
        log.debug("Request to get BookCatalog : {}", id);
        return bookCatalogRepository.findById(id).map(bookCatalogMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookCatalog : {}", id);
        bookCatalogRepository.deleteById(id);
    }
}
