package com.penta.aptlibrary;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;
import com.penta.annotation.ApjLog;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Symbol;


/**
 * Created by linyueyang on 2018/11/14.
 */
@AutoService(Processor.class)
public class LogProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(ApjLog.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(ApjLog.class);
        if (null != elements && elements.size() > 0) {
            File file = new File("log.txt");
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                for (Element element : elements) {
                    Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) element;

                    Attribute.Compound compound = methodSymbol.getMetadata().getDeclarationAttributes().get(0).getValue();
                    if (null != compound) {
                        Map<Symbol.MethodSymbol, Attribute> map = compound.getElementValues();

                        for (Attribute value : map.values()) {
                            fw.write(value.getValue().toString());
                        }
                    }
                    fw.write(":");
                    fw.write(methodSymbol.owner.toString());
                    fw.write("#");
                    fw.write(element.toString());
                    fw.write(System.getProperty("line.separator"));
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
