package com.github.zhangbiao;


import com.github.zhangbiao.entity.List2PdfEntity;
import com.github.zhangbiao.entity.List2PdfFieldEntity;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {


    @org.junit.Test
    public void data2Pdf() throws Exception {
        List<Entity> sources = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            sources.add(new Entity("1min",
                    "2023-01-06 17:38:26",
                    "2023-01-06 17:38:26",
                    "测试人员",
                    "部门",
                    "琼B123456",
                    "贵州省贵阳市观山湖区观山街道阳关大道110号联合广场贵州省贵阳市观山湖区观山街道阳关大道110号联合广场贵州省贵阳市观山湖区观山街道阳关大道110号联合广场贵州省贵阳市观山湖区观山街道阳关大道110号联合广场贵州省贵阳市观山湖区观山街道阳关大道110号联合广场end",
                    "贵州省贵阳市观山湖区观山街道阳关大道110号联合广场",
                    "12312313.12"
            ));
        }

        List<List2PdfFieldEntity<Entity, ?>> fieldList = new ArrayList<>();
        fieldList.add(new List2PdfFieldEntity<>("时间", 0, 105, Entity::getConsumeTimeFormat));
        fieldList.add(new List2PdfFieldEntity<>("始发地签到时间", 1, 175, Entity::getSignDateStart));
        fieldList.add(new List2PdfFieldEntity<>("目的地签到时间", 2, 175, Entity::getSignDateEnd));
        fieldList.add(new List2PdfFieldEntity<>("人员", 3, 140, Entity::getUserName));
        fieldList.add(new List2PdfFieldEntity<>("部门", 4, 280, Entity::getDeptName));
        fieldList.add(new List2PdfFieldEntity<>("车牌", 5, 175, Entity::getCarNum));
        fieldList.add(new List2PdfFieldEntity<>("始发地", 6, 315, Entity::getAddressStart));
        fieldList.add(new List2PdfFieldEntity<>("目的地", 7, 315, Entity::getAddressEnd));
        fieldList.add(new List2PdfFieldEntity<>("公里数", 8, 140, Entity::getDistanceFormat));

        List2PdfEntity<Entity> entity = new List2PdfEntity<>(sources, fieldList);
        ToPDF pdf = new List2Pdf<>(
                entity,
                Files.newOutputStream(Paths.get("/Users/zhangbiao/Downloads/2.pdf")),
                "./doc/font/SimHei.TTF"
        );
        long start = System.currentTimeMillis();
        pdf.exec();
        System.out.println(System.currentTimeMillis() - start);
    }





    public class Entity {
        String consumeTimeFormat;

        String signDateStart;

        String signDateEnd;

        String userName;

        String deptName;

        String carNum;

        String addressStart;

        String addressEnd;

        String distanceFormat;

        public Entity(String consumeTimeFormat, String signDateStart, String signDateEnd, String userName, String deptName, String carNum, String addressStart, String addressEnd, String distanceFormat) {
            this.consumeTimeFormat = consumeTimeFormat;
            this.signDateStart = signDateStart;
            this.signDateEnd = signDateEnd;
            this.userName = userName;
            this.deptName = deptName;
            this.carNum = carNum;
            this.addressStart = addressStart;
            this.addressEnd = addressEnd;
            this.distanceFormat = distanceFormat;
        }

        public String getConsumeTimeFormat() {
            return consumeTimeFormat;
        }

        public void setConsumeTimeFormat(String consumeTimeFormat) {
            this.consumeTimeFormat = consumeTimeFormat;
        }

        public String getSignDateStart() {
            return signDateStart;
        }

        public void setSignDateStart(String signDateStart) {
            this.signDateStart = signDateStart;
        }

        public String getSignDateEnd() {
            return signDateEnd;
        }

        public void setSignDateEnd(String signDateEnd) {
            this.signDateEnd = signDateEnd;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getAddressStart() {
            return addressStart;
        }

        public void setAddressStart(String addressStart) {
            this.addressStart = addressStart;
        }

        public String getAddressEnd() {
            return addressEnd;
        }

        public void setAddressEnd(String addressEnd) {
            this.addressEnd = addressEnd;
        }

        public String getDistanceFormat() {
            return distanceFormat;
        }

        public void setDistanceFormat(String distanceFormat) {
            this.distanceFormat = distanceFormat;
        }
    }
}
