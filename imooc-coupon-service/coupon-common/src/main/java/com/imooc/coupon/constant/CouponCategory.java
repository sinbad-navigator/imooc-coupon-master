package com.imooc.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 优惠券分类枚举
 * @AUTHOR yhf
 * @CREATE 2024-02-11 22:39
 */
@Getter
@AllArgsConstructor
public enum CouponCategory {

    MANJIAN("满减券", "001"),
    ZHEKOU("折扣券", "002"),
    LIJIAN("立减券", "003");
    /**
     * 优惠券描述（分类）
     */
    private String description;
    /**
     * 优惠券分类编码
     */
    private String code;
    // 用于根据提供的字符串代码(code)返回相应的CouponCategory枚举常量
    public static CouponCategory of(String code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }
}
