package com.bangbang.exception;

public class MemberException extends Exception {

    public MemberException(String message) {
        super(message);
    }

    public static final MemberException MEMBER_ACCOUNT_DUPLICATED = new MemberException("이미 가입된 아이디입니다.");
    public static final MemberException MEMBER_EMAIL_DUPLICATED = new MemberException("이미 가입된 이메일입니다.");
    public static final MemberException MEMBER_NICKNAME_DUPLICATED = new MemberException("이미 가입된 닉네임입니다.");
    public static final MemberException MEMBER_ACCOUNT_NOT_FOUND = new MemberException("일치하는 아이디가 없습니다.");
    public static final MemberException MEMBER_PASSWORD_NOT_CORRECT = new MemberException("비밀번호가 일치하지 않습니다.");
    public static final MemberException MEMBER_ID_NOT_FOUND = new MemberException("존재하지 않는 유저 id입니다.");
    public static final MemberException MEMBER_ALREADY_HAS_COURSE = new MemberException("이미 추가된 코스입니다.");
    public static final MemberException MEMBER_NOT_HAS_COURSE = new MemberException("저장하지 않은 코스입니다.");
}
