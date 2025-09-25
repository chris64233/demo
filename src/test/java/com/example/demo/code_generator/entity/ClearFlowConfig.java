package com.example.demo.code_generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangchi
 * @since 2025-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClearFlowConfig implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String tradeType;

    private String handlerName;

    private Integer seq;


}
