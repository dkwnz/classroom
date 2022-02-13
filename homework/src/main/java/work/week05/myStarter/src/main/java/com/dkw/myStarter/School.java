package com.dkw.myStarter;

import java.util.List;

public class School {
    private List<Klass> myClasses;

    public School(List<Klass> myClasses) {
        this.myClasses = myClasses;
    }

    @Override
    public String toString() {
        return "MyClass::" + myClasses.toString();
    }
}
