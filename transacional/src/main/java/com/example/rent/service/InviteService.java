package com.example.rent.service;

import com.example.rent.dto.InvitePendingResponseDto;
import com.example.rent.dto.InviteRespondRequestDto;
import com.example.rent.dto.InviteRespondResponseDto;

public interface InviteService {
    InviteRespondResponseDto respondToInvite(Long inviteId, InviteRespondRequestDto request);

    java.util.List<InvitePendingResponseDto> listPendingInvites();
}
