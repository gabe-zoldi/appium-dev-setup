/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.ngwebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class ByAngular {

    protected final JavascriptExecutor jse;

    public ByAngular(JavascriptExecutor jse) {
        this.jse = jse;
    }

    public ByAngularRepeater repeater(String repeater) {
        return new ByAngularRepeater(jse, repeater);
    }

    public ByAngularBinding binding(String binding) {
        return new ByAngularBinding(jse, binding);
    }

    public abstract static class BaseBy extends By {

        protected final JavascriptExecutor jse;

        public BaseBy(JavascriptExecutor jse) {
            this.jse = jse;
        }

        protected final void errorIfNull(Object o) {
            if (o == null || o instanceof List && ((List) o).size() == 0) {
                throw new NoSuchElementException(this + " didn't have any matching elements at this place in the DOM");
            }
        }

    }
}