package com.turkcell.libraryappddd.domain.model.publisher;

import com.turkcell.libraryappddd.domain.model.DomainId;

public class Publisher {

    private final DomainId<Publisher> id;
    private String name;

    private Publisher(DomainId<Publisher> id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Publisher create(String name)
    {
        validateName(name);
        return new Publisher(DomainId.generate(),name);
    }

    public static Publisher rehydrate(DomainId<Publisher>id, String name)
    {
    return new Publisher(id, name);
    }

    private static void validateName(String name){
        if(name ==null || name.isEmpty())
        {
            throw new IllegalArgumentException("Publisher name cannot be empty or null");
        }
        if(name.length() > 255){
            throw new IllegalArgumentException("Publisher name too long");
        }
    }
    public void rename(String name){
        validateName(name);
        this.name = name;
    }

    public DomainId<Publisher> getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

