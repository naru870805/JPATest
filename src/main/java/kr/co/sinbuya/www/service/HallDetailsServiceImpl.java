package kr.co.sinbuya.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sinbuya.entity.PAEHallDetails;
import kr.co.sinbuya.entity.ProductArticle;
import kr.co.sinbuya.entity.SiteChild;
import kr.co.sinbuya.repository.PAEHallDetailsRepository;
import kr.co.sinbuya.repository.SiteChildRepository;

@Service("HallDetailsService")
public class HallDetailsServiceImpl implements HallDetailsService {
	
	@Autowired private PAEHallDetailsRepository detailsRepository;
	
	@Autowired private SiteChildRepository siteChildRepository;

	@Transactional
	@Override
	public List<PAEHallDetails> findHallDetailsByArticle(long id) {
		
		List<PAEHallDetails> result = detailsRepository.findByArticle(new ProductArticle(id));
		return result;
		
	}

	@Transactional
	@Override
	public SiteChild updateStatus(Long cid) {
		SiteChild child = siteChildRepository.findOne(cid);
		child.setStatus(2);
		return child;
	}


}
