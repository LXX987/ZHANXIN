package com.huaqi.zhanxin.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "视频得分")
public class VideoScore {
    private Integer user_id;
    private Integer video_id;
    private Boolean watch;
    private Integer question_score;

    public VideoScore(Integer user_id, Integer video_id, Boolean watch, Integer question_score) {
        this.user_id = user_id;
        this.video_id = video_id;
        this.watch = watch;
        this.question_score = question_score;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Integer video_id) {
        this.video_id = video_id;
    }

    public Boolean getWatch() {
        return watch;
    }

    public void setWatch(Boolean watch) {
        this.watch = watch;
    }

    public Integer getQuestion_score() {
        return question_score;
    }

    public void setQuestion_score(Integer question_score) {
        this.question_score = question_score;
    }
}
