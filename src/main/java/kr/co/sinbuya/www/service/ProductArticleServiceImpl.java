package kr.co.sinbuya.www.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sinbuya.entity.OtherLog;
import kr.co.sinbuya.entity.ProductArticle;
import kr.co.sinbuya.entity.ProductOption;
import kr.co.sinbuya.repository.ProductArticleRepository;
import kr.co.sinbuya.repository.ProductOptionRepository;

@Service("ProductArticleService")
public class ProductArticleServiceImpl implements ProductArticleService {
	
	@Autowired private ProductArticleRepository productArticleRepository;
	@Autowired private ProductOptionRepository productOptionRepository;

	@Override
	public List<ProductArticle> findArticlesByCategory(String term, long key) {
		
		PageRequest request = new PageRequest(0, 5);
		return productArticleRepository.findByLikeTitleAndCategoryId("%" + term + "%", key, request);

	}

	@Override
	public List<ProductArticle> findArticlesByCategory(long categoryId) {
		
//		List<ProductArticle> result = null;
//		Session session = sessionFactory.getCurrentSession();
//		session.getTransaction().begin();
//		session.setDefaultReadOnly(false);
//
//		try {
//			Criteria cr = session.createCriteria(ProductArticle.class)
//					.add(Restrictions.eq("categoryId", categoryId))
//					.add(Restrictions.eq("enabled",true))
//					.add(Restrictions.ge("status", 1))
//					.add(Restrictions.eq("priceHaving",true))					
//					.addOrder(Order.desc("status"))
//					.addOrder(Order.desc("commentSize"))
//					.setMaxResults(3);
//
//			result = cr.list();
//
//			session.getTransaction().commit();
//
//		} catch (Exception ex) {
//			result = null;
//			session.getTransaction().rollback();
//			ex.printStackTrace();
//		}
//
//		return result;
		return null;

	}

	@Override
	public List<ProductArticle> findArticlesByReview(Long categoryId, List<Long> articleIds) {

//		List<ProductArticle> result = null;
//		Session session = sessionFactory.getCurrentSession();
//		session.getTransaction().begin();
//		session.setDefaultReadOnly(true);
//
//		try {
//
//			StringBuilder sb = new StringBuilder();
//			sb.append(" SELECT article_id FROM ( ");
//			sb.append("	  SELECT A.article_id, count(*) X FROM review_articles A ");
//			sb.append("     WHERE A.review_id in ( ");
//			sb.append("       SELECT B.review_id FROM review_articles B where B.article_id in (:ids) ");
//			sb.append("     )  AND A.article_id not in (:ids) ");
//			sb.append("     AND A.article_id in (select v.id from product_article v WHERE v.category_id = " + categoryId
//					+ ")");
//			sb.append("     GROUP BY A.article_id ");
//			sb.append(" ) N ");
//			sb.append(" ORDER BY N.X DESC limit 3 ");
//
//			Query q = session.createSQLQuery(sb.toString()).addScalar("article_id", new LongType())
//					.setParameterList("ids", articleIds);
//			
//			final List<Long> ids = q.list();
//			
//			if (ids.size() > 0) {
//
//				Criteria cr = session.createCriteria(ProductArticle.class)
//						.add(Restrictions.in("id", ids))
//						.add(Restrictions.eq("priceHaving",true))
//						.add(Restrictions.eq("enabled",true))	
//						.add(Restrictions.ge("status", 1))
//						.addOrder(Order.asc("id"));
//				
//				result = cr.list();
//				
//				Collections.sort(result, new Comparator<ProductArticle>() {
//					@Override
//					public int compare(ProductArticle o1, ProductArticle o2) {
//						return ids.indexOf(o1.getId()) > ids.indexOf(o2.getId()) ? -1 : 1;
//					}
//				});
//
//			} else {
//				Criteria cr = session.createCriteria(ProductArticle.class)
//						.add(Restrictions.eq("categoryId", categoryId))
//						.add(Restrictions.eq("priceHaving",true))
//						.add(Restrictions.eq("enabled",true))	
//						.add(Restrictions.ge("status", 1))
//						.addOrder(Order.asc("id"));
//				
//				result = cr.list();
//				Collections.shuffle(result);
//				
//				result = result.subList(0, 3);
//			}
//
//			session.getTransaction().commit();
//
//		} catch (Exception e) {
//			result = null;
//			session.getTransaction().rollback();
//			logger.error(e.getMessage(), e);
//		}
//
//		return result;
		
		return null;
	}

	@Override
	public Long findPriceByArticleId(Long articleId, String mode) {
		
		try {
			ProductArticle article = productArticleRepository.findOne(articleId);
			
			String title = "%";
			if (article.getCategoryId() == 1011) title = "%2회 실장%";
			if (article.getCategoryId() == 1010) title = "%20P%";
			if (article.getCategoryId() == 1012) title = "%촬영+본식%";
			
			PageRequest request = new PageRequest(0, 1, Sort.Direction.ASC, "price");
			Page<ProductOption> option = productOptionRepository.findByArticleAndLikeTitle(article, title, request);
			
			return option.getContent().get(0).getPrice();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public Page<ProductArticle> findArticlesByCategoryId(long categoryId, int page , int limit) {

		
		return null;
		
	}
	
	@Override
	public List<ProductOption> findArticlesByOption(long articleId) {
		return productOptionRepository.findByArticle(new ProductArticle(articleId));
	}
	
	@Override
	public OtherLog createLog(long typeId, String type, HttpServletRequest request) {
		
		return null;
		
	}
	
	@Transactional
	@Override
	public ProductArticle findArticleById(long id) {
		return productArticleRepository.findOne(id);
	}			

	
	@Override
	public List<ProductArticle> findrecommendBycategory(Long id, Long categoryId) {
		if (id == null) id = -1L;

		PageRequest request = new PageRequest(0, 3, Sort.Direction.DESC, "status");
		return productArticleRepository.findByOptionMode((categoryId.equals(1010L)) ? "R" : "RB", id, categoryId, request);
	}
}
