package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.dto.AllMemberList;
import com.thoughtworks.capability.gtb.entrancequiz.service.GroupAssignService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
