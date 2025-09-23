package org.shark.boot09.board.controller;

import java.time.LocalDateTime;

import org.shark.boot09.board.dto.BoardDTO;
import org.shark.boot09.board.service.BoardRedisService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/boards")
@RequiredArgsConstructor
@RestController
public class BoardApiRedisController {

  private final BoardRedisService boardRedisService;
  
  @PostMapping
  public String saveBoard(@RequestBody BoardDTO boardDTO) {
    boardDTO.setCreateAt(LocalDateTime.now().toString());
    boardRedisService.save(boardDTO);
    return "Board Saved Complete!";
  }
  
  @GetMapping("/{bid}")
  public BoardDTO getBoard(@PathVariable(value = "bid") Long bid) {
    return boardRedisService.findById(bid);
  }
  
  @DeleteMapping("{bid}")
  public String deleteBoard(@PathVariable(name = "bid") Long bid) {
    boardRedisService.deleteById(bid);
    return "Board Deleted~";
  }
  
}
