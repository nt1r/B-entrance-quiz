package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.dto.AllMemberList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupAssignServiceTest {
    @Autowired
    private GroupAssignService groupAssignService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void shouldInitMemberListSuccess() {
        // Given

        // When
        AllMemberList allMemberListList = groupAssignService.getAllMembers();

        // Then
        assertEquals("沈乐棋", allMemberListList.getMemberList().get(0).getName());
        assertEquals("王登宇", allMemberListList.getMemberList().get(4).getName());
        assertEquals("廖燊", allMemberListList.getMemberList().get(7).getName());
        assertEquals("肖美琦", allMemberListList.getMemberList().get(11).getName());
        assertEquals("齐瑾浩", allMemberListList.getMemberList().get(13).getName());
        assertEquals("党泽", allMemberListList.getMemberList().get(19).getName());
        assertEquals("马庆", allMemberListList.getMemberList().get(23).getName());
        assertEquals("赵允齐", allMemberListList.getMemberList().get(29).getName());
    }
}