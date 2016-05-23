package com.wojciechmaciejewski.githubapirequester.model.dto;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
public abstract class AskElementAbstract implements AskElement {


    public static final int USER_TYPE = 0;
    public static final int REPO_TYPE = 1;


    @IntDef({USER_TYPE, REPO_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ElementType {
    }

    @ElementType
    int elementType;

    @ElementType
    public int getElementType(){
        return elementType;
    }


}
