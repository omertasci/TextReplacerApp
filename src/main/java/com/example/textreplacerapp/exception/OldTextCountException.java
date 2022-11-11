package com.example.textreplacerapp.exception;

public class OldTextCountException extends Exception
{
    int count;

    public OldTextCountException(String str)
    {
        super(str);
    }
    public OldTextCountException(String str, int count)
    {
        super(str);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
