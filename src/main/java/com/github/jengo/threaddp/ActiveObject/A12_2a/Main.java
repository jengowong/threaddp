package com.github.jengo.threaddp.ActiveObject.A12_2a;

import com.github.jengo.threaddp.ActiveObject.A12_2a.activeobject.ActiveObject;
import com.github.jengo.threaddp.ActiveObject.A12_2a.activeobject.ActiveObjectFactory;

public class Main {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new AddClientThread("Diana", activeObject).start();
    }

}
