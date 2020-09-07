package com.thoughtworks.capability.gtb.entrancequiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupTeamDto {
    String name;
    List<TeamMemberDto> memberList;
}
