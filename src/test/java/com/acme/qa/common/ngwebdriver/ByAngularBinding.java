/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.ngwebdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class ByAngularBinding extends By {

    private final JavascriptExecutor jse;

    private String binding;

    public ByAngularBinding(JavascriptExecutor jse, String binding) {
        this.jse = jse;
        this.binding = binding;
    }

    private String makeJsBy(String oneOrAll) {
        return
                "var using = arguments[0] || document;\n" +
                        "var binding = '" + binding + "';\n" +
                        "var bindings = using.getElementsByClassName('ng-binding');\n" +
                        "var matches = [];\n" +
                        "for (var i = 0; i < bindings.length; ++i) {\n" +
                        "  var bindingName = angular.element(bindings[i]).data().$binding[0].exp ||\n" +
                        "      angular.element(bindings[i]).data().$binding;\n" +
                        "  if (bindingName.indexOf(binding) != -1) {\n" +
                        "    matches.push(bindings[i]);\n" +
                        "  }\n" +
                        "}\n" +
                        "return matches" + oneOrAll + ";";
    }

    @Override
    public WebElement findElement(SearchContext context) {
        if (context instanceof WebDriver) {
            context = null;
        }
        return (WebElement) jse.executeScript(makeJsBy("[0]"), context);
    }

    @Override
    public List<WebElement> findElements(SearchContext searchContext) {
        if (searchContext instanceof WebDriver) {
            searchContext = null;
        }
        return (List<WebElement>) jse.executeScript(makeJsBy(""), searchContext);
    }

}
