package com.htphuoc.bookstore.service.impl;

import com.htphuoc.bookstore.dto.PublishingCompanyDto;
import com.htphuoc.bookstore.exception.AlreadyExistsException;
import com.htphuoc.bookstore.exception.NotFoundException;
import com.htphuoc.bookstore.model.Author;
import com.htphuoc.bookstore.model.PublishingCompany;
import com.htphuoc.bookstore.repository.PublishingCompanyRepository;
import com.htphuoc.bookstore.service.PublishingCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishingCompanyServiceImpl implements PublishingCompanyService {
    @Autowired
    private PublishingCompanyRepository publishingCompanyRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> getAllPublishingCompany(Integer page, Integer size) {
        List<PublishingCompanyDto> publishingCompanyDtos = new ArrayList<>();
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page - 1, size);
            List<PublishingCompany> publishingCompanies = publishingCompanyRepository.findAll(pageable).getContent();
            for (PublishingCompany publishingCompany : publishingCompanies) {
                PublishingCompanyDto publishingCompanyDto = modelMapper.map(publishingCompany, PublishingCompanyDto.class);
                publishingCompanyDtos.add(publishingCompanyDto);
            }
        } else {
            List<PublishingCompany> publishingCompanies = publishingCompanyRepository.findAll();
            for (PublishingCompany publishingCompany : publishingCompanies) {
                PublishingCompanyDto publishingCompanyDto = modelMapper.map(publishingCompany, PublishingCompanyDto.class);
                publishingCompanyDtos.add(publishingCompanyDto);
            }
        }
        return new ResponseEntity<>(publishingCompanyDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> searchPublishingCompany(String keyword) {
        List<PublishingCompanyDto> publishingCompanyDtos = new ArrayList<>();
        List<PublishingCompany> publishingCompanies = publishingCompanyRepository.findAll();
        for (PublishingCompany publishingCompany : publishingCompanies) {
            if (publishingCompany.getName().equals(keyword)) {
                PublishingCompanyDto publishingCompanyDto = modelMapper.map(publishingCompany, PublishingCompanyDto.class);
                publishingCompanyDtos.add(publishingCompanyDto);
            }
        }

        return new ResponseEntity<>(publishingCompanies, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PublishingCompanyDto> addPublishingCompany(PublishingCompany publishingCompany) throws Exception{
        if (publishingCompanyRepository.existsByName(publishingCompany.getName())) {
            throw new AlreadyExistsException("PublishingCompany already exists !!!");
        }
        PublishingCompany newPublishingCompany = publishingCompanyRepository.save(publishingCompany);
        PublishingCompanyDto publishingCompanyDto = modelMapper.map(newPublishingCompany, PublishingCompanyDto.class);

        return new ResponseEntity<>(publishingCompanyDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PublishingCompanyDto> updatePublishingCompany(Long id, PublishingCompany publishingCompany) {
        publishingCompany.setId(id);
        PublishingCompany oldPublishingCompany = publishingCompanyRepository.findById(id).orElse(null);
        if (oldPublishingCompany != null) {
            publishingCompany.setCreatedAt(oldPublishingCompany.getCreatedAt());
            publishingCompany.setCreatedBy(oldPublishingCompany.getCreatedBy());
            oldPublishingCompany = modelMapper.map(publishingCompany, oldPublishingCompany.getClass());
            PublishingCompany updatePublishingCompany = publishingCompanyRepository.save(oldPublishingCompany);
            PublishingCompanyDto publishingCompanyDto = modelMapper.map(updatePublishingCompany, PublishingCompanyDto.class);

            return new ResponseEntity<>(publishingCompanyDto, HttpStatus.CREATED);
        }

        throw new NotFoundException("PublishingCompany not found with id = " + id);
    }

    @Override
    public ResponseEntity<Object> deletePublishingCompany(Long id) {
        PublishingCompany publishingCompany = publishingCompanyRepository.findById(id).orElse(null);
        if (publishingCompany != null) {
            String publishingCompanyName = publishingCompany.getName();
            publishingCompanyRepository.delete(publishingCompany);
            return new ResponseEntity<>("You successfully deleted PublishingCompany " + publishingCompanyName, HttpStatus.OK);
        }

        throw new NotFoundException("PublishingCompany not found with id = " + id);
    }

    @Override
    public void createPublishingCompany(String name) {
        PublishingCompany newPublishingCompany = new PublishingCompany();
        newPublishingCompany.setName(name);
        publishingCompanyRepository.save(newPublishingCompany);
    }
}
