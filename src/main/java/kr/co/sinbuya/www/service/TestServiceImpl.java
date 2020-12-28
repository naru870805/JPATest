package kr.co.sinbuya.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sinbuya.entity.DevelopTest;
import kr.co.sinbuya.repository.DevelopTestRepository;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Autowired private DevelopTestRepository developTestRepository;

	@Transactional
	@Override
	public DevelopTest findById(long id) {
		return developTestRepository.findOne(id);
	}
	
}
