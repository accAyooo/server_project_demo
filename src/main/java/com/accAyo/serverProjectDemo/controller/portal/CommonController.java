package com.accAyo.serverProjectDemo.controller.portal;

import com.accAyo.serverProjectDemo.service.impl.AuthCodeService;
import com.accAyo.serverProjectDemo.service.impl.SpiderService;
import com.accAyo.serverProjectDemo.util.code.CaptchaUtil;
import com.accAyo.serverProjectDemo.util.code.ICaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:55 2018/5/24
 */
@Controller
public class CommonController {

    @Resource
    private AuthCodeService authCodeService;
    @Resource
    private SpiderService spiderService;

    protected static ICaptchaUtil captchaUtil = CaptchaUtil.getInstance();

    /**
     * 返回验证码
     * @param t
     * @param request
     * @param response
     */
    @RequestMapping("/auth/code")
    @ResponseBody
    public void getAccountsLoginAuthCode(@RequestParam String t,
                                         HttpServletRequest request, HttpServletResponse response) {
        if ( t == null || "".equals(t)) return;

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "nocache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        Object[] objects = captchaUtil.getImageChallenge();
        BufferedImage bufferedImage = (BufferedImage) objects[0];

        authCodeService.setAuthCode("" + objects[1], t, request);

        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", servletOutputStream);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (servletOutputStream == null) {
                servletOutputStream = null;
            }
        }
    }

}
