package org.chobit.tiger.web.api;

import org.chobit.tiger.model.Pair;
import org.chobit.tiger.service.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author robin
 */
@RestController
@RequestMapping("/api/volume")
public class VolumeController {


    @Autowired
    private VolumeService volumeService;


    @GetMapping("/suggest/{workId}")
    public Pair<String, Object> suggest(@PathVariable("workId") int workId,
                                        @RequestParam String key) {
        return volumeService.suggest(workId, key);
    }

}
