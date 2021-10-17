package com.linkinone.Lio.Controller;


import com.linkinone.Lio.dto.BoardDto;
import com.linkinone.Lio.dto.CommentDto;
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

    //스터디찾기
    @GetMapping("/study-find.html")
    public String study_find() {
        return "/study-find.html";
    }

    //프로젝트찾기
    @GetMapping("/project-find.html")
    public String project_find() {
        return "/project-find.html";
    }

    //자유게시판
    @GetMapping("/board-free.html")
    public String board_free() {
        return "/board-free.html";
    }

    //질문게시판
    @GetMapping("/board-question.html")
    public String board_question() {
        return "/board-question.html";
    }

    //

    //공지사항
    @GetMapping("/board-notice.html")
    public String board_notice() {
        return "/board-notice.html";
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




