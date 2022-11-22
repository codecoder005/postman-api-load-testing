package com.orgofarmsgroup.controller;

import com.orgofarmsgroup.dto.OrderDto;
import com.orgofarmsgroup.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private static List<OrderDto> orders = new ArrayList<>();
    static {
        orders.add(OrderDto.builder().oid(202211301000000L).build());
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<ResponseDto> getOrders(HttpServletRequest request) {
        try{
            ResponseDto responseDto = new ResponseDto(request, HttpStatus.OK, orders);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }catch (Exception ex) {
            LOGGER.error("UserController.search().Exception {}", ex.getMessage());
            return ResponseEntity.badRequest().body(new ResponseDto(request, HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong."));
        }
    }
}
