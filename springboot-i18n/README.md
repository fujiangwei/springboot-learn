## i18n介绍
i18n（其来源是英文单词 internationalization的首末字符i和n，18为中间的字符数）是“国际化”的简称。在资讯领域，国际化(i18n)指让产品（出版物，软件，硬件等）无需做大的改变就能够适应不同的语言和地区的需要。对程序来说，在不修改内部代码的情况下，能根据不同语言及地区显示相应的界面。 在全球化的时代，国际化尤为重要，因为产品的潜在用户可能来自世界的各个角落。通常与i18n相关的还有L10n（“本地化”的简称）。

## 默认国际化原理
   
   * 文件放在resources/目录下并命名必须以 messages 开头，这是因为 MessageSourceAutoConfiguration 类中指定了前缀
   * SpringMVC 会识别用户的首选地区，根据这个地区显示内容，用户区域通过区域解析器识别，它必须显示 LocaleResolver 接口，默认采用的区域解析器是 AcceptHeaderLocaleResolver，它是验证 HTTP 请求头的头部信息 accept-language 来解析区域，这个头部由用户浏览器底层根据系统的区域进行设定

## 修改默认messages配置前缀
   
   在application配置中添加
   
    #i18n
    spring:
        messages:
            encoding: UTF-8
            basename: i18n/messages
            
## 代码中使用国际化
> 注入 MessageSource 对象，通过 getMessage 方法获取信息

    messageSource.getMessage("welcome", null, locale);
    
    说明：第一个参数是国际化文件的key，第二个参数是key对应value中的占位符数据，第三个是当前区域

## 会话区域解析器 SessionLocaleResolver
> 注入 Bean，会话区域解析器只针对当前会话有效
   
     @Bean
     public LocaleResolver localeResolver() {
         SessionLocaleResolver slr = new SessionLocaleResolver();
         //设置默认区域,
         slr.setDefaultLocale(Locale.ENGLISH);
         return slr;
     }
## Cookie区域解析器

    @Bean
    public LocaleResolver localeResolver2() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setCookieName("localeCookie");
        //设置默认区域
        clr.setDefaultLocale(Locale.ENGLISH);
        //设置cookie有效期.
        clr.setCookieMaxAge(3600);
        return clr;
    }
    
## 问题
> 浏览器访问出现乱码

   1、设置国际化的编码和你的编译器（idea等）一致，如编译器为utf-8则
   
    #i18n
    spring:
        messages:
            encoding: UTF-8
            
   2、修改当前编译器的编码为UTF-8，之后将国际化文件中的乱码内容改为当前编码下的内容即可