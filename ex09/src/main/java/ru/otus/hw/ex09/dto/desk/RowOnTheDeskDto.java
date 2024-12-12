package ru.otus.hw.ex09.dto.desk;

import lombok.Data;

// в примере в таблицу можно передавать по именованным колонкам, так их и оставил, на первый раз, но это не удобно
@Data
public class RowOnTheDeskDto {
    private String leftClm;
    private String rightClm;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
}
