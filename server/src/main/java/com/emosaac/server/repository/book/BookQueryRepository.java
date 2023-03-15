package com.emosaac.server.repository.book;

import com.emosaac.server.domain.book.Book;
import com.emosaac.server.dto.book.BookDayResponse;
import com.emosaac.server.dto.book.QBookDayResponse;
import com.emosaac.server.dto.novel.QNovelDayResponse;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.emosaac.server.domain.book.QBook.book;

@RequiredArgsConstructor
@Repository
public class BookQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    // 요일별 소설 리스트
    public Slice<BookDayResponse> findBookListByDay(int typeCd, String day, PageRequest page, Long prevId, Double prevScore, String criteria) {
        List<BookDayResponse> content = jpaQueryFactory.select(new QBookDayResponse(book))
                .from(book)
                .where(
                        book.type.eq(typeCd),
                        book.day.contains(day),
                        book.type.eq(1),
                        //no-offset 페이징 처리
//                        ltBookId(prevId),
                        cursorIdAndCursorScore(prevId, prevScore),
                        filterGenreCd(criteria)
                )
//                .orderBy(findCriteria(criteria))
                .limit(page.getPageSize()+1)
                .orderBy(book.score.desc(),book.bookId.desc())  // 평점 추가
                .fetch();

        boolean hasNext = false;
        if (content.size() == page.getPageSize()+1) {
            content.remove(page.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, page, hasNext);
    }

    // 장르별 소설 리스트
    public Slice<BookDayResponse> findBookListByGenre(int typeCd, Long genreCode, PageRequest page, Long prevId, String criteria) {
        List<BookDayResponse> content = jpaQueryFactory.select(new QBookDayResponse(book))
                .from(book)
                .where(
                        book.type.eq(typeCd),
                        book.genre.gerneId.eq(genreCode),
                        book.type.eq(1),
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

    // 소설 상세 조회
    public Optional<Book> findBookByBook(Long bookId) {
        return Optional.ofNullable(jpaQueryFactory.select(book)
                .from(book)
                .where(
                        book.bookId.eq(bookId)
                )
                .fetchOne());
    }

    private BooleanExpression ltBookId(Long cursorId) {
        return cursorId == null ? null : book.bookId.lt(cursorId);
    }
    private Predicate cursorIdAndCursorScore(Long cursorId, Double cursorScore) {
        return (book.score.eq(cursorScore)
                .and(book.bookId.lt(cursorId)))
                .or(book.score.lt(cursorScore));
    }

    private OrderSpecifier<?> findCriteria(String criteria) { //정렬 조건
        if (criteria.contains("date")) {
            return book.regist.asc();
        } else if (criteria == null) {
            return book.hit.desc();
        }

        return book.bookId.desc();
    }

    /*-  10: 로맨스, 11: 로판, 12: 드라마, 13: 판타지, 14: 액션/무협, 15: BL/GL, 16: 공포 27: 현판, 28: 미스터리 */
    private Predicate filterGenreCd(String criteria) {
        if(criteria.contains("로맨스")){
            return book.genre.gerneId.eq(10L);
        } else if(criteria.contains("로판")){
            return book.genre.gerneId.eq(11L);
        } else if(criteria.contains("판타지")){
            return book.genre.gerneId.eq(13L);
        } else if(criteria.contains("무협")){
            return book.genre.gerneId.eq(14L);
        } else if(criteria.contains("BL/GL")){
            return book.genre.gerneId.eq(15L);
        } else if(criteria.contains("현판")){
            return book.genre.gerneId.eq(27L);
        } else if(criteria.contains("미스터리")){
            return book.genre.gerneId.eq(28L);
        }

        return null;
    }
}
