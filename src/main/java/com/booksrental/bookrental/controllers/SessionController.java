package com.booksrental.bookrental.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Integer> count(HttpSession httpSession) {
        Integer count = (Integer) httpSession.getAttribute("count");
        if (count == null) {
            count = 1;
        } else {
            count++;
        }

        httpSession.setAttribute("count", count);
        return ResponseEntity.ok(count);
    }
}
