package com.bootlabs.controller;

import com.bootlabs.dto.BookDto;
import com.bootlabs.dto.BookPatchDTO;
import com.bootlabs.entities.Book;
import com.bootlabs.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;


/**
 * REST controller for managing {@link Book}.
 *
 * @author @bootteam
 */

@RestController
@Validated
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> replaceBook(@PathVariable Long id, @Valid @RequestBody BookDto dto) {

        Book entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        // Replace all fields
        entity.setTitle(dto.title());
        entity.setIsbn(dto.isbn());
        entity.setDescription(dto.description());
        entity.setPage(dto.page());
        entity.setPrice(dto.price());

        var book = repo.save(entity);
        return ResponseEntity.ok(new BookDto(book.getId(), book.getTitle(), book.getIsbn(), book.getDescription(), book.getPage(), book.getPrice()));
    }


    @PutMapping("/patch/{id}")
    public ResponseEntity<BookDto> patchBook(@PathVariable Long id, @RequestBody Map<String, Object> updates) {

        Book entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        // Replace existing fields
        updates.forEach((key, value) -> {
            switch (key) {
                case "title" -> entity.setTitle((String) value);
                case "isbn" -> entity.setIsbn((String) value);
                case "description" -> entity.setDescription((String) value);
                case "page" -> entity.setPage((Integer) value);
                case "price" -> entity.setPrice(new BigDecimal(value.toString()));
            }
        });

        var book = repo.save(entity);

        return ResponseEntity.ok(new BookDto(book.getId(), book.getTitle(), book.getIsbn(), book.getDescription(), book.getPage(), book.getPrice()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> partialBook(@PathVariable Long id, @RequestBody BookPatchDTO dto) {

        Book entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        // Replace existing fields
        dto.title().ifPresent(entity::setTitle);
        dto.isbn().ifPresent(entity::setIsbn);
        dto.description().ifPresent(entity::setDescription);
        dto.page().ifPresent(entity::setPage);
        dto.price().ifPresent(entity::setPrice);

        var book = repo.save(entity);

        return ResponseEntity.ok(new BookDto(book.getId(), book.getTitle(), book.getIsbn(), book.getDescription(), book.getPage(), book.getPrice()));
    }


}
