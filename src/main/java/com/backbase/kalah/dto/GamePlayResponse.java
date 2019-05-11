package com.backbase.kalah.dto;

import lombok.Data;

import java.util.Map;

@Data
public class GamePlayResponse {
    int id;
    String uri;
    Map<Integer,String> status;
}
