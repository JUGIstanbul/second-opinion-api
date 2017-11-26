package org.jugistanbul.secondopinion.api.entity;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public enum NicknameCache {

    INSTANCE;

    private CopyOnWriteArrayList<Nickname> nicknames = new CopyOnWriteArrayList();

    public void addAll(List<Nickname> nicknameList)
    {
        if(nicknameList!=null)
        {
            nicknames.addAll(nicknameList);
        }
    }

    public Nickname getRandomOne()
    {
        if(nicknames.isEmpty())
        {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(nicknames.size());

        return nicknames.get(randomIndex);
    }

    public boolean isReloadNeeded() {
        return nicknames.isEmpty();
    }

    public int size() {
        return nicknames.size();
    }

    public void clear() {
        nicknames.clear();
    }
}
