package com.accAyo.serverProjectDemo.util.code;

import com.octo.captcha.service.CaptchaServiceException;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:30 2018/5/24
 */
public interface ICaptchaUtil {

    public Object[] getImageChallenge() throws CaptchaServiceException;
}
