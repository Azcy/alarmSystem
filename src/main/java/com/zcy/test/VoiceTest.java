package com.zcy.test;

import com.zcy.service.VoiceService;
import com.zcy.util.VoiceInitUtil;

import java.util.Map;

public class VoiceTest {
    public static void main(String[] args) {
        String voiceText="报警！报警！，券兑换数量飙升";
        Map<String,String> voiceInit= VoiceInitUtil.initiateParam("小燕","关","20","40","100");
        VoiceService voiceService=new VoiceService();
        voiceService.start(voiceInit,voiceText);
    }
}
