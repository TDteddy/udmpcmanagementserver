package com.urban.ROI_System;

import com.urban.ROI_System.repository.ProjectRepository;
import com.urban.ROI_System.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

//@SpringBootTest
public class directory {

    @Autowired
    ProjectRepository plr = BeanUtil.getBean(ProjectRepository.class);

//    //@Test
    public void dddd(){
//        System.out.println(plr.getProjectReport(27L));
    }

    public static void main(String[] args) throws IOException {
        File f = new File("src/main/java/ROI_System/config/key/urbanroi-663efb743341.json");
        f.mkdir();
        System.out.println(f.getAbsolutePath());
    }
}
