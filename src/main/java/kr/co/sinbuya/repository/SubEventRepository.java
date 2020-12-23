package kr.co.sinbuya.repository;

import org.springframework.data.repository.CrudRepository;

import kr.co.sinbuya.entity.SubEvent;

public interface SubEventRepository extends CrudRepository<SubEvent, Long> {

	SubEvent findTop1ByPhoneNumber(String phone);

}
