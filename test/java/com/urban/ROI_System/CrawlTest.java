package com.urban.ROI_System;

import com.urban.ROI_System.model.ProjectModel;
import com.urban.ROI_System.repository.ProjectLogRepository;
import com.urban.ROI_System.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@SpringBootTest
public class CrawlTest {

    @Value("${crawlServer.url}")
    private String serverUrl;
    private ExecutorService pool = Executors.newCachedThreadPool(); //스레드 풀

    @Autowired
    private ProjectRepository pra;
    @Autowired
    private ProjectLogRepository pr;

//    @Test
    public void main() {

        System.out.println(pr.findFirstByProjectModelOrderByCreateDateDesc(new ProjectModel(54L)));
//        System.out.println("serverUrl :" + serverUrl);
//        List<ProjectModel> targetList = pr.findAll();   //url 리스트 출력
//        for (ProjectModel m : targetList) {
//            String encodeStr = null;
//            try {
//                System.out.println(m.getNo());
//                encodeStr = URLEncoder.encode(m.getLink(), "UTF-8");
//                Runnable a = new ExecuteCrawling(serverUrl+encodeStr+"/"+m.getNo());
//                pool.execute(a);
//                System.out.println("execute");
////                System.out.println(serverUrl+encodeStr);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
