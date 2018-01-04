package ru.nw.vir;

import java.util.Date;

public class Greeting {

    private final long id;
	private final String name;
    private final String subject;

    public Greeting(long id, String name, String subject) {
        this.id = id;
		this.name = name;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public String getsubject() {
        return subject;
    }
	
	public String getname() {
        return name;
    }
}