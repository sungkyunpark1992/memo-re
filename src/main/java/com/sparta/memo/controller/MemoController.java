package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.service.MemoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemoController {
//데이터를 전달해오는 방식이 바뀌면 Controller를 수정한다. 즉, Controller는 데이터를 주고 받는 부분을 담당한다.
    private final JdbcTemplate jdbcTemplate;

    public MemoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {//응답하는 dto = MemoResponseDto ; 받아오는 dto = MemoRequestDto
//반환타입이 MemoResponseDto인 createMemo메소드를 만든다. 데이터가 HTTP바디에서 json형태로 넘어올건데 어떻게 받냐?@RequestBody로 받는다. 클라이언트에게서 전달받은 MemoRequestDto타입의 requestDto 데이터(변수)
        MemoService memoService = new MemoService(jdbcTemplate);//MemoService 인스턴스화 할때 jdbcTemplate넣어줘야 하는 이유;MemoService클래스의 생성자를 보면 알 수 있다.
        return memoService.createMemo(requestDto);
        //MemoService클래스의 createMemo라는 메소드에 requestDto를 인자로 실행해서 반환한다.
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {//MemoResponseDto가 메모 하나인대 메모가 한개가 아닌 여러 메모를 가지므로 List형식으로 반환해야 한다.
//반환타입이 List<MemoResponseDto>인 getMemos메소드를 만든다.
        MemoService memoService = new MemoService(jdbcTemplate);
        return memoService.getMemos();//MemoService클래스에서 getMemos라는 메소드를 실행시켜 반환한다.
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        MemoService memoService = new MemoService(jdbcTemplate);
        return memoService.updateMemo(id, requestDto);
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        MemoService memoService = new MemoService(jdbcTemplate);
        return memoService.deleteMemo(id);
    }
}