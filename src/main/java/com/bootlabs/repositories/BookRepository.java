
package com.bootlabs.repositories;

import com.bootlabs.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h2>BookRepository</h2>
 * Description: Spring Data repository for the {@link Book} entity.
 * 
 * @author @bootteam
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
