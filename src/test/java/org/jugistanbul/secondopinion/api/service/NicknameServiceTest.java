package org.jugistanbul.secondopinion.api.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.jugistanbul.secondopinion.api.config.BaseMockitoTest;
import org.jugistanbul.secondopinion.api.entity.Nickname;
import org.jugistanbul.secondopinion.api.entity.NicknameCache;
import org.jugistanbul.secondopinion.api.repository.NicknameRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NicknameServiceTest extends BaseMockitoTest{

    @Mock
    NicknameRepository nicknameRepository;
    @InjectMocks
    PatientService patientService;

    List<Nickname> nicknames;

    @Before
    public void fillNicknames()
    {
        nicknames = new ArrayList<>();
    }

    @After
    public void resetCache()
    {
        NicknameCache.INSTANCE.clear();
    }


    @Test
    public void should_select_nickname_randomly()
    {
        //Given
        nicknames.add(new Nickname("taylan"));
        nicknames.add(new Nickname("michael"));
        nicknames.add(new Nickname("jetty"));
        nicknames.add(new Nickname("gokalp"));
        nicknames.add(new Nickname("hudson"));

        //When
        when(nicknameRepository.findAll()).thenReturn(nicknames);
        NicknameService nicknameService = new NicknameService(nicknameRepository);

        //Then
        ResponseEntity<Nickname> responseEntity = nicknameService.suggest();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isIn(nicknames);
    }

    @Test
    public void should_return_nocontent_when_listisempty()
    {
        //Given

        //When
        when(nicknameRepository.findAll()).thenReturn(nicknames);
        NicknameService nicknameService = new NicknameService(nicknameRepository);

        //Then
        ResponseEntity<Nickname> responseEntity = nicknameService.suggest();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    public void should_return_nocontent_when_listisnull()
    {
        //Given

        //When
        when(nicknameRepository.findAll()).thenReturn(null);
        NicknameService nicknameService = new NicknameService(nicknameRepository);

        //Then
        ResponseEntity<Nickname> responseEntity = nicknameService.suggest();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getBody()).isNull();
    }
}
