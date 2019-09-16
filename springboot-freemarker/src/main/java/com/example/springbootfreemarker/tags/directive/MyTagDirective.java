package com.example.springbootfreemarker.tags.directive;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/2/16
 * @time: 20:54
 * @modifier:
 * @since:
 */
@Component
public class MyTagDirective implements TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        Integer tagId = -1;
        String tagName = "";

        //自己的逻辑 todo
        Map<String, Object> result = new HashMap<String, Object>(2);
        if (map.containsKey("tagId")) {
            tagId = Integer.parseInt(map.get("tagId").toString());
        }

        if (map.containsKey("tagName")) {
            tagName = (String) map.get("tagName").toString();
        }

        result.put("tagId", tagId);
        result.put("tagName", tagName);

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        environment.setVariable("myTag", builder.build().wrap(result));
        templateDirectiveBody.render(environment.getOut());
    }
}
