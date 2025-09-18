package org.shark.boot05.board.controller;

import java.util.Map;
import java.util.NoSuchElementException;

import org.shark.boot05.board.dto.BoardDTO;
import org.shark.boot05.board.service.BoardService;
import org.shark.boot05.common.dto.PageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

/*
 * 홈화면   GET   /
 * 목록     GET   /board/list?page=1&size=20&sort=DESC
 * 등록폼   GET   /board/write
 * 등록     POST  /board/create
 * 상세     GET   /board/detail?bid=1
 * 수정폼   GET   /board/edit?bid=1
 * 수정     POST  /board/update
 * 삭제     POST  /board/delete
 */

@RequiredArgsConstructor
@Controller
public class BoardController {

  private final BoardService boardService;
  
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleIllegalArgumentException(IllegalArgumentException e, Model model) {
    model.addAttribute("error", "잘못된 요청입니다. (" + e.getMessage() + ")");
    return "error/400";
  }
  
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleNoSuchElementException(NoSuchElementException e, Model model) {
    model.addAttribute("error", "요청하신 자원을 찾을 수 없습니다. (" + e.getMessage() + ")");
    return "error/404";
  }
  
  
  @GetMapping("/")
  public String home() {
    return "home";  //----- SpringResourceTemplateResolver에 의해서 prefix, suffix가
                    //----- prefix="/templates"
                    //----- suffix=".html"
  }
  
  @GetMapping("/board/list")
  public String list(Model model
                   , PageDTO dto
                   , @RequestParam(value = "sort", defaultValue = "DESC") String sort) {
    if (sort.isEmpty() || (!sort.equalsIgnoreCase("asc") && !sort.equalsIgnoreCase("desc"))) {
      sort = "DESC";
    }
    Map<String, Object> result = boardService.getBoardList(dto, sort);
    model.addAttribute("boardList", result.get("boardList"));
    model.addAttribute("pageDTO", result.get("pageDTO"));
    return "board/list";
  }
  
  @GetMapping("/board/detail")
  public String detail(@RequestParam(value = "bid", required = false) Long bid, Model model) {
    if (bid == null) {
      throw new IllegalArgumentException("게시글 ID 정보를 확인할 수 없습니다.");
    }
    if (bid <= 0) {
      throw new IllegalArgumentException("게시글 ID는 1 이상의 정수입니다.");
    }
    BoardDTO foundBoard = boardService.getBoardById(bid);
    if (foundBoard == null) {
      throw new NoSuchElementException("게시글 ID가 " + bid + "인 게시글이 존재하지 않습니다.");
    }
    model.addAttribute("board", boardService.getBoardById(bid));
    return "board/detail";
  }
  
  @PostMapping("/board/write")
  public String write(BoardDTO board, RedirectAttributes redirectAttr) {
    redirectAttr.addFlashAttribute("msg", boardService.createBoard(board) ? "등록 성공" : "등록 실패");
    return "redirect:/board/list";
  }
  
  @PostMapping("/board/update")
  public String update(BoardDTO board, RedirectAttributes redirectAttr) {
    if (boardService.updateBoard(board)) {
      redirectAttr.addFlashAttribute("msg", "수정 성공")
                  .addAttribute("bid", board.getBid());
      return "redirect:/board/detail";
    }
    redirectAttr.addFlashAttribute("msg", "수정 실패");
    return "redirect:/board/list";
  }
  
  @PostMapping("/board/delete")
  public String delete(Long bid, RedirectAttributes redirectAttr) {
    redirectAttr.addFlashAttribute("msg", boardService.deleteBoard(bid) ? "삭제 성공" : "삭제 실패");
    return "redirect:/board/list";
  }
  
}
