package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory;
import lombok.Getter;

import javax.persistence.*;
import java.util.*;

@Entity // 객체와 테이블 매핑
public class User {

    @Id // 프라이머리 키 설정 test
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    private Long id = null; // bigint = long

    @Column(nullable = false, length = 25, name = "name") // 널x, 길이제한, 매핑될 컬럼명 지정
    private String name;

    // 그외 제약조건 없으면 @Column 생략가능
    private Integer age;

    // 유저 하나에 대출기록 여러개니까 one to many
    // 테이블을 봤을 때 누가 주도권을 가지고 있는지 설정 누가 상대방을 보고 있는가 외래키 누가 쥐고 있나? 그런 느낌
    // 연관관계 주인 아닌 쪽에 mappedBy옵션 달아줘야함 주인이 가지고 있는 필드를 넣어주면 연결이 됨\
    // 유저삭제될 때 폭포처럼 흘러서 대출 레코드까지 삭제됨
    // 객체와 관계 끊어진 데이터 자동 삭제 orphanRemoval
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistoryList = new ArrayList<>();

    protected User() {
    }

    public User(String name, Integer age) {
        // 이름은 널이 되면 안 되니까 널 체크
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name (%s)이 들어왔습니다.", name)); // 변수를 통해 동적으로 문자열 생성
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }

    // 유저에서 직접 userLoanHistory를 가지고 있은까 서비스단이 아니라 직접 유저에서 추가가 가능해짐
    public void loanBook(String bookName) {
        this.userLoanHistoryList.add(new UserLoanHistory(this, bookName));
    }

    // 유저에서 직접 반납처리
    public void returnBook(String bookName) {

        // 리스토리에서 찾고 filter로 충족한 것만 남긴다  돌린다음 파라미터랑 이름 같은 책을 찾으면
        // findFirst로 가장 처음 꺼를 리턴하고 해당 리턴값은 optional이라 혹시 몰라 예외도 넣어줌
        UserLoanHistory targetHistory = this.userLoanHistoryList.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        targetHistory.doReturn();
    }

}
