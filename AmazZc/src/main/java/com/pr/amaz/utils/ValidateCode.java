package com.pr.amaz.utils;

import java.awt.image.BufferedImage;

/**
 * 图片验证码生成器
 */
public class ValidateCode {
    //图片的宽度
    private int width = 160;
    //图片的高度
    private int height = 28;
    //验证码字符个数
    private int codeCount = 4;
    //验证码干扰线
    private int lineCount = 150;
    //验证码
    private String code = null;
    //验证码图片Buffer
    private BufferedImage buffImage = null;




}
