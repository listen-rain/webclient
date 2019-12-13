package com.example.webclient.demo.router;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDto {
    private Integer groupId;

    private String groupName;
}

/*
{
    "bigGroupId": 0,
    "fullPinYin": "string",
    "groupId": 0,
    "groupMotto": "string",
    "groupName": "string",
    "roomId": 0,
    "simplePinYin": "string",
    "teamFlag": 0,
    "token": "string"
  }
*/
