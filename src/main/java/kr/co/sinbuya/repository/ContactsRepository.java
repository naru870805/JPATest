package kr.co.sinbuya.repository;

import org.springframework.data.repository.CrudRepository;

import kr.co.sinbuya.entity.Contacts;

public interface ContactsRepository extends CrudRepository<Contacts, Long> {

}
