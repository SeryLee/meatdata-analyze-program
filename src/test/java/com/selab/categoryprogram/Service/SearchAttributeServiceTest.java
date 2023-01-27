package com.selab.categoryprogram.Service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class SearchAttributeServiceTest {
    @Test
    void getAttributeNameTest() {
        List<String> attributeName = new ArrayList<>();
        List<Class<?>> voList = getVOList();
        for (Class<?> aClass : voList) {
            Field[] fields = aClass.getDeclaredFields();
            sortAttributeName(attributeName, fields);
        }
        attributeName = attributeName.stream().distinct().collect(Collectors.toList());
        for (String s : attributeName) {
            log.info(s);
        }
    }

    public static List<Class<?>> getVOList() {
        List<Class<?>> voList = new ArrayList<>();
        StringBuilder packagePath = new StringBuilder("./com/selab/categoryprogram/MongoDBSchema");
        URL packageURL = Thread.currentThread().getContextClassLoader().getResource(packagePath.toString());
        File file = new File(packageURL.getFile());
        if(file.exists()) {
            String[] files = file.list();
            for (String tempName : files) {
                if(tempName.contains("class") && !tempName.contains("Dto")) {
                    tempName = tempName.substring(0, tempName.length() - 6);
                    try{
                        Class c = Class.forName("com.selab.categoryprogram.MongoDBSchema." + tempName);
                        voList.add(c);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (!tempName.contains("class") && !tempName.contains("Dto")) {
                    packagePath.append("/").append(tempName);
                    URL url = Thread.currentThread().getContextClassLoader().getResource(packagePath.toString());
                    File newFile = new File(url.getFile());
                    if(newFile.exists()) {
                        String[] newFiles = newFile.list();
                        for (String tempName2 : newFiles) {
                            tempName2 = tempName2.substring(0, tempName2.length() - 6);
                            try {
                                Class c = Class.forName("com.selab.categoryprogram.MongoDBSchema." + tempName + "." + tempName2);
                                voList.add(c);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    packagePath = new StringBuilder("./com/selab/categoryprogram/MongoDBSchema");
                }
            }
        }

        return voList;
    }

    private static void sortAttributeName(List<String> attributeName, Field[] fields) {
        for (Field field : fields) {
            String name = field.getName();
            if (!name.contains("VO")) {
                attributeName.add(name);
            }
        }
    }
}