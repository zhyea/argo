package org.chobit.tiger.model;

import org.chobit.tiger.service.entity.Chapter;

/**
 * @author robin
 */
public class ChapterAndVol extends Chapter {

    private String volName;

    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName;
    }
}
