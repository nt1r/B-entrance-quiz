package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.dto.AddNewMemberRequestDto;
import com.thoughtworks.capability.gtb.entrancequiz.dto.AllMemberList;
import com.thoughtworks.capability.gtb.entrancequiz.dto.GroupMemberResponseDto;
import com.thoughtworks.capability.gtb.entrancequiz.service.GroupAssignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupAssignController {
    private final GroupAssignService groupAssignService;

    public GroupAssignController(GroupAssignService groupAssignService) {
        this.groupAssignService = groupAssignService;
    }

    @GetMapping("/group-api/init-list")
    public AllMemberList getAllMembers() {
        return groupAssignService.getAllMembers();
    }

    @PostMapping("/group-api/member")
    public AllMemberList addOneMember(@RequestBody AddNewMemberRequestDto newMemberRequestDto) {
        return groupAssignService.addOneMember(newMemberRequestDto);
    }

    @GetMapping("group-api/assign")
    public GroupMemberResponseDto assignGroup() {
        return groupAssignService.assignGroup();
    }
}
