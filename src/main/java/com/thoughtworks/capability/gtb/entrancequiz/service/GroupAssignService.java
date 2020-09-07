package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GroupAssignService {
    public static List<TeamMemberDto> memberList = new ArrayList<>();
    public static List<String> teamNameList = new ArrayList<>();

    private Integer autoIncreaseId = 1;
    private final Integer GROUP_NUMBER = 6;
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
        initTeamName();
    }

    private void initTeamName() {
        for (int i = 0; i < GROUP_NUMBER; ++i) {
            teamNameList.add("Team " + (i + 1));
        }
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

    public GroupMemberResponseDto assignGroup() {
        List<TeamMemberDto> randomMemberList = generateRandomMemberList(memberList);
        List<Integer> memberCountList = generateMemberCountList(memberList.size(), GROUP_NUMBER);
        return assignGroupWithCountList(randomMemberList, memberCountList);
    }

    private GroupMemberResponseDto assignGroupWithCountList(List<TeamMemberDto> randomMemberList, List<Integer> memberCountList) {
        /* set 'teamList' */
        List<GroupTeamDto> groupTeamDtoList = new ArrayList<>();
        int lastAssignedMemberIndex = 0;
        for (int teamIndex = 0; teamIndex < memberCountList.size(); ++teamIndex) {
            /* assign members */
            int assignCount = memberCountList.get(teamIndex);
            List<TeamMemberDto> memberList = new ArrayList<>();
            for (int memberIndex = 0; memberIndex < assignCount; ++memberIndex) {
                memberList.add(randomMemberList.get(memberIndex + lastAssignedMemberIndex));
            }
            lastAssignedMemberIndex += assignCount;
            /* assign members */

            GroupTeamDto groupTeamDto = GroupTeamDto.builder()
                    .name(teamNameList.get(teamIndex))
                    .memberList(memberList)
                    .build();
            groupTeamDtoList.add(groupTeamDto);
        }
        /* set 'teamList' */

        return GroupMemberResponseDto.builder()
                .teamList(groupTeamDtoList)
                .build();
    }

    private List<Integer> generateMemberCountList(int size, Integer groupNumber) {
        List<Integer> countList = new ArrayList<>();

        int average = size / groupNumber;
        for (int i = 0; i < groupNumber; ++i) {
            countList.add(average);
        }

        int assignedCount = average * groupNumber;
        int teamIndex = 0;
        for (int count = assignedCount; count < size; ++count) {
            countList.set(teamIndex++, average + 1);
        }

        return countList;
    }

    private List<TeamMemberDto> generateRandomMemberList(List<TeamMemberDto> memberList) {
        // deep copy first
        List<TeamMemberDto> randomList = new ArrayList<>(memberList);

        int generatedCount = 0;
        Random random = new Random();

        while (generatedCount < randomList.size()) {
            // get one random number from 0 - size of 'memberList'
            int randomIndex = random.nextInt(randomList.size() - generatedCount);

            // set the last member with random index by exchanging
            exchangeWithTheLast(randomList, randomIndex);
            generatedCount++;
        }
        return randomList;
    }

    private void exchangeWithTheLast(List<TeamMemberDto> randomList, int randomIndex) {
        int lastIndex = randomList.size() - 1;
        TeamMemberDto cache = randomList.get(randomIndex);
        randomList.set(randomIndex, randomList.get(lastIndex));
        randomList.set(lastIndex, cache);
    }
}
