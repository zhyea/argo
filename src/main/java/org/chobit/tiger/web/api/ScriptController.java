package org.chobit.tiger.web.api;

import org.chobit.tiger.service.ScriptService;
import org.chobit.tiger.service.entity.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author robin
 */
@RestController
@RequestMapping("/api/spt")
public class ScriptController {

    @Autowired
    private ScriptService scriptService;

    @RequestMapping({"/data", ""})
    public List<Script> data() {
        return scriptService.all();
    }

}
