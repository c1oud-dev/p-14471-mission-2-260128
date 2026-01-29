package org.example;

/**
 * 작가명(author)와 명언내용(content)에는 특수문자를 입력되지 않는다.
 * 최대한 라이브러리를 사용하지 않고 구현
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<WiseSaying> list = new ArrayList<>();
    static int findIndexById(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lastId = 0;

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = br.readLine();

            if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = br.readLine();

                System.out.print("작가 : ");
                String author = br.readLine();

                lastId++;
                list.add(new WiseSaying(lastId, content, author));
                System.out.println(lastId + "번 명언이 등록되었습니다.");

            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = list.size() - 1; i >= 0; i--) {
                    System.out.println(list.get(i));
                }
            } else if (cmd.startsWith("삭제")){
                int id = Integer.parseInt(cmd.replace("삭제?id=", ""));
                int idx = findIndexById(id);

                if (idx == -1) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                } else {
                    list.remove(idx);
                    System.out.println(id + "번 명언이 삭제되었습니다.");
                }

            } else if (cmd.startsWith("수정")) {
                int id = Integer.parseInt(cmd.replace("수정?id=", ""));
                int idx = findIndexById(id);

                if (idx == -1) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                } else {
                    WiseSaying ws = list.get(idx);

                    System.out.println("명언(기존) : " + ws.content);
                    System.out.print("명언 : ");
                    String newContent = br.readLine();

                    System.out.println("작가(기존) : " + ws.author);
                    System.out.print("작가 : ");
                    String newAuthor = br.readLine();

                    // 수정
                    ws.content = newContent;
                    ws.author = newAuthor;

                }

            } else if (cmd.equals("종료")) {
                break;
            } else {
                System.out.println("다시 입력해 주세요.");
            }
        }
    }
}

class WiseSaying {
    int id;
    String content;
    String author;

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + content;
    }
}
