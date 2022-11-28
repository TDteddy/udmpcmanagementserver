/*
package com.urban.ROI_System;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.api.services.analytics.model.Account;
import com.google.api.services.analytics.model.McfData;
import com.google.api.services.analyticsreporting.v4.model.ReportRow;
import com.urban.ROI_System.google_api.reporting.*;
import com.urban.ROI_System.model.*;
import com.urban.ROI_System.repository.*;
import com.urban.ROI_System.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
public class Test {
    public static void main(ArrayList<Account> accList,ArrayList<ProductModel> prodList) {
        Map<String, String> map = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();

        for(Account a: accList) map.put(a.getId(), a.getName());
        for (ProductModel temp : prodList) {
            String displayName = null;
            if((displayName = map.get(temp.getAccountId())) != null)
                result.add(displayName);
        } //for
    } //main



    @Autowired
    ProductLogRepository logRepo;
    @org.junit.jupiter.api.Test
    public void test1() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse("2021-10-20 00:00", formatter);
        LocalDateTime end = LocalDateTime.parse("2021-10-26 00:00", formatter);

//        logRepo.findAllWithUserModel(start, end, 1L).forEach(i -> System.out.println(i.toString()));
    }


    @Autowired
    ProductRepository prodRepo;
    @Autowired
    UserRepository userRepo;
    @org.junit.jupiter.api.Test
    @Transactional
    public void test3() throws JsonProcessingException {
        UserModel user = userRepo.findByNo(1L);
        List<ProductModel> rows = prodRepo.findByUserModel(user);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        System.out.println(objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(rows));
    }


    @Transactional
    @org.junit.jupiter.api.Test
    public void test4() {
        LocalDateTime start = LocalDateTime.of(2021, 11, 1, 23, 59);
        LocalDateTime end = LocalDateTime.now();

        List<ProductModel> rows = prodRepo.getProductByUserModelAndLogCreateDate(start, end, 1L);
        System.out.println("rows");
        for(ProductModel i : rows) {
            System.out.println(i.getProductName());

            for (ProductLogModel j : i.getProductLogModel()) {
                System.out.println(j.getCreateDate());
            }
        };
    }


    @Autowired
    ProjectRepository projectRepository;

    @org.junit.jupiter.api.Test
    public void test5() {
        ProjectModel project = new ProjectModel();
        project.setNo(27L);
        project.setProjectName(null);
        project.setProperty(6000000L);
        project.setLink("sadfdsfsff");

        projectRepository.save(project);
    }

    @Autowired
    DataUpdateService updateService;
    @org.junit.jupiter.api.Test
    public void test6() throws GeneralSecurityException, IOException {
        updateService.deleteData();
    }

    @org.junit.jupiter.api.Test
    public void test8() throws GeneralSecurityException, IOException {
        Reporting reporting = new Reporting();
        reporting.initialize("247412345");

        System.out.println(reporting.getSingleDaySourceMediumData("naver", "brandsearch_pc"));
    }

    @org.junit.jupiter.api.Test
    public void test9() throws GeneralSecurityException, IOException {
        Reporting reporting = new Reporting();
        reporting.initialize("247412345");

        DataUpdateService updateService = new DataUpdateService();
        //updateService.updateData(prodRepo.findByNo(51L));
    }


    @Autowired BigCategoryRepository bRepo;
    @Autowired SmallCategoryRepository sRepo;

    @org.junit.jupiter.api.Test
    public void test10() {
        String[] b = {
                "패션의류", "패션잡화",
                "화장품/미용", "디지털/가전", "가구/인테리어", "출산/육아", "식품",
                "스포츠/레저", "생활/건강", "여가/생활편의", "면세점", "도서"
        };

        BigCategoryModel bModel = new BigCategoryModel();

        for (int i = 0; i < b.length; i++) {
            bModel.setNo(i + 1);
            bModel.setName(b[i]);
            bRepo.save(bModel);
        }

        String[][] s = {
                {
                    "여성의류", "여성언더웨어/잠옷", "남성의류", "남성언더웨어/잠옷"
                },
                {
                    "여성신발", "남성신발", "신발용품", "여성가방", "남성가방", "여행용가방/소품", "지갑", "벨트",
                    "모자", "장갑", "양말", "선글라스/안경테", "헤어액세서리", "패션소품", "시계", "순금",
                    "주얼리", "제화브랜드"
                },
                {
                    "스킨케어", "베이스메이크업", "색조메이크업", "클렌징", "마스크/팩", "선케어", "남성화장품",
                    "향수", "바디케어", "헤어케어", "헤어스타일링", "네일케어", "뷰티소품"
                },
                {
                    "노트북", "노트북액세서리", "휴대폰액세서리", "PC", "모니터", "영상가전", "음향가전",
                    "생활가전", "이미용가전", "카메라/캠코더용품", "주방가전", "자동차기기", "계절가전",
                    "휴대폰", "학습기기", "게임기/타이틀", "PC액세서리", "태블릿PC", "태블릿PC액세서리",
                    "모니터주변기기", "주변기기", "멀티미디어장비", "저장장치", "PC부품", "네트워크장비",
                    "소프트웨어", "광학기기/용품"
                },
                {
                    "침실가구", "거실가구", "주방가구", "수납가구", "아동/주니어가구", "서재/사무용가구",
                    "아웃도어가구", "DIY자재/용품", "인테리어소품", "침구단품", "침구세트", "솜류",
                    "카페트/러그", "커튼/블라인드", "홈데코", "수예"
                },
                {
                    "분유", "기저귀", "물티슈", "이유식", "아기간식", "수유용품", "유모차", "카시트",
                    "외출용품", "목욕용품", "스킨/바디용품", "위생/건강용품", "구강청결용품", "유아세제",
                    "소독/살균용품", "안전용품", "유아가구", "이유식용품", "임부복", "임산부용품", "유아침구",
                    "출산/돌기념품", "신생아의류", "유아동의류", "유아동언더웨어/잠옷", "유아동잡화", "수영복/용품",
                    "유아동 주얼리", "유아발육용품", "완구/매트", "인형", "교구"
                },
                {
                    "축산", "수산", "농산물", "반찬", "김치", "음료", "과자", "아이스크림/빙수",
                    "가공식품", "냉동/간편조리식품", "건강식품", "다이어트식품", "전통주"
                },
                {
                    "등산", "캠핑", "골프", "자전거", "스키/보드", "낚시", "수영", "헬스", "요가/필라테스",
                    "인라인스케이트", "스케이트/보드/롤러", "오토바이/스쿠터", "축구", "야구" ,"농구", "배구",
                    "탁구", "배드민턴", "테니스", "스쿼시", "족구", "볼링", "스킨스쿠버", "검도", "댄스",
                    "권투", "보호용품", "무술용품", "수련용품", "스포츠액세서리", "마라톤용품", "당구용품", "기타스포츠용품"
                },
                {
                    "공구", "문구/사무용품", "화방용품", "자동차용품", "수집품", "관상어용품", "악기", "반려동물",
                    "음반", "DVD", "종교", "주방용품", "세탁용품", "건강측정용품", "건강관리용품", "당뇨관리용품",
                    "의료용품", "실버용품", "재활운동용품", "물리치료/저주파용품", "좌욕/좌훈용품", "냉온/찜질용품",
                    "구강위생용품", "눈건강용품", "발건강용품", "원예/식물정원/원예용품", "안마용품", "블루레이",
                    "욕실용품", "수납/정리용품", "청소용품", "생활용품"
                },
                {
                    "원데이클래스", "국내여행/체험", "해외여행", "렌터카", "생활편의", "예체능레슨", "자기계발/취미 레슨",
                    "홈케어서비스"
                },
                {
                    "화장품", "향수", "시계/기프트", "주얼리", "패션/잡화", "전자제품", "식품/건강"
                },
                {
                    "소설", "시/에세이", "경제/경영", "자기계발", "인문", "역사", "사회/정치", "자연/과학", "예술/대중문화",
                    "종교", "유아", "어린이", "가정/요리", "여행", "국어/외국어", "컴퓨터/IT", "청소년", "수험서/자격증",
                    "만화", "잡지", "외국도서", "건강/취미", "고등학교 참고서", "중학교 참고서", "초등학교 참고서", "중고도서"
                }
        };

        SmallCategoryModel sModel = new SmallCategoryModel();
        int idx = 1;

        for (int i = 0; i < s.length; i++) {
            sModel.setBigCategoryModel(bRepo.getOne(i + 1));
            for (int j = 0; j < s[i].length; j++) {
                sModel.setNo(idx++);
                sModel.setName(s[i][j]);
                sRepo.save(sModel);
            }
        }
    }


    @Autowired BigCategoryRepository big;
    @Autowired BigCategoryService bigService;

    @org.junit.jupiter.api.Test
    @Transactional
    public void test11() {
        List<BigCategoryModel> list = big.findAll();
        System.out.println(list.get(1).getSmallCategoryList());

        System.out.println(bigService.getOne(1).toString());
    }

    @org.junit.jupiter.api.Test
    public void test12() throws GeneralSecurityException, IOException {
        Reporting reporting = new Reporting();
        reporting.initialize("249550440");

        McfData data = reporting.getSingleDayMcfData();
        Double assistedValue =
                data.getRows()
                    .stream()
                    .map(row ->
                        (String) row.get(1).get("primitiveValue"))
                    .mapToDouble(Double::parseDouble).sum();

        System.out.println(assistedValue);

        assistedValue = reporting.getSingleDayMcfDataAssistedValue();
        System.out.println(assistedValue);
    }

    @org.junit.jupiter.api.Test
    public void test13() throws GeneralSecurityException, IOException {
        Reporting reporting = new Reporting();
        reporting.initialize("249550440");

        reporting.test().get(0).getData().getRows().forEach(System.out::println);
    }

    @org.junit.jupiter.api.Test
    public void test14() throws GeneralSecurityException, IOException {
        Reporting reporting = new Reporting();
        reporting.initialize("237873931");

        System.out.println(reporting.getSingleDayRevenueData());

        List<List<McfData.Rows>> list = reporting.getIndirectList();

        try {
            list.forEach(System.out::println);
        } catch (NullPointerException ignored) {}

        System.out.println("-------------------------------");
        List<ReportRow> list2 = reporting.getDirectList();

        list2.forEach(System.out::println);
    }

    @org.junit.jupiter.api.Test
    public void test16() {
        long a = 10;
        long b = 3453;
        System.out.println((double)a / (double)b);

        System.out.println(0.0 / 0.0);
    }

    @Autowired ProductLogService prodLogService;
    @Autowired ProjectLogService projectLogService;
    @Autowired ProjectService projectService;

    @org.junit.jupiter.api.Test
    public void test15() {
        Reporting api = new Reporting();

        for(ProductModel product : prodRepo.findAll()) {
            System.out.println("---------------------------");
            try { api.initialize(product.getViewId()); }
            catch (GeneralSecurityException | IOException e) { System.out.println("Reporting API Initialize Error"); }

            System.out.println("[PRODUCT NO : " + product.getNo() + "]");

            long totalSales = Long.parseLong(api.getSingleDayRevenueData());
            long totalProperty = 0;
            long totalDirect = 0;
            long totalIndirect = 0;
            long totalBrandValue = 0;
            double totalROI = 0.0;

            Map<Long, ProjectLogModel> projectLogs = new HashMap<>();

            Map<Long, Long> directMap = getDirectMap(api.getDirectList());
            System.out.println("[directMap]");
            System.out.println(directMap.toString());

            Map<Long, Long> indirectMap = getIndirectMap(api.getIndirectList());
            System.out.println("[indirectMap]");
            System.out.println(indirectMap.toString());

            List<ProjectModel> projects = projectRepository.findByProductModel(product);

            if (directMap.size() > 0 || indirectMap.size() > 0) {
                if (projects != null) {
                    for (ProjectModel project: projects) {
                        long projectNo = project.getNo();
                        long direct = mapNullCheck(directMap, projectNo);
                        long indirect = mapNullCheck(indirectMap, projectNo);

                        totalProperty += project.getProperty();
                        totalDirect += direct;
                        totalIndirect += indirect;

                        projectLogs.put(
                                projectNo,
                                ProjectLogModel
                                        .builder()
                                        .direct(direct)
                                        .indirect(indirect)
                                        .total(direct + indirect)
                                        .projectModel(project).build()
                        );
                    }
                }
            }

            System.out.println("[projectLogs]");
            System.out.println(projectLogs.toString());

            try { totalBrandValue = totalSales - (totalDirect + totalIndirect); }
            catch (ArithmeticException e) { totalBrandValue = 0; }
            try {
                totalROI = (double)totalSales / (double)totalProperty;
                if (Double.isNaN(totalROI) || Double.isInfinite(totalROI)) totalROI = 0;
            }
            catch (ArithmeticException e) { totalROI = 0; }

            System.out.println("[TOTAL BRAND VALUE : " + totalBrandValue + "]");
            System.out.println("[TOTAL ROI : " + totalROI + "]");
            System.out.println("[TOTAL SALES : " + totalSales + "]");
            System.out.println("[TOTAL PROPERTY : " + totalProperty + "]");

            ProductLogModel prodLog = prodLogService.getLastLog(product);
            if (prodLog == null || !prodLog.getCreateDate().toString().split("T")[0].equals(LocalDateTime.now().toString().split("T")[0])) {
              // 날짜가 일치하는 로그 없을경우
                prodLog = new ProductLogModel();
            }
            prodLog.setProductModel(product);
            prodLog.setTotalSales(totalSales);
            prodLog.setTotalProperty(totalProperty);
            prodLog.setTotalRoi(totalROI);

            System.out.println(prodLog.toString());
            prodLogService.save(prodLog);

            System.out.println("* PRODUCT LOG SAVED * ");

            // 제품 업데이트
            product.setTotalProperty(totalProperty);
            product.setTotalRoi(totalROI);
            product.setTotalSales(totalSales);
            prodRepo.save(product);

            System.out.println("* PRODUCT SAVED * ");

            System.out.println("[PROJECT SAVE]");
            for (ProjectModel project : projects) {
                System.out.println("project no : " + project.getNo());

                ProjectLogModel projectLog;

                try {
                    projectLog = projectLogs.get(project.getNo());
                    System.out.println(projectLog.toString());
                } catch (NullPointerException e) {
                    System.out.println("project log map is null");
                    continue;
                }

                System.out.println(projectLog.toString());
                long sales = projectLog.getDirect() + projectLog.getIndirect();
                long brandValue = totalBrandValue / (totalSales / sales);

                System.out.println("project sales : " + sales);
                System.out.println("project brandValue : " + brandValue);

                projectLog.setBrandValue(brandValue);
                projectLogService.save(projectLog);

                System.out.println("* PROJECT LOG SAVED * ");

                project.setSales(sales);
                project.setBrandValue(brandValue);
                projectService.saveNoArgs(project);

                System.out.println("* PROJECT UPDATED * ");
            }
        }
    }

    public Map<Long, Long> getDirectMap(List<ReportRow> directList) {
        //{"dimensions":["urban-roi","projectNo"],"metrics":[{"values":["직접기여"]}]}

        Map<Long, Long> map = new HashMap<>();
        if (directList == null) return map;

        long projectNo;
        long value;

        for (ReportRow row : directList) {
            try {
                projectNo = Long.parseLong(row.getDimensions().get(1));
            } catch (NumberFormatException e) { continue; }

            try { value = Long.parseLong(row.getMetrics().get(0).getValues().get(0)); }
            catch (NumberFormatException e) { value = 0; }
            map.put(projectNo, value);
        }

        return map;
    }

    public Map<Long, Long> getIndirectMap(List<List<McfData.Rows>> indirectList) {
        // [{"primitiveValue":"campaign-id"}, {"primitiveValue":"campaign-content"}, {"primitiveValue":"assistedValue"}]
        Map<Long, Long> map = new HashMap<>();
        if (indirectList == null) return map;

        long projectNo;
        long value;

        for (List<McfData.Rows> row : indirectList) {
            try {
                projectNo = Long.parseLong(row.get(0).getPrimitiveValue());
            } catch (NumberFormatException e) { continue; }

            try { value = Long.parseLong(row.get(2).getPrimitiveValue()); }
            catch (NumberFormatException e) { value = 0; }
        }

        return map;
    }

    public long mapNullCheck(Map<Long, Long> map, long no) {
        try {
            return map.get(no);
        } catch (NullPointerException e) { return 0; }
    }

    @org.junit.jupiter.api.Test
    public void test17() throws ParseException {
        LocalDateTime date = LocalDateTime.now();

        System.out.println(date.format( DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

}
*/
