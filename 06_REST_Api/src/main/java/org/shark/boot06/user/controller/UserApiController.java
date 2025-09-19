package org.shark.boot06.user.controller;

import java.util.Map;

import org.shark.boot06.user.dto.UserDTO;
import org.shark.boot06.user.dto.response.ResponseUserDTO;
import org.shark.boot06.user.exception.ErrorResponseDTO;
import org.shark.boot06.user.exception.UserNotFoundException;
import org.shark.boot06.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/users")
@RequiredArgsConstructor
@RestController
public class UserApiController {

  private final UserService userService;
  
  // ExceptionHandler
  /*@ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponseDTO handleUserNotFoundException(UserNotFoundException e) {
    return ErrorResponseDTO.builder()
        .status(404)
        .errorMessage(e.getMessage())
        .build();
  }
  */
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(UserNotFoundException e) {
    ErrorResponseDTO dto = ErrorResponseDTO.builder()
        .errorCode("UE-100")
        .errorMessage(e.getMessage())
        .build();
    return ResponseEntity.status(404).body(dto);
  }
  
  /*
   * Postman 요청 시 주의사항
   * 
   * 1. x-www-form-urlencoded 형식으로 데이터를 보내는 경우 (폼을 사용하는 경우)
   *    create(UserDTO user)
   * 2. raw 형식 중 JSON 데이터를 보내는 경우
   *    create(@RequestBody UserDTO user)
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseUserDTO create(@RequestBody UserDTO user) {
    return ResponseUserDTO.builder()
        .status(201)  //----- 요청이 성공적을 처리되었으며, 새로운 자원이 생성되었음을 의미.
        .message("회원 등록이 성공했습니다.")
        .results(Map.of("createdUser", userService.createUser(user)))
        .build();
  }
  
}
