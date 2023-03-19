package org.chobit.tiger.web.front;

import org.chobit.tiger.model.NavNode;
import org.chobit.tiger.model.SettingModel;
import org.chobit.tiger.service.NavService;
import org.chobit.tiger.service.ScriptService;
import org.chobit.tiger.service.SettingService;
import org.chobit.tiger.service.entity.Script;
import org.chobit.tiger.web.AbstractPageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.List;

import static org.chobit.tiger.constants.Constants.DESCRIPTION;
import static org.chobit.tiger.constants.Constants.KEYWORDS;

/**
 * @author robin
 */
public abstract class AbstractFrontPageController extends AbstractPageController {

    @Autowired
    private SettingService settingService;
    @Autowired
    private NavService navService;
    @Autowired
    private ScriptService scriptService;

    @Override
    public String view(String viewName, ModelMap map, String title) {
        NavNode nav = navService.buildNavTree();
        SettingModel settings = settingService.all();

        map.put("nav", nav);
        map.put("siteName", settings.getName());
        map.put("notice", settings.getNotice());

        if (!map.containsKey(KEYWORDS)) {
            map.put(KEYWORDS, settings.getKeywords());
        } else {
            String s = map.getAttribute(KEYWORDS) + "," + settings.getKeywords();
            map.put(KEYWORDS, s);
        }
        if (!map.containsKey(DESCRIPTION)) {
            map.put(DESCRIPTION, settings.getDescription());
        }
        if (!map.containsKey("logo")) {
            map.put("logo", settings.getLogo());
        }
        if (!map.containsKey("background")) {
            map.put("background", settings.getBackgroundImg());
        }
        if (!map.containsKey("bgRepeat")) {
            map.put("bgRepeat", settings.getBgRepeat());
        }

        List<Script> all = scriptService.all();
        for (Script c : all) {
            map.put(c.getCode(), c.getScript());
        }

        return super.view(viewName, map, title);
    }

    @Override
    protected String titleParent() {
        return settingService.getSiteName();
    }
}
