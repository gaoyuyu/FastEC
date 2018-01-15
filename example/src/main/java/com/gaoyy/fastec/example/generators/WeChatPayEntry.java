package com.gaoyy.fastec.example.generators;


import com.example.annotations.PayEntryGenerator;
import com.gaoyy.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.gaoyy.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
