package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Nickname;
import org.jugistanbul.secondopinion.api.service.NicknameService;
import org.jugistanbul.secondopinion.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/v1/nicknames")
public class NicknameController {

    private NicknameService nicknameService;

    public NicknameController(NicknameService nicknameService) {
        this.nicknameService = nicknameService;
    }

    @GetMapping
    public ResponseEntity<Nickname> suggest()
    {
        return nicknameService.suggest();
    }
}
