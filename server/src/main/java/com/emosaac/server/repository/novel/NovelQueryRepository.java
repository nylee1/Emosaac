package com.emosaac.server.repository.novel;

import com.emosaac.server.domain.book.Book;
import com.emosaac.server.domain.book.DayNovel;
import com.emosaac.server.domain.book.QBook;
import com.emosaac.server.dto.QBookResponse;
import com.emosaac.server.dto.novel.NovelDayResponse;
import com.emosaac.server.dto.novel.QNovelDayResponse;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.emosaac.server.domain.book.QBook.book;
import static com.emosaac.server.domain.book.QDayNovel.dayNovel;

@RequiredArgsConstructor
@Repository
public class NovelQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Slice<NovelDayResponse> findBookListByDay(String day, PageRequest page, Long prevId, String criteria) {
        List<NovelDayResponse> content = jpaQueryFactory.select(new QNovelDayResponse(book))
                .from(book)
                .where(
                        book.day.contains(day),
                        //no-offset 페이징 처리
                        ltBookId(prevId)
                )
//                .orderBy(findCriteria(criteria))
                .limit(page.getPageSize()+1)
                .orderBy(book.bookId.desc())
                .fetch();

        boolean hasNext = false;
        if (content.size() == page.getPageSize()+1) {
            content.remove(page.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, page, hasNext);
    }

    private BooleanExpression ltBookId(Long cursorId) {
        return cursorId == null ? null : book.bookId.lt(cursorId);
    }

    private OrderSpecifier<?> findCriteria(String criteria) { //정렬 조건
        if (criteria.contains("date")) {
            return book.regist.asc();
        } else if (criteria == null) {
            return book.hit.desc();
        }

        return book.bookId.desc();
    }
}