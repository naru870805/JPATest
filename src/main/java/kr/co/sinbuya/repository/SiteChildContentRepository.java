package kr.co.sinbuya.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.co.sinbuya.entity.SiteChildContent;

public interface SiteChildContentRepository extends CrudRepository<SiteChildContent, Long> {

	List<SiteChildContent> findBySiteId(Long id);

}
