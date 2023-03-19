package org.chobit.tiger.web.api;

import org.chobit.tiger.model.Page;
import org.chobit.tiger.model.PageResult;
import org.chobit.tiger.model.Pair;
import org.chobit.tiger.model.WorkModel;
import org.chobit.tiger.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author robin
 */
@RestController
@RequestMapping("/api/work")
public class WorkController {


    @Autowired
    private WorkService workService;


    @PostMapping("/data")
    public PageResult<WorkModel> data(@RequestBody Page page) {
        return workService.findInPage(page);
    }


    @PostMapping("/delete")
    public boolean delete(@RequestBody List<Integer> ids) {
        return workService.deleteByIds(ids);
    }


    @GetMapping("/suggest")
    public Pair<String, List<WorkModel>> suggest(@RequestParam String key) {
        List<WorkModel> list = workService.findWithKeyword(key);
        return new Pair<>(key, list);
    }
}
