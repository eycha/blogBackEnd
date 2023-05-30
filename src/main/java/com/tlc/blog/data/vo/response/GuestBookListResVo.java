package com.tlc.blog.data.vo.response;

import com.tlc.blog.data.entity.GuestBook;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GuestBookListResVo {

    List<GuestBookItem> items;

    @Getter
    private static class GuestBookItem {

        private final Long id;
        private final String writer;
        private final String content;
        private final LocalDate date;

        public GuestBookItem(Long id, String writer, String content, LocalDate date) {
            this.id = id;
            this.writer = writer;
            this.content = content;
            this.date = date;
        }
    }

    public GuestBookListResVo(List<GuestBook> items) {
        List<GuestBookItem> guestBookItemList = new ArrayList<>();

        items.forEach((guestBook) -> {
            GuestBookItem guestBookItem = new GuestBookItem(guestBook.getId(),guestBook.getMember().getUserId(),guestBook.getContent(),guestBook.getCreated().toLocalDate());
            guestBookItemList.add(guestBookItem);
        });

        this.items = guestBookItemList;
    }
}
