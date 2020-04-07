package org.example.adapters.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.example.adapters.repositories.models.AuthorModel;
import org.example.adapters.repositories.models.translators.AuthorModelTranslator;
import org.example.application.dtos.AuthorDTO;
import org.example.application.ports.out.AuthorsRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class JPAAuthorsRepository implements AuthorsRepositoryPort {

    private MicronautAuthorsRepository authorsRepository;

    public JPAAuthorsRepository(MicronautAuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    @Override
    public AuthorDTO create(AuthorDTO dto) {
        AuthorModel model = AuthorModelTranslator.fromDTO(dto);

        model = authorsRepository.save(model);

        return AuthorModelTranslator.toDTO(model);
    }

    @Override
    public Optional<AuthorDTO> findByName(String name) {
        Optional<AuthorModel> optModel = authorsRepository.findByName(name);

        if (!optModel.isPresent()) {
            return Optional.<AuthorDTO>empty();
        }

        return Optional.of(AuthorModelTranslator.toDTO(optModel.get()));
    }

    @Override
    public Set<AuthorDTO> findByNameIn(Set<String> authorNames) {
        return AuthorModelTranslator.toDTO(authorsRepository.findByNameIn(authorNames));
    }
}

@Repository
interface MicronautAuthorsRepository extends org.springframework.data.repository.Repository<AuthorModel, Long> {
    public List<AuthorModel> findAll();

    public Optional<AuthorModel> findByName(String name);

    public AuthorModel save(AuthorModel model);

    public Set<AuthorModel> findByNameIn(Set<String> authors);
}
