package br.dev.grilo.taskmanager.user.infra.repository;

import br.dev.grilo.taskmanager.user.infra.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
