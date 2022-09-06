package com.htphuoc.bookstore.controller;

import com.htphuoc.bookstore.dto.PublishingCompanyDto;
import com.htphuoc.bookstore.model.PublishingCompany;
import com.htphuoc.bookstore.service.PublishingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/publishing-companies")
public class PublishingCompanyController {
    @Autowired
    private PublishingCompanyService publishingCompanyService;

    @GetMapping
    public ResponseEntity<Object> getAllPublishingCompany(@RequestParam(name = "page", required = false, defaultValue = "") Integer page,
                                                          @RequestParam(name = "size", required = false, defaultValue = "") Integer size) {
        return publishingCompanyService.getAllPublishingCompany(page, size);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchPublishingCompany(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {
        return publishingCompanyService.searchPublishingCompany(keyword);
    }

    @PostMapping
    public ResponseEntity<PublishingCompanyDto> addPublishingCompany(@Valid @RequestBody PublishingCompany publishingCompany) throws Exception{
        return publishingCompanyService.addPublishingCompany(publishingCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublishingCompanyDto> updatePublishingCompany(@PathVariable(name = "id") Long id,
                                                                        @Valid @RequestBody PublishingCompany publishingCompany) {
        return publishingCompanyService.updatePublishingCompany(id, publishingCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePublishingCompany(@PathVariable(name = "id") Long id) {
        return publishingCompanyService.deletePublishingCompany(id);
    }
}
