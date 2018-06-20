package com.carlog.chicsign.carlog.model;

import com.carlog.chicsign.carlog.Interface.ICarLog;

import java.io.Serializable;

/**
 * Created by chicsign on 2018-06-20.
 */

public class CarInfoModel implements ICarLog, Serializable {

    private int pathSeq;
    private String pathFull;
    private int pathUseCnt;
    private int pathRecommend;  // 0 : 유저생성 , 1 : 추천
    private String pathLastUse;
    private String[] pathSeqList;
    private Boolean selected = false;
    int totalScrapCnt = 0;


    public int getTotalScrapCnt() {
        return totalScrapCnt;
    }

    public void setTotalScrapCnt(int totalScrapCnt) {
        this.totalScrapCnt = totalScrapCnt;
    }

    public int getPathSeq() {
        return pathSeq;
    }

    public void setPathSeq(int pathSeq) {
        this.pathSeq = pathSeq;
    }

    public String getPathFull() {
        return pathFull;
    }

    public void setPathFull(String pathFull) {
        this.pathFull = pathFull;
    }

    public int getPathUseCnt() {
        return pathUseCnt;
    }

    public void setPathUseCnt(int pathUseCnt) {
        this.pathUseCnt = pathUseCnt;
    }

    public String getPathLastUse() {
        return pathLastUse;
    }

    public void setPathLastUse(String pathLastUse) {
        this.pathLastUse = pathLastUse;
    }

    public String[] getPathSeqList() {
        return pathSeqList;
    }

    public void setPathSeqList(String[] pathSeqList) {
        this.pathSeqList = pathSeqList;
    }

    public int getPathRecommend() {
        return pathRecommend;
    }

    public void setPathRecommend(int pathRecommend) {
        this.pathRecommend = pathRecommend;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
