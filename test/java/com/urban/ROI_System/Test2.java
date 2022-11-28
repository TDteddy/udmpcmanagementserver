package com.urban.ROI_System;

import com.urban.ROI_System.model.BigCategoryModel;
import com.urban.ROI_System.model.RecommendModel;
import com.urban.ROI_System.model.SmallCategoryModel;
import com.urban.ROI_System.model.UserModel;
import com.urban.ROI_System.repository.BigCategoryRepository;
import com.urban.ROI_System.repository.RecommendRepository;
import com.urban.ROI_System.repository.SmallCategoryRepository;
import com.urban.ROI_System.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Array;
import java.util.*;

@SpringBootTest
public class Test2 {

    @Autowired
    private RecommendRepository recommendRepository;

//    @Test
//    public void deleteUser(){
//        userRepository.delete(UserModel.builder().no(1L).build());
//    }

    @Test
    public void test1() {
        //gender ALL = A
        //age ALL = 99
        Integer smallCategoryNo=189, bigCategoryNo=10, age=20;
        Character gender = 'M';

        RecommendModel parameter = RecommendModel.builder().smallCategoryNo(smallCategoryNo).bigCategoryNo(bigCategoryNo).age(age).gender(gender).build();

        List<RecommendModel> resultList = new ArrayList<>();
        List<RecommendModel> FindAll = recommendRepository.findAll();

        for (RecommendModel a:FindAll) {
            if(a.equalsScore(parameter) >= 50) {
                resultList.add(a);
            } //if
        } // for

        if(resultList.size() >= 2){
            System.err.println("------------------------");
            resultList.sort((o1, o2) -> (o1.getScore() + (o1.getRoi()/10.0)) < (o2.getScore() + (o2.getRoi()/10.0))? 1:-1);
        }

        for (RecommendModel a:resultList) {
            System.out.println(a.toString());
        }

    } // test();

    @Autowired
    BigCategoryRepository bigCategoryRepository;

    @Autowired
    SmallCategoryRepository smallCategoryRepository;

    @Test
    public void test2() {
        int[] bigCategoryArr = {1, 2, 3, 6, 7, 10, 12};
        //int[] bigCategoryArr = {1, 2, 3, 4, 12};
        int[][] smallCategoryArr = {
                {1, 2},
                {5, 8, 11, 22},
                {23, 24, 25, 26, 27, 33, 35},
                {79, 80, 97, 99, 101},
                {122},
                {189, 190, 191},
                {204, 207}
                /* male
                {3, 4},
                {6, 9, 11, 12, 22},
                {29, 30},
                {36, 39, 40, 49, 51},
                {204, 206, 207, 217, 219}
                 */
        };

        List<BigCategoryModel> bigCategoryModels = new ArrayList<>();
        for (int i = 0; i < bigCategoryArr.length; i++) {
            BigCategoryModel b = bigCategoryRepository.findByNo(bigCategoryArr[i]);
            List<SmallCategoryModel> smallCategoryModels = new ArrayList<>();

            for (int j = 0; j < smallCategoryArr[i].length; j++) smallCategoryModels.add(smallCategoryRepository.findByNo(smallCategoryArr[i][j]));
            b.setSmallCategoryList(smallCategoryModels);
            bigCategoryModels.add(b);
        }

        List<RecommendModel> recommendModels = new ArrayList<>();
        String[][] site = {
                {"facebook", "youtube", "insta"},
                {"facebook", "youtube", "insta"},
                {"naver", "facebook", "youtube", "insta"},
                {"naver", "kakao", "youtube"}
                /* male
                {"insta", "google", "youtube"},
                {"google", "facebook", "insta", "youtube"},
                {"google", "facebook", "naver", "youtube"},
                {"facebook", "daum", "kakao", "naver"}
                 */
        };

        for (BigCategoryModel b : bigCategoryModels)
            for (SmallCategoryModel c : b.getSmallCategoryList())
                for (int i = 10; i <= 40; i += 10)
                    for (int j = 0; j < site[i / 10 - 1].length; j++) {
                        RecommendModel r = new RecommendModel();
                        r.setAge(i);
                        r.setBigCategoryNo(b.getNo());
                        r.setSmallCategoryNo(c.getNo());
                        r.setGender('F');
                        //r.setGender('M');
                        r.setSite(site[i / 10 - 1][j]);
                        r.setType(Math.round(Math.random() * 1) == 0 ? "홍보" : "광고");

                        int len = site[i / 10 - 1].length;
                        int max = 800 / len * (j + 1);
                        int min = Math.max(max - (800 / len), 100);
                        double rand = Math.round(min + (Math.random() * (max - min)));
                        //System.out.println(r.getSite() + " : " + rand + ", min-" + min + ", max-" + max);
                        r.setRoi(rand);
                        recommendModels.add(r);
                    }

        recommendRepository.saveAll(recommendModels);
    }

}
