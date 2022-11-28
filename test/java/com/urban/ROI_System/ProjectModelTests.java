package com.urban.ROI_System;

import com.urban.ROI_System.model.ProjectLogDTO;
import com.urban.ROI_System.repository.ProductRepository;
import com.urban.ROI_System.repository.ProjectLogRepository;
import com.urban.ROI_System.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//@SpringBootTest
public class ProjectModelTests {
    @Autowired private ProjectService service;
    @Autowired private ProductRepository prodRepo;

    //@Test
    public void updateLastCollectionDate() {
        service.updateLastCollectionDate(38L);
    }

    @Autowired
    ProjectLogRepository projectLogRepo;

//    //@Test
    public void test() {
        List<ProjectLogDTO> logs = projectLogRepo.getProject(25L);
        logs.forEach(i -> {
//            System.out.println(i.getCreateDate().toString() + " " + i.getDirect() + " "+ i.getIndirect()+ " "+ i.getTotal());
        });
    }

}
