package org.shark.boot05.board.service;

import java.util.List;

import org.shark.boot05.board.dto.BoardDTO;

public interface BoardService {
  List<BoardDTO> getBoardList();
  BoardDTO getBoardById(Long bid);
  Boolean createBoard(BoardDTO board);
  Boolean updateBoard(BoardDTO board);
  Boolean deleteBoard(Long bid);
}
