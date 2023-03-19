package com.emosaac.server.service.search;

import com.emosaac.server.common.CommonResponse;
import com.emosaac.server.dto.book.BookDayResponse;
import com.emosaac.server.repository.search.TagNovelRepository;
import com.emosaac.server.repository.search.TagQueryRepository;
import com.emosaac.server.repository.search.TagToonRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {
    private final TagNovelRepository tagNovelRepository;
    private final TagToonRepository tagToonRepository;
    private final TagQueryRepository tagQueryRepository;

    public List<BookDayResponse> findBookListByTagName(String tagName, String type, int size, Long prevId, Double prevScore) {
        if(type.equals("total")){
            return tagQueryRepository.findTotalBookListByTagName(tagName,  PageRequest.ofSize(size), prevId, prevScore);
        }else if(type.equals("webtoon")){
            return tagQueryRepository.findBookListByTagName(tagName, 0,  PageRequest.ofSize(size), prevId, prevScore);
        }else{
            return tagQueryRepository.findBookListByTagName(tagName, 1,  PageRequest.ofSize(size), prevId, prevScore);
        }
    }

    public List<BookDayResponse> findBookListByTitle(String content, String type, int size, Long prevId, Double prevScore) {
        if(type.equals("total")){
            return tagQueryRepository.findTotalBookListByTitle(content,  PageRequest.ofSize(size), prevId, prevScore);
        }else if(type.equals("webtoon")){
            return tagQueryRepository.findBookListByTitle(content,  0, PageRequest.ofSize(size), prevId, prevScore);
        }else{
            return tagQueryRepository.findBookListByTitle(content,  1, PageRequest.ofSize(size), prevId, prevScore);
        }

    }
}
