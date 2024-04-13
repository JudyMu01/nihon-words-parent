package org.dan.nihonwords.vo.word;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dan.nihonwords.model.product.SkuAttrValue;
import org.dan.nihonwords.model.product.SkuImage;
import org.dan.nihonwords.model.product.SkuPoster;
import org.dan.nihonwords.model.word.Word;
import org.dan.nihonwords.model.word.WordImage;
import org.dan.nihonwords.model.word.WordMeaning;
import org.dan.nihonwords.model.word.WordPoster;

import java.util.List;

/**
 * @author mcd
 * @create 2023-08-15 10:41
 */
@Data
public class WordVo extends Word {
    @ApiModelProperty(value = "词书分类")
    private Long bookId;

    @ApiModelProperty(value = "海报列表")
    private List<WordPoster> wordPosterList;

    @ApiModelProperty(value = "单词翻译")
    private List<WordMeaning> wordMeaningList;

    @ApiModelProperty(value = "图片")
    private List<WordImage> wordImagesList;
}
