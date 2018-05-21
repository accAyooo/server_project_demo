package com.accAyo.serverProjectDemo.controller.web.portal;

import com.accAyo.serverProjectDemo.common.EnumBookGroupType;
import com.accAyo.serverProjectDemo.common.EnumSiteType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午11:50 2018/5/19
 */

@Controller
@RequestMapping("/user")
public class AccountsController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object registerAction(String email, String password, int cityId, String name, EnumBookGroupType groupType,
                                 EnumSiteType siteType, int spreadId) throws Exception {

        // todo 判断用户是否登录


        // todo 检查手机号是否可用


        //

        return null;
    }
}
