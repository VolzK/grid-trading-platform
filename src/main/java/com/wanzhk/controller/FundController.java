package com.wanzhk.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.wanzhk.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基金
 *
 * @author Hunter
 * <p>
 * 2020-05-06
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class FundController {

    // 基金实时信息：http://fundgz.1234567.com.cn/js/001186.js?rt=1463558676006
    public static final String GET_FUND_REAL_TIME_DATA = "http://fundgz.1234567.com.cn/js/{}.js?rt=1463558676006";

    // 基金详细信息：http://fund.eastmoney.com/pingzhongdata/001186.js?v=20160518155842
    public static final String GET_FUND_DETAILS = "http://fund.eastmoney.com/pingzhongdata/{}.js?v=20160518155842";

    // 所有基金名称列表代码：http://fund.eastmoney.com/js/fundcode_search.js
    public static final String GET_FUND_LIST = "http://fund.eastmoney.com/js/fundcode_search.js";


    // 所有基金公司名称列表代码：http://fund.eastmoney.com/js/jjjz_gs.js?dt=1463791574015


    /**
     * 获取基金涨幅，估算，净值等信息
     *
     * @param fundCode 基金代码
     * @return
     */
    @RequestMapping("/fund/{fundcode}")
    public AjaxResult getFundInfoApi(@PathVariable("fundcode") String fundCode) {
        String format = StrUtil.format(GET_FUND_REAL_TIME_DATA, fundCode);
        String result = null;
        try {
            result = HttpUtil.get(format, CharsetUtil.CHARSET_UTF_8);
        } catch (Exception e) {
            log.error("HTTP请求失败");
            return AjaxResult.error("接口请求失败", "");
        }
        String resultJsonStr = result.substring(result.indexOf("{"), result.indexOf("}") + 1);
        return AjaxResult.success("数据请求成功", JSON.toJSON(resultJsonStr));
    }

    /**
     * 基金详细信息
     *
     * @param fundCode 基金代码
     * @return
     */
    @RequestMapping("/fund/details/{fundcode}")
    public AjaxResult getFundFullInfoAPI(@PathVariable("fundcode") String fundCode) {
        String format = StrUtil.format(GET_FUND_DETAILS, fundCode);
        String result = null;
        try {
            result = HttpUtil.get(format, CharsetUtil.CHARSET_UTF_8);
        } catch (Exception e) {
            log.error("HTTP请求失败");
            return AjaxResult.error("接口请求失败", "");
        }
        return AjaxResult.success("数据请求参数", result);
    }


    /**
     * 基金列表
     *
     * @param page 页码
     * @param size 数据量
     * @return
     */
    @RequestMapping("/fund/list")
    public AjaxResult getFundList(@RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "10") Integer size) {
        String result = null;
        try {
            result = HttpUtil.get(GET_FUND_LIST, CharsetUtil.CHARSET_UTF_8);
        } catch (Exception e) {
            log.error("HTTP请求失败");
            return AjaxResult.error("接口请求失败", "");
        }
        System.out.println(result);
        return AjaxResult.success("数据请求参数", result);
    }

    public static void main(String[] args) {
        FundController fundController = new FundController();
        fundController.getFundList(1, 1);
    }

}
