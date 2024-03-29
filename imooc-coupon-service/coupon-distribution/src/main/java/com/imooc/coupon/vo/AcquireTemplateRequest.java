package com.imooc.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取优惠券请求对象定义
 *
 * @AUTHOR yhf
 * @CREATE 2024-02-14 15:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcquireTemplateRequest {
    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 优惠券模版信息
     */
    private CouponTemplateSDK templateSDK;

}
