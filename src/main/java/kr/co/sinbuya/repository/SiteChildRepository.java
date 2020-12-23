package kr.co.sinbuya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.sinbuya.entity.SiteChild;

public interface SiteChildRepository extends JpaRepository<SiteChild, Long> {

	@Query("select s from SiteChild s where s.host like :host or s.url like :host or s.url like :url")
	SiteChild getByHostOrUrl(@Param("host") String host, @Param("url") String url);

}
