package com.gaoyy.fastec.example.generators;


import com.example.annotations.EntryGenerator;
import com.gaoyy.latte.wechat.templates.WXEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.gaoyy.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
