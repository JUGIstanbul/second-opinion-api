package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.entity.Nickname;
import org.jugistanbul.secondopinion.api.entity.NicknameCache;
import org.jugistanbul.secondopinion.api.repository.NicknameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NicknameService {

    private NicknameRepository repository;

    public NicknameService(NicknameRepository repository)
    {
        this.repository = repository;
    }

    public ResponseEntity<Nickname> suggest()
    {

        if(NicknameCache.INSTANCE.isReloadNeeded())
        {
            List<Nickname> nicknames = repository.findAll();
            NicknameCache.INSTANCE.addAll(nicknames);
        }

        Nickname randomlySelectdNickname = NicknameCache.INSTANCE.getRandomOne();

        if(randomlySelectdNickname == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(randomlySelectdNickname, HttpStatus.OK);
    }
}
