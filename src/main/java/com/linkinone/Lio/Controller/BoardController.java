package com.linkinone.Lio.Controller;


import com.linkinone.Lio.domain.entity.BoardEntity;
import com.linkinone.Lio.dto.BoardDto;
import com.linkinone.Lio.dto.BoardTechDto;
import com.linkinone.Lio.dto.CommentDto;
import com.linkinone.Lio.dto.TechDto;
import com.linkinone.Lio.service.BoardService;
import com.linkinone.Lio.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;
    private CommentService commentService;

    // 메인 페이지
    @GetMapping("/")
    public String index(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getFreeBoardlist(pageNum);
        List<BoardDto> boardList2 = boardService.getIndexStudyboardlist(pageNum);
        List<BoardDto> boardList3 = boardService.getIndexProjectboardlist(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("boardList2", boardList2);
        model.addAttribute("boardList3", boardList3);

        return "/index";
    }

    //자유게시판
    @GetMapping("/board-free.html")
    public String board_free(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
            List<BoardDto> boardList = boardService.getFreeBoardlist(pageNum);
            Integer[] pageList = boardService.getFreePageList(pageNum);
            model.addAttribute("boardList", boardList);
            model.addAttribute("pageList", pageList);

        return "/board-free.html";
    }

    //자유게시판 글작성
    @GetMapping("/board-free-write.html")
    public String board_free_write(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author", userDetails.getUsername());
        return "/board-free-write.html";
    }

    //자유 게시판 글 작성
    @PostMapping("/board-free-write.html")
    public String board_free_writeEx(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/board-free.html";
    }

    //자유게시판 상세조회
    @GetMapping("/board-free.html/{no}")
    public String board_free_detail(@PathVariable("no") Long no, Model model, Authentication authentication) {
        BoardDto boardDTO = boardService.getPost(no);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        model.addAttribute("boardDto", boardDTO);

        //댓글
        List<CommentDto> commentList = commentService.getComment(no);
        model.addAttribute("commentDto", commentList);

        return "/board-free-content.html";
    }

    //자유게시판 댓글 작성
    @PostMapping("/board-free.html/{no}")
    public String board_free_comment(CommentDto commentDto) {
        commentService.savePost(commentDto);

        return "redirect:/board-free.html/{no}";
    }
    
    //자유게시판 댓글 삭제
    @DeleteMapping("/board-free.html/{no}")
    public String delete_free_comment(@PathVariable("no") Long no) {

        commentService.deleteComment(no);

        return "redirect:/board-free.html/";
    }
    

    //자유게시판 수정, 업데이트
    @GetMapping("/board-free-update.html/{no}")
    public String board_free_edit(@PathVariable("no") Long no, Model model, Authentication authentication) {
        BoardDto boardDTO = boardService.getPost(no);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        model.addAttribute("boardDto", boardDTO);
        return "/board-free-update.html";
    }

    @PutMapping("/board-free-update.html/{no}")
    public String board_free_update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/board-free.html/{no}";
    }

    //자유게시판 삭제
    @DeleteMapping("/board-free-update.html/{no}")
    public String delete_free_board(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board-free.html/";
    }



    //질문게시판
    @GetMapping("/board-question.html")
    public String board_question(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList2 = boardService.getQuestionBoardList(pageNum);
        Integer[] pageList = boardService.getQuestionPageList(pageNum);

        model.addAttribute("boardList", boardList2);
        model.addAttribute("pageList", pageList);

        return "/board-question.html";
    }

    @GetMapping("/board-question-write.html")
    public String board_question_write(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        return "/board-question-write.html";
    }

    @PostMapping("/board-question-write.html")
    public String board_question_writeEx(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/board-question.html";
    }

    @GetMapping("/board-question.html/{no}") //질문게시판 상세조회
    public String board_question_detail(@PathVariable("no") Long no, Model model, Authentication authentication) {
        BoardDto boardDTO = boardService.getPost(no);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        model.addAttribute("boardDto", boardDTO);

        List<CommentDto> commentList = commentService.getComment(no);
        model.addAttribute("commentDto", commentList);


        return "/board-free-content.html";
    }

    //질문게시판 댓글 작성
    @PostMapping("/board-question.html/{no}")
    public String board_question_comment(CommentDto commentDto) {
        commentService.savePost(commentDto);

        return "redirect:/board-question.html/{no}";
    }

    //질문게시판 댓글 삭제
    @DeleteMapping("/board-question.html/{no}")
    public String delete_question_comment(@PathVariable("no") Long no) {

        commentService.deleteComment(no);

        return "redirect:/board-question.html/";
    }



    //공지사항
    @GetMapping("/board-notice.html")
    public String board_notice(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList3 = boardService.getNoticeBoardList(pageNum);
        Integer[] pageList = boardService.getNoticePageList(pageNum);

        model.addAttribute("boardList", boardList3);
        model.addAttribute("pageList", pageList);

            return "/board-notice.html";
    }

    //공지사항 글 작성
    @GetMapping("/board-notice-write.html")
    public String board_notice_write(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        return "/board-notice-write.html";
    }

    @PostMapping("/board-notice-write.html")
    public String board_notice_writeEx(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/board-notice.html";
    }

    @GetMapping("/board-notice.html/{no}") // 공지사항 상세조회
    public String board_notice_detail(@PathVariable("no") Long no, Model model, Authentication authentication) {
        BoardDto boardDTO = boardService.getPost(no);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        model.addAttribute("boardDto", boardDTO);

        List<CommentDto> commentList = commentService.getComment(no);
        model.addAttribute("commentDto", commentList);


        return "/board-free-content.html";
    }

    //공지 게시판 댓글 작성
    @PostMapping("/board-notice.html/{no}")
    public String board_notice_comment(CommentDto commentDto) {
        commentService.savePost(commentDto);

        return "redirect:/board-notice.html/{no}";
    }

    //공지 게시판 댓글 삭제
    @DeleteMapping("/board-notice.html/{no}")
    public String delete_notice_comment(@PathVariable("no") Long no) {

        commentService.deleteComment(no);

        return "redirect:/board-notice.html/";
    }




    //스터디 게시판
    @GetMapping("/study-find.html")
    public String study_find(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList4 = boardService.getStudyBoardlist(pageNum);
        Integer[] pageList = boardService.getStudyPageList(pageNum);

        model.addAttribute("boardList", boardList4);
        model.addAttribute("pageList", pageList);

        return "/study-find.html";
    }

    //글 작성
    @GetMapping("/study-recruit.html")
    public String study_recruit(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());

        List<TechDto> techlist = boardService.getTech();
        model.addAttribute("techlist", techlist);

        return "/study-recruit.html";
    }

    @PostMapping("/study-recruit.html")
    public String study_recruitEx(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/study-find.html";
    }

    //상세조회 ( 스터디 )
    @GetMapping("/study-content.html/{no}")
    public String study_detail(@PathVariable("no") Long no, Model model, Authentication authentication) {
        BoardDto boardDTO = boardService.getPost(no);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        model.addAttribute("boardDto", boardDTO);


        List<CommentDto> commentList = commentService.getComment(no);
        model.addAttribute("commentDto", commentList);

        return "/study-project-content.html";


    }

    //스터디 게시판 댓글 작성
    @PostMapping("/study-content.html/{no}")
    public String board_study_comment(CommentDto commentDto) {
        commentService.savePost(commentDto);

        return "redirect:/study-content.html/{no}";
    }

    //스터디 게시판 댓글 삭제
    @DeleteMapping("/study-content.html/{no}")
    public String delete_study_comment(@PathVariable("no") Long no) {

        commentService.deleteComment(no);

        return "redirect:/study-find.html/";
    }



    //프로젝트 찾기 게시판
    @GetMapping("/project-find.html")
    public String project_find(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList5 = boardService.getProjectBoardlist(pageNum);
        Integer[] pageList = boardService.getProjectPageList(pageNum);

        model.addAttribute("boardList", boardList5);
        model.addAttribute("pageList", pageList);

        return "/project-find.html";
    }
    //글 작성
    @GetMapping("/project-recruit.html")
    public String project_recruit(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());


        List<TechDto> techlist = boardService.getTech();
        model.addAttribute("techlist", techlist);

        return "/project-recruit.html";
    }

    @PostMapping("/project-recruit.html") // 프로젝트 글 작성 포스트매핑
    public String project_recruitEx(BoardDto boardDto, BoardTechDto boardTechDto) {

        boardService.savePost(boardDto);
        boardService.saveTech(boardTechDto);

        return "redirect:/project-find.html";
    }

    @GetMapping("/project-content.html/{no}")
    public String project_detail(@PathVariable("no") Long no, Model model, Authentication authentication) {
        BoardDto boardDTO = boardService.getPost(no);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        model.addAttribute("boardDto", boardDTO);

        List<CommentDto> commentList = commentService.getComment(no);
        model.addAttribute("commentDto", commentList);


        return "/study-project-content.html";
    }

    //프로젝트 게시판 댓글 작성
    @PostMapping("/project-content.html/{no}")
    public String board_project_comment(CommentDto commentDto) {
        commentService.savePost(commentDto);

        return "redirect:/project-content.html/{no}";
    }

    //프로젝트 게시판 댓글 삭제
    @DeleteMapping("/project-content.html/{no}")
    public String delete_project_comment(@PathVariable("no") Long no) {

        commentService.deleteComment(no);

        return "redirect:/project-find.html/";
    }





}

    /* 게시글 목록
    @GetMapping("/board/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list.html";
    }

    @GetMapping("/board/list2")
    public String list2(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardlist2(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list2.html";
    }

    @GetMapping("/post")
    public String write(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());

        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);


        return "redirect:/board/list";
    }

    
    //게시글 상세 조회
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model, Authentication authentication) {
        BoardDto boardDTO = boardService.getPost(no);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        model.addAttribute("boardDto", boardDTO);

        return "board/detail.html";
    }

    //상세 조회에서의 댓글
    @PostMapping("/commentwrite")
    public String commentwrite(CommentDto commentDto) {
        commentService.savePost(commentDto);

        return "redirect:/board/list";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model, Authentication authentication) {
        BoardDto boardDTO = boardService.getPost(no);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/board/list";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board/list";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/list.html";
    }

 */




