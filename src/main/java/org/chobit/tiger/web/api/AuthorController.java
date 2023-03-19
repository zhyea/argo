package org.chobit.tiger.web.api;

import org.chobit.tiger.model.Page;
import org.chobit.tiger.model.PageResult;
import org.chobit.tiger.model.Pair;
import org.chobit.tiger.model.WorkModel;
import org.chobit.tiger.service.AuthorService;
import org.chobit.tiger.service.WorkService;
import org.chobit.tiger.tools.LowerCaseKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author robin
 */
@RestController
@RequestMapping("/api/author")
public class AuthorController {


    @Autowired
    private AuthorService authorService;
    @Autowired
    private WorkService workService;


    @PostMapping("/data")
    public PageResult<LowerCaseKeyMap> all(@RequestBody Page page) {
        return authorService.queryInPage(page);
    }


    @GetMapping("/suggest")
    public Pair<String, Object> suggest(@RequestParam("key") String key) {
        return authorService.findSuggest(key);
    }

    @PostMapping("/works/{authorId}")
    public PageResult<WorkModel> works(@PathVariable("authorId") int authorId,
                                       @RequestBody Page page) {
        return workService.findWithAuthor(authorId, page);
    }

}
