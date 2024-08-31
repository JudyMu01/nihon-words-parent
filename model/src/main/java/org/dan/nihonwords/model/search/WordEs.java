package org.dan.nihonwords.model.search;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author mcd
 * @create 2024-08-29 21:11
 */
@Data
@Document(indexName = "words" ,shards = 3,replicas = 1)
public class WordEs {
    // 商品Id= skuId
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String keyword;

    /**
     * 声调
     */
    @Field(type = FieldType.Integer, index = false)
    private Integer accent;

    /**
     * 假名
     */
    @Field(type = FieldType.Text)
    private String kana;

    /**
     * 基本含义
     */
    @Field(type = FieldType.Text)
    private String translate;

    /**
     * 展示图片
     */
    @Field(type = FieldType.Keyword, index = false)
    private String imgUrl;

    //  es 中能分词的字段，这个字段数据类型必须是 text！keyword 不分词！
    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text, index = false)
    private List<String> MeaningList;

}
