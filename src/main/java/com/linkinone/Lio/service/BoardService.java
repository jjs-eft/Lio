package com.linkinone.Lio.service;


import com.linkinone.Lio.domain.entity.BoardEntity;
import com.linkinone.Lio.domain.repository.BoardRepository;
import com.linkinone.Lio.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {

    private BoardRepository boardRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 10;       // 한 페이지에 존재하는 게시글 수(자유, 질문, 공지)
    private static final int SP_PAGE_POST_COUNT = 6;       // 한 페이지에 존재하는 게시글 수(인덱스 추천수, 프로젝트, 스터디)
    private static final int INDEX_PAGE_POST_COUNT = 3;       // 인덱스 프로젝트, 스터디



    // 자유게시판
    @Transactional
    public List<BoardDto> getFreeBoardlist(Integer pageNum) {
            String BT = "free";
            Page<BoardEntity> page = boardRepository.findAllByBoardtype(BT, PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
            List<BoardEntity> boardEntities = page.getContent();
            List<BoardDto> boardDtoList = new ArrayList<>();

            for (BoardEntity boardEntity : boardEntities) {
                    boardDtoList.add(this.convertEntityToDto(boardEntity));
            }

            return boardDtoList;
        }

    @Transactional
    public Long getFreeBoardCount() {
        String BT = "free";
        return boardRepository.countByBoardtype(BT);
    }

    public Integer[] getFreePageList(Integer curPageNum) { //자유게시판 페이징
        Integer[] pageFreeList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수

        Double postsTotalCount = Double.valueOf(this.getFreeBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageFreeList[idx] = val;
        }

        return pageFreeList;
    }





    // 질문게시판

    @Transactional
    public List<BoardDto> getQuestionBoardList(Integer pageNum) {
        String BT = "question";
        Page<BoardEntity> page = boardRepository.findAllByBoardtype(BT, PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList2 = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
                boardDtoList2.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList2;
    }

    @Transactional
    public Long getQuestionBoardCount() {
        String BT = "question";
        return boardRepository.countByBoardtype(BT);
    }


    public Integer[] getQuestionPageList(Integer curPageNum) { // 질문게시판 페이징
        Integer[] pageQuestionList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수

        Double postsTotalCount = Double.valueOf(this.getQuestionBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageQuestionList[idx] = val;
        }

        return pageQuestionList;
    }



    // 공지게시판

    @Transactional
    public List<BoardDto> getNoticeBoardList(Integer pageNum) { // 공지게시판
        String BT = "notice";
        Page<BoardEntity> page = boardRepository.findAllByBoardtype(BT, PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList3 = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList3.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList3;
    }

    @Transactional
    public Long getNoticeBoardCount() {
        String BT = "notice";
        return boardRepository.countByBoardtype(BT);
    }


    public Integer[] getNoticePageList(Integer curPageNum) { // 공지게시판 페이징
        Integer[] pageNoticeList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수 

        Double postsTotalCount = Double.valueOf(this.getNoticeBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageNoticeList[idx] = val;
        }

        return pageNoticeList;
    }

    // 스터디 찾기 게시판
    @Transactional
    public List<BoardDto> getStudyBoardlist(Integer pageNum) {
        String BT = "study";
        Page<BoardEntity> page = boardRepository.findAllByBoardtype(BT, PageRequest.of(pageNum - 1, SP_PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList4 = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList4.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList4;
    }

    @Transactional
    public Long getStudyBoardCount() {
        String BT = "study";
        return boardRepository.countByBoardtype(BT);
    }

    public Integer[] getStudyPageList(Integer curPageNum) {
        Integer[] pageStudyList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수

        Double postsTotalCount = Double.valueOf(this.getStudyBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/SP_PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageStudyList[idx] = val;
        }

        return pageStudyList;
    }

    // 프로젝트 찾기
    @Transactional
    public List<BoardDto> getProjectBoardlist(Integer pageNum) {
        String BT = "project";
        Page<BoardEntity> page = boardRepository.findAllByBoardtype(BT, PageRequest.of(pageNum - 1, SP_PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList5 = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList5.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList5;
    }

    @Transactional
    public Long getProjectBoardCount() {
        String BT = "project";
        return boardRepository.countByBoardtype(BT);
    }

    public Integer[] getProjectPageList(Integer curPageNum) { //자유게시판 페이징
        Integer[] pageProjectList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수

        Double postsTotalCount = Double.valueOf(this.getProjectBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/SP_PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageProjectList[idx] = val;
        }

        return pageProjectList;
    }


    //인덱스 스터디
    @Transactional
    public List<BoardDto> getIndexStudyboardlist(Integer pageNum) {
        String BT = "study";
        Page<BoardEntity> page = boardRepository.findAllByBoardtype(BT, PageRequest.of(pageNum - 1, INDEX_PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList6 = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList6.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList6;
    }
    
    //인덱스 프로젝트
    @Transactional
    public List<BoardDto> getIndexProjectboardlist(Integer pageNum) {
        String BT = "project";
        Page<BoardEntity> page = boardRepository.findAllByBoardtype(BT, PageRequest.of(pageNum - 1, INDEX_PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
        List<BoardEntity> boardEntities = page.getContent();
        List<BoardDto> boardDtoList7 = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList7.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList7;
    }



    @Transactional
    public BoardDto getPost(Long post_id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(post_id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        return this.convertEntityToDto(boardEntity);
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getPost_id();
    }

    @Transactional
    public void deletePost(Long post_id) {
            boardRepository.deleteById(post_id);
    }

    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDtoList;

        for (BoardEntity boardEntity : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList;
    }



    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        return BoardDto.builder()
                .post_id(boardEntity.getPost_id())
                .boardtype(boardEntity.getBoardtype())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();
    }
}
