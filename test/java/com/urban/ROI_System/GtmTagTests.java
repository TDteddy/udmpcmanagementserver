package com.urban.ROI_System;

import com.urban.ROI_System.model.GtmTagModel;
import com.urban.ROI_System.model.ProductModel;
import com.urban.ROI_System.model.ProjectLogDTO;
import com.urban.ROI_System.repository.GtmTagRepository;
import com.urban.ROI_System.repository.ProductRepository;
import com.urban.ROI_System.repository.ProjectLogRepository;
import com.urban.ROI_System.vo.ProjectLogVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
public class GtmTagTests {

    @Autowired ProductRepository prodRepo;
    @Autowired ProjectLogRepository projectLogRepository;
    @Autowired GtmTagRepository gtmRepo;

    //@Test
    public void test1() {
        ProductModel product = prodRepo.findByNo(28L);
        List<GtmTagModel> gtms = gtmRepo.findByProductModel(product);

        System.out.println(gtms.size());
    }

    @org.junit.Test
    public List<ProjectLogVO> getProjectLogList() {
        Long no = 25L;
        List<ProjectLogVO> list = new ArrayList<>();
        for (ProjectLogDTO dto : projectLogRepository.getProject(no)) {
            ProjectLogVO vo = new ProjectLogVO();
            vo.setCreateDate(dto.getCreateDate());
            vo.setROI(dto.getROI());
            vo.setDirect(dto.getDirect());
            vo.setIndirect(dto.getIndirect());
            list.add(vo);
            System.out.println(vo);
        }
        return  list;
    }

}
