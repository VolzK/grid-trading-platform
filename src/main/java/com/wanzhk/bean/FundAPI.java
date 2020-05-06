package com.wanzhk.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基金接口
 *
 * @author Wanzhk
 * <p>
 * 2020-05-06
 */
@Data
@NoArgsConstructor
public class FundAPI {

    // 基金代码
    private String fundcode;
    // 基金名称
    private String name;
    // 截止时间
    private String jzrq;
    // 最新净值
    private String dwjz;
    // 净值估算
    private String gsz;
    // 涨幅估算
    private String gszzl;
    // 涨幅时间
    private String gztime;
}
