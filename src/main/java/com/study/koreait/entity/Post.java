package com.study.koreait.entity;

import com.study.koreait.dto.FindPostResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class Post {
    private int postId;
    private String title;
    private String content;

    public FindPostResDto toFindPostResDto() {
        return FindPostResDto.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}