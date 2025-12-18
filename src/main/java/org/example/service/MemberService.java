package org.example.service;

import org.example.Member;
import org.example.dao.MemberDao;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.Connection;
import java.util.Map;

public class MemberService {

    private MemberDao memberDao;
    public MemberService() {
        this.memberDao = new MemberDao();
    }

    public boolean isLoginIdDup(Connection conn, String loginId) {
        return memberDao.isLoginIdDup(conn, loginId);
    }

    public int doJoin(Connection conn, String loginId, String loginPw, String name) {
        return memberDao.doJoin(conn, loginId, loginPw, name);
    }

    public Member getMemberByLoginId(Connection conn, String loginId) {
        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?;", loginId);

        Map<String, Object> memberMap = DBUtil.selectRow(conn, sql);

        if (memberMap.isEmpty()) {
            return null;
        }

        return new Member(memberMap);
    }
}