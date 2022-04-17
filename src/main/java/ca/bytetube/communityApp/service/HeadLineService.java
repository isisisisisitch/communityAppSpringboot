package ca.bytetube.communityApp.service;


import ca.bytetube.communityApp.entity.HeadLine;

import java.util.List;

public interface HeadLineService {

    public static final String HLLISTKEY = "headlinelist";
    /**
     * 根据传入的条件返回指定的头条列表
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition);


}
