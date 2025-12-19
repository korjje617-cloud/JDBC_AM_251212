package org.example.session;

import org.example.dto.Member;

public class Session {
    public Member loginedMember;
    public int loginedMemberId;

    public Session() {
        loginedMember = null;
        loginedMemberId = -1;
    }

    public void login(Member member) {
        loginedMember = member;
        loginedMemberId = member.getId();
    }

    public void logout() {
        loginedMember = null;
        loginedMemberId = -1;
    }

    public boolean isLogined() {
        return loginedMember != null;
    }
}