package org.example;

import org.example.exception.SQLErrorException;

public class Main {
    public static void main(String[] args) {

        /// 프로그램 실행 시 App 클래스의 run 메서드 실행
        try {
            new App().run();
        } catch (SQLErrorException e) {
            System.out.println(e.getOrigin()); // 일반 출력
            System.err.println(e.getOrigin()); // 빨간글씨로 출력
            e.getOrigin().printStackTrace(); // 오류 경로 추적
        }
    }
}