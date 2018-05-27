package com.accAyo.serverProjectDemo.controller.portal;

import com.accAyo.serverProjectDemo.service.impl.AuthCodeService;
import com.accAyo.serverProjectDemo.service.impl.MemcachedService;
import com.accAyo.serverProjectDemo.util.code.CaptchaUtil;
import com.accAyo.serverProjectDemo.util.code.ICaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
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
    private MemcachedService memcachedService;

    protected static ICaptchaUtil captchaUtil = CaptchaUtil.getInstance();

    @RequestMapping("/auth/test")
    public void authTest() {
        if (memcachedService.get("name#shixiangyu") != null) {
            memcachedService.delete("name#shixiangyu");
        }
        memcachedService.set("name#shixiangyu", "shixiangyu");
        System.out.println(memcachedService.get("name#shixiangyu"));
    }

    @RequestMapping("/auth/code")
    @ResponseBody
    public void getAccountsLoginAuthCode(@RequestParam String t,
                                         HttpServletRequest request, HttpServletResponse response) {
        // todo 判断是否存在authcode

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "nocache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        Object[] objects = captchaUtil.getImageChallenge();
        BufferedImage bufferedImage = (BufferedImage) objects[0];


        String fileName = t + ".png";
//        String path = "/WEB-INF/code/";
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/code");
        File codeImage = new File(path + "/" + fileName);

        if (!codeImage.exists()) {
            try {
                if (!codeImage.getParentFile().exists()) {
                    codeImage.mkdirs();
                }
                codeImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(codeImage);
            ImageIO.write(bufferedImage, "jpg", fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileOutputStream = null;
        }
    }
}
