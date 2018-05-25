package com.accAyo.serverProjectDemo.util.code;

import com.octo.captcha.Captcha;
import com.octo.captcha.image.gimpy.Gimpy;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.AbstractManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:12 2018/5/24
 */


public class CaptchaUtil extends
        AbstractManageableImageCaptchaService implements ImageCaptchaService, ICaptchaUtil {

    protected CaptchaUtil(CaptchaStore captchaStore, CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize, int captchaStoreLoadBeforeGarbageCollection) {
        super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
    }

    private static CaptchaUtil instance;

    private static CaptchaEngine engine;

    public static CaptchaUtil getInstance() {
        if (instance == null) {
            engine = new CaptchaEngine();
            instance = new CaptchaUtil(new FastHashMapCaptchaStore(), engine, 180,
                    100000, 75000);
        }
        return instance;
    }
    @Override
    public Object[] getImageChallenge() throws CaptchaServiceException {
        Captcha captcha = engine.getNextCaptcha();
        Object challenge = super.getChallengeClone(captcha);
        captcha.disposeChallenge();
        return new Object[]{challenge, ((Gimpy)captcha).getResponse()};
    }
}
