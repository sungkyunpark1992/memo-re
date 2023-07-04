package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController//클라이언트 요청에 대해서 json데이터만 주기 때문에 html을 따로 반환하지 않으므로 RestController를 쓴다.
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo > memoList = new HashMap<>();
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){//@RequestBody를 쓰는 이유; 데이터가 HTTP의 Body부분에서 json형태로 넘어 온다. 어떻게 받지?? = @RequestBody를 쓴다.
        //RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        //Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet())+1 : 1;
        memo.setId(maxId);

        //DB저장
        memoList.put(memo.getId(),memo);

        //Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        // Map To List
        List<MemoResponseDto> responseList = memoList.values().stream()
                .map(MemoResponseDto::new).toList();

        return responseList;
    }
}
