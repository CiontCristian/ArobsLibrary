package arobs.library.core.service;

import arobs.library.core.model.Book;
import arobs.library.core.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Controller
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooksWithoutCopies() {
        return bookRepository.findAllWithoutCopies();
    }

    @Override
    public List<Book> findAllBooksWithCopies() {
        return bookRepository.findAllWithCopies();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Optional<Book> modifyBook(Book book) {
        Book newBook = bookRepository.findById(book.getId()).orElse(null);
        if(newBook == null)
            return Optional.empty();

        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setDate(book.getDate());
        newBook.setDescription(book.getDescription());
        newBook.setTags(book.getTags());

        return Optional.of(newBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
