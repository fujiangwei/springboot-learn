package com.example.springbooti18n.controller;

import com.example.springbooti18n.consts.CommonConsts;
import com.example.springbooti18n.utils.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/2/18
 * @time: 13:58
 * @modifier:
 * @since:
 */
@Controller
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MessagesUtil messagesUtil;

    @GetMapping(value = "hello")
    public String hello(Model model) {

        model.addAttribute("i18n", messageSource.getMessage("welcome", null, Locale.US));

        return "hello";
    }

    @GetMapping(value = "/message")
    @ResponseBody
    public String message(HttpServletRequest request) {
        //获取当前的本地区域信息
        Locale locale = LocaleContextHolder.getLocale();
        //或者
        Locale locale1= RequestContextUtils.getLocale(request);

        Locale aDefault = Locale.getDefault();

        //不能使用Locale.ENGLISH
        Locale english = Locale.US;
        //不能Locale.CHINESE
        Locale chinese = Locale.CHINA;

        //其中第二个参数为占位符数据
        String welcome = messageSource.getMessage("welcome", null, english);
        String hello = messageSource.getMessage("hello", new String[]{"i18n"}, english);

        String welcome1 = messagesUtil.getMessage("welcome", chinese);
        String hello1 = messagesUtil.getMessage("hello", new String[]{"i18n"}, chinese);
        System.out.println(hello1 + welcome1);

        return hello + welcome;
    }

    /**
     * 切换语言,只作用于session
     * @param request
     * @param lang
     * @return
     */
    @RequestMapping("/i18n")
    public String changeSessionLanauage(HttpServletRequest request, String lang){
        System.out.println(lang);
        if(CommonConsts.LANG_ZH.equals(lang)){
            //代码中即可通过以下方法进行语言设置
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("zh","CN"));
        }else if(CommonConsts.LANG_EN.equals(lang)){
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,new Locale("en","US"));
        }
        return "redirect:/hello";
    }

    /**
     * 作用于session和cookie
     * @param request
     * @param response
     * @param lang
     * @return
     */
//    @RequestMapping("/i18n2")
//    public String changeSessionLanauage2(HttpServletRequest request, HttpServletResponse response, String lang){
//        System.out.println(lang);
//        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
//        if(CommonConsts.LANG_ZH.equals(lang)){
//            localeResolver.setLocale(request, response, new Locale("zh","CN"));
//        }else if(CommonConsts.LANG_EN.equals(lang)){
//            localeResolver.setLocale(request, response, new Locale("en","US"));
//        }
//        return"redirect:/hello";
//    }
}
