package com.urban.ROI_System;

import com.urban.ROI_System.model.CrawlSiteListModel;
import com.urban.ROI_System.exception.validity.UserRegularChecker;
import com.urban.ROI_System.repository.CrawlSiteListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
public class CrawlSiteListModelTests {

	@Autowired
	private CrawlSiteListRepository repo;
	
	//@Test
	void save() {
		try {
			new UserRegularChecker().idChecker("aaaaaaaa3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	void saveCrawlSites() {
		List<CrawlSiteListModel> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			CrawlSiteListModel crawlSite = new CrawlSiteListModel();
			System.out.println(i+": "+crawlSite.hashCode());
			crawlSite.setName("test" + i);
			list.add(crawlSite);
		}
		
		repo.saveAll(list);
	}
	
	//@Test --> fail 자식과 부모 동시에넣기 불가능
	
	//@Test --> 자식 부모 따로넣기
	
	//@Test
	void selectAll() {
		for(CrawlSiteListModel crawlSite : repo.findAll())
			System.out.println(crawlSite.getNo());
	}
	
	//@Test
	void findByNo() {
		System.out.println(repo.findByNo(1L).getName());
	}
	
	//@Test
	void update() {
		CrawlSiteListModel crawlSite = repo.findByNo(1L);
		crawlSite.setName("change");
		repo.save(crawlSite);
		
		System.out.println(repo.findByNo(1L).getName());
	}
	
	//@Test
	void delete() {
		CrawlSiteListModel crawlSite = repo.findByNo(1L);
		repo.delete(crawlSite);
		
		System.out.println(repo.findByNo(1L));
	}

//	//@Test
	void existByNo() {
		for (int i = 1; i <= 5; i++) {
			System.out.println(i + " result : " + repo.existsByNo((long)i));
		}
	}
}
