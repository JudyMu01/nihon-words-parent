package org.dan.nihonwords.model.word;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dan.nihonwords.model.base.BaseEntity;

/**
 * <p>
 * mq去重表
 * </p>
 *
 * @author mcd
 * @since 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MqRepeatRecord extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务编号
     */
    private String businessNo;



}
