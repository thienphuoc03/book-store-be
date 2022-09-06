package com.htphuoc.bookstore.service;

import com.htphuoc.bookstore.dto.PublishingCompanyDto;
import com.htphuoc.bookstore.model.PublishingCompany;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface PublishingCompanyService {
    ResponseEntity<Object> getAllPublishingCompany(Integer page, Integer size);

    ResponseEntity<Object> searchPublishingCompany(String keyword);

    ResponseEntity<PublishingCompanyDto> addPublishingCompany(PublishingCompany publishingCompany) throws Exception;

    ResponseEntity<PublishingCompanyDto> updatePublishingCompany(Long id, PublishingCompany publishingCompany);

    ResponseEntity<Object> deletePublishingCompany(Long id);
}
