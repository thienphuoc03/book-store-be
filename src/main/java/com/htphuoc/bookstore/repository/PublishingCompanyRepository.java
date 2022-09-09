package com.htphuoc.bookstore.repository;

import com.htphuoc.bookstore.model.PublishingCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

@Repository
public interface PublishingCompanyRepository extends JpaRepository<PublishingCompany, Long> {
    Boolean existsByName(@NotBlank String name);

    PublishingCompany findByName(String name);
}
