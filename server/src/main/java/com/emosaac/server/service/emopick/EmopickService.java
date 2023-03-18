package com.emosaac.server.service.emopick;

import com.emosaac.server.common.exception.ResourceNotFoundException;
import com.emosaac.server.domain.book.Book;
import com.emosaac.server.domain.emo.Emopick;
import com.emosaac.server.domain.user.User;
import com.emosaac.server.dto.book.BookDetailResponse;
import com.emosaac.server.dto.emopick.BookReveiwResponse;
import com.emosaac.server.dto.emopick.DetailResponse;
import com.emosaac.server.dto.emopick.EmopickDetailResponse;
import com.emosaac.server.dto.emopick.EmopickSaveRequest;
import com.emosaac.server.repository.book.BookQueryRepository;
import com.emosaac.server.repository.emopick.EmopickRepository;
import com.emosaac.server.service.CommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmopickService {

    private final EmopickRepository emopickRepository;
    private final BookQueryRepository bookQueryRepository;
    private final CommonService commonService;

    // 이모픽 리스트 조회

    // 이모픽 상세 조회
    public DetailResponse<BookReveiwResponse> findEmopickDetail(Long emopickId) {
        Emopick emopick = emopickRepository.findById(emopickId).orElseThrow(() -> new ResourceNotFoundException("emopick", "emopickId", emopickId));

        List<BookReveiwResponse> webtoon = new ArrayList<>();
        List<BookReveiwResponse> novel = new ArrayList<>();

        if (emopick.getWebtoonSeq() != "") {
            String[] webtoonId = emopick.getWebtoonSeq().split("_");
            webtoon = getList(emopick, webtoonId);
        }

        if (emopick.getWebtoonSeq() != "") {
            String[] novelId = emopick.getNovelSeq().split("_");
            novel = getList(emopick, novelId);
        }

        DetailResponse result = new DetailResponse(emopick.getUser(), emopick.getTitle(), emopick.getContent(), webtoon, novel);

        return result;
    }

    // 이모픽 등록
    @Transactional
    public Long createEmopickByUser(EmopickSaveRequest request, Long userId) {
        User user = commonService.getUser(userId);
        Emopick emopick = emopickRepository.save(request.of(user));

        String webtoonIdStr = "";
        String novelIdStr = "";

        // 웹툰 리스트
        if (request.getWebtoonList() != null || !request.getWebtoonList().isEmpty())
            webtoonIdStr = setIdStr(emopick, request.getWebtoonList());

        // 노블 리스트
        if (request.getNovelList() != null || !request.getNovelList().isEmpty())
            novelIdStr = setIdStr(emopick, request.getNovelList());

        emopick.setSeq(webtoonIdStr, novelIdStr);

        return emopick.getEmopickId();
    }

    private String setIdStr(Emopick emopick, Map<Long, String> bookList){
        String result = "";

        for (Entry<Long, String> emo : bookList.entrySet()) {
            emopick.addEmopick(emo.getKey(), emo.getValue());
            result += emo.getKey().toString() + "_";
        }

        return result;
    }

    // 이모픽 수정

    // 이모픽 삭제

    ///////////////////////
    private List<BookReveiwResponse> getList(Emopick emopick, String[] bookId) {
        List<BookReveiwResponse> result = new ArrayList<>();

        for (String id : bookId) {
            if (id.equals("")) break;
            Book book = commonService.getBook(Long.valueOf(id));

            BookReveiwResponse bookReveiwResponse = new BookReveiwResponse(book, emopick.getEmopickList().get(book.getBookId()));

            result.add(bookReveiwResponse);
        }

        return result;
    }

}