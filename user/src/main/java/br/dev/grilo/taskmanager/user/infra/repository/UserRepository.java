package br.dev.grilo.taskmanager.user.infra.repository;

import br.dev.grilo.taskmanager.user.infra.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
