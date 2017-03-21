package com.ld.web.enumeration;

/**
 * 
 *<p>Title: CloneStatus</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-03-18
 */
public enum CloneStatus {

    NOT_INIT(0), CLONE_ING(1), CLONE_SUCC(2), CLONE_ERR(3);

    private int value = 0;

    public int value() {
        return this.value;
    }

    public static CloneStatus get(int value) {

        for (CloneStatus i : CloneStatus.values()) {
            if (value == i.value) {
                return i;
            }
        }
        return NOT_INIT;
    }

    private CloneStatus(int value) {
        this.value = value;
    }

}
