package kr.co.sinbuya.www.service;

import java.util.List;

import kr.co.sinbuya.entity.PAEHallDetails;
import kr.co.sinbuya.entity.SiteChild;

public interface HallDetailsService {

	List<PAEHallDetails> findHallDetailsByArticle(long id);

	SiteChild updateStatus(Long cid);
}
