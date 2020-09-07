package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.dto.AddNewMemberRequestDto;
import com.thoughtworks.capability.gtb.entrancequiz.dto.AllMemberList;
import com.thoughtworks.capability.gtb.entrancequiz.dto.TeamMemberDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GroupAssignService {
    public static List<TeamMemberDto> memberList = new ArrayList<>();
    private Integer autoIncreaseId = 1;
    private List<String> initMemberNameList = Arrays.asList(
            "沈乐棋",
            "徐慧慧",
            "陈思聪",
            "王江林",
            "王登宇",
            "杨思雨",
            "江雨舟",
            "廖燊",
            "胡晓",
            "但杰",
            "盖迈达",
            "肖美琦",
            "黄云洁",
            "齐瑾浩",
            "刘亮亮",
            "肖逸凡",
            "王作文",
            "郭瑞凌",
            "李明豪",
            "党泽",
            "肖伊佐",
            "贠晨曦",
            "李康宁",
            "马庆",
            "商婕",
            "余榕",
            "谌哲",
            "董翔锐",
            "陈泰宇",
            "赵允齐",
            "张柯",
            "廖文强",
            "刘轲",
            "廖浚斌",
            "凌凤仪"
    );

    public GroupAssignService() {
        initMemberList();
    }

    private void initMemberList() {
        memberList.clear();
        for (String memberName: initMemberNameList) {
            memberList.add(generateNewMember(memberName));
        }
    }

    private TeamMemberDto generateNewMember(String name) {
        return TeamMemberDto.builder()
                .id(autoIncreaseId++)
                .name(name)
                .build();
    }

    public AllMemberList getAllMembers() {
        return AllMemberList.builder()
                .memberList(memberList)
                .build();
    }

    public AllMemberList addOneMember(AddNewMemberRequestDto newMemberRequestDto) {
        if (isAddNewMemberRequestDtoValid(newMemberRequestDto)) {
            memberList.add(generateNewMember(newMemberRequestDto.getName()));
        }
        return getAllMembers();
    }

    private boolean isAddNewMemberRequestDtoValid(AddNewMemberRequestDto newMemberRequestDto) {
        return !newMemberRequestDto.getName().equals("");
    }
}
