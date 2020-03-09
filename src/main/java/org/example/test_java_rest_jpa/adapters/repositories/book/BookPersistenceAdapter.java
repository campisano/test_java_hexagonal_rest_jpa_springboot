package org.example.test_java_rest_jpa.adapters.repositories.book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.out.BookPersistencePort;
import org.springframework.stereotype.Repository;

@Repository
public class BookPersistenceAdapter implements BookPersistencePort {

    private BookRepositorySpring bookRepositorySpring;

    public BookPersistenceAdapter(BookRepositorySpring bookRepositoryJPA) {
        this.bookRepositorySpring = bookRepositoryJPA;
    }

    @Override
    public List<BookDTO> findByReader(String reader) {
        return bookRepositorySpring.findByReader(reader).stream().map(BookModelTranslator::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO create(BookDTO dto) {
        BookModel model = BookModelTranslator.fromDTO(dto);

        model = bookRepositorySpring.save(model);

        return BookModelTranslator.toDTO(model);
    }

    @Override
    @Transactional
    public BookDTO updateByIsbn(BookDTO dto) {
        if (dto.getIsbn() == null) {
            throw new RuntimeException("Isbn cannot be null");
        }

        Optional<BookModel> optModel = bookRepositorySpring.findByIsbn(dto.getIsbn());
        if (!optModel.isPresent()) {
            throw new RuntimeException("No book exist with specified isbn " + dto.getIsbn());
        }

        BookModel model = optModel.get();
        model.setIsbn(dto.getIsbn());
        model.setTitle(dto.getTitle());
        model.setAuthor(dto.getAuthor());
        model.setDescription(dto.getDescription());

        model = bookRepositorySpring.save(model);

        return BookModelTranslator.toDTO(model);
    }
}
