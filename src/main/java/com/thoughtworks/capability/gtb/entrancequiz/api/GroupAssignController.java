package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.dto.AddNewMemberRequestDto;
import com.thoughtworks.capability.gtb.entrancequiz.dto.AllMemberList;
import com.thoughtworks.capability.gtb.entrancequiz.dto.GroupMemberResponseDto;
import com.thoughtworks.capability.gtb.entrancequiz.dto.RenameTeamRequestDto;
import com.thoughtworks.capability.gtb.entrancequiz.service.GroupAssignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupAssignController {
    private final GroupAssignService groupAssignService;

    public GroupAssignController(GroupAssignService groupAssignService) {
        this.groupAssignService = groupAssignService;
    }

    @GetMapping("/group-api/init-list")
    @CrossOrigin
    public AllMemberList getAllMembers() {
        return groupAssignService.getAllMembers();
    }

    @PostMapping("/group-api/member")
    @CrossOrigin
    public AllMemberList addOneMember(@RequestBody AddNewMemberRequestDto newMemberRequestDto) {
        return groupAssignService.addOneMember(newMemberRequestDto);
    }

    @GetMapping("/group-api/assign")
    @CrossOrigin
    public GroupMemberResponseDto assignGroup() {
        return groupAssignService.assignGroup();
    }

    @GetMapping("/group-api/cached-assign")
    @CrossOrigin
    public ResponseEntity<GroupMemberResponseDto> getCacheAssignGroup() {
        return groupAssignService.getCachedAssignGroup();
    }

    @PostMapping("/group-api/rename-team")
    @CrossOrigin
    public ResponseEntity<String> renameTeam(@RequestBody RenameTeamRequestDto renameTeamRequestDto) {
        return groupAssignService.renameTeam(renameTeamRequestDto);
    }
}
