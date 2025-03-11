package com.monit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monit.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {


}