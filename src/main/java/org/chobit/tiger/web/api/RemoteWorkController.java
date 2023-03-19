package org.chobit.tiger.web.api;

import org.chobit.tiger.model.RemoteChapterRequest;
import org.chobit.tiger.model.RemoteResponse;
import org.chobit.tiger.model.RemoteWorkRequest;
import org.chobit.tiger.service.RemoteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author robin
 */
@RestController
@RequestMapping("/api/remote")
public class RemoteWorkController {

    @Autowired
    private RemoteCodeService remoteCodeService;


    @PostMapping("/chapter")
    public RemoteResponse addChapter(@RequestHeader("code") String code,
                                     @RequestBody RemoteChapterRequest request) {
        return remoteCodeService.addChapter(code, request);
    }


    @PostMapping("/work")
    public RemoteResponse addWork(@RequestHeader("code") String code,
                                  @RequestBody RemoteWorkRequest request) {
        return remoteCodeService.addWork(code, request);
    }


}
