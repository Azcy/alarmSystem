package com.zcy.util;

import java.util.HashMap;
import java.util.Map;

public class VoiceInitUtil {
    private static Map<String, String> mParamMap = new HashMap<String, String>();


    public static Map<String, String> initiateParam(String voiceName, String backgoundSound,
                                                    String speed, String pitch, String volume) {
        mParamMap.put("VOICE_NAME", voiceName);
        mParamMap.put("BACKGROUND_SOUND", backgoundSound);
        mParamMap.put("SPEED", speed);
        mParamMap.put("PITCH", pitch);
        mParamMap.put("VOLUME", volume);
        return mParamMap;
    }
}
