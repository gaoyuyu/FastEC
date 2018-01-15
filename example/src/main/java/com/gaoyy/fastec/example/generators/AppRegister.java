package com.gaoyy.fastec.example.generators;


import com.example.annotations.AppRegisterGenerator;
import com.gaoyy.latte.wechat.templates.AppRegisterTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.gaoyy.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister
{
}
