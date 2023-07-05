package com.sparta.memo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {//DTO (Data Transfer Object) 데이터를 이동할때 사용하는 객체
    //(Request)받아올 때 사용되는 객체 RequestDto
    private String username;
    private String contents;
}