package com.zcy.service;

import java.util.Map;
import com.iflytek.cloud.speech.*;
public class VoiceService {

    public static SynthesizerListener synthesizeListener;

    public void start(Map<String, String> mParamMap, String mText) {
        // TODO Auto-generated method stub
        Setting.setShowLog(true);
        SpeechUtility.createUtility(SpeechConstant.APPID +"=5a2a4fff");
//System.out.println(mText);
//System.out.println(mParamMap.get("VOICE_NAME"));
//System.out.println(mParamMap.get("BACKGROUND_SOUND"));
//System.out.println(mParamMap.get("SPEED"));
//System.out.println(mParamMap.get("PITCH"));
//System.out.println(mParamMap.get("VOLUME"));

//String path=this.getClass().getClassLoader().getResource("/").getPath();
//xxx/xunfei/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/XunFeiDemo/WEB-INF/classes/


        SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer( );

        mTts.setParameter(SpeechConstant.VOICE_NAME, mParamMap.get("VOICE_NAME"));
        mTts.setParameter(SpeechConstant.BACKGROUND_SOUND,mParamMap.get("BACKGROUND_SOUND"));
        mTts.setParameter(SpeechConstant.SPEED, mParamMap.get("SPEED"));
        mTts.setParameter(SpeechConstant.PITCH, mParamMap.get("PITCH"));
        mTts.setParameter(SpeechConstant.VOLUME, mParamMap.get("VOLUME"));
//String path=mParamMap.get("ProjectPath").replace('\\', '/');
//System.out.println(path+"testService.pcm");
//mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH,  mParamMap.get("ProjectPath")+"testService.pcm");

        synthesizeListener = new SynthesizerListener() {

            @Override
            public void onBufferProgress(int arg0, int arg1, int arg2,
                                         String arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onCompleted(SpeechError error) {
                // TODO Auto-generated method stub
                System.out.println( "onCompleted enter: "+error );
                if( null != error ){
                    System.out.println( "error: "+error.getErrorCode() );
                }
                System.out.println( "onCompleted leave" );
            }

            @Override
            public void onEvent(int i, int i1, int i2, int i3, Object o, Object o1) {

            }

            @Override
            public void onSpeakBegin() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSpeakPaused() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSpeakProgress(int arg0, int arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSpeakResumed() {
                // TODO Auto-generated method stub

            }

        };

        mTts.startSpeaking( mText, synthesizeListener );
    }


}
