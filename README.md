# to-pdf

# 使用 --源码Test
```
// sources -> 源数据，Entity -> 数据实体
// List<Entity> sources = getSources(500);
List2PdfBuilder<Entity> builder = new List2PdfBuilder<>(
    Entity.class,
    sources,
    "/Users/zhangbiao/Downloads/3.pdf",
    "./doc/font/SimHei.TTF",
    PageSize.A4
);
ToPDF pdf = builder.build();
// 执行pdf生成
pdf.exec();
```
