package com.urban.ROI_System;

import com.urban.ROI_System.model.ProductLogModel;
import com.urban.ROI_System.model.ProductModel;
import com.urban.ROI_System.model.ProjectModel;
import com.urban.ROI_System.model.UserModel;
import com.urban.ROI_System.repository.ProductLogRepository;
import com.urban.ROI_System.repository.ProductRepository;
import com.urban.ROI_System.repository.ProjectRepository;
import com.urban.ROI_System.repository.UserRepository;
import com.urban.ROI_System.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
public class ProductModelTests {

	@Autowired private ProductService productService;
	@Autowired private UserRepository userRepo;
	@Autowired private ProductRepository productRepo;
	@Autowired private ProductLogRepository logRepo;
	@Autowired private ProjectRepository projectRepo;
	

	@Transactional
//	//@Test
	void update() {
		UserModel user = userRepo.findByNo(10L);
		ProductModel prod = productRepo.findByNo(609L);
		System.out.println(prod.getProductName());

		prod.setProductName("빵빵님 푸시하세요");
		prod.setTotalProperty(1231321L);
		productRepo.save(prod);

		System.out.println(productRepo.findByNo(609L).getProductName() + " " + productRepo.findByNo(609L).getTotalProperty());
	}

//	@Test
	void save() {
		UserModel user = userRepo.findByNo(10L);
		System.out.println(productService.save("saveTest", "1231231", user));
	}

	//@Test
	void find() {
		UserModel user = userRepo.findByNo(1L);
		List<ProductModel> list = productRepo.findByUserModel(user);
		list.forEach(e -> System.out.println(e.getProductName()));
	}
	
	//@Test
	void saveAll() {
		UserModel user = userRepo.findByNo(2L);
		List<ProductModel> list = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			ProductModel prod = ProductModel.builder().userModel(user).productName("test" + i).build();
			list.add(prod);
		}
		productRepo.saveAll(list);
	}
	
	//@Test
	void saveLogAndProject() {
		ProductModel prod = ProductModel.builder().userModel(userRepo.findByNo(1L)).productName("product" + 100).build();
		List<ProductLogModel> logs = new ArrayList<>();
		List<ProjectModel> projectModels = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			ProductLogModel log = ProductLogModel.builder().productModel(prod).totalRoi(10.0 + i).build();
//			ProjectModel project = ProjectModel.builder().productModel(prod).projectName("project" + i).build();
			ProjectModel projectModel =null;//= ProjectModel.builder().productModel(prod).projectName("project" + i).build();

			logs.add(log);
			projectModels.add(projectModel);
		}
		
		prod.setProductLogModel(logs);
		//prod.setProjectModel(projectModels);
		
		productRepo.save(prod);
		logRepo.saveAll(logs);
		projectRepo.saveAll(projectModels);
	}

	//@Test
	void findAllByCreateDateBetween() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startDate = LocalDateTime.parse("2021-05-13 00:00", formatter);
		LocalDateTime endDate = LocalDateTime.parse("2021-06-10 00:00", formatter);

		UserModel userModel = userRepo.findByNo(10L);

		for(ProductModel prod : productRepo.findAllByCreateDateBetweenAndUserModelOrderByTotalSalesDesc(startDate, endDate, userModel))
			System.out.println(prod.getCreateDate());
	}
}
