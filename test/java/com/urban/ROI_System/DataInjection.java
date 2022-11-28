package com.urban.ROI_System;

import com.urban.ROI_System.model.RecommendModel;
import com.urban.ROI_System.repository.RecommendRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DataInjection {

    @Autowired
    private RecommendRepository recommendRepository;

    private int MaxResult = 3;

    @org.junit.Test
    public void test1() {
        //gender ALL = A
        //age ALL = 99
        Integer smallCategoryNo=1, bigCategoryNo=1, age=99;
        Character gender = 'A';

        RecommendModel parameter = RecommendModel.builder().smallCategoryNo(smallCategoryNo).bigCategoryNo(bigCategoryNo).age(age).gender(gender).build();

        List<RecommendModel> resultList = new ArrayList<>();
        List<RecommendModel> FindAll = recommendRepository.findAll();

        for (RecommendModel a:FindAll) {
            if(a.equalsScore(parameter) >= 50) {
                resultList.add(a);
            } //if
        } // for

        resultList.sort((o1, o2) -> (o1.getScore() + o1.getRoi()/10) < (o2.getScore() + o2.getRoi()/10)? 1:0);

        for (RecommendModel a:resultList) {
            System.out.println(a.toString());
        }

    } // test();
}