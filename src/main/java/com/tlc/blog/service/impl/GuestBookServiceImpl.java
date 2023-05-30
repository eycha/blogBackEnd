package com.tlc.blog.service.impl;

import com.tlc.blog.data.entity.GuestBook;
import com.tlc.blog.data.entity.Member;
import com.tlc.blog.data.enumerate.Authorization;
import com.tlc.blog.data.repository.GuestBookRepository;
import com.tlc.blog.data.repository.MemberRepository;
import com.tlc.blog.data.vo.GuestBookAddReqVo;
import com.tlc.blog.data.vo.GuestBookUpdateReqVo;
import com.tlc.blog.data.vo.response.GuestBookListResVo;
import com.tlc.blog.error.Error;
import com.tlc.blog.error.ErrorSpec;
import com.tlc.blog.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public GuestBookListResVo list() {
        List<GuestBook> guestBookList = guestBookRepository.findByDeleted(false);
        return new GuestBookListResVo(guestBookList);
    }

    @Override
    @Transactional
    public GuestBookListResVo add(Long memberId, GuestBookAddReqVo guestBookAddReqVo) {
        Member member = memberRepository.findByIdAndDeleted(memberId, false).orElseThrow(() -> Error.of(ErrorSpec.NotFoundMember));

        guestBookRepository.save(new GuestBook(member, guestBookAddReqVo.getContent(), false));
        List<GuestBook> guestBookList = guestBookRepository.findByDeleted(false);

        return new GuestBookListResVo(guestBookList);
    }

    @Override
    @Transactional
    public GuestBookListResVo update(Long memberId, Long guestBookId, GuestBookUpdateReqVo guestBookUpdateReqVo) {
        Member member = memberRepository.findByIdAndDeleted(memberId, false).orElseThrow(() -> Error.of(ErrorSpec.NotFoundMember));
        GuestBook guestBook = guestBookRepository.findByIdAndDeleted(guestBookId, false).orElseThrow(() -> Error.of(ErrorSpec.NotFoundGuestBook));

        if (!guestBook.getMember().getId().equals(member.getId())) throw Error.of(ErrorSpec.NoAuthorization);

        guestBook.setContent(guestBookUpdateReqVo.getContent());
        List<GuestBook> guestBookList = guestBookRepository.findByDeleted(false);

        return new GuestBookListResVo(guestBookList);
    }

    @Override
    @Transactional
    public void delete(Long memberId, Long guestBookId) {
        Member member = memberRepository.findByIdAndDeleted(memberId, false).orElseThrow(() -> Error.of(ErrorSpec.NotFoundMember));
        GuestBook guestBook = guestBookRepository.findByIdAndDeleted(guestBookId, false).orElseThrow(() -> Error.of(ErrorSpec.NotFoundGuestBook));

        if (guestBook.getMember().getId().equals(member.getId()) || member.getAuthorization().equals(Authorization.ROLE_ADMIN)) {
            guestBook.setDeleted(true);
        }
    }
}
