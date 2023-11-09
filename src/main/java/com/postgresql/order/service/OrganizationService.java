package com.postgresql.order.service;

import com.postgresql.order.entity.Organization;
import com.postgresql.order.repo.OrganiztionsRepository;
import com.postgresql.order.exception.MainServiceBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    private OrganiztionsRepository organiztionsRepository;

    public Organization getOrganization(Long organizationId) throws MainServiceBusinessException {

        try{
            if(!organiztionsRepository.existsById(organizationId)){
                throw new MainServiceBusinessException("Organization with id " + organizationId + " does not exist");
            }
            return organiztionsRepository.findById(organizationId).orElse(null);
        } catch (Exception e){
            throw new MainServiceBusinessException("Error getting organization");
        }


    }

}
