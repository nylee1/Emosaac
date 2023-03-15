package com.emosaac.server.repository.comment;

import com.emosaac.server.domain.book.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookCommentRepository extends JpaRepository<BookComment, Long> {
    @Query("select c from BookComment c where c.commentId = :commentId")
    Optional<BookComment> findComment(@Param("commentId") Long commnetId);
}
