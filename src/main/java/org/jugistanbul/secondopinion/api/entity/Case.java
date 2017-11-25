package org.jugistanbul.secondopinion.api.entity;

import java.io.Serializable;


public class Case implements Serializable{
    private String note;

    public void setNote(String text)
    {

        note = text;
    }

    public String getNote()
    {
        return note;
    }
}
