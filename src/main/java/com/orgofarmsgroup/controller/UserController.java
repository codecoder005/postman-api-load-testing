package com.orgofarmsgroup.controller;

import com.orgofarmsgroup.dto.ResponseDto;
import com.orgofarmsgroup.dto.UserDto;
import com.orgofarmsgroup.filter.SearchFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<ResponseDto> get(HttpServletRequest request, HttpServletResponse httpResponse) {
        LOGGER.info("UserController.getUsers()");
        try{
            UserDto user = new UserDto(101L, "John", "john@email.com");
            ResponseDto responseDto = new ResponseDto(request, HttpStatus.OK, user);
            Cookie expirationDateCookie = new Cookie("EXP_DATE", "2022-12-31");
            httpResponse.addCookie(expirationDateCookie);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }catch (Exception ex){
            LOGGER.error("UserController.getUsers().Exception {}", ex.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDto(request, HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong."));
        }
    }

    @GetMapping(path = "/search", produces = {"application/json"})
    public ResponseEntity<ResponseDto> search(@Valid SearchFilters filters, HttpServletRequest request) {
        try{
            ResponseDto responseDto = new ResponseDto(request, HttpStatus.OK, filters);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }catch (Exception ex) {
            LOGGER.error("UserController.search().Exception {}", ex.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDto(request, HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong."));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDto> secureApi(HttpServletRequest request) {
        try{
            String authorizationHeader = request.getHeader("Authorization");
            System.out.println(request.getHeader("Authorization"));
            System.out.println(request.getHeader("api_key"));
            ResponseDto responseDto = new ResponseDto(request, HttpStatus.OK, "");
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }catch (Exception ex) {
            LOGGER.error("UserController.search().Exception {}", ex.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDto(request, HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong."));
        }
    }
}
