package org.jugistanbul.secondopinion.api.entity;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class NicknameCacheTest {

    @After
    public void resetCache()
    {
        NicknameCache.INSTANCE.clear();
    }

    @Test
    public void should_save_filledlist_when_parameter_is_null()
    {
        List<Nickname> nicknames = new ArrayList<>();
        nicknames.add(new Nickname("taylan"));
        nicknames.add(new Nickname("michael"));
        nicknames.add(new Nickname("jetty"));
        nicknames.add(new Nickname("gokalp"));
        nicknames.add(new Nickname("hudson"));

        NicknameCache.INSTANCE.addAll(nicknames);

        NicknameCache.INSTANCE.addAll(null);

        assertThat(NicknameCache.INSTANCE.size()).isEqualTo(5);
    }

    @Test
    public void should_save_emptylist_when_parameter_is_null()
    {
        List<Nickname> nicknames = new ArrayList<>();
        nicknames.add(new Nickname("taylan"));
        nicknames.add(new Nickname("michael"));
        nicknames.add(new Nickname("jetty"));
        nicknames.add(new Nickname("gokalp"));
        nicknames.add(new Nickname("hudson"));

        NicknameCache.INSTANCE.addAll(null);

        assertThat(NicknameCache.INSTANCE.size()).isEqualTo(0);
    }

    @Test
    public void should_return_null_when_list_is_empty()
    {
        assertThat(NicknameCache.INSTANCE.getRandomOne()).isNull();
    }

    @Test
    public void should_return_need_reload_when_list_is_empty()
    {
        assertThat(NicknameCache.INSTANCE.isNeedReload()).isTrue();
    }

    @Test
    public void should_return_not_need_reload_when_list_isnot_empty()
    {
        List<Nickname> nicknames = new ArrayList<>();
        nicknames.add(new Nickname("taylan"));
        nicknames.add(new Nickname("michael"));
        nicknames.add(new Nickname("jetty"));
        nicknames.add(new Nickname("gokalp"));
        nicknames.add(new Nickname("hudson"));

        NicknameCache.INSTANCE.addAll(nicknames);
        assertThat(NicknameCache.INSTANCE.isNeedReload()).isFalse();
    }

    @Test
    public void should_return_size()
    {
        List<Nickname> nicknames = new ArrayList<>();
        nicknames.add(new Nickname("taylan"));
        nicknames.add(new Nickname("michael"));
        nicknames.add(new Nickname("jetty"));
        nicknames.add(new Nickname("gokalp"));
        nicknames.add(new Nickname("hudson"));

        NicknameCache.INSTANCE.addAll(nicknames);
        assertThat(NicknameCache.INSTANCE.size()).isEqualTo(5);
    }

    @Test
    public void should_clear_list()
    {
        List<Nickname> nicknames = new ArrayList<>();
        nicknames.add(new Nickname("taylan"));
        nicknames.add(new Nickname("michael"));
        nicknames.add(new Nickname("jetty"));
        nicknames.add(new Nickname("gokalp"));
        nicknames.add(new Nickname("hudson"));

        NicknameCache.INSTANCE.addAll(nicknames);
        assertThat(NicknameCache.INSTANCE.size()).isEqualTo(5);
        NicknameCache.INSTANCE.clear();
        assertThat(NicknameCache.INSTANCE.size()).isEqualTo(0);
    }

    @Test
    public void should_return_random_one()
    {
        List<Nickname> nicknames = new ArrayList<>();
        nicknames.add(new Nickname("taylan"));
        nicknames.add(new Nickname("michael"));
        nicknames.add(new Nickname("jetty"));
        nicknames.add(new Nickname("gokalp"));
        nicknames.add(new Nickname("hudson"));

        NicknameCache.INSTANCE.addAll(nicknames);
        assertThat(NicknameCache.INSTANCE.getRandomOne()).isIn(nicknames);
    }
}
