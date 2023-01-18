package com.github.zhangbiao;


import com.github.zhangbiao.annotation.ToPdfField;
import com.github.zhangbiao.entity.List2PdfEntity;
import com.github.zhangbiao.entity.List2PdfFieldEntity;
import com.github.zhangbiao.handler.List2Pdf;
import com.github.zhangbiao.handler.ToPDF;
import com.github.zhangbiao.builder.List2PdfBuilder;
import com.itextpdf.kernel.geom.PageSize;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {


    @org.junit.Test
    public void data2Pdf() throws Exception {
        List<Entity> sources = getSources(500);

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
                PageSize.A4,
                "./doc/font/SimHei.TTF"
        );
        long start = System.currentTimeMillis();
        pdf.exec();
        System.out.println(System.currentTimeMillis() - start);
    }

    @org.junit.Test
    public void testAnn() {
        List<Entity> sources = getSources(500);
        List2PdfBuilder<Entity> builder = new List2PdfBuilder<>(
                Entity.class,
                sources,
                "/Users/zhangbiao/Downloads/3.pdf",
                "./doc/font/SimHei.TTF",
//                PageSize.A4.rotate()
                PageSize.A4
        );
        ToPDF pdf = builder.build();
        long start = System.currentTimeMillis();
        pdf.exec();
        System.out.println(System.currentTimeMillis() - start);
    }


    private List<Entity> getSources(int size) {
        List<Entity> sources = new ArrayList<>();

        for (int i = 0; i < size; i++) {
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
        return sources;
    }

    public class Entity {

        @ToPdfField(name = "时间", index = 0, width = 105)
        String consumeTimeFormat;

        @ToPdfField(name = "始发地签到时间", index = 10, width = 175)
        String signDateStart;

        @ToPdfField(name = "目的地签到时间", index = 20, width = 175)
        String signDateEnd;

        @ToPdfField(name = "人员", index = 30, width = 140)
        String userName;

        @ToPdfField(name = "部门", index = 40, width = 280)
        String deptName;

        @ToPdfField(name = "车牌", index = 50, width = 175)
        String carNum;

        @ToPdfField(name = "始发地", index = 60, width = 315)
        String addressStart;

        @ToPdfField(name = "时间", index = 70, width = 315)
        String addressEnd;

        @ToPdfField(name = "公里数", index = 80, width = 140)
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
