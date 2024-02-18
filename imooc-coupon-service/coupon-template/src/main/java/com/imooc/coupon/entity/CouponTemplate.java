package com.imooc.coupon.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.coupon.constant.CouponCategory;
import com.imooc.coupon.constant.DistributeTarget;
import com.imooc.coupon.constant.ProductLine;
import com.imooc.coupon.converter.CouponCategoryConverter;
import com.imooc.coupon.converter.DistributeTargetConverter;
import com.imooc.coupon.converter.ProductLineConverter;
import com.imooc.coupon.converter.Ruleconverter;
import com.imooc.coupon.serialize.CouponTemplateSerialize;
import com.imooc.coupon.vo.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 优惠券模版实体类定义
 * 基础属性 + 规则属性
 *
 * @AUTHOR yhf
 * @CREATE 2024-02-12 14:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class) // 监听器可以给列自动赋值
@Table(name = "coupon_template") //映射的表
@JsonSerialize(using = CouponTemplateSerialize.class) // 自定义序列化器 返回给前端的数据
public class CouponTemplate implements Serializable {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键的生成策略
    @Column(name = "id", nullable = false) // 标识列的名称，非空  @Transient注解是把这个字段不要映射到表中
    @Basic
    private Integer id;
    /**
     * 是否是可用状态
     */
    @Column(name = "available", nullable = false)
    private Boolean available;

    /**
     * 是否过期
     */
    @Column(name = "expired", nullable = false)
    private Boolean expired;

    /**
     * 优惠券名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 优惠券logo
     */
    @Column(name = "logo", nullable = false)
    private String logo;

    /**
     * 优惠券描述
     */
    @Column(name = "intro", nullable = false)  // desc 是数据库的关键字，不可以指定为列的名称，这里将intro列映射到desc字段上
    private String desc;

    /**
     * 优惠券分类
     */
    @Convert(converter = CouponCategoryConverter.class) // 转换器
    @Column(name = "category", nullable = false)
    private CouponCategory category;

    /**
     * 产品线
     */
    @Convert(converter = ProductLineConverter.class)
    @Column(name = "product_line", nullable = false)
    private ProductLine productLine;

    /**
     * 总数
     */
    @Column(name = "coupon_count", nullable = false)
    private Integer count;

    /**
     * 创建时间
     */
    @CreatedDate // 通过 @EntityListeners(AuditingEntityListener.class) 监听器可以给列自动赋值
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 创建用户
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 优惠券模版编码
     */
    @Column(name = "template_key", nullable = false)
    private String key;

    /**
     * 目标用户
     */
    @Convert(converter = DistributeTargetConverter.class)
    @Column(name = "target", nullable = false)
    private DistributeTarget target;

    /**
     * 优惠券规则
     */
    @Convert(converter = Ruleconverter.class)
    @Column(name = "rule", nullable = false)
    private TemplateRule rule;

    public CouponTemplate(String name, String logo, String desc, String category,
                          Integer productLine, Integer count, Long userId, Integer target, TemplateRule rule) {
        this.available = false;
        this.expired = false;
        this.name = name;
        this.logo = logo;
        this.desc = desc;
        this.category = CouponCategory.of(category);
        this.productLine = ProductLine.of(productLine);
        this.count = count;
        this.userId = userId;
        //优惠券唯一编码 = 4（产品线和类型）+ 8（日期：20240212）+ id（扩充为4位）
        this.key = productLine.toString() + category + new SimpleDateFormat("yyyyMMdd").format(new Date());
        this.target = DistributeTarget.of(target);
        this.rule = rule;
    }
}
