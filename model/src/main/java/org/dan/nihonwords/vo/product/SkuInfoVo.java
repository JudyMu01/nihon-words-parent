package org.dan.nihonwords.vo.product;

import org.dan.nihonwords.model.product.*;
import org.dan.nihonwords.model.product.SkuAttrValue;
import org.dan.nihonwords.model.product.SkuImage;
import org.dan.nihonwords.model.product.SkuInfo;
import org.dan.nihonwords.model.product.SkuPoster;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SkuInfoVo extends SkuInfo {

	@ApiModelProperty(value = "海报列表")
	private List<SkuPoster> skuPosterList;

	@ApiModelProperty(value = "属性值")
	private List<SkuAttrValue> skuAttrValueList;

	@ApiModelProperty(value = "图片")
	private List<SkuImage> skuImagesList;

}

