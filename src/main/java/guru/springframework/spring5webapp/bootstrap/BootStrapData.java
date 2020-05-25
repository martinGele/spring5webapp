package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author erik = new Author("Eric", "Evan");
        Book book = new Book("Domain Driven Design", "13422");

        erik.getBook().add(book);
        book.getAuthors().add(erik);

        authorRepository.save(erik);
        bookRepository.save(book);

        Author rod = new Author("Rod", "Jhonson");
        Book noEjb = new Book("J2EE", "Developer");

        Publisher publisher = new Publisher("Martin Gelevski", "Kisela Voda 1/1-15");

        rod.getBook().add(noEjb);
        noEjb.getAuthors().add(rod);
        publisher.getBooks().add(book);
        publisher.getBooks().add(noEjb);

        authorRepository.save(rod);
        bookRepository.save(noEjb);
        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of Books: "+ publisher.getBooks().size());


    }
}
