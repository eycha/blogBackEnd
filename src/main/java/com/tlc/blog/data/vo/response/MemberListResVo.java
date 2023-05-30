package com.tlc.blog.data.vo.response;

import com.tlc.blog.data.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberListResVo {
    List<Member> items;

    public MemberListResVo(List<Member> items) {
        this.items = items;
    }
}
