package com.emosaac.server.service.emopick;

import com.emosaac.server.common.PagedResponse;
import com.emosaac.server.common.SlicedResponse;
import com.emosaac.server.common.exception.ResourceForbiddenException;
import com.emosaac.server.common.exception.ResourceNotFoundException;
import com.emosaac.server.domain.book.Book;
import com.emosaac.server.domain.book.BookComment;
import com.emosaac.server.domain.book.BookCommentLike;
import com.emosaac.server.domain.emo.*;
import com.emosaac.server.domain.user.User;
import com.emosaac.server.dto.book.BookDetailResponse;
import com.emosaac.server.dto.comment.CommentLikeResponse;
import com.emosaac.server.dto.comment.CommentResponse;
import com.emosaac.server.dto.comment.CommentSaveRequest;
import com.emosaac.server.dto.comment.CommentUpdateRequest;
import com.emosaac.server.dto.emopick.*;
import com.emosaac.server.repository.book.BookQueryRepository;
import com.emosaac.server.repository.book.BookRepository;
import com.emosaac.server.repository.emopick.*;
import com.emosaac.server.service.CommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static com.emosaac.server.domain.emo.QEmopick.emopick;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmopickService {

    private final EmopickRepository emopickRepository;
    private final EmopickQueryRepository emopickQueryRepository;
    private final EmoLikeRepository emoLikeRepository;
    private final EmopickDetailRepository emopickDetailRepository;
    private final BookRepository bookRepository;
    private final BookQueryRepository bookQueryRepository;
    private final CommonService commonService;

    public SlicedResponse<EmopickListResponse> findEmopickListByUser(int size, Long prevId, Long userId) {

        Slice<EmopickListResponse> page = emopickQueryRepository.findEmopickListByUser(PageRequest.ofSize(size), prevId, userId);

        List<ImageinEmopickListResponse> result = new ArrayList<>();

        for (EmopickListResponse emoList : page.getContent()){
            String bookSeqs = "";
            if(emoList.getWebtoonSeq() != null)
                bookSeqs += emoList.getWebtoonSeq();
            if(emoList.getNovelSeq() != null)
                bookSeqs += emoList.getNovelSeq();

            String[] books = bookSeqs.split("_");
            List<String> thumbnails = new ArrayList<>();
            for (String bookId : books){
                String thumbnail = bookQueryRepository.findThumbnail(Long.valueOf(bookId));
                thumbnails.add(thumbnail);
            }

            result.add(new ImageinEmopickListResponse(emoList, thumbnails));
        }


        return new SlicedResponse<>(result, page.getNumber()+1, page.getSize(), page.isFirst(), page.isLast(), page.hasNext());
    }

    // 이모픽 리스트 조회 + 썸네일 n 개도 함께
    public SlicedResponse<EmopickListResponse> findEmopickList(int size, Long prevId){


        Slice<EmopickListResponse> page = emopickQueryRepository.findEmopickList(PageRequest.ofSize(size), prevId);

        List<ImageinEmopickListResponse> result = new ArrayList<>();

        for (EmopickListResponse emoList : page.getContent()){
            String bookSeqs = "";
            if(emoList.getWebtoonSeq() != null)
                bookSeqs += emoList.getWebtoonSeq();
            if(emoList.getNovelSeq() != null)
                bookSeqs += emoList.getNovelSeq();

            String[] books = bookSeqs.split("_");
            List<String> thumbnails = new ArrayList<>();
            for (String bookId : books){
                String thumbnail = bookQueryRepository.findThumbnail(Long.valueOf(bookId));
                thumbnails.add(thumbnail);
            }

            result.add(new ImageinEmopickListResponse(emoList, thumbnails));
        }


        return new SlicedResponse<>(result, page.getNumber()+1, page.getSize(), page.isFirst(), page.isLast(), page.hasNext());

    }

    // 이모픽 상세 조회
    public DetailResponse<BookReveiwResponse> findEmopickDetail(Long emopickId, Long userId) {
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

        Boolean emoLikeStatus = false;
        if(emoLikeRepository.existsByEmopickIdAndUserId(emopickId, userId).isPresent())
            emoLikeStatus = true;
        
        DetailResponse result = new DetailResponse(emopick.getUser(), emopick.getTitle(), emopick.getContent(), webtoon, novel, emoLikeStatus);

        return result;
    }

    // 이모픽 등록
    @Transactional
    public Long createEmopickByUser(EmopickSaveRequest request, Long userId) {
//        User user = commonService.getUser(userId);
//        Emopick emopick = emopickRepository.save(request.of(user));
//
//        String webtoonIdStr = "";
//        String novelIdStr = "";
//
//        // 웹툰 리스트
//        if (request.getWebtoonList() != null || !request.getWebtoonList().isEmpty())
//            webtoonIdStr = setIdStr(emopick, request.getWebtoonList());
//
//        // 노블 리스트
//        if (request.getNovelList() != null || !request.getNovelList().isEmpty())
//            novelIdStr = setIdStr(emopick, request.getNovelList());
//
//        emopick.setSeq(webtoonIdStr, novelIdStr);
//
//        return emopick.getEmopickId();

        User user = commonService.getUser(userId);
        Emopick emopick = emopickRepository.save(request.of(user));

        if (request.getWebtoonList() != null || !request.getWebtoonList().isEmpty())
            setEmopickDetail(emopick, request.getWebtoonList(), 0);

        if (request.getNovelList() != null || !request.getNovelList().isEmpty())
            setEmopickDetail(emopick, request.getNovelList(), 1);

        return emopick.getEmopickId();
    }

    private void setEmopickDetail(Emopick emopick, Map<Long, String> bookList, int type){
        for(Entry<Long, String> review : bookList.entrySet()){
            Book book = commonService.getBook(review.getKey());
            EmopickDetail emopickDetail = EmopickDetail.builder().emopick(emopick).book(book).review(review.getValue()).type(type).build();
            emopickDetailRepository.save(emopickDetail);
        }
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
    @Transactional
    public Long updateEmopickByUser(Long emopickId, EmopickSaveRequest request, Long userId) {
        Emopick emopick = emopickRepository.findById(emopickId).orElseThrow(() -> new ResourceNotFoundException("emopick", "emopickId", emopickId));

        validEmopickUser(userId, emopick.getUser().getUserId());

        emopick.update(request);

//        String webtoonIdStr = "";
//        String novelIdStr = "";
//
//        // 웹툰 리스트
//        if (request.getWebtoonList() != null || !request.getWebtoonList().isEmpty())
//            webtoonIdStr = setIdStr(emopick, request.getWebtoonList());
//
//        // 노블 리스트
//        if (request.getNovelList() != null || !request.getNovelList().isEmpty())
//            novelIdStr = setIdStr(emopick, request.getNovelList());
//
//        emopick.setSeq(webtoonIdStr, novelIdStr);

        if (request.getWebtoonList() != null || !request.getWebtoonList().isEmpty())
            updateEmopickDetail(emopick, request.getWebtoonList(), 0);

        if (request.getNovelList() != null || !request.getNovelList().isEmpty())
            updateEmopickDetail(emopick, request.getNovelList(), 1);

        return emopick.getEmopickId();
    }

    private void updateEmopickDetail(Emopick emopick, Map<Long, String> bookList, int type) {
        for(Entry<Long, String> review : bookList.entrySet()){
            Book book = commonService.getBook(review.getKey());
            EmopickDetail emopickDetail = emopickDetailRepository.findByEmopickIdAndBookId(emopick.getEmopickId(), book.getBookId());
            emopickDetail.update(book, review.getValue(), type);
        }
    }

    // 이모픽 삭제
    @Transactional
    public Long deleteEmopickByUser(Long emopickId, Long userId) {
        Emopick emopick = emopickRepository.findById(emopickId).orElseThrow(() -> new ResourceNotFoundException("emopick", "emopickId", emopickId));

        validEmopickUser(userId, emopick.getUser().getUserId());

        emopickDetailRepository.deleteByEmopickId(emopick.getEmopickId());

        emopick.clearUser();
        emopickRepository.deleteById(emopickId);
        return emopickId;
    }

    // 이모픽 좋아요
    @Transactional
    public Object toggleLikesByEmopick(Long emopickId, Long userId) {
        Emopick emopick = emopickRepository.findById(emopickId).orElseThrow(() -> new ResourceNotFoundException("emopick", "emopickId", emopickId));
        User user = commonService.getUser(userId);

        LikeEmo likeEmo = LikeEmo.builder().emopick(emopick).user(user).build();
        return emopick.toggleLikes(likeEmo);
    }

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

    public void validEmopickUser(Long currentUser, Long emopickUser) {

        if (currentUser == emopickUser || currentUser.equals(emopickUser)) {
            return;
        }
        else {
            throw new ResourceForbiddenException("본인이 작성한 글이 아닙니다");
        }
    }

    // 댓글
//    public List<CommentResponse> findParentEmopickCommentList(Long userId, Long emopickId, String criteria, int offset, int size) {
//        List<CommentResponse> res = emopickCommentQueryRepository.findParentCommentByEmopickId(emopickId, criteria, PageRequest.of(offset - 1, size));
//        for(CommentResponse cr : res){
//            cr.updateTotalCount(emopickCommentQueryRepository.findParentCommentCount(emopickId));
//            if(emopickCommentQueryRepository.findEmopickCommentLikeState(cr.getCommentId(), userId).isEmpty()){
//                cr.updateLikeState(false);
//            }else {
//                cr.updateLikeState(true);
//            }
//        }
//        return res;
//    }
//
//    public List<CommentResponse> findChildEmopickCommentList(Long userId, Long parentId, String criteria, int offset, int size) {
//        List<CommentResponse> res = emopickCommentQueryRepository.findChildCommentByParentId(parentId, criteria, PageRequest.of(offset - 1, size));
//        for(CommentResponse cr : res){
//            cr.updateTotalCount( emopickCommentQueryRepository.findChildCommentCount(parentId));
//            if( emopickCommentQueryRepository.findEmopickCommentLikeState(cr.getCommentId(), userId).isEmpty()){
//                cr.updateLikeState(false);
//            }else {
//                cr.updateLikeState(true);
//            }
//        }
//        return res;
//    }
//    @Transactional
//    public Long createEmopickComment(Long userId, Long emopickId, CommentSaveRequest request) {
//        Emopick emopick = emopickRepository.findById(emopickId).orElseThrow(() -> new ResourceNotFoundException("emopick", "emopickId", emopickId));
//        User user = commonService.getUser(userId);
//
//        EmopickComment emopickComment = null;
//        if(request.getParentId() != null){
//            EmopickComment parent = emopickCommentRepository.findComment(request.getParentId())
//                    .orElseThrow(() -> new ResourceNotFoundException("ParentComment", "parentId", request.getParentId()));
//            emopickComment =  emopickCommentRepository.save(request.of(user, emopick, parent,1));
//            parent.setChild(emopickComment);
//        }
//        else{
//            emopickComment = emopickCommentRepository.save(request.of(user, emopick, 0));
//        }
//        return emopickComment.getCommentId();
//    }
//    @Transactional
//    public Object updateEmopickComment(Long userId, Long commentId, CommentUpdateRequest request) {
//        EmopickComment emopickComment = emopickCommentRepository.findComment(commentId)
//                .orElseThrow(() -> new ResourceNotFoundException("EmopickComment", "commentId", commentId));
//
//        validEmopickCommentUser(userId, emopickComment.getUser().getUserId());
//        emopickComment.update(request);
//        return emopickComment.getCommentId();
//    }
//    @Transactional
//    public Object deleteEmopickComment(Long userId, Long commentId) {
//        EmopickComment emopickComment = emopickCommentRepository.findComment(commentId)
//                .orElseThrow(() -> new ResourceNotFoundException("EmopickComment", "commentId", commentId));
//        validEmopickCommentUser(userId, emopickComment.getUser().getUserId());
//
//        if(emopickComment.getParent() == null && !emopickComment.getChildren().isEmpty()){ // 부모 댓글인데 자식 댓글이 있는 경우
//
//            emopickComment.updateDeleteStatus();
//        }else if(emopickComment.getParent() != null){ // 자식 댓글 삭제할 경우
//            // 부모 댓글 삭제 & 자식이 없다면 부모 댓글도 삭제
//            EmopickComment parent = emopickComment.getParent();
//            emopickCommentRepository.deleteByCommentId(commentId);
//            if(parent.getIsDelete() && emopickCommentRepository.findParentComment(parent.getCommentId()).isEmpty()){
//                emopickCommentRepository.deleteByCommentId(parent.getCommentId());
//            }
//        }else{
//            emopickCommentRepository.deleteByCommentId(commentId);
//        }
//        return commentId;
//    }
//    public void validEmopickCommentUser(Long currentUser, Long CommentUser) {
//
//        if (currentUser == CommentUser || currentUser.equals(CommentUser)) {
//            return;
//        } else {
//            throw new ResourceForbiddenException("본인이 작성한 글이 아닙니다");
//        }
//    }
//
//    @Transactional
//    public CommentLikeResponse toggleBookCommentLike(Long userId, Long commentId) {
//        EmopickComment emopickComment = emopickCommentRepository.findComment(commentId)
//                .orElseThrow(() -> new ResourceNotFoundException("EmopickComment", "commentId", commentId));
//        User user = commonService.getUser(userId);
//        EmoCommentLike emoCommentLike = EmoCommentLike.builder().emopickComment(emopickComment).user(user).build();
//        return new CommentLikeResponse(emopickComment.getCommentId(), emopickComment.toggleEmopickCommentLike(emoCommentLike), emopickComment.getTotalLikes());
//    }
}
